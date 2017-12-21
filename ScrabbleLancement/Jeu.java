<<<<<<< HEAD
/**
 * Package qui gère le lancement du jeu
 */
package ScrabbleLancement;

import java.util.List;
=======
package ScrabbleLancement;

import javax.xml.xpath.XPathExpressionException;
>>>>>>> master

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Plateau;
import scrabble.Sac;
import view.ScrabbleViewGUI;
<<<<<<< HEAD

import view.ScrabbleViewConsole;
import view.ScrabbleViewConsoleLancement;

/**
 * Classe qui lance le jeu
 * @author Fauconnier
 */
public class Jeu {
	
	/**
	 * Initialise la partie
	 */
	public Jeu() {
		
		Plateau plateau = new Plateau();
		Joueur joueur = new Joueur();
		Sac sac = new Sac();

		ScrabbleViewConsoleLancement lancementConsole = new ScrabbleViewConsoleLancement();
		List<Object> paramSocket = lancementConsole.setSocket();
		
		ScrabbleController controller = new ScrabbleController(plateau, joueur, sac, paramSocket);
		ScrabbleViewConsole console = new ScrabbleViewConsole(plateau, joueur, sac, controller);
		ScrabbleViewGUI GUI = new ScrabbleViewGUI(plateau, joueur, sac, controller);
	
		controller.addView(console);
		controller.addView(GUI);
		
		controller.socketRecevoir();
	}
	
	/**
	 * Lance le jeu
	 * @param args inutilisé
	 */
	public static void main(String args[]) {
		new Jeu();
		
=======
//import view.GUISave;

import view.ScrabbleViewConsole;

public class Jeu {

	public Jeu() throws XPathExpressionException {
		// TODO Auto-generated constructor stub
		Plateau plateau = new Plateau();
		Joueur joueur = new Joueur();
		Sac sac = new Sac();
		joueur.pioche(sac);
		
		ScrabbleController controller = new ScrabbleController(plateau, joueur, sac);
		ScrabbleViewConsole console = new ScrabbleViewConsole(plateau, joueur, controller);
		ScrabbleViewGUI GUI = new ScrabbleViewGUI(plateau, joueur, sac, controller);
		
		controller.addView(console);
		controller.addView(GUI);
	}
	public static void main(String args[]) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Jeu();
				} catch (XPathExpressionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
>>>>>>> master
	}
}
