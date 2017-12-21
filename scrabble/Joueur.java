/**
<<<<<<< HEAD
 * Package Modèle
=======
 * Package scrabble
>>>>>>> master
 */
package scrabble;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;

/**
<<<<<<< HEAD
 * Classe gérant les joueurs
 * @author Fauconnier/Henriquet
 */
public class Joueur extends Observable {
=======
 * @author Fauconnier/Henriquet
 * Classe gérant les joueurs
 */
public class Joueur extends Observable{
>>>>>>> master

	/**
	 * Score total du joueur
	 */
	private int score;
	
	/**
<<<<<<< HEAD
	 * Score total du joueur adverse
	 */
	private int scoreAdverse;
	
	/**
	 * True si c'est le tour du joueur, sinon False
	 */
	private boolean tourJoueur = false;
=======
	 * True si c'est le tour du joueur, sinon False
	 */
	private boolean doitJouer = false;
>>>>>>> master
	
	/**
	 * Tableau de Lettre représentant les Lettres du joueur
	 */
<<<<<<< HEAD
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
=======
	private  List<Lettre> mainJoueur;
	
>>>>>>> master
	
	/**
	 * Génération d'un nombre random compris entre deux chiffres
	 * @param minNum le nombre min
	 * @param maxNum le nombre max
	 * @return le nombre random
	 */
	public int generateNumber(int minNum, int maxNum) {
		int random = (int)(Math.random() * maxNum + minNum);
		return random;
<<<<<<< HEAD
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
	 * @param nbreTourPasserAdverse variable s'incrémentant à chaque fois que l'adversaire passe
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
	 * @param nbreTourPasser variable s'incrémentant à chaque fois que l'on passe
	 */
	public void setNbreTourPasser(int nbreTourPasser) {
		this.nbreTourPasser = nbreTourPasser;
	}
	
	/**
	 * actualise le score du joueur
	 * @param score le nouveau score
=======
		}
	
	/**
	 * @param actualise le score du joueur
>>>>>>> master
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
<<<<<<< HEAD
	 * renvoie le score du joueur
	 * @return le score du joueur
=======
	 * @param actualise le score du joueur
>>>>>>> master
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
<<<<<<< HEAD
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
=======
	 * @param ajoute le score d'un mot au joueur
>>>>>>> master
	 */
	public void addScore(int score) {
		this.score += score;
	}

	/**
<<<<<<< HEAD
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
=======
>>>>>>> master
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
<<<<<<< HEAD
		this.tourJoueur = tour;
=======
		this.doitJouer = tour;
>>>>>>> master
		this.setMainJoueur(new ArrayList<Lettre>());
	}

	/**
	* Récupère une lettre dans la main du joueur
	* @param positionMain la position de la lettre à récupérer
<<<<<<< HEAD
	* @return la lettre tirée de la main
=======
>>>>>>> master
	*/
	public Lettre getLettreMain(int positionMain) {
		return this.getMainJoueur().get(positionMain);
	}
	
	/**
<<<<<<< HEAD
	* Récupère le label d'une lettre (se situant dans la main du joueur)
	* @param positionMain la position de la lettre
	* @return Le label de la lettre
=======
	* Récupère le label d'un lettre (se situant dans la main du joueur)
	* @param positionMain la position de la lettre
>>>>>>> master
	*/
	public char getLabelLettreMain(int positionMain) {
		return getLettreMain(positionMain).getLabel();
	}
	
	/**
<<<<<<< HEAD
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
=======
>>>>>>> master
	* Récupère la taille de la main du joueur 
	* @return la taille (int)
	*/
	public int getSizeMainJoueur() {
		 return this.getMainJoueur().size();
	}
	
	/**
	* Retourne toute la main du joueur
<<<<<<< HEAD
	* @return la main du joueur
=======
	* @return the mainJoueur
>>>>>>> master
	*/
	public List<Lettre> getMainJoueur() {
		return this.mainJoueur;
	}
	
	/**
	 * Utilisé pour créer des copies de main 
<<<<<<< HEAD
	 * @param mainJoueur main a attribué au joueur
=======
	 * @param mainJoueur
>>>>>>> master
	 */
	public void setMainJoueur(List<Lettre> mainJoueur) {
		this.mainJoueur = mainJoueur;
	}
	
	/**
	 * Permet de piocher des lettres
	 * @param sac l'objet sac qui contient les lettres
	 */
	public void pioche(Sac sac){
<<<<<<< HEAD
		
		if(sac.tailleContenuSac() > 0) { //Si le sac n'est pas vide
			if(this.getSizeMainJoueur() < 7 ) { //Si il y a moins de 7 lettres dans la main du joueur
				
				int nombrePieceAPrendre = 7 - getMainJoueur().size();
				
=======
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
>>>>>>> master
				if(sac.tailleContenuSac() < nombrePieceAPrendre) {
					nombrePieceAPrendre = sac.tailleContenuSac();
				}
				for(int i = 0; i < nombrePieceAPrendre; i++) {
					int positionSac = generateNumber(0, sac.tailleContenuSac());
					getMainJoueur().add(sac.getPositionLettreDansSac(positionSac));
					sac.removeLettreDuSac(positionSac);
				}
<<<<<<< HEAD
			}
			else{
				MessageDErreur.setMsgDErreur("Pioche impossible");
			}
		}
		nbreTourPasser = 0;
		setTourJoueur(!getTourJoueur());
=======
				setChanged();
				notifyObservers();
			}
			else{
				System.out.println("Pioche impossible");
			}
		}
>>>>>>> master
	}


	/**
	 * Remélange les lettres dans le sac
	 * @param exitLettre la liste des lettres à remélanger 
	 * @param sac l'objet sac qui récupère les lettres
	 */
	public void melanger(List<Lettre> exitLettre, Sac sac){
<<<<<<< HEAD
		
		if(!getMainJoueur().isEmpty()) {

			boolean stopperRecherche = false;
			
			for(int i = 0; i < exitLettre.size(); i++) {	 
				for(int j = 0; j < this.getSizeMainJoueur(); j++) {
	        	   		if(exitLettre.get(i).getLabel() == this.getMainJoueur().get(j).getLabel()) {
	        	   			if(!stopperRecherche) {
	        	   				sac.addLettreAuSac(exitLettre.get(i)); 
		    					this.getMainJoueur().remove(this.getMainJoueur().get(j));
		    					stopperRecherche = true;
	        	   			}
	        	   		}
	            }
				if(!stopperRecherche) {
					MessageDErreur.setMsgDErreur("Lettre inexistante dans la main");
				}
				stopperRecherche = false;
			}
			
			this.pioche(sac);
		}
		else {
			MessageDErreur.setMsgDErreur("Main vide rien à mélanger");
=======
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
>>>>>>> master
		}
	}
	
	/**
	 * Vérifie que les lettres rentrées en console sont dans la main
<<<<<<< HEAD
	 * @param x la position x de la première lettre du mot posé
	 * @param y la position x de la première lettre du mot posé
	 * @param orientation l'orientation du mot
	 * @param plateau le plateau sur lequel le mot est posé
	 * @param mot le mot à jouer
	 * @param motJoker le mot à jouer avec la position des jokers (joker == ?)
	 * @param motJoue La liste de lettre du mots sur le plateau
	 * @param nbrJoker le nombre de joker joués (1-2)
	 */
	public void verifierLettreMain(int x , int y, char orientation, Plateau plateau, String[] mot, 
			String[] motJoker, List<Lettre> motJoue, int nbrJoker) {
=======
	 * @param mot le mot à  jouer
	 * @param motJoue La liste de lettre du mots sur le plateau
	 * @return Une liste de lettre récupérée depuis la main
	 */
	public void verifierLettreMain(String[] mot, List<Lettre> motJoue) {
>>>>>>> master
		//Gère les lettres de la main
		Lettre tempLettre = null; //Lettre temporaire
		int j = 0; //variable incrémentale 
		boolean lettreTrouve = false; //Flag, lettre trouvée
		
		HashSet<Integer> lettrePrise = new HashSet<Integer>(); //Garde les positions des lettres déjà prises
		
		for(int i = 0; i < mot.length; i++) {
			lettreTrouve = false;
			j = 0;
			
			//Tant qu' on n'a pas itéré toute la main et que la lettre n'est pas trouvée
<<<<<<< HEAD
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
=======
			while(j < 7 && lettreTrouve == false) {
				if(mot[i].charAt(0) != (this.getLabelLettreMain(j))) {
					tempLettre = null;
				} else { 
					if(lettrePrise.contains(j)) {
						tempLettre = null;
>>>>>>> master
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
<<<<<<< HEAD
	 * @param plateau le plateau sur lequel poser le mot
=======
	 * @param plateau le plateau sur lequel posé le mot
>>>>>>> master
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
<<<<<<< HEAD
					MessageDErreur.setMsgDErreur("Vous ne possédez pas les lettres requises.");
=======
					System.out.println("Vous ne possèdez pas les lettres requises.");
>>>>>>> master
					return false;
				}
				
				if(plateau[x + xPos][y - yPos].getLettre() == null) {
					plateau[x + xPos][y - yPos].setLettre(motJoue.get(i));
					motMain.add(motJoue.get(i));
				} else {
					if(plateau[x + xPos][y - yPos].getLabelCase() != motArray[i].charAt(0)) {
<<<<<<< HEAD
						MessageDErreur.setMsgDErreur("Impossible de poser le mot");
=======
						System.out.println("Impossible de poser le mot");
>>>>>>> master
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				this.setMainJoueur(saveMain);
				plateau = plateauSave;
<<<<<<< HEAD
				MessageDErreur.setMsgDErreur("Erreur : votre mot sort du plateau");
=======
				System.out.println(plateau);
				System.out.println("Erreur : votre mot sort du plateau");
>>>>>>> master
				return false;
			}

			if(orientation == 'h') {
				xPos ++;
			} else if (orientation == 'v') {
				yPos ++;
			} else {
<<<<<<< HEAD
				MessageDErreur.setMsgDErreur("Erreur d'orientation");
=======
				System.out.println("Erreur");
>>>>>>> master
				plateau = plateauSave;
				return false;
			}
			
		}
		return true;
	}
	
	/**
	 * Enlève les mots posés sur le plateau (de la main)
	 * @param motMain Les lettres venant de la main
<<<<<<< HEAD
	 * @param sac le sac du jeu
=======
>>>>>>> master
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
<<<<<<< HEAD
	 * @param mot le mot à jouer
	 * @return nbrJoker le nombre de joker
=======
	 * @param motArray le mot à jouer
	 * @return le nombre de joker
>>>>>>> master
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
		
<<<<<<< HEAD
		boolean joker1ok = false; //flag, true si le 1er joker est set
=======
		boolean joker1ok = false;
>>>>>>> master
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
<<<<<<< HEAD
	 * @return le label vérifié ou '/' si incorrect
=======
	 * @return le label vérifié ou '$' si incorrect
>>>>>>> master
	 */
	public char testJoker(String joker) {
		char labelChar;
		String labelString = joker.toLowerCase();
		String abc = "azertyuiopqsdfghjklmwxcvbn";
		if(abc.contains(labelString)) {
			labelChar = labelString.charAt(0);
		}
		else {
<<<<<<< HEAD
			MessageDErreur.setMsgDErreur("Caractère non authorisé !");
=======
			System.out.println("Caractère non authorisé !");
>>>>>>> master
			return '/';
		}
		return labelChar;
	}
	
	/**
<<<<<<< HEAD
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
=======
	 * Permet au joueur de passer le tour
	 */
	public void passer() {
		System.out.println("Passer");
>>>>>>> master
	}
	
	/**
	 * Affiche la main du joueur
	 * @return string contenant un String de la main du joueur
	 */
	public String toString() {
		String joueur;
<<<<<<< HEAD
		String score = "Vous avez : " + this.score + " point(s).";
		String scoreAdverse = "Votre adversaire à : " + this.scoreAdverse + " point(s).";
=======
		String score = "Score : " + this.score;
>>>>>>> master
		String string = "Votre main : ";
		for(int i = 0; i < this.getSizeMainJoueur(); i++)
	    {
			string +=this.getLabelLettreMain(i) + " ";
	    }
<<<<<<< HEAD
		joueur = score + '\n' + scoreAdverse + '\n' + string;
=======
		joueur = score + '\n' + string;
>>>>>>> master
		return joueur;
	}

}
