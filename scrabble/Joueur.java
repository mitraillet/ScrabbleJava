/**
 * 
 */
package scrabble;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Joueur {

	/**
	 * Score total du joueur
	 */
	protected int score;
	
	/**
	 * True si c'est le tour du joueur, sinon False
	 */
	protected boolean doitJouer = false;
	
	/**
	 * Tableau de Lettre représentant les Lettres du joueur
	 */
	protected Lettre[] mainJoueur = new Lettre[3];
	
	/**
	 * @param actualise le score du joueur
	 */
	public void setScore(int score) {
		this.score = score;
	}

	
	/**
	 * Constructeur du joueur
	 * @param score le score du joueur
	 * @param tour initialise le tour du joueur
	 */
	public Joueur(int score, boolean tour) {
		this.score = score;
		this.doitJouer = tour;
		//this.mainJoueur = Lettre.class.pioche(7)
	}

	
	/**
	 * Permet de placer un mot sur le plateau
	 */
	public void jouer() {
		System.out.println("Jouer");
	}
	
	/**
	 * Permet de piocher des lettres
	 */
	public void pioche() {
		System.out.println("Pioche");
	}
	
	/**
	 * Remélange les lettres dans le sac
	 */
	public void melanger() {
		System.out.println("Melange");
	}
	
	/**
	 * Permet au joueur de passer le tour
	 */
	public void passer() {
		System.out.println("Passer");
	}

}
