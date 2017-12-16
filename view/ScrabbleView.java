package view;

import java.util.Observer;

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Plateau;
import scrabble.Sac;

public abstract class ScrabbleView  implements Observer {
	
	protected Joueur joueur;
	protected ScrabbleController controller;
	protected Plateau plateau;
	protected Sac sac;

	/**
	 * Le constructeur par défaut des vues
	 * @param plateau le plateau créant de part ce qu'il contient le tableau graphique 
	 * @param joueur le joueur qui joue
	 * @param controller le controller qui va permettre les vérifications
	 */
	public ScrabbleView(Plateau plateau, Joueur joueur, Sac sac, ScrabbleController controller){

			this.plateau = plateau;
			this.joueur = joueur;
			this.controller = controller;
			this.sac = sac;
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
