/**
 * 
 */
package scrabble;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Case {
	
	private Lettre lettre;
	/** bonus serait un chiffre entre 0 et 5 pour chaque "bonus" 
	 *  0 = sans bonus
	 *  1 = lettre double
	 *  2 = lettre triple
	 *  3 = mot double
	 *  4 = mot triple
	 *  5 = unique la première case par où doit passer le premier mot
	 */
	private int bonus;
	
	public Case(int bonus) {
		this.lettre = null;
		// je ne vois pas comment inclure la lettre car elle ne vient pas tout de suite à l'initialisation
		this.bonus = bonus;
	}

}
