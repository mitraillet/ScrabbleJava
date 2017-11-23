package view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


import scrabble.Joueur;
import scrabble.Plateau;
import scrabble.Dictionnaire;
import controller.ScrabbleController;


public class ScrabbleViewConsole extends ScrabbleView implements Observer{
	protected Scanner sc;
	protected Dictionnaire dictionnaire;
	
	public ScrabbleViewConsole(Plateau plateau, Joueur joueur, ScrabbleController controller) {
		// TODO Auto-generated constructor stub
		super(plateau, joueur, controller);
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();	
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(plateau);
		System.out.println(joueur);
		printHelp();
		
	}
	
	private void printPlateauMain(){
		System.out.println(plateau);
		System.out.println(joueur);
		printHelp();
	}
	
	private class ReadInput implements Runnable {
		public void run() {
			while(true){
				try{
					String c = sc.next();
					switch(c){
					case "M" :
						affiche("Mélanger");
						String label = sc.next();
						controller.melangeMain(label);
						break;
					case "P" :
						affiche("Passer");
						break;
					case "J" : 
						affiche("Jouer");
						String mot1 = sc.next();
						if(plateau.verification(mot1)){
							int tailleMotDepuisLaMain = 0;
							affiche("Mot Correct");
							for (int i=0; i < joueur.getSizeMainJoueur(); i++) {
								if(mot1.contains(joueur.getLabelLettreMain(i) + "")) {
									tailleMotDepuisLaMain++;
								}
							}
							if(mot1.length() <= tailleMotDepuisLaMain) {
								//controller.jouer();
								affiche("Correct");
							}
							else {
								printPlateauMain();
								affiche(mot1);
								affiche("Vous ne possédez pas les lettres appropriées pour ce mot");
							}
						}
						else {
							printPlateauMain();
							affiche(mot1 + " est un mot incorrect");
						}
						break;
					default : 
						affiche("Opération incorrecte");
				}
				}
				catch(InputMismatchException e){
					printPlateauMain();
					affiche("Format d'input incorrect");
				}
			}
		}


	}
	/**
	 * Méthode pour afficher les lettres et les commandes associées
	 */
	private void printHelp(){
		affiche("Pour mélanger : M + lettre à mélanger.");
		affiche("Pour jouer : J + mot à jouer.");
		affiche("Pour passer : P");
	}
	/**
	 * Méthode pour afficher en console
	 */
	@Override
	public void affiche(String string) {
		System.out.println(string);
	}

}
