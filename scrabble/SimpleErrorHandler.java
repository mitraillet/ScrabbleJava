/**
 * 
 */
package scrabble;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;



/**
 * @author Fauconnier
 * Erreur de lecture XML
 */
public class SimpleErrorHandler implements ErrorHandler { 
    
    //JAXP = Java API for XML Processing
    
    /**
    * Récupère les avertissements lors de la lecture d'un XML
    * @param e exception du package JAXP
    * @throws erreur du package JAXP
    */
    public void warning(SAXParseException e) throws SAXException {
        System.out.println("WARNING : " + e.getMessage());
    }

    /**
    * Récupère les erreurs lors de la lecture d'un XML
    * @param e exception du package JAXP
    * @throws erreur du package JAXP
    */
    public void error(SAXParseException e) throws SAXException {
        System.out.println("ERROR : " + e.getMessage());
        throw e;
    }

    /**
    * Récupère les erreurs amenant au crash, lors de la lecture d'un XML
    * @param e exception du package JAXP
    * @throws erreur du package JAXP
    */
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("FATAL ERROR : " + e.getMessage());
        throw e;
    }
}
