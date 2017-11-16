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
 *
 */
public class Plateau {

	public HashSet<String> dictionnaire = new HashSet<String>();
	
	public Case[][] plateau;
	
	public Plateau() throws XPathExpressionException {
		this.construireDico();
		this.plateau = new Case [15][15];
		this.initPlateau();
	}
	
	public void initCasePlateau(Case caseSet, int x, int y) {
		this.plateau[x][y] = caseSet;
	}
	
	/**
	 * Construis le dictionnaire sur base du fichier dictionnaire.txt
	 */
	private void construireDico() {
		try {
			File dico = new File("ressource/dictionnaire.txt"); //Path du dictionnaire.txt
			BufferedReader br = new BufferedReader(new FileReader(dico)); //CrÃ©tation du buffer
			String line; //Variable pour les lignes
			while ((line = br.readLine()) != null) { //Pour chaque ligne Ã©xÃ©cute la boucle
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
	
	
	private void initPlateau() throws XPathExpressionException {
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      factory.setIgnoringElementContentWhitespace(true);

	      try {
	         //MÃ©thode qui permet d'activer la vÃ©rification du fichier
	         factory.setValidating(true);
	         
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         
	         //crÃ©ation de notre objet d'erreurs
	         ErrorHandler errHandler = new SimpleErrorHandler();
	         //Affectation de notre objet au document pour interception des erreurs Ã©ventuelles
	         builder.setErrorHandler(errHandler);
	         
	         // Parsing d'un XML via une URI
	         String uri = "./ressource/dataCase.xml";
	         
	         //On rajoute un bloc de capture
	         //pour intercepter les erreurs au cas oÃ¹ il y en a
	         try {
	            //Document xml = builder.parse(fileXML);
	        	 	Document xml = builder.parse(uri);
	            Element root = xml.getDocumentElement();
	            
	            System.out.println(root.getNodeName());
	            
	            XPathFactory xpf = XPathFactory.newInstance();
	            XPath path = xpf.newXPath();
	            
	            int j = 1;  //Variable pour incrÃ©menter les lignes
	            int c = 0; //Variable pour incrÃ©menter les cases
	            
	            for (int i = 1; i < 226; i++){
	            		c++;
	            	
	            		String expressionX = "/plateau/ligne[" + j + "]/case[" + c + "]/x";
	            		String expressionY = "/plateau/ligne[" + j + "]/case[" + c + "]/y";
	            		String expressionBonus = "/plateau/ligne[" + j + "]/case[" + c + "]/bonus";
	            		
	            		int x = ((Double)path.evaluate(expressionX, root, XPathConstants.NUMBER)).intValue();
	            		
	            		int y = ((Double)path.evaluate(expressionY, root, XPathConstants.NUMBER)).intValue();
	            		
	            		int bonus = ((Double)path.evaluate(expressionBonus, root, XPathConstants.NUMBER)).intValue();
	            		
	            		Case caseNouvelle = new Case(bonus);
	            		this.initCasePlateau(caseNouvelle, x, y);	            	
	            		
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
	 * ItÃ©ration d'un dictionnaire pour trouver concordance 
	 * avec le mot entrÃ© par le joueur et vÃ©rifier s'il existe
	 * @return true si mot est dans le dictionnaire sinon false
	 */
	public boolean verification(String mot) {
		return dictionnaire.contains(mot);
	}
	
	
	/**
	 * Recherche des mots pÃ©riphÃ©riques Ã© celui placÃ© par le joueur
	 * qui se seraient crÃ©Ã©s et appel de la mÃ©thode vÃ©rification
	 */
	public void verificationPeripherique() {
		// nom Ã  changer surement
	}
	
	
	/**
	 * Calcul du score en prenant compte les bonus
	 * Utilisation des valeurs de chaques lettres pondÃ©rÃ©es avec le bonus
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
