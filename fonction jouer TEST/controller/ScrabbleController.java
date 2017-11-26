package controller;

import java.util.ArrayList;
import java.util.List;

import scrabble.Dictionnaire;
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
	Dictionnaire dictionnaire;
	ScrabbleView vue;
	public ScrabbleController(Plateau plateau, Joueur joueur, Sac sac, Dictionnaire dictionnaire) {
		this.plateau = plateau;
		this.joueur = joueur;
		this.sac = sac;
		this.dictionnaire = dictionnaire;
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
	 * Permet de placer un mot sur le plateau
	 * @param x La position x de la première lettre
	 * @param y La position y de la première lettre
	 */
	public void poserMot(int x, int y, char orientation, String mot){
			Case[][] plateauSave = (plateau.plateau).clone(); //Sauvegarde du plateau
			List<Lettre> saveMain = joueur.mainJoueur; //Sauvegarde de la main
			List<Lettre> motJoue = new ArrayList<Lettre>(); //La liste de lettre du mots sur le plateau
			List<Lettre> motMain = new ArrayList<Lettre>(); //liste des lettres enlevée de la main
			
			int xPos = 0; //incrément Position x
			int yPos = 0; //incrément Position y
			String[] motArray = mot.split(""); //String séparé en Array de lettre
			
			int j = 0; //variable incrémentale 
			boolean lettreTrouve = false; //Flag, lettre trouvée
			Lettre tempLettre = null; //Lettre temporaire
			
			@SuppressWarnings("unused") //Utilisé dans certain if(){}
			boolean tourStop = false; //Flag, true si erreur
			
			//Gère les lettres de la main
			for(int i = 0; i < motArray.length; i++) {
				lettreTrouve = false;
				j = 0;
				
				//Tant qu' on n'a pas itéré toute la main et que la lettre n'est pas trouvée
				while(j < 7 && lettreTrouve == false) {
					if(motArray[i].charAt(0) != (joueur.getLabelLettreMain(j))) {
						tempLettre = null;
					} else { 
						tempLettre = joueur.getLettreMain(j);
						lettreTrouve = true;
					}
					j++;
				}
				
				motJoue.add(tempLettre);
			}
			
			//Gère la pose des Lettres
			for(int i = 0; i < mot.length(); i++) {
				try {
					if(plateau.plateau[x + xPos][y - yPos].getLettre() == null) {
						plateau.plateau[x + xPos][y - yPos].setLettre(motJoue.get(i));
						motMain.add(motJoue.get(i));
					} else {
						try {
							if(plateau.plateau[x + xPos][y - yPos].getLabelCase() != motArray[i].charAt(0)) {
								tourStop = true;
								return;
							}
						} catch (NullPointerException e){
							System.out.println("Erreur : motJoue vide !");
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					joueur.mainJoueur = saveMain;
					plateau.plateau = new Case [15][15];
					plateau.plateau = plateauSave.clone();
					System.out.println(plateau);
					System.out.println(joueur);
					System.out.println("Erreur : votre mot sort du plateau");
					return;
				}

				if(orientation == 'h') {
					xPos ++;
				} else if (orientation == 'v') {
					yPos ++;
				} else {
					System.out.println("Erreur");
					plateau.plateau = new Case [15][15];
					plateau.plateau = plateauSave.clone();
					return;
				}
				
			}
			
			//Envoie les objets lettres au plateau
			plateau.motJoue = motJoue;
			
			if(plateau.debutPartie == true) {
				if(plateau.verificationPeripherique(x, y, orientation, motMain)) {
					for (int i = 0; i < motMain.size(); i++) {
						if(motJoue.get(i) != null){
							joueur.mainJoueur.remove(motMain.get(i));
						}
					}
					joueur.pioche(sac);
				} else {
					joueur.mainJoueur = saveMain;
					plateau.plateau = new Case [15][15];
					plateau.plateau = plateauSave.clone();
					System.out.println(plateau);
					System.out.println(joueur);
					System.out.println("\nPlacement du Mot incorrect");
				}
			} else {
				//Enlève les lettres jouée de la main
				if(plateau.checkPremierMot(x, y, orientation)) {
					for (int i = 0; i < motMain.size(); i++) {
						joueur.mainJoueur.remove(motMain.get(i));
					}
					joueur.pioche(sac);
				} else {
					joueur.mainJoueur = saveMain;
					plateau.plateau = new Case [15][15];
					plateau.plateau = plateauSave.clone();
					System.out.println(plateau);
					System.out.println(joueur);
					System.out.println("\nPlacement du Premier Mot incorrect");
				}
			}
			
			motJoue = new ArrayList<Lettre>(); //Remets à zéro la variable
			
	}
	
	
	public void addView(ScrabbleView vue) {
		this.vue = vue;
		
	}

}