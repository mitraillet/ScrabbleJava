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
	 * Le libell� du caract�re
	 */
	private char label;
=======
	 * Le libellé du caractère
	 */
	char label;
>>>>>>> 0a29c43b46fce9f32066a17a4a4198a6bb298234
	
	/**
	 * La valeur de la lettre (score)
	 */
<<<<<<< HEAD
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
	 * @param label le label �initialiser
	 */
	public void setLabel(char label) {
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
	 * Constructeur par défaut de la classe Lettre
	 * instancie directement la valeur � 0 
	 * et un charact�re invalide pour la cr�ation de mot
	 * Ce constructeur n'est utilis� que pour le Joker
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
	 * Constructeur par défaut
	 */
	public Lettre() {
		this.label = 'a';
		this.valeur = 0;
>>>>>>> 0a29c43b46fce9f32066a17a4a4198a6bb298234
	}

}
