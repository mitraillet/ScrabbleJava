/**
 * 
 */
package scrabble;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Joker extends Lettre {

	/**
	 * Permet de définir la valeur du libellé
	 */
	public void setJoker() {
		this.label = 'h';
	}
	
	public Joker(char nom, int valeur, int instance) {
		super();
		this.valeur = 0;
	}

}
