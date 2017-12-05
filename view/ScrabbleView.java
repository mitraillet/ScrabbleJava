package view;

import java.util.Observer;

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Plateau;

public abstract class ScrabbleView  implements Observer {
	
	protected Joueur joueur;
	protected ScrabbleController controller;
	protected Plateau plateau;

	public ScrabbleView(Plateau plateau, Joueur joueur, ScrabbleController controller){

			this.plateau = plateau;
			this.joueur = joueur;
			this.controller = controller;
			//plateau.addObserver(this);
			joueur.addObserver(this);
		}

		public abstract void affiche(String string) ; {
		// TODO Auto-generated constructor stub
	}

}
