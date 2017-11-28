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
	 * Renvoie la lettre contenue dans la case
	 * @return lettre la lettre contenue dans la case
	 */
	public Lettre getLettre() {
		return lettre;
	}
	
	/**
	 * Ajouter/Mettre à jour la lettre contenue dans la case
	 * @param lettre l'objet lettre à mettre dans la case 
	 */
	public void setLettre(Lettre lettre) {
		this.lettre = lettre;
	}
	
	/**
	 * Renvoie l'indice du bonus de la case
	 * @return l'indice du bonus 
	 */
	public int getBonus() {
		return this.bonus;
	}
	
	/**
	 * Mettre à jour le bonus de la cqse
	 * @param bonus le chiffre du bonus pour la case
	 */
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	/**
	 * Renvoie le label de la lettre contenue dans la case
	 * @return le label de la lettre contenue dans la case
	 */
	public char getLabelCase() {
		return this.lettre.getLabel();
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
	
	/**
	 * Constructeur de l'objet Case
	 * @param bonus le bonus associé à la case
	 * @param lettre la lettre associé à la case
	 */
	public Case(int bonus, Lettre lettre) {
		this.lettre = lettre;
		this.bonus = bonus;
	}
	
	
}