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
	 * Tableau de Lettre reprÃ©sentant les Lettres du joueur
	 */
	protected  List<Lettre> mainJoueur = new ArrayList<Lettre>();
	
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
		this.mainJoueur.removeAll(mainJoueur);
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
				
				int positionSac =generateNumber(0, Sac.contenuSac.size());
				mainJoueur.add(Sac.contenuSac.get(positionSac));
				Sac.contenuSac.remove(positionSac);
			}
			
		}
		else{
			System.out.println("Pioche impossible");
		}	
	}
	
	/**
	 * RemÃ©lange les lettres dans le sac
	 */
	public void melanger(String [] args) {
		System.out.println("Melange");
		Lettre obj = new Lettre();
		 for(int i = 0; i < 7 ; i++) {
			 for(int j = 0; j < args.length; j++)
					if(args[j] != null && mainJoueur.get(i).label == args[j].charAt(0)) {
						obj = mainJoueur.get(i);
						args[j] = null;
						mainJoueur.remove(mainJoueur.indexOf(obj));
					}
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
