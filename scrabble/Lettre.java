/**
 * Classe permettant la cr�ation des Pi�ces
 * du scrabble repr�sentant les lettres 
 * er leur valeur associ�e
 */
package scrabble;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Lettre {

	/**
	 * Le libell� du caract�re
	 */
	private char label;
	
	/**
	 * La valeur de la lettre (score)
	 */
	private int valeur;
	
	/**
	 * M�thode retournant le label
	 * utiliser pour retourn� le label de l'objet
	 * @return la label
	 */
	public char getLabel() {
		return label;
	}

	/**
	 * M�thode permettant d'attribuer un label 
	 * utiliser pour attribuer au joker un label
	 * donc mis en protected pour emp�cher le changement 
	 * d'une lettre autre que d'un joker 
	 * @param label le label �initialiser
	 */
	protected void setLabel(char label) {
		this.label = label;
	}

	/**
	 * M�thode retournant la valeur de la Lettre 
	 * utiliser pour le calcul des points
	 * @return la valeur
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * Constructeur par défaut de la classe Lettre
	 * instancie directement la valeur � 0 
	 * et un charact�re invalide pour la cr�ation de mot
	 * Ce constructeur n'est utilis� que pour le Joker
	 */
	public Lettre() {
		this('?',0);
	}

	/**
	 * Constructeur de la classe Lettre
	 * @param label le label de la lettre
	 * @param valeur la valeur de la lettre
	 * @param instance le nombre de fois que la lettre se trouve dans le sac
	 */
	public Lettre(char label, int valeur) {
		this.label = label;
		this.valeur = valeur;
	}
}
