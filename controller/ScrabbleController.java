<<<<<<< HEAD
/**
 * Package Scrabble
 */
package controller;

import java.io.IOException;
=======
package controller;

>>>>>>> master
import java.util.ArrayList;
import java.util.List;

import scrabble.Joueur;
import scrabble.Lettre;
<<<<<<< HEAD
import scrabble.MessageDErreur;
=======
>>>>>>> master
import scrabble.Plateau;
import scrabble.Sac;
import scrabble.Case;
import view.ScrabbleView;
<<<<<<< HEAD
import ScrabbleLancement.gestionSocket;

/**
 * Controller du jeu
 * @author Fauconnier/Henriquet
 */
public class ScrabbleController {
	/**
	 * Le plateau de jeu
	 */
	Plateau plateau; 
	
	/**
	 * Le joueur
	 */
	Joueur joueur;
	
	/**
	 * Le sac
	 */
	Sac sac;
	
	/**
	 * La gestion des sockets
	 */
	gestionSocket socket;
	
	/**
	 * La vue
	 */
	ScrabbleView vue;
	
	/**
	 * Constructeur du Controller
	 * @param plateau le plateau de jeu
	 * @param joueur le joueur
	 * @param sac le sac
	 * @param paramSocket la gestion des sockets
	 */
	public ScrabbleController(Plateau plateau, Joueur joueur, Sac sac, List<Object> paramSocket) {
		this.plateau = plateau;
		this.joueur = joueur;
		this.sac = sac;
		
		this.startSocket(paramSocket);
	}
	
	/**
	 * Gestion des sockets
	 * @param paramSocket Une liste contenant : 1) true si c'est le serveur, sinon false. 2) l'ip
	 */
	public void startSocket(List<Object> paramSocket) {
		socket = new gestionSocket(plateau, sac);
		boolean estServeur = (boolean) paramSocket.get(0);
		String ip = (String) paramSocket.get(1);
		
		try {
			socket.setSocket(estServeur, 12345, ip);
		} catch (IOException e) {
			System.out.println("Erreur lors de l'initilisation de la connexion");
		}
		
		//Les 2 joueurs piochent leurs premières lettres dans le même sac.
		if(estServeur) {
			joueur.pioche(sac);
			socket.envoyerDonnee(joueur, plateau, sac);
			socket.recevoirSac(joueur);
			joueur.setTourJoueur(true);
			
		} else {
			socket.recevoirSac(joueur);
			joueur.pioche(sac);
			socket.envoyerDonnee(joueur, plateau, sac);
			joueur.setTourJoueur(false);
		}
	}
	
	/**
	 * Lance la réception des données
	 */
	public void socketRecevoir() {
		socket.recevoirDonnee(joueur, plateau, sac);
		if(sac.tailleContenuSac() == 0) { //Si le sac est vide
			if( joueur.getSizeMainJoueur() > 1) {
				MessageDErreur.setMsgDErreur("L'adversaire n'a plus que " + joueur.getMainJoueurAdverse().size() + " lettres dans sa main.");
			}
			else {
				MessageDErreur.setMsgDErreur("L'adversaire n'a plus qu'une lettre dans sa main.");
			}
		}
	}
	
	/**
	 * Mélange la main du joueur
	 * @param label les Lettres à remettre dans le sac
	 */
=======

public class ScrabbleController {
	Plateau plateau; 
	Joueur joueur;
	Sac sac;
	ScrabbleView vue;
	public ScrabbleController(Plateau plateau, Joueur joueur, Sac sac) {
		this.plateau = plateau;
		this.joueur = joueur;
		this.sac = sac;
	}
	
>>>>>>> master
	public void melangeMain(String label) {
		List<Lettre> exitLettre = new ArrayList<Lettre>();
	
		boolean continueRecherche = true;
		
		for(int i = 0; i < label.length(); i++) {
			for(int j = 0; j < joueur.getSizeMainJoueur(); j++) {
				if(label.charAt(i) == joueur.getLabelLettreMain(j)) {
					if(continueRecherche == true ) {
						exitLettre.add(joueur.getLettreMain(j));
						continueRecherche = false;
					}
				}
			}
			continueRecherche = true;
			
		}
		
		joueur.melanger(exitLettre, sac);
<<<<<<< HEAD
		this.finDuJeu();
		socket.envoyerDonnee(joueur, plateau, sac);
	}

	/**
	 * Passe le tour du joueur
	 */
	public void passer() {
		joueur.passer();
		this.finDuJeu();
		socket.envoyerDonnee(joueur, plateau, sac);
	}
	
	/**
	 * 
	 * Permet de placer un mot sur le plateau
	 * @param x La position x de la première lettre
	 * @param y La position y de la première lettre
	 * @param orientation sens d'écriture du mot
	 * @param mot Le mot à poser sur le plateau
	 * @param saveMainJoueur Une sauvegarde de la main pour éviter une perte de Lettre
	 * @param nbrJoker le nombre de joker joué
	 * @param motJoker le mot avec les jokers (joker = ?)
	 */
	public void poserMot(int x, int y, char orientation, String mot, List<Lettre> saveMainJoueur, int nbrJoker, String motJoker){
		
		Case[][] plateauJeu = plateau.getPlateau(); //Plateau de jeu
		Case[][] plateauSave = plateau.copyPlateau(); //Sauvegarde du plateau
		
		List<Lettre> saveMain = saveMainJoueur;
		List<Lettre> motJoue = new ArrayList<Lettre>(); //La liste de lettre du mots sur le plateau
		List<Lettre> motMain = new ArrayList<Lettre>(); //liste des lettres enlevée de la main
		int score = 0;

		String[] jokerArray = motJoker.split("");
		String[] motArray = mot.split(""); //String séparé en Array de lettre
		
		joueur.verifierLettreMain(x, y, orientation, plateau, motArray, jokerArray, motJoue, nbrJoker);
		if(!plateau.verification(mot)) {
			MessageDErreur.setMsgDErreur("Mot incorrect");
			return;
		}
		
		if(joueur.poserMotPlateau(x, y, motJoue, motMain, motArray, plateauSave, 
				saveMain, orientation, plateauJeu) == false) {
			joueur.setMainJoueur(saveMain);
			plateau.setPlateau(plateauSave);
			//MessageDErreur.setMsgDErreur("Mot impossible à placer");
			return;
		} else {
			plateau.setPlateau(plateauJeu);
		}
		
		//Vérifie les mots
		if(plateau.isDebutPartie()) {
			if(plateau.verificationPeripherique(x, y, orientation, motMain, motJoue)) {
				score = plateau.calculScore(x, y, orientation, motMain, motJoue);
				plateau.setScoreJoueur(joueur, score);
				joueur.viderLaMain(motMain, sac);
			} else {
				joueur.setMainJoueur(saveMain);
				plateau.setPlateau(plateauSave);
				//MessageDErreur.setMsgDErreur("Placement du Mot incorrect");
				return;
			}
			
		} else {
			//Enlève les lettres jouée de la main
			if(plateau.checkPremierMot(x, y, orientation, joueur, motMain, motJoue)) {
				score = plateau.calculScore(x, y, orientation, motMain, motJoue);
				plateau.setScoreJoueur(joueur, score);
				joueur.viderLaMain(motMain, sac);
			} else {
				joueur.setMainJoueur(saveMain);
				plateau.setPlateau(plateauSave);
				MessageDErreur.setMsgDErreur("Placement du Premier Mot incorrect");
				return;
			}
		}
		
		if(!joueur.getTourJoueur()) {
			this.finDuJeu();
			socket.envoyerDonnee(joueur, plateau, sac);
		}
			
	}
	
	/**
	 * Si la partie est finie envoie les données en vue du calcul des scores
	 */
	public void checkFin() {
		if(joueur.getFinPartie()) {
			socket.envoyerDonnee(joueur, plateau, sac);
		} 
	}
	
	/**
	 * Détecte la fin de la partie
	 * return true si la partie est finie, sinon false
	 */
	public void finDuJeu() {
		if((joueur.getNbreTourPasser() >= 3 && joueur.getNbreTourPasserAdverse() >= 3) 
				|| joueur.getMainJoueur().isEmpty()) {
			joueur.setFinPartie(true);
		}
	}
	
	/**
	 * Active l'interaction entre la vue et le code
	 * @param vue à ajouter au controller
	 */
=======
	}

	/**
	 * Permet de placer un mot sur le plateau
	 * @param x La position x de la première lettre
	 * @param y La position y de la première lettre
	 */
	public void poserMot(int x, int y, char orientation, String mot, List<Lettre> saveMainJoueur){
			Case[][] plateauJeu = plateau.plateau; //Plateau de jeu
			Case[][] plateauSave = plateau.copyPlateau(); //Sauvegarde du plateau
			List<Lettre> saveMain = saveMainJoueur;
			List<Lettre> motJoue = new ArrayList<Lettre>(); //La liste de lettre du mots sur le plateau
			List<Lettre> motMain = new ArrayList<Lettre>(); //liste des lettres enlevée de la main
	
			String[] motArray = mot.split(""); //String séparé en Array de lettre
			
			joueur.verifierLettreMain(motArray, motJoue);
			
			if(joueur.poserMotPlateau(x, y, motJoue, motMain, motArray, plateauSave, 
					saveMain, orientation, plateauJeu) == false) {
				joueur.setMainJoueur(saveMain);
				plateau.plateau = plateauSave;
				return;
			} else {
				plateau.plateau = plateauJeu;
			}
			
			//Vérifie les mots
			if(plateau.debutPartie == true) {
				if(plateau.verificationPeripherique(x, y, orientation, motMain, motJoue)) {
					plateau.setScoreJoueur(joueur);
					joueur.viderLaMain(motMain, sac);
				} else {
					joueur.setMainJoueur(saveMain);
					plateau.plateau = plateauSave;
					System.out.println("\nPlacement du Mot incorrect");
				}
				
			} else {
				//Enlève les lettres jouée de la main
				if(plateau.checkPremierMot(x, y, orientation, joueur, motMain)) {
					plateau.setScoreJoueur(joueur);
					joueur.viderLaMain(motMain, sac);	
				} else {
					joueur.setMainJoueur(saveMain);
					plateau.plateau = plateauSave;
					System.out.println("\nPlacement du Premier Mot incorrect");
				}
			}
			motJoue = new ArrayList<Lettre>(); //Remets à zéro la variable
			
	}
>>>>>>> master
	public void addView(ScrabbleView vue) {
		this.vue = vue;
		
	}

}