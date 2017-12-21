/**
 * Package view gérant les vues
 */
package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Classe ayant permit la création de bouton personnalisé
 * @author Fauconnier/Henriquet
 */
public class Bouton extends JButton {
	
	/**
	 * Numéro de version de la classe
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur du bouton personnalisé
	 * @param icon l'icône du bouton
	 * @param iconHover l'icône survolée
	 * @param disabledIcon l'icône désactivée
	 */
	public Bouton(ImageIcon icon, ImageIcon iconHover, ImageIcon disabledIcon) {
		super();
		this.setContentAreaFilled(false); // On met à false pour empêcher le composant de peindre l'intérieur du JButton.
		this.setBorderPainted(false); // De même, on ne veut pas afficher les bordures.
		this.setFocusPainted(false); // On n'affiche pas l'effet de focus.
		this.setDisabledIcon(disabledIcon);
		this.setIcon(icon);
		this.setRolloverIcon(iconHover);
	}
	
}
