/**
 * 
 */
package scrabble;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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
	private final List<Lettre> contenuSac;
	
	/**
	 * @return le sac
	 */
	//public Lettre[] getSac() {
	public List<Lettre> getSac() {
		return contenuSac;
	}
	/**
	 * @param ajoute les lettres au sac 
	 * @throws XPathExpressionException 
	 */
	public Sac() throws XPathExpressionException {
		this.contenuSac = new ArrayList<Lettre>();
		this.remplissageSac();
		Joker joker1 = new Joker();
		Joker joker2 = new Joker();
		contenuSac.add(joker1);
		contenuSac.add(joker2);
		
	}
	
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
	 * Méthode lié a la méthode remelange de joueur
	 * pour pouvoir changer certaines lettres
	 * Appel de la méthode pioche
	 */
	public void melangeMain() {
		
	}

	/**
	 * Récupère les donnees des lettre dans le fichier dataLettre.XML et remplis la variable sac
	 * @throws XPathExpressionException
	 */
	private void remplissageSac() throws XPathExpressionException {
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
	            		String expressionlabel = "/alphabet/lettre[" + i + "]/label";
	            		String expressionValeur = "/alphabet/lettre[" + i + "]/valeur";
	            		String expressionInstance = "/alphabet/lettre[" + i + "]/instance";
	            		//String str = (String)path.evaluate(expressionlabel, root);
	            		//System.out.println(str);
	            		
	            		char labelLettre = ((String)path.evaluate(expressionlabel, root)).charAt(0);
	            		//System.out.println(labelLettre);
	            		
	            		int valeurLettre = ((Double)path.evaluate(expressionValeur, root, XPathConstants.NUMBER)).intValue();
	            		//System.out.println(valeurLettre);
	            		
	            		int instanceLettre = ((Double)path.evaluate(expressionInstance, root, XPathConstants.NUMBER)).intValue();
	            		//System.out.println(instanceLettre);
	            		Lettre addLettre = new Lettre(labelLettre, valeurLettre);
	            		for(int j = 0; j < instanceLettre; j ++) {
	            			contenuSac.add(addLettre);
	            		}
	            		
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

	
}


