/**
 * Instanciation du joker objet étendu de Lettre
 */
package scrabble;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Joker extends Lettre {

	/**
	 * Permet de dÃ©finir la valeur du libellÃ©
	 */
	public void setJoker(char label) {
		this.setLabel(label);
	}
	
	public Joker() {
		super();
	}

}
