package ScrabbleLancement;

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Plateau;
import scrabble.Sac;
import view.ScrabbleViewGUI;

import view.ScrabbleViewConsole;

public class Jeu {

	public Jeu() {
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
		new Jeu();
	}
}
