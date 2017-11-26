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
		
		System.out.println("---------------------------------------------------------");
		
		Lettre a = new Lettre('a', 1);
		Lettre u = new Lettre('u', 1);
		Lettre t = new Lettre('t', 1);
		Lettre x = new Lettre('x', 10);
		Lettre b = new Lettre('b', 1);
		Lettre e = new Lettre('e', 11);
		
		plateau.motJoue.add(u);
		//plateau.motJoue.add(b);
		//plateau.motJoue.add(e);
		
		plateau.plateau[7][7].setLettre(a);
		plateau.plateau[8][8].setLettre(t);
		plateau.plateau[9][7].setLettre(x);
		//plateau.verificationPeripherique(8, 7, 'v');
		
		System.out.println("---------------------------------------------------------");
		
		Dictionnaire dictionnaireMot = new Dictionnaire();
		System.out.println(dictionnaireMot.verification("bonjour"));
		
		System.out.println("---------------------------------------------------------");
		
		joueur.pioche(sac);
		System.out.println(joueur.mainJoueur.size());
		joueur.melanger(testChar, sac);
		System.out.println(joueur.mainJoueur.size());
		
	}
}
