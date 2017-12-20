/**
 * Package qui gère le lancement du jeu
 */
package ScrabbleLancement;

import java.util.List;

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Plateau;
import scrabble.Sac;
import view.ScrabbleViewGUI;

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
		
	}
}
