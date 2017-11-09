/**
 * 
 */
package scrabble;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Sac {
	
	//private Lettre[] sac = new Lettre[102];
	static List<Lettre> contenuSac = new ArrayList<Lettre>();
	
	/**
	 * @return le sac
	 */
	//public Lettre[] getSac() {
	public List<Lettre> getSac() {
		return contenuSac;
	}
	/**
	 * @param ajoute les lettres au sac 
	 */
	/*public void setSac(Lettre[] sac) {
	public List<Lettre> setSac() {
		this.sac = sac;
	}*/
	
	/**
	 * Méthode lié é la méthode pioche du joueur 
	 * en vue de lui compléter sa main ou de la lui remplir complétement
	 */
	public void pioche() {
		/*
		 * ajout de lettres dans la main de joueur de tel sorte que nmbre de lettre
		 * dans sa main soit égal é 0 et pioche de lettre random
		 */
	}
	/**
	 * Méthode en vue du remplissage du sac avec un nombre cohérent des différentes lettres
	 * utilisation de ... pour le remplissage
	 */
	public void remplissageSac(Lettre l) {
		/*
		 * chercher comment remplir automatique le sac
		 */
		contenuSac.add(l);
	}
	/**
	 * Méthode lié a la méthode remelange de joueur
	 * pour pouvoir changer certaines lettres
	 * Appel de la méthode pioche
	 */
	public void melangeMain() {
		
	}

	public static void recupLettre() throws XPathExpressionException {
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
	         String uri = "./ressource/dataLettre.xml";
	         
	         //On rajoute un bloc de capture
	         //pour intercepter les erreurs au cas où il y en a
	         try {
	            //Document xml = builder.parse(fileXML);
	        	 	Document xml = builder.parse(uri);
	            Element root = xml.getDocumentElement();
	            
	            System.out.println(root.getNodeName());
	            
	            XPathFactory xpf = XPathFactory.newInstance();
	            XPath path = xpf.newXPath();
	             
	            for (int i = 1; i < 27; i++){
	            		String expressionNom = "/alphabet/lettre[" + i + "]/nom";
	            		String expressionValeur = "/alphabet/lettre[" + i + "]/valeur";
	            		String expressionInstance = "/alphabet/lettre[" + i + "]/instance";
	            		//String str = (String)path.evaluate(expressionNom, root);
	            		//System.out.println(str);
	            		
	            		String nomLettre = (String)path.evaluate(expressionNom, root);
	            		//System.out.println(nomLettre);
	            		
	            		int valeurLettre = ((Double)path.evaluate(expressionValeur, root, XPathConstants.NUMBER)).intValue();
	            		//System.out.println(valeurLettre);
	            		
	            		int instanceLettre = ((Double)path.evaluate(expressionInstance, root, XPathConstants.NUMBER)).intValue();
	            		//System.out.println(instanceLettre);
	            		
	            		
	            		Lettre lettre = new Lettre(nomLettre, valeurLettre, instanceLettre);
	            		
	            }
	            
	            System.out.println();
	               
	            
	         } catch (SAXParseException e){}
	         
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }      
	   }

	
	public static void main(String[] args) throws XPathExpressionException {
		
	}
	
}


