/**
 * 
 */
package scrabble;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Case {
	
	/**
	 * Objet Lettre à importer  et surtout mettre à jour dans la case
	 */
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
	
	/**
	 * Constructeur de Case par défaut
	 */
	public Case() {
		this.lettre = null;
		// je ne vois pas comment inclure la lettre car elle ne vient pas tout de suite à l'initialisation
		this.bonus = 0;
	}
	/**
	 * Constructeur de Case
	 * @param bonus associé à la case
	 */
	public Case(int bonus) {
		this.lettre = null;
		// je ne vois pas comment inclure la lettre car elle ne vient pas tout de suite à l'initialisation
		this.bonus = bonus;
	}
	/**
	 * @return la lettre contenue dans la case
	 */
	protected Lettre getLettre() {
		return lettre;
	}
	/**
	 * @param lettre l'objet à mettre dans la case 
	 */
	protected void setLettre(Lettre lettre) {
		this.lettre = lettre;
	}
	/**
	 * @return l'intitulé du bonus
	 */
	protected String getBonus() {
		if(bonus == 0) {
			return "Sans bonus";
		}
		else if (bonus == 1) {
			return "Lettre double";
		}
		else if (bonus == 2) {
			return "Lettre triple";
		}
		else if (bonus == 3) {
			return "Mot double";
		}
		else if (bonus == 4) {
			return "Mot triple";
		}
		else if (bonus == 5) {
			return "Premier mot doublé";
		}
		return "Sans bonus";
	}
	/**
	 * @param bonus le chiffre du bonus pour la case
	 */
	protected void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
}
