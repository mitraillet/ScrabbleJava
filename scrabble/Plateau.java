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
	
	
	/**
	 * Recherche des mots périphériques é celui placé par le joueur
	 * qui se seraient créés et appel de la méthode vérification
	 */
	public void verificationPeripherique() {
		// nom à changer surement
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
