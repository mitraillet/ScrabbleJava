/**
 * 
 */
package scrabble;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.*;

/**
 * @author Fauconnier/Henriquet
 * Classe de gestion du plateau. 
 * Contient la vérification des mots et le calcul des scores
 */
public class Plateau {

	/**
	* dictionnaire Contient tous les mots de la langues française 
	*/
	public HashSet<String> dictionnaire = new HashSet<String>();
	
	/**
	* Tableau à double entrée représentant le plateau
	*/
	public Case[][] plateau;
	
	/**
	 * True si début de la partie, sinon false;
	 */
	public boolean debutPartie;
	
	public List<Lettre> motJoue = new ArrayList<Lettre>();
	
	/**
	* Contructeur par défaut du sac
	* @throws XPathExpressionException gestion des erreurs
	*/
	public Plateau() {
		this.construireDico();
		this.plateau = new Case [15][15];
		this.initPlateau();
	}
	
	/**
	* Place un objet case à une position donnée du plateau (XY)
	* @param x la position x du plateau
	* @param y la position y du plateau
	*/
	public void initCasePlateau(Case caseSet, int x, int y) {
		this.plateau[x][y] = caseSet;
	}
	/**
	 * Construis le dictionnaire sur base du fichier dictionnaire.txt
	 */
	private void construireDico() {
		try {
			File dico = new File("ressource/MotValideScrabble.txt"); //Path du dictionnaire.txt
			BufferedReader br = new BufferedReader(new FileReader(dico)); //Crétation du buffer
			String line; //Variable pour les lignes
			while ((line = br.readLine()) != null) { //Pour chaque ligne exécute la boucle
					this.dictionnaire.add(line.toLowerCase());
			}
			br.close(); //Ferme le fichier
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Initialise le plateau.
	* Lis le fichier dataCase.xml et place les cases dans le plateau
	*/
	private void initPlateau() {
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      factory.setIgnoringElementContentWhitespace(true);

	      try {
	         //Méthode qui permet d'activer la vérification du fichier
	         factory.setValidating(true);
	         
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         
	         //création de notre objet d'erreurs
	         ErrorHandler errHandler = new SimpleErrorHandler();
	         //Affectation de notre objet au document pour interception des erreurs éventuelles
	         builder.setErrorHandler(errHandler);
	         
	         // Parsing d'un XML via une URI
	         String uri = "./ressource/dataCase.xml";
	         
	         //On rajoute un bloc de capture
	         //pour intercepter les erreurs au cas où il y en a
	         try {
	            Document xml = builder.parse(uri); //Lis le fichier 
	            Element root = xml.getDocumentElement(); //Récupère l'élément racine du fichier  
	            
	            XPathFactory xpf = XPathFactory.newInstance(); //Crée une instance de xpath
	            XPath path = xpf.newXPath(); 
	            
	            int j = 1;  //Variable pour incrémenter les lignes
	            int c = 0; //Variable pour incrémenter les cases
	            
	            for (int i = 1; i < 226; i++){
	            		c++; //Incrémente le compteur de case
	            	
	            		String expressionX = "/plateau/ligne[" + j + "]/case[" + c + "]/x";
	            		String expressionY = "/plateau/ligne[" + j + "]/case[" + c + "]/y";
	            		String expressionBonus = "/plateau/ligne[" + j + "]/case[" + c + "]/bonus";
	            		
	            		int x = 0;
	            		int y = 0;
	            		int bonus = 0;
	            		
	            		try {
	            			x = ((Double)path.evaluate(expressionX, root, XPathConstants.NUMBER)).intValue();
	            			y = ((Double)path.evaluate(expressionY, root, XPathConstants.NUMBER)).intValue();
	            			bonus = ((Double)path.evaluate(expressionBonus, root, XPathConstants.NUMBER)).intValue();
	            		} catch(XPathExpressionException e) {
	            			System.out.println("Erreur Système : vérifier ressource plateau");
	            		}
	            		
	            		Case caseNouvelle = new Case(bonus);
	            		this.initCasePlateau(caseNouvelle, x, y);	            	
	            		
			    	//Incrémente la ligne toutes les 15 cases lues
	            		if(i%15 == 0) {
	            			j++;
	            			c = 0;
	            		}
	            }
	                  
	         } catch (SAXParseException e){}  
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } 
	}
	
	/**
	 * Itération d'un dictionnaire pour trouver concordance 
	 * avec le mot entré par le joueur et vérifier s'il existe
	 * @return true si mot est dans le dictionnaire sinon false
	 */
	public boolean verification(String mot) {
		return dictionnaire.contains(mot);
	}
	
	/**
	 * Flag, un mot est-il adjacent (mot horizontal) ? Si oui --> true
	 */
	private boolean	estAdjacentH;
	
	/**
	 * Flag, un mot est-il adjacent (Mot vertical) ? Si oui --> true
	 */
	private boolean estAdjacentV;
	
	/**
	 * Recherche des mots périphériques à celui placé par le joueur
	 * qui se seraient créés et appel de la méthode vérification
	 * @param x La position x de la première lettre
	 * @param y La position y de la première lettre
	 * @param orientation l'orientation du mot, h = horizontal, v = vertical
	 */
	public boolean verificationPeripherique(int x, int y, char orientation, List<Lettre> motMain, List<Lettre> motJoue) {
		estAdjacentH = false;
		estAdjacentV = false;
		
		if(motJoue.size() == 1) {
			
			if(this.checkGaucheDroite(x, y, 0, motJoue) != true) {
				return false;
			}
			
			if(this.checkHautBas(x, y, 0, motJoue) != true) {
				return false;
			}
			
			
			if(estAdjacentH == true || estAdjacentV == true) {
				System.out.println("Le mot est correct");
				this.calculScore(x, y, orientation, motMain);
				return true;
			} else {
				System.out.println("Erreur : Placez le mot adjacent à un autre");
				return false;
			}

		} else {
			
			String motPrincipal = "";
			
			char labelLettre;
			if(motJoue.get(0) != null) {
				labelLettre = motJoue.get(0).getLabel();
			} else {
				labelLettre = this.plateau[x][y].getLabelCase();
			}
			
			if(orientation == 'v') {
				motPrincipal += getLabelToList(checkHaut(x, y)) + labelLettre + getLabelToList(checkBas(x, y));
			} else if (orientation == 'h') {
				motPrincipal += getLabelToList(checkGauche(x, y)) + labelLettre + getLabelToList(checkDroite(x, y));
			} else {
				System.out.println("Orientation incorrecte");
				return false;
			}
			
			if (this.verification(motPrincipal) == false) {
				System.out.println("Le mot " + motPrincipal + " est incorrect");
				return false;
			}
			
			for(int i = 0; i < (motJoue.size()); i++) {
				
				if(orientation =='h') {
					if(this.checkHautBas(x+i, y, i, motJoue) != true) {
						System.out.println("Le mot vertical n'est pas correct");
						return false;
					}
				}
				
				if(orientation =='v') {
					if(this.checkGaucheDroite(x, y-i, i, motJoue) != true) {
						System.out.println("Le mot horizontal n'est pas correct");
						return false;
					}
				}
			}
			
			if(orientation =='h') {
				if(estAdjacentH != true && motPrincipal.length() <= motMain.size()) {
					System.out.println("Placez le mot adjacent à un autre");
					return false;
				}
			} else {
				if(estAdjacentV != true && motPrincipal.length() <= motMain.size()) {
					System.out.println("Placez le mot adjacent à un autre");
					return false;
				}
			}
			
			this.calculScore(x, y, orientation, motMain);
			System.out.println("OK mot");
			return true;
		}

	}
	
	//----------------------------------------------------------------------------------
	
	//Vérification haut/bas, gauche/droite
	
	/**
	 * Vérifie les mots au dessus de la lettre
	 * @param x la position x de la lettre
	 * @param y la position y de la lettre
	 * @return la chaine de caractère au dessus de la lettre
	 */
	public List<Case> checkHaut(int x, int y){
		List<Case> contientLettre = new ArrayList<Case>();
		int j = 1;
		try {
			if(plateau[x][y+1].lettre != null) {
				while(plateau[x][y+j].lettre != null) {
					contientLettre.add(plateau[x][y+j]);
					j++;
				}
				estAdjacentH = true;
			} 
		} catch(IndexOutOfBoundsException e) {
			//Empêche le crash, si le mot est au bord du plateau
		}
		Collections.reverse(contientLettre); //Inverse la liste
		System.out.println("hautMot : " + this.getLabelToList(contientLettre));
		return contientLettre;
	}
	
	/**
	 * Vérifie les mots en dessous de la lettre
	 * @param x la position x de la lettre
	 * @param y la position y de la lettre
	 * @return la chaine de caractère en dessous de la lettre
	 */
	public List<Case> checkBas(int x, int y){
		List<Case> contientLettre = new ArrayList<Case>();
		int j = 1;
		try {
			if(plateau[x][y-1].lettre != null) {
				while(plateau[x][y-j].lettre != null) {
					contientLettre.add(plateau[x][y-j]);
					j++;
				}
				estAdjacentH = true;
			} 
		} catch(IndexOutOfBoundsException e) {
			//Empêche le crash, si le mot est au bord du plateau
		}
		System.out.println("BasMot : " + this.getLabelToList(contientLettre));
		return contientLettre;
	}
	
	/**
	 * Vérifie les mots à gauche de la lettre
	 * @param x la position x de la lettre
	 * @param y la position y de la lettre
	 * @return la chaine de caractère à gauche de la lettre
	 */
	public List<Case> checkGauche(int x, int y){
		List<Case> contientLettre = new ArrayList<Case>();
		int j = 1;
		try {
			if(plateau[x-1][y].lettre != null) {
				while(plateau[x-j][y].lettre != null) {
					contientLettre.add(plateau[x-j][y]);
					j++;
				}
				estAdjacentV = true;
			} 
		} catch(IndexOutOfBoundsException e) {
			//Empêche le crash, si le mot est au bord du plateau
		}
		Collections.reverse(contientLettre); //Inverse la liste
		System.out.println("GaucheMot : " + this.getLabelToList(contientLettre));
		return contientLettre;
	}
	
	/**
	 * Vérifie les mots à droite de la lettre
	 * @param x la position x de la lettre
	 * @param y la position y de la lettre
	 * @return la chaine de caractère à droite de la lettre
	 */
	public List<Case> checkDroite(int x, int y){
		List<Case> contientLettre = new ArrayList<Case>();
		int j = 1;
		try {
			if(this.plateau[x+1][y].lettre != null) {
				while(this.plateau[x+j][y].lettre != null) {
					contientLettre.add(plateau[x+j][y]);
					j++;
				}
				estAdjacentV = true;
			} 	
		} catch(IndexOutOfBoundsException e) {
			//Empêche le crash, si le mot est au bord du plateau
		}
		System.out.println("DroiteMot : " + this.getLabelToList(contientLettre));
		return contientLettre;
	}
	
	/**
	 * Vérifie le mot en haut et en bas
	 * @param x la position x de la lettre
	 * @param y la position y de la lettre
	 * @param getNum le numéro de la lettre dans motJoue
	 * @return true si tous est juste, sinon false
	 */
	public boolean checkHautBas(int x, int y, int getNum, List<Lettre> motJoue) {
		char labelLettre;
		if(motJoue.get(getNum) != null) {
			labelLettre = motJoue.get(getNum).getLabel();
		} else {
			labelLettre = this.plateau[x][y].getLabelCase();
		}
		
		String motHaut = this.getLabelToList(checkHaut(x, y));
		String motBas = this.getLabelToList(checkBas(x, y));
		
		
		
		if(motHaut != "" && motBas != "") {
			String tempMot = motHaut + labelLettre + motBas;
			if(this.verification(tempMot) == false) {
				System.out.println("Mot incorrect");
				return false;
			}
			
		} else if(motHaut != "") {
			if(this.verification(motHaut + labelLettre) == false) {
				System.out.println("Le mot en haut est incorrect");
				return false;
			}
			
		} else if(motBas != "") {
			if(this.verification(labelLettre + motBas) == false) {
				System.out.println("Le mot en bas est incorrect");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Vérifie le mot à gauche et à droite
	 * @param x la position x de la lettre
	 * @param y la position y de la lettre
	 * @param getNum le numéro de la lettre dans motJoue
	 * @return true si tous est juste, sinon false
	 */
	public boolean checkGaucheDroite(int x, int y, int getNum, List<Lettre> motJoue) {
		char labelLettre;
		if(motJoue.get(getNum) != null) {
			labelLettre = motJoue.get(getNum).getLabel();
		} else {
			labelLettre = this.plateau[x][y].getLabelCase();
		}
		
		String motGauche = this.getLabelToList(checkGauche(x, y));
		String motDroit = this.getLabelToList(checkDroite(x, y));
		
		
		if(motGauche != "" && motDroit != "") {
			String tempMot = motGauche + labelLettre + motDroit;
			if(this.verification(tempMot) == false) {
				System.out.println("Mot incorrect");
				return false;
			}
		} else if(motGauche != "") {
			if(this.verification(motGauche + labelLettre) == false) {
				System.out.println("Le mot à droite est incorrect");
				return false;
			}
		} else if(motDroit != "") {
			if(this.verification(labelLettre + motDroit) == false) {
				System.out.println("Le mot à gauche est incorrect");
				return false;
			}
		}
		return true;
	}
	
	//----------------------------------------------------------------------------------
	
	//Utilitaire
	
	/**
	 * Récupère le label d'une liste de lettre dans des cases
	 * @param listCase la liste de case
	 * @return le mot formé par les labels
	 */
	public String getLabelToList(List<Case> listCase) {
		String tempMot = "";
		for(int i = 0; i < listCase.size(); i++) {
			tempMot += listCase.get(i).getLabelCase();
		}
		return tempMot;
	}
	
	/**
	 * Récupère le score d'une liste de lettre dans des cases sans prendre
	 * en compte les bonus
	 * @param listCase la liste de case
	 * @return le score
	 */
	public int getScoreToList(List<Case> listCase) {
		int tempScore = 0;
		for(int i = 0; i < listCase.size(); i++) {
			tempScore += listCase.get(i).getValeurCase();
		}
		return tempScore;
	}
	
	/**
	 * Récupère les labels d'une liste de lettre
	 * @param listLettre la liste de lettre
	 * @return le mot 
	 */
	public String getMotToList(List<Lettre> listLettre) {
		String tempMot = "";
		for(int i = 0; i < listLettre.size(); i++) {
			tempMot += listLettre.get(i).getLabel();
		}
		return tempMot;
	}
	
	/**
	 * Récupère les objets Case du plateau en fonction des coordonées de celle-ci
	 * @param x La position x de la première case
	 * @param y La position y de la première case
	 * @return la case du plateau en (x,y)
	 */
	public Case getCase(int x, int y) {
		return this.plateau[x][y];
	}
	
	/**
	 * Récupère les bonus de Case du plateau en fonction des coordonées de celle-ci
	 * @param x La position x de la première case
	 * @param y La position y de la première case
	 * @return le bonus de la case du plateau en (x,y)
	 */
	public int getPlateauBonus(int x, int y) {
		return this.plateau[x][y].getBonus();
	}
	
	/**
	 * Récupère les labels des lettres contenues dans la Case du plateau en fonction des coordonées de celle-ci
	 * @param x La position x de la première case
	 * @param y La position y de la première case
	 * @return les labels des lettres contenues dans la case du plateau en (x,y)
	 */
	public char getPlateauLabel(int x, int y) {
		return this.plateau[x][y].getLabelCase();
	}
	/**
	 * Copie le plateau, pour la sauvegarde
	 * @param tab le tableau à copier
	 * @return le tableau copié
	 */
	public Case[][] copyPlateau() {
		Case[][] tempArray = new Case [15][15];
		for(int i = 0; i < 15; i++) { //X
			for(int h = 0; h < 15; h++) { //Y
				if(this.plateau[0 + i][0 + h].getLettre() != null) {
					tempArray[0 + i][0 + h] = new Case(this.plateau[0 + i][0 + h].getBonus(), this.plateau[0 + i][0 + h].getLettre());
				} else {
					tempArray[0 + i][0 + h] = new Case(this.plateau[0 + i][0 + h].getBonus(), null);
				}
			}
		}
		return tempArray;
	}
	
	/**
	 * Converti une liste de cases en liste de Lettre
	 * @param cases la liste de cases
	 * @return la liste de lettre récupérée des cases
	 */
	public List<Lettre> convertListCaseToListLettre(List<Case> cases) {
		List<Lettre> listTemp = new ArrayList<Lettre>();
		for(int i = 0; i < cases.size(); i++) {
			listTemp.add(cases.get(i).getLettre());
		}
		return listTemp;
	}
	
	//----------------------------------------------------------------------------------
	
	/**
	 * Test du premier mot
	 * @param x La position x de la première lettre
	 * @param y La position y de la première lettre
	 */
	public boolean checkPremierMot(int x, int y, char orientation, Joueur joueurActuel, List<Lettre> motMain){
			int j = 0;
			boolean estCentre = false; //Si c'est OK  --> true = au milieu du plateau
			switch (orientation) {
				case 'v' :
					while(plateau[x][y - j].lettre != null) {
						if(plateau[x][y - j].getBonus() == 5) {
							estCentre = true;
						}
						j++;
					} 
					
					break;
				case 'h' : 
					while(plateau[x + j][y].lettre != null) {
						if(plateau[x + j][y].getBonus() == 5) {
							estCentre = true;
						}
						j++;
					} 
					
					break;
				default : 
					System.out.println("Erreur, orientation incorrecte");
					estCentre = false;
			}
			if (estCentre) {
				this.calculScore(x, y, orientation, motMain);
				this.debutPartie = true;
			}
			return estCentre;
	}
	
	/**
	 * Contient le score temporaire lors du calcul de score
	 */
	public int tempScore = 0;
	
	/**
	 * Toutes les lettres doublées
	 */
	public List<Lettre> lettreDouble = new ArrayList<Lettre>();
	
	/**
	 * Toutes les lettres triplées
	 */
	public List<Lettre> lettreTriple = new ArrayList<Lettre>();
	
	/**
	 * Lettre sans bonus
	 */
	public List<Lettre> lettreScore = new ArrayList<Lettre>();
	
	/**
	 * Calcul le score du mot posé
	 * @param x la postion x de la première lettre posée
	 * @param y la postion y de la première lettre posée
	 * @param orientation l'orientation du mot (h ou v)
	 * @param motMain le nombre de lettre jouée depuis la main (en cas de scrabble)
	 */
	public void calculScore(int x, int y, char orientation, List<Lettre> motMain) {
		List<Lettre> scorePrincipal = new ArrayList<Lettre>();
		
		//Vide les listes
		lettreTriple.removeAll(lettreTriple);
		lettreDouble.removeAll(lettreDouble);
		lettreScore.removeAll(lettreScore);
		
		int doubleMot = 0;
		int tripleMot = 0;
		
		int xDebut = x;
		int yDebut = y;
		
		if(orientation == 'h') {
			scorePrincipal.addAll(this.convertListCaseToListLettre(this.checkGauche(x, y)));
			scorePrincipal.add(this.plateau[x][y].getLettre());
			scorePrincipal.addAll(this.convertListCaseToListLettre(this.checkDroite(x, y)));
			
			xDebut = x - this.checkGauche(x, y).size();
			
		} else { //orientation == 'v'
			scorePrincipal.addAll(this.convertListCaseToListLettre(this.checkHaut(x, y)));
			scorePrincipal.add(this.plateau[x][y].getLettre());
			scorePrincipal.addAll(this.convertListCaseToListLettre(this.checkBas(x, y)));
			
			yDebut = y + this.checkHaut(x, y).size();
			
		}
		
		int h = 0;
		int v = 0;
		
		for(int i = 0; i < scorePrincipal.size(); i++) {
			switch (this.plateau[xDebut + h][yDebut - v].getBonus()) {
				case 3:
					doubleMot += 1;
					break;
				case 4:
					tripleMot += 1;
					break;
				case 5:
					doubleMot += 1;
					break;
				default:
				
			}
		
			if(orientation == 'h') {
				h++;
			} else {
				v++;
			}
		}
		
		h = 0;
		v = 0;
		
		for(int i = 0; i < scorePrincipal.size(); i++) {
			
			Case caseActuel = this.plateau[xDebut + h][yDebut - v];
			
			int bonusActuel = caseActuel.getBonus();
			boolean flagEstCompte = false; //Flag, true si la lettre est comptabilisé
			
			if(bonusActuel == 1) {
				lettreDouble.add(caseActuel.getLettre());
				flagEstCompte = true;
			} else if (bonusActuel == 2) {
				lettreTriple.add(caseActuel.getLettre());
				flagEstCompte = true;
			} 
			
			if(doubleMot == 0 && tripleMot == 0 && flagEstCompte == false) {
				lettreScore.add(caseActuel.getLettre());
			} else if(doubleMot > 0) {
				lettreDouble.add(caseActuel.getLettre());
			} else if(tripleMot > 0) {
				lettreTriple.add(caseActuel.getLettre());
			}
			
			//caseActuel.setBonus(0);
			
			if(orientation == 'h') {
				h++;
			} else {
				v++;
			}
		}
		this.calculScoreMot(motMain);
		this.calculScorePeripherique(x, y, orientation);
	}
	
	/**
	 * Calcule le score périphérique d'un mot
	 * @param x la position x de la première lettre posée
	 * @param y la position y de la première lettre posée
	 * @param orientation l'orientation du mot (h ou v)
	 */
	public void calculScorePeripherique(int x, int y, char orientation) {
		int scoreSecondaireTemp = 0;
		int h = 0;
		int v = 0;
		for(int i = 0; i < motJoue.size(); i++) { //motJoue, la longueur du mot posé
			scoreSecondaireTemp = 0;
			
			if(motJoue.get(i) != null) {
				if(orientation == 'h') {
					scoreSecondaireTemp += this.getScoreToList(this.checkHaut(x + h, y));
					scoreSecondaireTemp += this.getScoreToList(this.checkBas(x + h, y));
					
				} else {
					scoreSecondaireTemp += this.getScoreToList(this.checkDroite(x, y - v));
					scoreSecondaireTemp += this.getScoreToList(this.checkGauche(x, y - v));
					
				}
				
				if(scoreSecondaireTemp != 0) {
					scoreSecondaireTemp += this.plateau[x + h][y - v].getValeurCase();
				}
				
				if(this.plateau[x + h][y - v].getBonus() == 3) {
					this.tempScore += (scoreSecondaireTemp)*2;
				} else if (this.plateau[x + h][y - v].getBonus() == 4) {
					this.tempScore += (scoreSecondaireTemp)*3;
				} else {
					this.tempScore += scoreSecondaireTemp;
				}
				
				if(orientation == 'h') {
					h++;
				} else {
					v++;
				}

			}
		}
		
		this.deleteBonus(x, y, orientation);
	}
	
	/**
	 * Calcule le score d'un mot en comptant les mots doubles et triples
	 * @param motMain le nombre de lettre jouées depuis la main (en cas de scrabble)
	 */
	public void calculScoreMot(List<Lettre> motMain) {
		int score = 0;
		
		if(motMain.size() == 7) {
			Lettre scrabble = new Lettre('$', 50); //Création d'une lettre de 50points pour le scrabble
			lettreScore.add(scrabble);
		}
		
		for(int i = 0; i < lettreDouble.size(); i++) {
			score += (lettreDouble.get(i).getValeur() * 2);
		}
		
		for(int i = 0; i < lettreTriple.size(); i++) {
			score += (lettreTriple.get(i).getValeur() * 3);
		}
		
		for(int i = 0; i < lettreScore.size(); i++) {
			score += lettreScore.get(i).getValeur();
		}
		
		this.tempScore += score;
	}
	
	/**
	 * Enlève du plateau les bonus déjà utilisés
	 * @param x la position x de départ du mot posé
	 * @param y la position y de départ du mot posé
	 * @param orientation l'orientation du mot posé
	 */
	public void deleteBonus(int x, int y, char orientation) {
		int h = 0;
		int v = 0;
		
		for(int i = 0; i < motJoue.size(); i++) {
			this.plateau[x + h][y - v].setBonus(0);
			
			if(orientation == 'h') {
				h++;
			} else {
				v++;
			}
			
		}
	}
	
	/**
	 * Actualise le score du joueur passé en paramètre
	 * @param joueurActuel le joueur qu'on actualise
	 */
	public void setScoreJoueur(Joueur joueurActuel) {
		joueurActuel.addScore(tempScore);
		this.tempScore = 0;
	}
	
	/**
	 * Affiche le plateau en console
	 * @return string contenant tout le plateau
	 */
	public String toString() {
		int j = 14;
		String string = "";
		for(int i = 0; i < 15; i++) {
			if(j < 10) {
				string += j + " |";
			}
			else if(j > 9) {
				string += j + "|";
			}
			for(int h = 0; h < 15; h++) {
				if(plateau[h][j].getLettre() == null) {
						string += (plateau[h][j].getBonus() + "|");
				}
				else {
					string += (plateau[h][j].getLabelCase() + "|");
				}
			}
			string += "\n";
			j--;
		}
		string += "  |";
		for (int i = 0; i < 15; i++) {
			if(i < 10) {
				string += i + "|";
			}
			else if(i > 9) {
				string += i ;
			}
		}
		string += "\n";
		return string;
	}	
}
