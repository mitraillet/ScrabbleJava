package ScrabbleLancement;

import java.io.IOException;

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Plateau;
import scrabble.Sac;
import view.ScrabbleViewGUI;

import view.ScrabbleViewConsole;

public class Jeu {
	
	public Jeu(boolean estServeur) throws IOException {
		// TODO Auto-generated constructor stub
		
		
		Plateau plateau = new Plateau();
		Joueur joueur = new Joueur(0, estServeur);
		Sac sac = new Sac();
		
		gestionSocket socket = new gestionSocket(plateau, sac);
		socket.setSocket(estServeur, 12345, "localhost");
		
		//Les 2 joueurs piochent leurs premières lettres dans le même sac.
		if(estServeur) {
			joueur.pioche(sac);
			socket.envoyerDonnee(joueur, plateau, sac);
			socket.recevoirSac(joueur);
		} else {
			socket.recevoirSac(joueur);
			joueur.pioche(sac);
			socket.envoyerDonnee(joueur, plateau, sac);
		}
		
		ScrabbleController controller = new ScrabbleController(plateau, joueur, sac, socket);
		ScrabbleViewConsole console = new ScrabbleViewConsole(plateau, joueur, controller);
		ScrabbleViewGUI GUI = new ScrabbleViewGUI(plateau, joueur, sac, controller);
		
		controller.addView(console);
		controller.addView(GUI);
		
		socket.recevoirDonnee(joueur);
	}
	
	public static void main(String args[]) throws IOException {
		new Jeu(args[0].equals("true")? true : false);
	}
}
