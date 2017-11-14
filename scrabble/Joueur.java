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
	 * Tableau de Lettre repr√©sentant les Lettres du joueur
	 */
	protected  List<Lettre> mainJoueur;
	
	
	/**
	 * G√©n√©ration d'un nombre random compris entre deux chiffres
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
				int positionSac = generateNumber(0, sac.getSac().size());
				mainJoueur.add(sac.getSac().get(positionSac)); // faire mÈthode dans Sac pour encapsulation 
				sac.getSac().remove(positionSac); // faire mÈthode dans Sac pour encapsulation 
			}
			
		}
		else{
			System.out.println("Pioche impossible");
		}
	}


	/**
	 * Rem√©lange les lettres dans le sac
	 */
	public void melanger(List<Lettre> exitLettre, Sac sac) {
		System.out.println("Melange");
		for(int i = 0; i < exitLettre.size(); i++) {
				sac.getSac().add(exitLettre.get(i)); //faire mÈthode dans Sac pour encapsulation
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
