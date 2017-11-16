<<<<<<< HEAD
/**
 * 
 */
package scrabble;

import java.util.ArrayList;
import java.util.List;

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
		

		System.out.println(sac.getSac().size());
		joueur.pioche(sac);
		for (int i = 0; i < 7; i++) {
			System.out.println(joueur.getLabelLettreMain(i));
		}
		System.out.println(sac.getSac().size());
		System.out.println("---------------------------------------------------------");
		List<Lettre> testChar = new ArrayList<Lettre>(7);
		testChar.add(sac.getSac().get(18));
		System.out.println(sac.getSac().get(18).getLabel());
		joueur.melanger(testChar, sac);
		for (int i = 0; i < 7; i++) {
			System.out.println(joueur.getLabelLettreMain(i));
		}
		System.out.println(sac.getSac().size());
		
		Plateau plateau = new Plateau();
		System.out.println(plateau.verification("mlsqhdfmql"));
		System.out.println(plateau.verification("bete"));
		
	}

}
=======
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
		
		sac.remplissageSac();
		
		for(int i = 0; i < sac.contenuSac.size(); i++)
	    {
	      System.out.println(i + " = " + sac.contenuSac.get(i).label);
	    }
		
	}

}
>>>>>>> 0a29c43b46fce9f32066a17a4a4198a6bb298234
