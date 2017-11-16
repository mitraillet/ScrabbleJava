/**
 * Classe case
 */
package scrabble;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Case {
	
	/**
	 * Contient la lettre "posée" sur la case
	 */
	protected Lettre lettre;
	
	/** 
	 * Définis le bonus de la case, selon le code suivant :
	 *  0 = sans bonus
	 *  1 = lettre double
	 *  2 = lettre triple
	 *  3 = mot double
	 *  4 = mot triple
	 *  5 = (unique) la case du millieu, où doit passer le premier mot
	 */
	private int bonus;	
	
	
	/**
	 * @return lettre la lettre contenue dans la case
	 */
	private Lettre getLettre() {
		return lettre;
	}
	
	/**
	 * @param lettre l'objet lettre à mettre dans la case 
	 */
	protected void setLettre(Lettre lettre) {
		this.lettre = lettre;
	}
	
	/**
	 * @return bonus l'indice du bonus 
	 */
	public int getBonus() {
		return this.bonus;
	}
	
	/**
	 * @param bonus le chiffre du bonus pour la case
	 */
	protected void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	
	
	/**
	 * Constructeur de l'objet Case par défaut (sans paramètre)
	 */
	public Case() {
		this(0);
	}
	
	/**
	 * Constructeur de l'objet Case
	 * @param bonus le bonus associé à la case
	 */
	public Case(int bonus) {
		this.lettre = null;
		this.bonus = bonus;
	}
	
}