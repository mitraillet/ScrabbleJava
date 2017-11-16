/**
 * Instanciation du joker objet �tendu de Lettre
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
	public void setJoker(char label) {
		this.setLabel(label);
	}
	
	public Joker() {
		super();
	}

}
