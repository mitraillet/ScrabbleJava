/**
<<<<<<< HEAD
 * Package Modèle
=======
 * Instanciation du joker objet étendu de Lettre
>>>>>>> master
 */
package scrabble;

import java.io.Serializable;

/**
 * Instanciation du joker objet étendu de Lettre
 * @author Fauconnier/Henriquet
 */
public class Joker extends Lettre implements Serializable {

	/**
<<<<<<< HEAD
	 * Numéro de version de la classe
	 */
	private static final long serialVersionUID = 1L;
	
	/**
=======
>>>>>>> master
	 * Permet de définir la valeur du label du joker 
	 * et ce seulement si c'est une lettre
	 * @param label de la lettre de type String pour plus de flexibilité
	 */
<<<<<<< HEAD
	public void setJoker(char label) {
			this.setLabel(label);
=======
	public void setJoker(String labelString) {
		char labelChar;
		labelString = labelString.toLowerCase();
		String abc = "azertyuiopqsdfghjklmwxcvbn";
		if(abc.contains(labelString)) {
			labelChar = labelString.charAt(0);
			this.setLabel(labelChar);
		}
		else {
			System.out.println("Caractère non authorisé !");
		}
>>>>>>> master
	}
	
	/**
	 * Constructeur par défaut, hérité de Lettre
	 */
	public Joker() {
		super();
	}

}
