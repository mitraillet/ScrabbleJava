/**
 * 
 */
package scrabble;

import javax.xml.xpath.XPathExpressionException;

/**
 * @author Fauconnier
 *
 */
public class JeuTest {

	/**
	 * @param args
	 * @throws XPathExpressionException 
	 */
	public static void main(String[] args) throws XPathExpressionException {
		// TODO Auto-generated method stub
		Sac sac = new Sac();
		//Lettre a = new Lettre('a', 1, 2);
		//Lettre b = new Lettre('b', 1, 2);
		
		sac.recupLettre();
		
		for(int i = 0; i < sac.contenuSac.size(); i++)
	    {
	      System.out.println(i + " = " + sac.contenuSac.get(i));
	    }
		
	}

}
