/**
 * Package Modèle
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
	
<<<<<<< HEAD
	/**
	 * Collection contenant l'entièreté des objets Lettres du scrabble à l'initialisation
	 */
	private List<Lettre> contenuSac;
=======
	private final List<Lettre> contenuSac;
>>>>>>> master
	
	/**
	 * Retourne le sac
	 * @return le sac
	 */
	public List<Lettre> getSac() {
		return contenuSac;
	}
	
	/**
<<<<<<< HEAD
	 * Actualise le contenu du sac
	 * @param sac le nouveau contenu du sac
	 */
	public void setSac(List<Lettre> sac) {
		this.contenuSac = sac;
	}
	
	/**
=======
>>>>>>> master
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
	 */
	public Sac() {
		this.contenuSac = new ArrayList<Lettre>();
		this.remplissageSac();
		Joker joker1 = new Joker();
		Joker joker2 = new Joker();
		contenuSac.add(joker1);
		contenuSac.add(joker2);	
	}

	/**
<<<<<<< HEAD
	 * Récupère les donnees des lettres dans le fichier dataLettre.XML et remplis la variable sac
=======
	 * Récupère les donnees des lettre dans le fichier dataLettre.XML et remplis la variable sac
>>>>>>> master
	 */
	private void remplissageSac() {
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      factory.setIgnoringElementContentWhitespace(true);

	      try {
	         //Méthode qui permet d'activer la vérification du fichier
	         factory.setValidating(true);
	         
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         
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
	            		
	            		char labelLettre = '/';
	            		int valeurLettre = 0;
	            		int instanceLettre = 0;
	            		
	            		try {
	            			labelLettre = ((String)path.evaluate(expressionlabel, root)).charAt(0);
	            		
	            			valeurLettre = ((Double)path.evaluate(expressionValeur, root, XPathConstants.NUMBER)).intValue();
	            		
	            			instanceLettre = ((Double)path.evaluate(expressionInstance, root, XPathConstants.NUMBER)).intValue();
	            			
	            		} catch (XPathExpressionException e) {
	            			System.out.println("Erreur Système : vérifier ressource sac");
	            		} 
	            		
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
<<<<<<< HEAD
	
	/**
	 * Affiche le nombre de lettres restantes dans le sac
	 */
	public String toString() {
		return "Il reste " + this.tailleContenuSac() + " lettre(s) dans le sac";
	}
=======
>>>>>>> master

}