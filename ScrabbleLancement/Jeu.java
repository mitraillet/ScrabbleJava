package ScrabbleLancement;

import java.io.IOException;
import java.util.List;

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Plateau;
import scrabble.Sac;
import view.ScrabbleViewGUI;

import view.ScrabbleViewConsole;
import view.ScrabbleViewConsoleLancement;

public class Jeu {
	
	public Jeu() throws IOException {
		
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
	
	public static void main(String args[]) throws IOException {
		new Jeu();
		
	}
}
