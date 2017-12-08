package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 
 * @author Fauconnier/Henriquet
 * Classe ayant permit la création de bouton personnalisé
 */
public class Bouton extends JButton {
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
