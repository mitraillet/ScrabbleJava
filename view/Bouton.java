package view;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Bouton extends JButton {
	public Bouton(ImageIcon icon, ImageIcon iconHover) {
		super();
		this.setForeground(Color.WHITE);
		this.setOpaque(false);
		this.setContentAreaFilled(false); // On met à false pour empêcher le composant de peindre l'intérieur du JButton.
		this.setBorderPainted(false); // De même, on ne veut pas afficher les bordures.
		this.setFocusPainted(false); // On n'affiche pas l'effet de focus.
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setIcon(icon);
		this.setRolloverIcon(iconHover);
		//Grâce à cette instruction, notre objet va s'écouter
	    //Dès qu'un événement de la souris sera intercepté, il en sera averti
	    //this.addMouseListener(this);
	}
	
}
