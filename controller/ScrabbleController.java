package controller;

import java.util.ArrayList;
import java.util.List;

import scrabble.Joueur;
import scrabble.Lettre;
import scrabble.Plateau;
import scrabble.Sac;
import scrabble.Case;
import view.ScrabbleView;

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
	}

	/**
	 * 
	 * Permet de placer un mot sur le plateau
	 * @param x La position x de la première lettre
	 * @param y La position y de la première lettre
	 * @param orientation sens d'écriture du mot
	 * @param mot Le mot à poser sur le plateau
	 * @param saveMainJoueur Une sauvegarde de la main pour éviter une perte de Lettre
	 * @return un Array d'oBjet pour pouvoir faire passer les messages d'erreurs et si l'opération s'est bien passé
	 */
	public String poserMot(int x, int y, char orientation, String mot, List<Lettre> saveMainJoueur){
		//TODO modif le code pour que les vérif se passe au niveau du controller
		String messageErreur = null;
		
		Case[][] plateauJeu = plateau.plateau; //Plateau de jeu
		Case[][] plateauSave = plateau.copyPlateau(); //Sauvegarde du plateau
		List<Lettre> saveMain = saveMainJoueur;
		List<Lettre> motJoue = new ArrayList<Lettre>(); //La liste de lettre du mots sur le plateau
		List<Lettre> motMain = new ArrayList<Lettre>(); //liste des lettres enlevée de la main

		String[] motArray = mot.split(""); //String séparé en Array de lettre
		
		joueur.verifierLettreMain(x, y, orientation, plateauJeu, motArray, motJoue);
		
		if(joueur.poserMotPlateau(x, y, motJoue, motMain, motArray, plateauSave, 
				saveMain, orientation, plateauJeu) == false) {
			joueur.setMainJoueur(saveMain);
			plateau.plateau = plateauSave;
			messageErreur = "Mot impossible à placer";
			return messageErreur;
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
				messageErreur = "Placement du Mot incorrect";
				return messageErreur;
			}
			
		} else {
			//Enlève les lettres jouée de la main
			if(plateau.checkPremierMot(x, y, orientation, joueur, motMain, motJoue)) {
				plateau.setScoreJoueur(joueur);
				joueur.viderLaMain(motMain, sac);	
			} else {
				joueur.setMainJoueur(saveMain);
				plateau.plateau = plateauSave;
				messageErreur = "Placement du Premier Mot incorrect";
				return messageErreur;
			}
		}
		return messageErreur;
			
	}
	public void addView(ScrabbleView vue) {
		this.vue = vue;
		
	}

}