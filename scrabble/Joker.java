/**
 * Instanciation du joker objet étendu de Lettre
 */
package scrabble;

import java.io.Serializable;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Joker extends Lettre implements Serializable {

	/**
	 * Permet de définir la valeur du label du joker 
	 * et ce seulement si c'est une lettre
	 * @param label de la lettre de type String pour plus de flexibilité
	 */
	public void setJoker(char label) {
			this.setLabel(label);
	}
	
	public Joker() {
		super();
	}

}
