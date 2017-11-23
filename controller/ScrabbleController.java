package controller;

import java.util.ArrayList;
import java.util.List;

import scrabble.Dictionnaire;
import scrabble.Joueur;
import scrabble.Lettre;
import scrabble.Plateau;
import scrabble.Sac;
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
		char [] charLabel = new char[7];
		for(int i = 0; i < label.length(); i++) {
			charLabel[i] = label.charAt(i);
		}
		
		for(int i = 0; i < charLabel.length; i++) {
			for(int j = 0; j < joueur.mainJoueur.size(); j++) {
				if(charLabel[i] == joueur.getLabelLettreMain(j)) {
					exitLettre.add(joueur.getLettreMain(j));
				}
			}
		}
		joueur.melanger(exitLettre, sac);
	}
	public void addView(ScrabbleView vue) {
		this.vue = vue;
		
	}

}