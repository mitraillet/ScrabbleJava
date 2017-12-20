/**
 * 
 */
package scrabble;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.*;

/**
 * @author Fauconnier/Henriquet
 * Classe de gestion du plateau. 
 * Contient la vérification des mots et le calcul des scores
 */
public class Plateau implements Serializable {
	
	/**
	* Dictionnaire contenant tous les mots éligibles au scrabble
	*/
	private transient HashSet<String> dictionnaire = new HashSet<String>();
	
	/**
	* Tableau à double entrée représentant le plateau
	*/
	private Case[][] plateau;
	
	/**
	 * True si partie déjà commencée, sinon false;
	 */
	private boolean debutPartie = false;
	
	/**
	* Contructeur par défaut du plateau
	*/
	public Plateau() {
		this.construireDico();
		this.plateau = new Case [15][15];
		this.initPlateau();
	}
	
	/**
	 * Permet de changer le plateau par un autre
	 * @param newPlateau variable contenant un double array de Case
	 */
	public void setPlateau(Case[][] newPlateau) {
		this.plateau = newPlateau;
	}
	
	/**
	 * Permet de récupérer le plateau en cours
	 * @return le plateau de jeu
	 */
	public Case[][] getPlateau() {
		return this.plateau;
	}
	
	/**
	* Place un objet case à une position donnée du plateau (XY)
	* @param caseSet la case à placer dans le plateau
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
			File dico = new File("ressource/LexiqueODS7.txt"); //Path du dictionnaire.txt
			BufferedReader br = new BufferedReader(new FileReader(dico)); //Crétation du buffer
			String line; //Variable pour les lignes
			while ((line = br.readLine()) != null) { //Pour chaque ligne exécute la boucle
					this.dictionnaire.add(line.toLowerCase());
			}
			br.close(); //Ferme le fichier
		} catch(FileNotFoundException e) {
			MessageDErreur.setMsgDErreur("Impossible de trouver les ressources pour le Dictionnaire");
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
	            			MessageDErreur.setMsgDErreur("Erreur Système : vérifier ressource plateau");
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
	    	  		MessageDErreur.setMsgDErreur("Erreur de configuration XML");
	      } catch (SAXException e) {
	    	  		MessageDErreur.setMsgDErreur("Erreur lors de la lecture du fichier XML");
	      } catch (IOException e) {
	    	  		MessageDErreur.setMsgDErreur("Erreur lors de la lecture du fichier XML");
	      } 
	}
	
	/**
	 * Itération d'un dictionnaire pour trouver concordance 
	 * avec le mot entré par le joueur et vérifier s'il existe
	 * @param mot le mot à vérifier
	 * @return true si mot est dans le dictionnaire sinon false
	 */
	public boolean verification(String mot) {
		return dictionnaire.contains(mot);
	}
	
	/**
	 * Recherche des mots périphériques à celui placé par le joueur
	 * qui se seraient créés et appel de la méthode vérification
	 * @param x La position x de la première lettre
	 * @param y La position y de la première lettre
	 * @param orientation l'orientation du mot, h = horizontal, v = vertical
	 * @param motMain Les lettres tirées de la main
	 * @param motJoue Les lettres à mettre sur le plateau
	 * @return true si les vérifications sont correctes, sinon false
	 */
	public boolean verificationPeripherique(int x, int y, char orientation, List<Lettre> motMain, List<Lettre> motJoue) {
			
		String motPrincipal = ""; //Le mot formé par les lettres posées
			
		char labelLettre; //Le label de la première lettre du mot
			
		//Définis le label de la première lettre du mot
		if(motJoue.get(0) != null) {
			labelLettre = motJoue.get(0).getLabel();
		} else {
			labelLettre = this.getPlateauLabel(x, y);
		}
			
		//Création du mot principal
		if(orientation == 'v') {
			motPrincipal += getLabelToList(checkHaut(x, y)) + labelLettre + getLabelToList(checkBas(x, y));
		} else if (orientation == 'h') {
			motPrincipal += getLabelToList(checkGauche(x, y)) + labelLettre + getLabelToList(checkDroite(x, y));
		} 
			
		//Si le mot principal est faux
		if (this.verification(motPrincipal) == false) {
			MessageDErreur.setMsgDErreur("Le mot " + motPrincipal + " est incorrect");
			return false;
		}
			
		boolean estAdjacentH = false;
		boolean estAdjacentV = false;
		
		//Pour chaque lettre du mot, vérifie les mots périphériques formés
		for(int i = 0; i < (motJoue.size()); i++) {
				
			if(orientation =='h') {
				if(this.checkHautBas(x+i, y, i, motJoue) != true) {
					MessageDErreur.setMsgDErreur("Le mot vertical n'est pas correct");
					return false;
				}
				
				if(estAdjacentH == false) {
					if(checkHaut(x+i, y).size() != 0 || checkBas(x+i, y).size() != 0) {
						estAdjacentH = true;
					}
				}
				
			}
				
			if(orientation =='v') {
				if(this.checkGaucheDroite(x, y-i, i, motJoue) != true) {
					MessageDErreur.setMsgDErreur("Le mot horizontal n'est pas correct");
					return false;
				}
				
				if(estAdjacentV == false) {
					if(checkGauche(x, y - i).size() != 0 || checkDroite(x, y - i).size() != 0) {
						estAdjacentV = true;
					}
				}
			}
		}
		
		if(estAdjacentV == false && estAdjacentH == false && motPrincipal.length() <= motMain.size()) {
			MessageDErreur.setMsgDErreur("Erreur : Placez le mot adjacent à un autre");
			return false;
		} else {
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
			if(this.getPlateauLettre(x, y + 1) != null) {
				while(this.getPlateauLettre(x, y + j) != null) {
					contientLettre.add(this.getCase(x, y + j));
					j++;
				}
			} 
		} catch(IndexOutOfBoundsException e) {
			//Empêche le crash, si le mot est au bord du plateau
		}
		Collections.reverse(contientLettre); //Inverse la liste
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
			if(this.getPlateauLettre(x, y - 1) != null) {
				while(this.getPlateauLettre(x, y - j) != null) {
					contientLettre.add(this.getCase(x, y - j));
					j++;
				}
			} 
		} catch(IndexOutOfBoundsException e) {
			//Empêche le crash, si le mot est au bord du plateau
		}
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
			if(this.getPlateauLettre(x - 1, y) != null) {
				while(this.getPlateauLettre(x - j, y) != null) {
					contientLettre.add(this.getCase(x - j, y));
					j++;
				}
			} 
		} catch(IndexOutOfBoundsException e) {
			//Empêche le crash, si le mot est au bord du plateau
		}
		Collections.reverse(contientLettre); //Inverse la liste
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
			if(this.getPlateauLettre(x + 1, y) != null) {
				while(this.getPlateauLettre(x + j, y) != null) {
					contientLettre.add(this.getCase(x + j, y));
					j++;
				}
			} 	
		} catch(IndexOutOfBoundsException e) {
			//Empêche le crash, si le mot est au bord du plateau
		}
		return contientLettre;
	}
	
	/**
	 * Vérifie le mot en haut et en bas
	 * @param x la position x de la lettre
	 * @param y la position y de la lettre
	 * @param getNum le numéro de la lettre dans motJoue
	 * @param motJoue le mot joué par le joueur
	 * @return true si tous est juste, sinon false
	 */
	public boolean checkHautBas(int x, int y, int getNum, List<Lettre> motJoue) {
		char labelLettre; // le label de la lettre à vérifier
		
		if(motJoue.get(getNum) != null) {
			labelLettre = motJoue.get(getNum).getLabel();
		} else {
			labelLettre = this.getPlateauLabel(x, y);
		}
		
		String motHaut = this.getLabelToList(checkHaut(x, y));
		String motBas = this.getLabelToList(checkBas(x, y));	
		
		//Vérification des mots formés
		if(motHaut != "" && motBas != "") {
			String tempMot = motHaut + labelLettre + motBas;
			if(this.verification(tempMot) == false) {
				MessageDErreur.setMsgDErreur("Mot incorrect");
				return false;
			}
			
		} else if(motHaut != "") {
			if(this.verification(motHaut + labelLettre) == false) {
				MessageDErreur.setMsgDErreur("Le mot en haut est incorrect");
				return false;
			}
			
		} else if(motBas != "") {
			if(this.verification(labelLettre + motBas) == false) {
				MessageDErreur.setMsgDErreur("Le mot en bas est incorrect");
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
		char labelLettre;// le label de la lettre à vérifier
		
		if(motJoue.get(getNum) != null) {
			labelLettre = motJoue.get(getNum).getLabel();
		} else {
			labelLettre = this.getPlateauLabel(x, y);
		}
		
		String motGauche = this.getLabelToList(checkGauche(x, y));
		String motDroit = this.getLabelToList(checkDroite(x, y));
		
		//Vérification des mots formés
		if(motGauche != "" && motDroit != "") {
			String tempMot = motGauche + labelLettre + motDroit;
			if(this.verification(tempMot) == false) {
				MessageDErreur.setMsgDErreur("Mot incorrect");
				return false;
			}
		} else if(motGauche != "") {
			if(this.verification(motGauche + labelLettre) == false) {
				MessageDErreur.setMsgDErreur("Le mot à droite est incorrect");
				return false;
			}
		} else if(motDroit != "") {
			if(this.verification(labelLettre + motDroit) == false) {
				MessageDErreur.setMsgDErreur("Le mot à gauche est incorrect");
				return false;
			}
		}
		return true;
	}
	
	//----------------------------------------------------------------------------------
	
	//Utilitaire
	
	/**
	 * Récupère le mot formé par les labels d'une liste de lettre inclue dans des cases
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
	 * Récupère le lettre d'une Case du plateau en fonction des coordonées de celle-ci
	 * @param x La position x de la case
	 * @param y La position y de la case
	 * @return le lettre contenue dans la case
	 */
	public Lettre getPlateauLettre(int x, int y) {
		return this.plateau[x][y].getLettre();
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
	 * Méthode pemettant de récuperer si la partie vient de commencer
	 * @return le debutPartie si c'est true la partie vient de commencer, si false
	 */
	public boolean isDebutPartie() {
		return debutPartie;
	}

	/**
	 * Méthode permettant de modifier debutPartie
	 * @param debutPartie change la valeur de debutPartie
	 */
	public void setDebutPartie(boolean debutPartie) {
		this.debutPartie = debutPartie;
	}	
	
	/**
	 * Copie le plateau
	 * @return le tableau copié
	 */
	public Case[][] copyPlateau() {
		Case[][] tempArray = new Case [15][15];
		for(int i = 0; i < 15; i++) { //X
			for(int h = 0; h < 15; h++) { //Y
				if(this.getPlateauLettre(0 + i, 0 + h) != null) {
					tempArray[0 + i][0 + h] = new Case(this.getPlateauBonus(0 + i, 0 + h), this.getPlateauLettre(0 + i, 0 + h));
				} else {
					tempArray[0 + i][0 + h] = new Case(this.getPlateauBonus(0 + i, 0 + h), null);
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
	 * Test si le  premier mot est correct
	 * @param x La position x de la première lettre
	 * @param y La position y de la première lettre
	 * @param orientation l'orientation du mot
	 * @param joueurActuel le joueur qui a posé le mot
	 * @param motMain les lettres tirées de la main
	 * @param motJoue le mot joué
	 * @return true si la premier mot est correct et bien placé, sinon false
	 */
	public boolean checkPremierMot(int x, int y, char orientation, Joueur joueurActuel, 
			List<Lettre> motMain, List<Lettre> motJoue){
			//int j = 0;
			boolean estCentre = false; //Si c'est OK  --> true = au milieu du plateau
			int h = 0;
			int v = 0;
			
			//Test si le mot passe par la casse du millieu (bonus = 5)
			while(this.getPlateauLettre(x + h, y -v) != null) {
				if(this.getPlateauBonus(x + h, y -v) == 5) {
					estCentre = true;
				}
				if(orientation == 'v') {
					v++;
				} else {
					h++;
				}
			} 
			
			//Si le mot est au centre
			if (estCentre) {
				this.setDebutPartie(true);
			}
			return estCentre;
	}
	
	/**
	 * Calcul le score du mot posé
	 * @param x la postion x de la première lettre posée
	 * @param y la postion y de la première lettre posée
	 * @param orientation l'orientation du mot (h ou v)
	 * @param motMain le nombre de lettre jouée depuis la main (en cas de scrabble)
	 * @param motJoue le mot joué
	 * @return le score du mot
	 */
	public int calculScore(int x, int y, char orientation, List<Lettre> motMain, List<Lettre> motJoue) {
		List<Lettre> scorePrincipal = new ArrayList<Lettre>();
		
		int tempScore = 0;
		List<Lettre> lettreDouble = new ArrayList<Lettre>();
		List<Lettre> lettreTriple = new ArrayList<Lettre>();
		List<Lettre> lettreScore = new ArrayList<Lettre>();
		
		int doubleMot = 0;
		int tripleMot = 0;
		
		int xDebut = x;
		int yDebut = y;
		boolean estScrabble = false;
		Lettre scrabble = new Lettre('/', 0);
		
		if(motMain.size() == 7) {
			scrabble = new Lettre('$', 50); //Création d'une lettre de 50 points pour le scrabble
			estScrabble = true;
		}
		
		
		//Calcul la longueur du mot posé
		if(orientation == 'h') {
			scorePrincipal.addAll(this.convertListCaseToListLettre(this.checkGauche(x, y)));
			scorePrincipal.add(this.getPlateauLettre(x, y));
			scorePrincipal.addAll(this.convertListCaseToListLettre(this.checkDroite(x, y)));
			
			xDebut = x - this.checkGauche(x, y).size();
			
		} else { //orientation == 'v'
			scorePrincipal.addAll(this.convertListCaseToListLettre(this.checkHaut(x, y)));
			scorePrincipal.add(this.getPlateauLettre(x, y));
			scorePrincipal.addAll(this.convertListCaseToListLettre(this.checkBas(x, y)));
			
			yDebut = y + this.checkHaut(x, y).size();
			
		}
		
		int h = 0;
		int v = 0;
		
		//Comptabilise les bonus
		for(int i = 0; i < scorePrincipal.size(); i++) {
			switch (this.getPlateauBonus(xDebut + h, yDebut - v)) {
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
					break;
			}
		
			if(orientation == 'h') {
				h++;
			} else {
				v++;
			}
		}
		
		h = 0;
		v = 0;
		
		//Ajoute les lettres dans les listes de score suivant les bonus
		for(int i = 0; i < scorePrincipal.size(); i++) {
			
			Case caseActuel = this.getCase(xDebut + h, yDebut - v);
			
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
			
			if(orientation == 'h') {
				h++;
			} else {
				v++;
			}
		}
		
		if(doubleMot == 0 && tripleMot == 0 && estScrabble == true) {
			lettreScore.add(scrabble);
		} else if(doubleMot > 0 && estScrabble == true) {
			lettreDouble.add(scrabble);
		} else if(tripleMot > 0 && estScrabble == true) {
			lettreTriple.add(scrabble);
		}
		
		tempScore += this.calculScorePeripherique(x, y, orientation, motJoue);
		tempScore += this.calculScoreMot(motMain, lettreDouble, lettreTriple, lettreScore);
		return tempScore;
	}
	
	/**
	 * Calcule le score périphérique d'un mot
	 * @param x la position x de la première lettre posée
	 * @param y la position y de la première lettre posée
	 * @param orientation l'orientation du mot (h ou v)
	 * @param motJoue le mot joué
	 * @return le score des mots périphérique
	 */
	public int calculScorePeripherique(int x, int y, char orientation, List<Lettre> motJoue) {
		int scoreSecondaireTemp = 0;
		int h = 0;
		int v = 0;
		
		int tempScore = 0;
		
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
					if(this.getPlateauBonus(x + h, y - v) == 1) {
						scoreSecondaireTemp += (this.getCase(x + h, y - v).getValeurCase())*2;
					} else if (this.getPlateauBonus(x + h, y - v) == 2) {
						scoreSecondaireTemp += (this.getCase(x + h, y - v).getValeurCase())*3;
					} else {
						scoreSecondaireTemp += this.getCase(x + h, y - v).getValeurCase();
					}
				}
				
				if(this.getPlateauBonus(x + h, y - v) == 3) {
					tempScore += (scoreSecondaireTemp)*2;
				} else if (this.getPlateauBonus(x + h, y - v) == 4) {
					tempScore += (scoreSecondaireTemp)*3;
				} else {
					tempScore += scoreSecondaireTemp;
				}

			}
			this.plateau[x + h][y - v].setBonus(0);
			if(orientation == 'h') {
				h++;
			} else {
				v++;
			}
		}
		
		return tempScore;
	}
	
	/**
	 * Calcule le score d'un mot en comptant les mots doubles et triples
	 * @param motMain le nombre de lettre jouées depuis la main (en cas de scrabble)
	 * @param lettreDouble les lettres doublées
	 * @param lettreTriple les lettres triplées
	 * @param lettreScore les lettres sans bonus
	 */
	public int calculScoreMot(List<Lettre> motMain, List<Lettre> lettreDouble, 
			List<Lettre> lettreTriple, List<Lettre> lettreScore) {
		int score = 0;
		
		for(int i = 0; i < lettreDouble.size(); i++) {
			score += (lettreDouble.get(i).getValeur() * 2);
		}
		
		for(int i = 0; i < lettreTriple.size(); i++) {
			score += (lettreTriple.get(i).getValeur() * 3);
		}
		
		for(int i = 0; i < lettreScore.size(); i++) {
			score += lettreScore.get(i).getValeur();
		}
		
		return score;
	}
	
	/**
	 * Actualise le score du joueur passé en paramètre
	 * @param joueurActuel le joueur qu'on actualise
	 * @param score le score à ajouter au joueur
	 */
	public void setScoreJoueur(Joueur joueurActuel, int score) {
		joueurActuel.addScore(score);
	}
	
	/**
	 * Vérifie si une case du plateau est remplie
	 * @param x la position x de départ du mot
	 * @param y la position y de départ du mot
	 * @param orientation l'orientation du mot
	 * @param plateau le plateau à vérifier
	 * @param nbrCase le numéro de la case
	 * @return true si la case est remplie, sinon false
	 */
	public boolean checkCaseRemplie(int x, int y, char orientation, int nbrCase){
		if(orientation == 'h') {
			try {
				if(this.plateau[x + nbrCase][y].getLettre() != null) {
					return true;
				}	
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		} else {
			try {
				if(this.plateau[x][y - nbrCase].getLettre() != null) {
					return true;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		return false;
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
				if(this.getPlateauLettre(h, j) == null) {
					if(this.getPlateauBonus(h, j) == 0) {
						string += (" |");
					}
					else {
						string += (this.getPlateauBonus(h, j) + "|");
					}
				}
				else {
					string += (this.getPlateauLabel(h, j) + "|" );
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
