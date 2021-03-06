/**
 * Package view gérant les vues
 */
package view;

import java.util.Observer;

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Plateau;
import scrabble.Sac;

/**
 * Classe abstraite des vues
 * @author Fauconnier/Henriquet
 */
public abstract class ScrabbleView  implements Observer {
	/**
	 * Le joueur
	 */
	protected Joueur joueur;
	
	/**
	 * Le controller
	 */
	protected ScrabbleController controller;
	
	/**
	 * Le plateau de jeu
	 */
	protected Plateau plateau;
	
	/**
	 * Le sac
	 */
	protected Sac sac;

	/**
	 * Le constructeur par défaut des vues
	 * @param plateau le plateau créant de part ce qu'il contient le tableau graphique 
	 * @param joueur le joueur qui joue
	 * @param sac le sac
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
	 * La méthode permettant l'affichage de message
	 * @param string le message à afficher
	 */
	public abstract void affiche(String string);

}
