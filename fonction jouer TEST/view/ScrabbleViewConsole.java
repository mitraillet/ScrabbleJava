package view;

import java.util.*;


import scrabble.Joueur;
import scrabble.Lettre;
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
						if(plateau.verification(mot1)) {
							/*int tailleMotDepuisLaMain = 0;
							affiche("Mot Correct");
							String[] motArray = mot1.split("");
							
							for (int i=0; i < joueur.getSizeMainJoueur(); i++) {
								if(mot1.contains(joueur.getLabelLettreMain(i) + "")) {
									tailleMotDepuisLaMain++;
								}
							}
							
							if(mot1.length() <= tailleMotDepuisLaMain) {
								//controller.jouer();
								for (int i=0; i < motArray.length; i++) {
									for(int j = 0; j < joueur.getSizeMainJoueur(); j++) {
										if(motArray[i].charAt(0) == joueur.getLabelLettreMain(j)) {
											plateau.motJoue.add(joueur.getLettreMain(j));
											tailleMotDepuisLaMain++;
										}
									}
								}*/
								
								affiche("Mot Correct");
								affiche("Veuillez entrer la position X");
								int intPosX = Integer.parseInt((sc.next()));
								affiche("Veuillez entrer la position Y");
								int intPosY = Integer.parseInt((sc.next()));
								affiche("Veuillez entrer l'orientation (h ou v)");
								char orientation = (sc.next()).charAt(0);
								controller.poserMot(intPosX, intPosY, orientation, mot1);
								//printPlateauMain();
							/*}
							else {
								printPlateauMain();
								affiche(mot1);
								affiche("Vous ne possédez pas les lettres appropriées pour ce mot");
							}*/
							//printPlateauMain();
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
