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
	public int getSizeMainJoueur() {
		 return this.getMainJoueur().size();
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
	public void pioche(Sac sac){
		System.out.println("Pioche");
		
		if(sac.tailleContenuSac() == 0) {
			if( this.getSizeMainJoueur() > 1) {
				System.out.println("L'adversaire n'a plus que " + this.getSizeMainJoueur() + " lettres dans sa main.");
			}
			else {
				System.out.println("L'adversaire n'a plus qu'une lettre dans sa main.");
			}
		}
		else {
			if(this.getSizeMainJoueur() < 7 ) {
				
				int nombrePieceAPrendre = 7 - mainJoueur.size();
				if(sac.tailleContenuSac() < nombrePieceAPrendre) {
					nombrePieceAPrendre = sac.tailleContenuSac();
				}
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
	}


	/**
	 * Remélange les lettres dans le sac
	 * @throws SacVideException 
	 */
	public void melanger(List<Lettre> exitLettre, Sac sac){
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
