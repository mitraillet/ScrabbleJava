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
			List<Lettre> motJoue = new ArrayList<Lettre>(); //La liste de lettre jouée depuis la main
			int xPos = 0; //incrément Position x
			int yPos = 0; //incrément Position y
			String[] motArray = mot.split(""); //String séparé en Array de lettre
			
			int j = 0; //variable incrémentale 
			boolean lettreTrouve = false; //Flag, lettre trouvée
			Lettre tempLettre = null; //Lettre temporaireÒ
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
						System.out.println("Lettre : " + tempLettre.getLabel() );
					}
					j++;
				}
				
				motJoue.add(tempLettre);
				System.out.println("taille " + motJoue.size());
			}
			
			//Gère la pose des Lettres
			for(int i = 0; i < mot.length(); i++) {
				if(plateau.plateau[x + xPos][y - yPos].getLettre() == null) {
					plateau.plateau[x + xPos][y - yPos].setLettre(motJoue.get(i));
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

				if(orientation == 'h') {
					xPos ++;
				} else if (orientation == 'v') {
					yPos ++;
				} else {
					System.out.println("Erreur");
					return;
				}
				
			}
			
			//Envoie les objets lettres au plateau
			plateau.motJoue = motJoue;
			
			//Enlève les lettres jouée de la main
			if(plateau.verificationPeripherique(x, y, orientation)) {
				for (int i = 0; i <motJoue.size(); i++) {
					if(motJoue.get(i) != null){
						joueur.mainJoueur.remove(motJoue.get(i));
					}
				}
				joueur.pioche(sac);
			} else {
				System.out.println("Placement du Mot incorrect");
				plateau.plateau = new Case [15][15];
				plateau.plateau = plateauSave.clone();
			}
			
			motJoue = new ArrayList<Lettre>(); //Remets à zéro la variable
			
	}
	
	
	public void addView(ScrabbleView vue) {
		this.vue = vue;
		
	}

}