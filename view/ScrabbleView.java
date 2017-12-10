package view;

import java.util.Observer;

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Plateau;

public abstract class ScrabbleView  implements Observer {
	
	protected Joueur joueur;
	protected ScrabbleController controller;
	protected Plateau plateau;

	/**
	 * Le constructeur par défaut des vues
	 * @param plateau le plateau créant de part ce qu'il contient le tableau graphique 
	 * @param joueur le joueur qui joue
	 * @param controller le controller qui va permettre les vérifications
	 */
	public ScrabbleView(Plateau plateau, Joueur joueur, ScrabbleController controller){

			this.plateau = plateau;
			this.joueur = joueur;
			this.controller = controller;
			//plateau.addObserver(this);
			joueur.addObserver(this);
		}

	/**
	 * La méthode permettant l'affichage des messages quand l'action est impossible
	 * @param string le message à afficher
	 */
		public abstract void affiche(String string) ; {
		// TODO Auto-generated constructor stub
	}

}
