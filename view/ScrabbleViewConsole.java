package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


import scrabble.Joueur;
import scrabble.Lettre;
import scrabble.Plateau;
import controller.ScrabbleController;


public class ScrabbleViewConsole extends ScrabbleView implements Observer{
	protected Scanner sc;
	
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
						List<Lettre> saveMain = joueur.getMainJoueur(); //Sauvegarde de la main
						
						int joker = joueur.detecteJoker(mot1);
						
						if(joker == 1) {
							affiche("Veuillez entrer le label du joker :");
							char joker1 = '/';
							char joker2 = '/';
							while(joker1 == '/') {
								joker1 = joueur.testJoker(sc.next());
							}
							mot1 = joueur.setJokerMain(joker1, joker2, mot1);
							
						} else if (joker == 2) {
							affiche("Veuillez entrer le label du premier joker :");
							char joker1 = '/';
							char joker2 = '/';
							while(joker1 == '/') {
								joker1 = joueur.testJoker(sc.next());
							}
							affiche("Veuillez entrer le label du deuxième joker :");
							while(joker2 == '/') {
								joker2 = joueur.testJoker(sc.next());
							}
							mot1 = joueur.setJokerMain(joker1, joker2, mot1);
						}
						
						if(plateau.verification(mot1)) {
							affiche("Mot Correct");
							affiche("Veuillez entrer la position X");
							int intPosX = Integer.parseInt((sc.next()));
							affiche("Veuillez entrer la position Y");
							int intPosY = Integer.parseInt((sc.next()));
							affiche("Veuillez entrer l'orientation (h ou v)");
							char orientation = (sc.next()).charAt(0);
							
							controller.poserMot(intPosX, intPosY, orientation, mot1, saveMain);
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
