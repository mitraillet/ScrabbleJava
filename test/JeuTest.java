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
		Joueur joueur = new Joueur();
		//Lettre a = new Lettre('a', 1, 2);
		//Lettre b = new Lettre('b', 1, 2);
		
		sac.remplissageSac();
		
		 /*for(int i = 0; i < sac.contenuSac.size(); i++)
		    {
		      System.out.println(i + " = " + sac.contenuSac.get(i).label);
		    }
		 
		 joueur.pioche();
		 
		 for(int i = 0; i < joueur.mainJoueur.size(); i++)
		    {
		      System.out.println(i + " = " + joueur.mainJoueur.get(i).label);
		    }
		 
		 for(int i = 0; i < sac.contenuSac.size(); i++)
		    {
		      System.out.println(i + " = " + sac.contenuSac.get(i).label);
		    }*/
		

		System.out.println(sac.contenuSac.size());
		joueur.pioche();
		for (int i = 0; i < 7; i++) {
			System.out.println(joueur.mainJoueur.get(i).label);
		}
		System.out.println(sac.contenuSac.size());
		System.out.println("---------------------------------------------------------");
		joueur.melanger();
		for (int i = 0; i < 7; i++) {
			System.out.println(joueur.mainJoueur.get(i).label);
		}
		System.out.println(sac.contenuSac.size());
		
	}

}
