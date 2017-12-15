package controller;

import java.util.ArrayList;
import java.util.List;

import scrabble.Joueur;
import scrabble.Lettre;
import scrabble.Plateau;
import scrabble.Sac;
import scrabble.Case;
import view.ScrabbleView;

import ScrabbleLancement.gestionSocket;

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
	 * 
	 * @param plateau le plateau de jeu
	 * @param joueur le joueur
	 * @param sac le sac
	 * @param socket la gestion des sockets
	 */
	public ScrabbleController(Plateau plateau, Joueur joueur, Sac sac, gestionSocket socket) {
		this.plateau = plateau;
		this.joueur = joueur;
		this.sac = sac;
		this.socket = socket;
	}
	
	/**
	 * Mélange la main du joueur
	 * @param label les Lettres à remettre dans le sac
	 */
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
		socket.envoyerDonnee(joueur, plateau, sac);
	}

	/**
	 * Passe le tour du joueur
	 */
	public void passer() {
		joueur.passer();
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
	 * @return un Array d'oBjet pour pouvoir faire passer les messages d'erreurs et si l'opération s'est bien passé
	 */
	public String poserMot(int x, int y, char orientation, String mot, List<Lettre> saveMainJoueur, int nbrJoker, String motJoker){
		//TODO modif le code pour que les vérif se passe au niveau du controller
		String messageErreur = null;
		
		Case[][] plateauJeu = plateau.getPlateau(); //Plateau de jeu
		Case[][] plateauSave = plateau.copyPlateau(); //Sauvegarde du plateau
		
		List<Lettre> saveMain = saveMainJoueur;
		List<Lettre> motJoue = new ArrayList<Lettre>(); //La liste de lettre du mots sur le plateau
		List<Lettre> motMain = new ArrayList<Lettre>(); //liste des lettres enlevée de la main
		int score = 0;

		String[] jokerArray = motJoker.split("");
		String[] motArray = mot.split(""); //String séparé en Array de lettre
		
		joueur.verifierLettreMain(x, y, orientation, plateauJeu, motArray, jokerArray, motJoue, nbrJoker);
		if(!plateau.verification(mot)) {
			messageErreur = "Mot incorrect";
			return messageErreur;
		}
		
		if(joueur.poserMotPlateau(x, y, motJoue, motMain, motArray, plateauSave, 
				saveMain, orientation, plateauJeu) == false) {
			joueur.setMainJoueur(saveMain);
			plateau.setPlateau(plateauSave);
			messageErreur = "Mot impossible à placer";
			return messageErreur;
		} else {
			plateau.setPlateau(plateauJeu);
		}
		
		//Vérifie les mots
		if(plateau.debutPartie == true) {
			if(plateau.verificationPeripherique(x, y, orientation, motMain, motJoue)) {
				score = plateau.calculScore(x, y, orientation, motMain, motJoue);
				plateau.setScoreJoueur(joueur, score);
				joueur.viderLaMain(motMain, sac);
			} else {
				joueur.setMainJoueur(saveMain);
				plateau.setPlateau(plateauSave);
				messageErreur = "Placement du Mot incorrect";
				return messageErreur;
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
				messageErreur = "Placement du Premier Mot incorrect";
				return messageErreur;
			}
		}
		
		if(joueur.getTourJoueur() == false) {
			socket.envoyerDonnee(joueur, plateau, sac);
		}
		
		return messageErreur;
			
	}
	
	public void finDuJeu() {
		if(joueur.getNbreTourPasser() == 6) {
			System.out.println("Vous avez mis fin à la partie");
			if(joueur.getScore() < joueur.getScoreAdverse()) {
				System.out.println("L'adversaire a gagné!");
			}
			else if(joueur.getScore() > joueur.getScoreAdverse()) {
				System.out.println("Vous avez gagné!!!");
			}
			else {
				System.out.println("Egalité!");
			}
		}
		else if(joueur.getMainJoueur().isEmpty()) {
			
		}
	}
	/**
	 * Active l'interaction entre la vue et le code
	 * @param vue à ajouter au controller
	 */
	public void addView(ScrabbleView vue) {
		this.vue = vue;
		
	}

}