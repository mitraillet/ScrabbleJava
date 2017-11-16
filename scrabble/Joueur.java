<<<<<<< HEAD
/**
 * 
 */
package scrabble;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Joueur {

	/**
	 * Score total du joueur
	 */
	private int score;
	
	/**
	 * True si c'est le tour du joueur, sinon False
	 */
	private boolean doitJouer = false;
	
	/**
	 * Tableau de Lettre représentant les Lettres du joueur
	 */
	protected  List<Lettre> mainJoueur;
	
	
	/**
	 * Génération d'un nombre random compris entre deux chiffres
	 * @param minNum le nombre min
	 * @param maxNum le nombre max
	 * @return le nombre random
	 */
	public int generateNumber(int minNum, int maxNum) {
		int random = (int)(Math.random() * maxNum + minNum);
		return random;
		}
	
	/**
	 * @param actualise le score du joueur
	 */
	public void setScore(int score) {
		this.score = score;
	}

	public Joueur(){
		this(0, true);
	}
	/**
	 * Constructeur du joueur
	 * @param score le score du joueur
	 * @param tour initialise le tour du joueur
	 */
	public Joueur(int score, boolean tour) {
		this.score = score;
		this.doitJouer = tour;
		this.mainJoueur = new ArrayList<Lettre>();
	}

	public Lettre getLettreMain(int positionMain) {
		return this.mainJoueur.get(positionMain);
	}
	
	public char getLabelLettreMain(int positionMain) {
		return getLettreMain(positionMain).getLabel();
	}
	
	/**
	 * @return the mainJoueur
	 */
	protected List<Lettre> getMainJoueur() {
		return mainJoueur;
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
	public void pioche(Sac sac) {
		System.out.println("Pioche");
		
		if(mainJoueur.size() < 7 ) {
			
			int nombrePieceAPrendre = 7 - mainJoueur.size();
			
			for(int i = 0; i < nombrePieceAPrendre; i++) {
				int positionSac = generateNumber(0, sac.tailleContenuSac());
				mainJoueur.add(sac.getPositionLettreDansSac(positionSac));
				sac.removeLettreDuSac(positionSac);
			}
			
		}
		else{
			System.out.println("Pioche impossible");
		}
	}


	/**
	 * Remélange les lettres dans le sac
	 */
	public void melanger(List<Lettre> exitLettre, Sac sac) {
		System.out.println("Melange");
		for(int i = 0; i < exitLettre.size(); i++) {
				sac.addLettreAuSac(exitLettre.get(i)); //faire m�thode dans Sac pour encapsulation
				mainJoueur.remove(exitLettre.get(i));
		}
		this.pioche(sac);
	}
	/**
	 * Permet au joueur de passer le tour
	 */
	public void passer() {
		System.out.println("Passer");
	}

}
=======
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
	private int score;
	
	/**
	 * True si c'est le tour du joueur, sinon False
	 */
	private boolean doitJouer = false;
	
	/**
	 * Tableau de Lettre représentant les Lettres du joueur
	 */
	private Lettre[] mainJoueur = new Lettre[3];
	
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
>>>>>>> 0a29c43b46fce9f32066a17a4a4198a6bb298234
