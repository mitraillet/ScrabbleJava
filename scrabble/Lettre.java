/**
 * Package Modèle
 */
package scrabble;

import java.io.Serializable;

/**
 * Classe permettant la création des Pièces
 * du scrabble représentant les lettres 
 * et leur valeur associée
 * @author Fauconnier/Henriquet
 */
public class Lettre implements Serializable {

	/**
	 * Numéro de version de la classe
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Le libellé du caractère
	 */
	private char label;
	
	/**
	 * La valeur de la lettre (score)
	 */
	private int valeur;
	
	/**
	 * Méthode retournant le label
	 * utiliser pour retourné le label de l'objet
	 * @return la label
	 */
	public char getLabel() {
		return label;
	}

	/**
	 * Méthode permettant d'attribuer un label 
	 * utiliser pour attribuer au joker un label
	 * donc mis en protected pour empêcher le changement 
	 * d'une lettre autre que d'un joker 
	 * @param label le label à initialiser
	 */
	protected void setLabel(char label) {
		this.label = label;
	}

	/**
	 * Méthode retournant la valeur de la Lettre 
	 * utiliser pour le calcul des points
	 * @return la valeur
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * Constructeur par dÃ©faut de la classe Lettre
	 * instancie directement la valeur à 0 
	 * et un charactère invalide pour la création de mot
	 * Ce constructeur n'est utilisé que pour le Joker
	 */
	public Lettre() {
		this('?',0);
	}

	/**
	 * Constructeur de la classe Lettre
	 * @param label le label de la lettre
	 * @param valeur la valeur de la lettre
	 */
	public Lettre(char label, int valeur) {
		this.label = label;
		this.valeur = valeur;
	}
}
