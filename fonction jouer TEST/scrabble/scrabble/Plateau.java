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
public class Plateau extends Observable {

	/**
	* dictionnaire Contient tous les mots de la langues française 
	*/
	public HashSet<String> dictionnaire = new HashSet<String>();
	
	
	/**
	* Tableau à double entrée representant le plateau
	*/
	public Case[][] plateau;
	
	/**
	 * True si début de la partie, sinon false;
	 */
	public boolean debutPartie;
	
	/**
	* Contructeur par défaut du sac
	* @throws XPathExpressionException gestion des erreurs
	*/
	public Plateau() {
		this.construireDico();
		this.plateau = new Case [15][15];
		this.debutPartie = false;
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
			File dico = new File("ressource/dictionnaire.txt"); //Path du dictionnaire.txt
			BufferedReader br = new BufferedReader(new FileReader(dico)); //Crétation du buffer
			String line; //Variable pour les lignes
			while ((line = br.readLine()) != null) { //Pour chaque ligne éxécute la boucle
					this.dictionnaire.add(line);
			}
			System.out.println(this.dictionnaire.size());
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
	            		
			    	//Incrémente la ligne toute les 15 cases lues
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
					return true;
				} else {
					System.out.println(this);
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
				
				for(int i = 0; i < (motJoue.size() ); i++) {
					
					if(orientation =='h') {
						if(this.checkHautBas(x+i, y, i, motJoue) != true) {
							return false;
						}
						
						if(estAdjacentH != true && motPrincipal.length() <= motMain.size()) {
							return false;
						}
					}
					
					if(orientation =='v') {
						if(this.checkGaucheDroite(x, y-i, i, motJoue) != true) {
							System.out.println("gauchedroite");
							return false;
						}
						
						if(estAdjacentV != true && motPrincipal.length() <= motMain.size()) {
							System.out.println("verif");
							return false;
						}
					}
				}	
				
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
		
		if(this.getLabelToList(checkHaut(x, y)) != "" && this.getLabelToList(checkBas(x, y)) != "") {
			String tempMot = this.getLabelToList(checkHaut(x, y)) + labelLettre + this.getLabelToList(checkBas(x, y));
			if(this.verification(tempMot) == false) {
				System.out.println("Mot incorrect");
				return false;
			}
			
		} else if(this.getLabelToList(checkHaut(x, y)) != "") {
			if(this.verification(this.getLabelToList(checkHaut(x, y)) + labelLettre) == false) {
				System.out.println("Le mot en haut est incorrect");
				return false;
			}
			
		} else if(this.getLabelToList(checkBas(x, y)) != "") {
			if(this.verification(labelLettre + this.getLabelToList(checkBas(x, y))) == false) {
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
		
		if(this.getLabelToList(checkGauche(x, y)) != "" && this.getLabelToList(checkDroite(x, y)) != "") {
			String tempMot = this.getLabelToList(checkGauche(x, y)) + labelLettre + this.getLabelToList(checkDroite(x, y));
			if(this.verification(tempMot) == false) {
				System.out.println("Mot incorrect");
				return false;
			}
		} else if(this.getLabelToList(checkGauche(x, y)) != "") {
			if(this.verification(this.getLabelToList(checkGauche(x, y)) + labelLettre) == false) {
				System.out.println("Le mot à droite est incorrect");
				return false;
			}
		} else if(this.getLabelToList(checkDroite(x, y)) != "") {
			if(this.verification(labelLettre + this.getLabelToList(checkDroite(x, y))) == false) {
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
	 * Récupère le score d'une liste de lettre dans des cases
	 * @param listCase la liste de case
	 * @return le score
	 */
	public int getScoreToList(List<Case> listCase) {
		int tempScore = 0;
		for(int i = 0; i < listCase.size(); i++) {
			//TODO
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
	
	//----------------------------------------------------------------------------------
	
	/**
	 * Test du premier mot
	 * @param x La position x de la première lettre
	 * @param y La position y de la première lettre
	 */
	public boolean checkPremierMot(int x, int y, char orientation){
			int j = 0;
			boolean test = false; //Si c'est OK  --> true
			switch (orientation) {
				case 'v' :
					while(plateau[x][y - j].lettre != null) {
						if(plateau[x][y - j].getBonus() == 5) {
							test = true;
						}
						j++;
					} 
					
					break;
				case 'h' : 
					while(plateau[x + j][y].lettre != null) {
						if(plateau[x + j][y].getBonus() == 5) {
							test = true;
						}
						j++;
					} 
					
					break;
				default : 
					System.out.println("Erreur, orientation incorrecte");
					test = false;
			}
			if (test) {
				this.debutPartie = true;
			}
			return test;
	}
	
	
	/**
	 * Calcul du score en prenant compte les bonus
	 * Utilisation des valeurs de chaques lettres pondérées avec le bonus
	 * et ajout dans la classe Joueur.score 
	 */
	public void calculScore() {
		// Enorme point d'interrogation sur ce que l'on doit faire et comment on peut le calculer
	}
	
	/**
	 * Affiche le plateau en console
	 * @return string contenant tout le plateau
	 */
	@Override
	public String toString() {
		int j = 14; //int j = 0;
		String string = "";
		for(int i = 0; i < 15; i++) {
			string += "|";
			for(int h = 0; h < 15; h++) {
				if(plateau[h][j].getLettre() == null) {
					string += (plateau[h][j].getBonus() + "|");
				}
				else {
					string += (plateau[h][j].getLabelCase() + "|");
				}
			}
			string += "\n";
			j--; //j++;
		}
		return string;
	}
}
