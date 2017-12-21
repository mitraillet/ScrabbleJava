<<<<<<< HEAD
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
=======
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
>>>>>>> master
	}
	
}
