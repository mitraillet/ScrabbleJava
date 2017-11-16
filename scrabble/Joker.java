<<<<<<< HEAD
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
	public void setJoker(char label) {
		this.setLabel(label);
	}
	
	public Joker() {
		super();
	}

}
=======
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
>>>>>>> 0a29c43b46fce9f32066a17a4a4198a6bb298234
