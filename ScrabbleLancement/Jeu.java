package ScrabbleLancement;

import java.io.IOException;
import java.util.Scanner;

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Plateau;
import scrabble.Sac;
import view.ScrabbleViewGUI;

import view.ScrabbleViewConsole;

public class Jeu {
	
	public Jeu(boolean estServeur, String addr) throws IOException {
		
		Plateau plateau = new Plateau();
		Joueur joueur = new Joueur(0, estServeur);
		Sac sac = new Sac();
		
		gestionSocket socket = new gestionSocket(plateau, sac);
		socket.setSocket(estServeur, 12345, addr);
		
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
		ScrabbleViewConsole console = new ScrabbleViewConsole(plateau, joueur, sac, controller);
		ScrabbleViewGUI GUI = new ScrabbleViewGUI(plateau, joueur, sac, controller);
		
		controller.addView(console);
		controller.addView(GUI);
		
		socket.recevoirDonnee(joueur, plateau, sac);
	}
	
	public static void main(String args[]) throws IOException {
		//new Jeu(args[0].equals("true")? true : false);

		Scanner scan = new Scanner(System.in);
		String bool;
		Boolean okServeur = false;
		Boolean estServeur;
		String ip = "localhost";
		
		while(!okServeur) {
			System.out.println("Taper true pour vous connectez en tant que serveur, false pour le client");
			bool = scan.next();
			if(bool.equals("true")) {
				System.out.println("Vous êtes le serveur");
				okServeur = true;
				estServeur = true;
				new Jeu(estServeur, ip);
			
			} else if(bool.equals("false")) {
				System.out.println("Vous êtes le client");
				okServeur = true;
				estServeur = false;
				while(true) {
					System.out.println("Veuillez entrer l'adresse du serveur : ");
					ip = scan.next();
					System.out.println("Tentative de connexion au serveur...");
					break;
				}
				new Jeu(estServeur, ip);
			
			} else {
				System.out.println("Commande non reconnue...");
				okServeur = false;
			}
		}
		
	}
}
