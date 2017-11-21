/**
 * 
 */
package scrabble;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashSet;

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

	/*
	* dictionnaire Contient tous les mots de la langues française 
	*/
	public HashSet<String> dictionnaire = new HashSet<String>();
	
	/*
	* Tableau à double entrée representant le plateau
	*/
	public Case[][] plateau;
	
	/*
	* Contructeur par défaut du sac
	* @throws XPathExpressionException gestion des erreurs
	*/
	public Plateau() throws XPathExpressionException {
		this.construireDico();
		this.plateau = new Case [15][15];
		this.initPlateau();
	}
	
	/*
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
	private void initPlateau() throws XPathExpressionException {
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
	            		
	            		int x = ((Double)path.evaluate(expressionX, root, XPathConstants.NUMBER)).intValue();
	            		
	            		int y = ((Double)path.evaluate(expressionY, root, XPathConstants.NUMBER)).intValue();
	            		
	            		int bonus = ((Double)path.evaluate(expressionBonus, root, XPathConstants.NUMBER)).intValue();
	            		
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
	
	public String motJoue;
	
	/**
	 * Recherche des mots périphériques é celui placé par le joueur
	 * qui se seraient créés et appel de la méthode vérification
	 */
	public boolean verificationPeripherique(int x, int y, char orientation, String mot) {
		if(this.verification(mot)) {
			if(mot.length() == 1) {
				
				if(this.checkDroite(x, y) != "" || this.checkGauche(x, y) != "") {
					String tempMot = checkGauche(x, y) + plateau[x][y] + checkDroite(x, y);
					if(this.verification(tempMot) == false) {
						return false;
					}
				} else if(this.checkGauche(x, y) != "") {
					if(this.verification(this.checkGauche(x, y) + plateau[x][y]) == false) {
						return false;
					}
				} else if(this.checkDroite(x, y) != "") {
					if(this.verification(plateau[x][y] +this.checkDroite(x, y)) == false) {
						return false;
					}
				}
				
				
				if(this.checkHaut(x, y) != "" || this.checkBas(x, y) != "") {
					String tempMot = checkHaut(x, y) + plateau[x][y] + checkBas(x, y);
					if(this.verification(tempMot) == false) {
						return false;
					}
				} else if(this.checkHaut(x, y) != "") {
					if(this.verification(this.checkHaut(x, y) + plateau[x][y]) == false) {
						return false;
					}
				} else if(this.checkBas(x, y) != "") {
					if(this.verification(plateau[x][y] + this.checkBas(x, y)) == false) {
						return false;
					}
				}
				
				return true;

			} else {
				char premiereLettre = mot.charAt(1);
				char dernièreLettre = mot.charAt(mot.length());
				
				if(orientation == 'h') {
					//TODO
				} else if(orientation =='v') {
					//TODO
				} else {
					System.out.println("Erreur d'orientation");
					return false;
				}
				
				return true;
			}
			
		
		} else {
			return false;
		}
	}
	
	//----------------------------------------------------------------------------------
	
	/**
	 * Vérifie les mots au dessus de la lettre
	 * @param x la position x de la lettre
	 * @param y la position y de la lettre
	 * @return la chaine de caractère au dessus de la lettre
	 */
	public String checkHaut(int x, int y){
		String tempMot = "";
		int j = 0;
		if(plateau[x][y+1] != null) {
			while(plateau[x][y+j] != null) {
				tempMot += plateau[x][y+j].getLabelCase();
				j++;
			}
			return this.inverseString(tempMot);
		} 
		return "";
	}
	
	/**
	 * Vérifie les mots en dessous de la lettre
	 * @param x la position x de la lettre
	 * @param y la position y de la lettre
	 * @return la chaine de caractère en dessous de la lettre
	 */
	public String checkBas(int x, int y){
		String tempMot = "";
		int j = 0;
		if(plateau[x][y-1] != null) {
			while(plateau[x][y-j] != null) {
				tempMot += plateau[x][y-j].getLabelCase();
				j++;
			}
			return this.inverseString(tempMot);
		} 
		return "";
	}
	
	/**
	 * Vérifie les mots à gauche de la lettre
	 * @param x la position x de la lettre
	 * @param y la position y de la lettre
	 * @return la chaine de caractère à gauche de la lettre
	 */
	public String checkGauche(int x, int y){
		String tempMot = "";
		int j = 0;
		if(plateau[x-1][y] != null) {
			while(plateau[x-j][y] != null) {
				tempMot += plateau[x-j][y].getLabelCase();
				j++;
			}
			return this.inverseString(tempMot);
		} 
		return "";
	}
	
	/**
	 * Vérifie les mots à droite de la lettre
	 * @param x la position x de la lettre
	 * @param y la position y de la lettre
	 * @return la chaine de caractère à droite de la lettre
	 */
	public String checkDroite(int x, int y){
		String tempMot = "";
		int j = 0;
		if(plateau[x+1][y] != null) {
			while(plateau[x+j][y] != null) {
				tempMot += plateau[x+j][y].getLabelCase();
				j++;
			}
			return this.inverseString(tempMot);
		} 
		return "";
	}
	
	//----------------------------------------------------------------------------------
	
	/**
	 * Test du premier mot
	 * @param x La position x de la première lettre
	 * @param y La position y de la première lettre
	 */
	public void checkPremierMot(int x, int y, char orientation, String mot){
		if(plateau[x][y] == null) { //Check si la case est vide
			if(plateau[x][y].getBonus() == 5) { //Check si le bonus est à 5
				this.poserMot(x, y, orientation, mot);
			} else {
				System.out.println("Erreur : Veuillez placer le premier mot au millieu");
			} 
		} else {
			System.out.println("Erreur critique. Relancez le jeu.");
		}
	}
	
	/**
	 * Permet de placer un mot sur le plateau
	 */
	public void poserMot(int x, int y, char orientation, String mot){
		String[] motCut = mot.split("");
		if(this.verification(mot)) {
			if(orientation == 'h') {
				for(int i = 0; i < motCut.length; i++) {
					int tempX = x + i;
					plateau[tempX][y].setLabelCase((motCut[i]).charAt(1));
				}
			} else if (orientation == 'v') {
				for(int i = 0; i < motCut.length; i++) {
					int tempY = x + i;
					plateau[x][tempY].setLabelCase((motCut[i]).charAt(1));
				}
			} else {
				System.out.println("Erreur, vérifier l'orientation");
			}
		} else {
			System.out.println("Mot incorrect");
		}
	}
	
	/**
	 * Inverse un String
	 * @param source la chaine de caractère à inverser
	 * @return la chaine inversée
	 */
	public String inverseString(String source) {
	    int i;
	    int len = source.length();
	    StringBuilder dest = new StringBuilder(len);

	    for (i = (len - 1); i >= 0; i--){
	        dest.append(source.charAt(i));
	    }

	    return dest.toString();
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
	 */
	public void plateauConsole() {
		int j = 0;
		for(int i = 0; i < 15; i++) {
			for(int h = 0; h < 15; h++) {
				System.out.print(plateau[h][j].getBonus() + "|");
			}
			System.out.print("\n");
			j++;
		}
	}
}
