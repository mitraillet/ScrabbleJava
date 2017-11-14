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
		this.score = 0;
		this.doitJouer = true;
		this.mainJoueur = new ArrayList<Lettre>();
	}
	/**
	 * Constructeur du joueur
	 * @param score le score du joueur
	 * @param tour initialise le tour du joueur
	 */
	public Joueur(int score, boolean tour) {
		this.score = score;
		this.doitJouer = tour;
		((Joueur) this.mainJoueur).pioche();
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
	public void pioche() {
		System.out.println("Pioche");
		
		if(mainJoueur.size() < 7 ) {
			
			int nombrePieceAPrendre = 7 - mainJoueur.size();
			
			for(int i = 0; i < nombrePieceAPrendre; i++) {
				int positionSac = generateNumber(0, Sac.getSac().size());
				mainJoueur.add(Sac.getSac().get(positionSac));
				Sac.getSac().remove(positionSac);
			}
			
		}
		else{
			System.out.println("Pioche impossible");
		}
	}


	/**
	 * Remélange les lettres dans le sac
	 */
	public void melanger(List<Lettre> exitLettre) {
		System.out.println("Melange");
		for(int i = 0; i < exitLettre.size(); i++) {
				Sac.getSac().add(exitLettre.get(i));
				mainJoueur.remove(exitLettre.get(i));
		}
		this.pioche();
	}
	/**
	 * Permet au joueur de passer le tour
	 */
	public void passer() {
		System.out.println("Passer");
	}

}
