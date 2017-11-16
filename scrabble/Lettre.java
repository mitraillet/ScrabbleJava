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
<<<<<<< HEAD
	 * Le libellé du caractère
	 */
	private char label;
=======
	 * Le libellÃ© du caractÃ¨re
	 */
	char label;
>>>>>>> 0a29c43b46fce9f32066a17a4a4198a6bb298234
	
	/**
	 * La valeur de la lettre (score)
	 */
<<<<<<< HEAD
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
	 * @param label le label à initialiser
	 */
	public void setLabel(char label) {
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
	 * Constructeur de la classe Lettre
=======
	int valeur;
	
	/**
	 * Constructeur
>>>>>>> 0a29c43b46fce9f32066a17a4a4198a6bb298234
	 * @param label le label de la lettre
	 * @param valeur la valeur de la lettre
	 * @param instance le nombre de fois que la lettre se trouve dans le sac
	 */
<<<<<<< HEAD
	public Lettre(char label, int valeur) {
		this.label = label;
		this.valeur = valeur;
	}
	
	/**
	 * Constructeur par dÃ©faut de la classe Lettre
	 * instancie directement la valeur à 0 
	 * et un charactère invalide pour la création de mot
	 * Ce constructeur n'est utilisé que pour le Joker
	 */
	public Lettre() {
		this('?',0);
=======
	public Lettre(char label, int valeur, int instance) {
		this.label = label;
		this.valeur = valeur;
		
		for(int i = 0; i < instance; i ++) {
			Sac.contenuSac.add(this);
		}
	}
	
	/**
	 * Constructeur par dÃ©faut
	 */
	public Lettre() {
		this.label = 'a';
		this.valeur = 0;
>>>>>>> 0a29c43b46fce9f32066a17a4a4198a6bb298234
	}

}
