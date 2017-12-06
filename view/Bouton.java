package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Bouton extends JButton {
	public Bouton(ImageIcon icon, ImageIcon iconHover) {
		super();
		this.setContentAreaFilled(false); // On met à false pour empêcher le composant de peindre l'intérieur du JButton.
		this.setBorderPainted(false); // De même, on ne veut pas afficher les bordures.
		this.setFocusPainted(false); // On n'affiche pas l'effet de focus.

		this.setIcon(icon);
		this.setRolloverIcon(iconHover);
	}
	
}
