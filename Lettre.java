/**
 * 
 */
package scrabble;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Lettre {

	/**
	 * Le libellé du caractère
	 */
	private char label;
	
	/**
	 * La valeur de la lettre (score)
	 */
	private int valeur;
	
	/**
	 * @return la label
	 */
	public char getLabel() {
		return label;
	}

	/**
	 * @param label le label à initialiser
	 */
	public void setLabel(char label) {
		this.label = label;
	}

	/**
	 * @return la valeur
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * @param valeur la valeur à initialiser
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	/**
	 * Constructeur
	 * @param label le label de la lettre
	 * @param valeur la valeur de la lettre
	 * @param instance le nombre de fois que la lettre se trouve dans le sac
	 */
	public Lettre(char label, int valeur, int instance) {
		this.label = label;
		this.valeur = valeur;
		
		for(int i = 0; i < instance; i ++) {
			Sac.getSac().add(this);
		}
	}
	
	/**
	 * Constructeur par défaut
	 */
	public Lettre() {
		this.label = 'a';
		this.valeur = 0;
	}

}
