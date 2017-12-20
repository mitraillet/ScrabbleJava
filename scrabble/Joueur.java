/**
 * Package scrabble
 */
package scrabble;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;

/**
 * @author Fauconnier/Henriquet
 * Classe gérant les joueurs
 */
public class Joueur extends Observable implements Serializable {

	/**
	 * Score total du joueur
	 */
	private int score;
	
	/**
	 * Score total du joueur adverse
	 */
	private int scoreAdverse;
	
	/**
	 * True si c'est le tour du joueur, sinon False
	 */
	private boolean tourJoueur = false;
	
	/**
	 * Tableau de Lettre représentant les Lettres du joueur
	 */
	private List<Lettre> mainJoueur;
	
	/**
	 * La main du joueur adverse (pour la fin de partie)
	 */
	private List<Lettre> mainJoueurAdverse;
	
	/**
	 * Variable d'incrément pour arrêter le jeu une fois à 6
	 */
	private int nbreTourPasser = 0;
	
	/**
	 * Variable d'incrément pour arrêter le jeu une fois à 6
	 */
	private int nbreTourPasserAdverse = 0;
	
	/**
	 * True si la partie est finie, sinon false
	 */
	private boolean finPartie = false;
	
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
	 * La main du joueur adverse (Pour la fin de partie)
	 * @param mainJoueurAdverse la main du joueur adverse
	 */
	public void setMainJoueurAdverse(List<Lettre> mainJoueurAdverse) {
		this.mainJoueurAdverse = mainJoueurAdverse;
	}
	
	/**
	 * La main du joueur adverse (Pour la fin de partie)
	 * @return la main du joueur adverse
	 */
	public List<Lettre> getMainJoueurAdverse() {
		return this.mainJoueurAdverse;
	}
	
	/**
	 * Retourne true si la partie est finie
	 * @return true si la partie est finie, sinon false
	 */
	public boolean getFinPartie() {
		return this.finPartie;
	}

	/**
	 * Actualise la valeur de la fin de partie et calcule le score final
	 * @param finPartie true si la partie est finie, sinon false
	 */
	public void setFinPartie(boolean finPartie) {
		boolean estDejaFinis = this.finPartie;
		this.finPartie = finPartie;
		
		if(finPartie && !estDejaFinis) {
			//Pour ne pas faire le calcul des scores 2 fois
			this.scoreFinPartie();
		}
	}
	
	/**
	 * renvoie le nombre de tour passer
	 * @return le nombre de tour passer
	 */
	public int getNbreTourPasser() {
		return this.nbreTourPasser;
	}
	
	/**
	 * actualise le nombre de tour passer par l'adversaire
	 * @param tourPasser variable s'incrémentant à chaque fois que l'adversaire passe
	 */
	public void setNbreTourPasserAdverse(int nbreTourPasserAdverse) {
		this.nbreTourPasserAdverse = nbreTourPasserAdverse;
	}
	
	/**
	 * renvoie le nombre de tour passer par l'adversaire
	 * @return le nombre de tour passer par l'adversaire 
	 */
	public int getNbreTourPasserAdverse() {
		return this.nbreTourPasserAdverse;
	}
	
	/**
	 * actualise le nombre de tour passer
	 * @param tourPasser variable s'incrémentant à chaque fois que l'on passe
	 */
	public void setNbreTourPasser(int nbreTourPasser) {
		this.nbreTourPasser = nbreTourPasser;
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
	 * @return le score du joueur
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * actualise le score du joueur adverse
	 * @param score le nouveau score adverse
	 */
	public void setScoreAdverse(int score) {
		this.scoreAdverse = score;
	}
	
	/**
	 * renvoie le score du joueur adverse
	 * @return le score du joueur adverse
	 */
	public int getScoreAdverse() {
		return this.scoreAdverse;
	}
	
	/**
	 * Ajoute le score d'un mot au joueur
	 * @param score le score à rajouter
	 */
	public void addScore(int score) {
		this.score += score;
	}

	/**
	 * actualise le tour du joueur et les vues
	 * @param tourJoueur true si le joueur doit jouer, sinon false
	 */
	public void setTourJoueur(boolean tourJoueur) {
		this.tourJoueur = tourJoueur;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * renvoie le tour du joueur
	 * @return le tour du joueur (true si le joueur doit jouer, sinon false)
	 */
	public boolean getTourJoueur() {
		return this.tourJoueur;
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
		this.tourJoueur = tour;
		this.setMainJoueur(new ArrayList<Lettre>());
	}

	/**
	* Récupère une lettre dans la main du joueur
	* @param positionMain la position de la lettre à récupérer
	* @return la lettre tirée de la main
	*/
	public Lettre getLettreMain(int positionMain) {
		return this.getMainJoueur().get(positionMain);
	}
	
	/**
	* Récupère le label d'une lettre (se situant dans la main du joueur)
	* @param positionMain la position de la lettre
	* @return Le label de la lettre
	*/
	public char getLabelLettreMain(int positionMain) {
		return getLettreMain(positionMain).getLabel();
	}
	
	/**
	* Récupère le label d'une lettre (se situant dans la main du joueur adverse)
	* @param positionMain la position de la lettre
	* @return Le label de la lettre
	*/
	public char getLabelLettreMainAdverse(int positionMain) {
		return getLettreMain(positionMain).getLabel();
	}
	
	/**
	* Récupère la valeur d'une lettre (se situant dans la main du joueur)
	* @param positionMain la position de la lettre
	* @return La lettre tirée de la main
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
	* @return la main du joueur
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
		
		if(sac.tailleContenuSac() > 0) { //Si le sac n'est pas vide
			if(this.getSizeMainJoueur() < 7 ) { //Si il y a moins de 7 lettres dans la main du joueur
				
				int nombrePieceAPrendre = 7 - getMainJoueur().size();
				
				if(sac.tailleContenuSac() < nombrePieceAPrendre) {
					nombrePieceAPrendre = sac.tailleContenuSac();
				}
				for(int i = 0; i < nombrePieceAPrendre; i++) {
					int positionSac = generateNumber(0, sac.tailleContenuSac());
					getMainJoueur().add(sac.getPositionLettreDansSac(positionSac));
					sac.removeLettreDuSac(positionSac);
				}
			}
			else{
				MessageDErreur.setMsgDErreur("Pioche impossible");
			}
		}
		nbreTourPasser = 0;
		setTourJoueur(!getTourJoueur());
	}


	/**
	 * Remélange les lettres dans le sac
	 * @param exitLettre la liste des lettres à remélanger 
	 * @param sac l'objet sac qui récupère les lettres
	 */
	public void melanger(List<Lettre> exitLettre, Sac sac){
		
		if(!getMainJoueur().isEmpty()) {
			for(int i = 0 ; i <  exitLettre.size(); i++) {
	            Object o = exitLettre.get(i);
				if(getMainJoueur().contains(o)) {
					sac.addLettreAuSac(exitLettre.get(i)); 
					getMainJoueur().remove(exitLettre.get(i));
				}
				else {
					MessageDErreur.setMsgDErreur("Lettre inexistante dans la main");
				}
			}
			this.pioche(sac);
		}
		else {
			MessageDErreur.setMsgDErreur("Main vide rien à mélanger");
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
	 * @param nbrJoker le nombre de joker joués (1-2)
	 * @return Une liste de lettre constituée de : La lettre, si touvée dans la main sinon null
	 */
	public void verifierLettreMain(int x , int y, char orientation, Plateau plateau, String[] mot, 
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
			while(j < this.mainJoueur.size() && !lettreTrouve) {
				if(mot[i].charAt(0) != (this.getLabelLettreMain(j))) { //Si la lettre n'est pas dans la main
					tempLettre = null;
				} else { 
					if(plateau.checkCaseRemplie(x, y, orientation, i)) {//Si la case du plateau est remplie
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
						} else if(this.getValeurLettreMain(j) == 0) { 
							//Gère le cas où le joker est détecté comme correct, mais mauvaise place dans le mot
							tempLettre = null;
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
	 * Pose le mot sur le plateau
	 * @param x la position x de départ
	 * @param y la position y de départ
	 * @param motJoue le mot posé (null = lettre déja sur le plateau)
	 * @param motMain les lettres tirée de la main
	 * @param motArray le mot posé (en entier)
	 * @param plateauSave la sauvegarde du plateau
	 * @param saveMain la sauvegarde de la main
	 * @param orientation l'orienation du mot
	 * @param plateau le plateau sur lequel poser le mot
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
					MessageDErreur.setMsgDErreur("Vous ne possédez pas les lettres requises.");
					return false;
				}
				
				if(plateau[x + xPos][y - yPos].getLettre() == null) {
					plateau[x + xPos][y - yPos].setLettre(motJoue.get(i));
					motMain.add(motJoue.get(i));
				} else {
					if(plateau[x + xPos][y - yPos].getLabelCase() != motArray[i].charAt(0)) {
						MessageDErreur.setMsgDErreur("Impossible de poser le mot");
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				this.setMainJoueur(saveMain);
				plateau = plateauSave;
				MessageDErreur.setMsgDErreur("Erreur : votre mot sort du plateau");
				return false;
			}

			if(orientation == 'h') {
				xPos ++;
			} else if (orientation == 'v') {
				yPos ++;
			} else {
				MessageDErreur.setMsgDErreur("Erreur d'orientation");
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
	 * @return le label vérifié ou '/' si incorrect
	 */
	public char testJoker(String joker) {
		char labelChar;
		String labelString = joker.toLowerCase();
		String abc = "azertyuiopqsdfghjklmwxcvbn";
		if(abc.contains(labelString)) {
			labelChar = labelString.charAt(0);
		}
		else {
			MessageDErreur.setMsgDErreur("Caractère non authorisé !");
			return '/';
		}
		return labelChar;
	}
	
	/**
	 * Copie la main du joueur
	 * @return la copie de la main
	 */
	public List<Lettre> copieMainJoueur() {
		List<Lettre> copieMain = new ArrayList<Lettre>();
		
		for(int i = 0; i < this.getSizeMainJoueur(); i++) {
			if(this.getLettreMain(i).getValeur() == 0) {
				Joker joker = new Joker();
				copieMain.add(joker);
			} else {
				copieMain.add(this.getLettreMain(i));
			}
		}
		
		return copieMain;
		
	}
	
	/**
	 * Permet au joueur de passer le tour
	 */
	public void passer() {
		int newNbreTourPasser = this.getNbreTourPasser() + 1;
		this.setNbreTourPasser(newNbreTourPasser);
		this.setTourJoueur(false);
	}
	
	/**
	 * Soustrais la valeur des lettres restées dans la main à la fin de la partie
	 */
	public void scoreFinPartie() {
		if(this.finPartie == true) {
			if(this.getMainJoueur().isEmpty()) {
				for(int i = 0; i < this.mainJoueurAdverse.size(); i++) {
					this.score += this.mainJoueurAdverse.get(i).getValeur();
				}
			} 
			
			else if(this.mainJoueurAdverse.isEmpty()) {
				for(int i = 0; i < this.getSizeMainJoueur(); i++) {
					this.score -= this.getValeurLettreMain(i);
				}
			}
			
			if(this.score < 0) {
				this.score = 0;
			}
		}
	}
	
	/**
	 * Affiche la main du joueur
	 * @return string contenant un String de la main du joueur
	 */
	public String toString() {
		String joueur;
		String score = "Vous avez : " + this.score + " point(s).";
		String scoreAdverse = "Votre adversaire à : " + this.scoreAdverse + " point(s).";
		String string = "Votre main : ";
		for(int i = 0; i < this.getSizeMainJoueur(); i++)
	    {
			string +=this.getLabelLettreMain(i) + " ";
	    }
		joueur = score + '\n' + scoreAdverse + '\n' + string;
		return joueur;
	}

}
