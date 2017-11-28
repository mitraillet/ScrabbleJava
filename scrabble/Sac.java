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
 * Sac contenant les Lettres
 */
public class Sac {
	
	private final List<Lettre> contenuSac;
	
	/**
	 * Retourne le sac
	 * @return le sac
	 */
	public List<Lettre> getSac() {
		return contenuSac;
	}
	
	/**
	* Retourne une lettre du sac
	* @param positionSac la position de la lettre a récuperer
	* @return la lettre selon la position
	*/
	public Lettre getPositionLettreDansSac(int positionSac) {
		return this.contenuSac.get(positionSac);
	}
	
	/**
	* Supprime une lettre du sac
	* @param positionSac la position de la lettre a supprimer
	*/
	public void removeLettreDuSac(int positionSac) {
		this.contenuSac.remove(positionSac);
	}
	
	/**
	* Ajoute une lettre dans le sac (a la fin de la liste)
	* @param lettre l'objet Lettre a ajouter au sac
	*/
	public void addLettreAuSac(Lettre lettre) {
		this.contenuSac.add(lettre);
	}
	
	/**
	* Retourne la taille du sac (nombre de lettre)
	* @return le nombre de lettre dans le sac
	*/
	public int tailleContenuSac() {
		return this.contenuSac.size();
	}
	
	/**
	 * Constructeur par défaut + initialisation du contenu du sac
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
	         
	         //Création de notre objet d'erreurs
	         ErrorHandler errHandler = new SimpleErrorHandler();
	         //Affectation de notre objet au document pour interception des erreurs éventuelles
	         builder.setErrorHandler(errHandler);
	         
	         // Parsing d'un XML via une URI
	         String uri = "./ressource/dataLettre.xml";
	         
	         //On rajoute un bloc de capture
	         //pour intercepter les erreurs au cas où il y en a
	         try {
		    //Récupère le fichier
	            Document xml = builder.parse(uri);
		    //Récupère l'élément racine du fichier 
	            Element root = xml.getDocumentElement();
	            
		    //Instancie xpath, qui permet de lire le fichier 
	            XPathFactory xpf = XPathFactory.newInstance();
	            XPath path = xpf.newXPath();
	             
	            for (int i = 1; i < 27; i++){
			    	//Pour chaque noeud case, récupère la label, la valeur et l'instancie
	            		String expressionlabel = "/alphabet/lettre[" + i + "]/label";
	            		String expressionValeur = "/alphabet/lettre[" + i + "]/valeur";
	            		String expressionInstance = "/alphabet/lettre[" + i + "]/instance";
	            		
	            		char labelLettre = ((String)path.evaluate(expressionlabel, root)).charAt(0);
	            		
	            		int valeurLettre = ((Double)path.evaluate(expressionValeur, root, XPathConstants.NUMBER)).intValue();
	            		
	            		int instanceLettre = ((Double)path.evaluate(expressionInstance, root, XPathConstants.NUMBER)).intValue();
	            		
	            		Lettre addLettre = new Lettre(labelLettre, valeurLettre);
	            		
			    	//Ajoute chaque lettre dans le sac
	            		for(int j = 0; j < instanceLettre; j ++) {
	            			contenuSac.add(addLettre);
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

}