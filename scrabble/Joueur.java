/**
 * Package scrabble
 */
package scrabble;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;

/**
 * @author Fauconnier/Henriquet
 * Classe gérant les joueurs
 */
public class Joueur extends Observable{

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
	private  List<Lettre> mainJoueur;
	
	
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
	 * actualise le score du joueur
	 * @param score le nouveau score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * renvoie le score du joueur
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Ajoute le score d'un mot au joueur
	 * @param score le score à rajouter
	 */
	public void addScore(int score) {
		this.score += score;
	}

	/**
	* Constructeur par défaut de la classe Joueur
	*/
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
		this.setMainJoueur(new ArrayList<Lettre>());
	}

	/**
	* Récupère une lettre dans la main du joueur
	* @param positionMain la position de la lettre à récupérer
	*/
	public Lettre getLettreMain(int positionMain) {
		return this.getMainJoueur().get(positionMain);
	}
	
	/**
	* Récupère le label d'une lettre (se situant dans la main du joueur)
	* @param positionMain la position de la lettre
	*/
	public char getLabelLettreMain(int positionMain) {
		return getLettreMain(positionMain).getLabel();
	}
	
	/**
	* Récupère la valeur d'une lettre (se situant dans la main du joueur)
	* @param positionMain la position de la lettre
	*/
	public int getValeurLettreMain(int positionMain) {
		return getLettreMain(positionMain).getValeur();
	}
	
	/**
	* Récupère la taille de la main du joueur 
	* @return la taille (int)
	*/
	public int getSizeMainJoueur() {
		 return this.getMainJoueur().size();
	}
	
	/**
	* Retourne toute la main du joueur
	* @return the mainJoueur
	*/
	public List<Lettre> getMainJoueur() {
		return this.mainJoueur;
	}
	
	/**
	 * Utilisé pour créer des copies de main 
	 * @param mainJoueur
	 */
	public void setMainJoueur(List<Lettre> mainJoueur) {
		this.mainJoueur = mainJoueur;
	}
	
	/**
	 * Permet de piocher des lettres
	 * @param sac l'objet sac qui contient les lettres
	 */
	public void pioche(Sac sac){
		System.out.println("Pioche");
		
		if(sac.tailleContenuSac() == 0) {
			if( this.getSizeMainJoueur() > 1) {
				System.out.println("L'adversaire n'a plus que " + this.getSizeMainJoueur() + " lettres dans sa main.");
				setChanged();
				notifyObservers();
			}
			else {
				System.out.println("L'adversaire n'a plus qu'une lettre dans sa main.");
				setChanged();
				notifyObservers();
			}
		}
		else {
			if(this.getSizeMainJoueur() < 7 ) {
				
				int nombrePieceAPrendre = 7 - getMainJoueur().size();
				if(sac.tailleContenuSac() < nombrePieceAPrendre) {
					nombrePieceAPrendre = sac.tailleContenuSac();
				}
				for(int i = 0; i < nombrePieceAPrendre; i++) {
					int positionSac = generateNumber(0, sac.tailleContenuSac());
					getMainJoueur().add(sac.getPositionLettreDansSac(positionSac));
					sac.removeLettreDuSac(positionSac);
				}
				setChanged();
				notifyObservers();
			}
			else{
				System.out.println("Pioche impossible");
			}
		}
	}


	/**
	 * Remélange les lettres dans le sac
	 * @param exitLettre la liste des lettres à remélanger 
	 * @param sac l'objet sac qui récupère les lettres
	 */
	public void melanger(List<Lettre> exitLettre, Sac sac){
		System.out.println("Melange");
		if(!getMainJoueur().isEmpty()) {
			for(int i = 0; i < exitLettre.size(); i++) {
				if(getMainJoueur().contains(exitLettre.get(i))) {
					sac.addLettreAuSac(exitLettre.get(i)); 
					getMainJoueur().remove(exitLettre.get(i));
				}
				else {
					System.out.println("Lettre inexistante dans la main");
				}
			}
			this.pioche(sac);
		}
		else {
			System.out.println("Main vide rien à mélanger");
		}
	}
	
	/**
	 * Vérifie que les lettres rentrées en console sont dans la main
	 * @param x la position x de la première lettre du mot posé
	 * @param y la position x de la première lettre du mot posé
	 * @param orientation l'orientation du mot
	 * @param plateau le plateau sur lequel le mot est posé
	 * @param mot le mot à jouer
	 * @param motJoker le mot à jouer avec la position des jokers (joker == ?)
	 * @param motJoue La liste de lettre du mots sur le plateau
	 * @return Une liste de lettre constituée de : La lettre, si touvée dans la main sinon null
	 */
	public void verifierLettreMain(int x , int y, char orientation, Case[][] plateau, String[] mot, 
			String[] motJoker, List<Lettre> motJoue, int nbrJoker) {
		//Gère les lettres de la main
		Lettre tempLettre = null; //Lettre temporaire
		int j = 0; //variable incrémentale 
		boolean lettreTrouve = false; //Flag, lettre trouvée
		
		HashSet<Integer> lettrePrise = new HashSet<Integer>(); //Garde les positions des lettres déjà prises
		
		for(int i = 0; i < mot.length; i++) {
			lettreTrouve = false;
			j = 0;
			
			//Tant qu' on n'a pas itéré toute la main et que la lettre n'est pas trouvée
			while(j < this.mainJoueur.size() && lettreTrouve == false) {
				if(mot[i].charAt(0) != (this.getLabelLettreMain(j))) { //Si la lettre n'est pas dans la main
					tempLettre = null;
				} else { 
					if(this.checkCaseVide(x, y, orientation, plateau, i) == true) {//Si la case du plateau est remplie
						tempLettre = null;
					} else if(lettrePrise.contains(j)) {//Si la lettre de la main est déja prise
						tempLettre = null;
					} else if(nbrJoker > 0) { //Si il y a des jokers
						if(motJoker[i].charAt(0) == '?') {
							if(this.getValeurLettreMain(j) == 0) {
								lettrePrise.add(j);
								tempLettre = this.getLettreMain(j);
								lettreTrouve = true;
							} 
						} else {
							lettrePrise.add(j);
							tempLettre = this.getLettreMain(j);
							lettreTrouve = true;
						}
					} else {
						lettrePrise.add(j);
						tempLettre = this.getLettreMain(j);
						lettreTrouve = true;
					}
				}
				j++;
			}
			
			motJoue.add(tempLettre);
		}
	}
	
	/**
	 * Vérifie si une case du plateau est remplie
	 * @param x la position x de départ du mot
	 * @param y la position y de départ du mot
	 * @param orientation l'orientation du mot
	 * @param plateau le plateau à vérifier
	 * @param i le numéro de la case
	 * @return true si la case est libre, sinon false
	 */
	public boolean checkCaseVide(int x, int y, char orientation, Case[][] plateau, int i){
		if(orientation == 'h') {
			try {
				if(plateau[x + i][y].getLettre() != null) {
					return true;
				}	
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		} else {
			try {
				if(plateau[x][y - i].getLettre() != null) {
					return true;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		return false;
	}
	
	/**
	 * Pose le mot sur le plateau
	 * @param x la position x de départ
	 * @param y la position y de départ
	 * @param motJoue le mot posé (null = lettre déja sur le plateau)
	 * @param motMain les lettres tirée de la main
	 * @param motArray le mot posé (en entier)
	 * @param plateauSave la sauvegarde du plateau
	 * @param saveMain la sauvegarde de la main
	 * @param orientation l'orienation du mot
	 * @param plateau le plateau sur lequel posé le mot
	 * @return true si le mot est posé sans erreur, sinon false
	 */
	public boolean poserMotPlateau(int x, int y, List<Lettre> motJoue, List<Lettre> motMain, 
			String[] motArray, Case[][] plateauSave, List<Lettre> saveMain, char orientation, Case[][] plateau) {
		//Gère la pose des Lettres
		int xPos = 0; //incrément Position x
		int yPos = 0; //incrément Position y
		for(int i = 0; i < motArray.length; i++) {
			try {
				if(plateau[x + xPos][y - yPos].getLettre() == null && motJoue.get(i) == null) {
					System.out.println("Vous ne possédez pas les lettres requises.");
					return false;
				}
				
				if(plateau[x + xPos][y - yPos].getLettre() == null) {
					plateau[x + xPos][y - yPos].setLettre(motJoue.get(i));
					motMain.add(motJoue.get(i));
				} else {
					if(plateau[x + xPos][y - yPos].getLabelCase() != motArray[i].charAt(0)) {
						System.out.println("Impossible de poser le mot");
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				this.setMainJoueur(saveMain);
				plateau = plateauSave;
				System.out.println("Erreur : votre mot sort du plateau");
				return false;
			}

			if(orientation == 'h') {
				xPos ++;
			} else if (orientation == 'v') {
				yPos ++;
			} else {
				System.out.println("Erreur d'orientation");
				plateau = plateauSave;
				return false;
			}
			
		}
		return true;
	}
	
	/**
	 * Enlève les mots posés sur le plateau (de la main)
	 * @param motMain Les lettres venant de la main
	 */
	public void viderLaMain(List<Lettre> motMain, Sac sac) {
		for (int i = 0; i < motMain.size(); i++) {
			if(motMain.get(i) != null){
				this.getMainJoueur().remove(motMain.get(i));
			}
		}
		this.pioche(sac);
	}
	
	/**
	 * Détecte le nombre de joker dans le mot à jouer
	 * @param motArray le mot à jouer
	 * @return le nombre de joker
	 */
	public int detecteJoker(String mot) {
		int nbrJoker = 0;
		String [] motArray = mot.split("");
		for(int i = 0; i < motArray.length; i++) {
			if(motArray[i].charAt(0) == '?') {
				nbrJoker++;
			}
		}
		return nbrJoker;
	}
	
	/**
	 * Attribue un label aux jokers
	 * @param joker1 le label du premier joker
	 * @param joker2 le label du deuxième joker
	 * @param mot le mot joué
	 * @return le mot avec les jokers modifiés
	 */
	public String setJokerMain(char joker1, char joker2, String mot) {
		
		boolean joker1ok = false; //flag, true si le 1er joker est set
		for(int i = 0; i < this.mainJoueur.size(); i++) {
			
			if(this.mainJoueur.get(i).getLabel() == '?') {
				if(joker1ok == false) {
					this.mainJoueur.get(i).setLabel(joker1);
					mot = mot.replaceFirst("\\?", Character.toString(joker1));
					joker1ok = true;
					
				} else if(joker2 != '/' && joker1ok == true) {
					mot = mot.replaceFirst("\\?", Character.toString(joker2));
					this.mainJoueur.get(i).setLabel(joker2);
				}
			}
		}
		
		return mot;
	}
	
	/**
	 * Vérifie le label rentré par l'utilisateur
	 * @param joker le label rentré par le joueur
	 * @return le label vérifié ou '$' si incorrect
	 */
	public char testJoker(String joker) {
		char labelChar;
		String labelString = joker.toLowerCase();
		String abc = "azertyuiopqsdfghjklmwxcvbn";
		if(abc.contains(labelString)) {
			labelChar = labelString.charAt(0);
		}
		else {
			System.out.println("Caractère non authorisé !");
			return '/';
		}
		return labelChar;
	}
	
	/**
	 * Permet au joueur de passer le tour
	 */
	public void passer() {
		System.out.println("Passer");
	}
	
	/**
	 * Affiche la main du joueur
	 * @return string contenant un String de la main du joueur
	 */
	public String toString() {
		String joueur;
		String score = "Score : " + this.score;
		String string = "Votre main : ";
		for(int i = 0; i < this.getSizeMainJoueur(); i++)
	    {
			string +=this.getLabelLettreMain(i) + " ";
	    }
		joueur = score + '\n' + string;
		return joueur;
	}

}
