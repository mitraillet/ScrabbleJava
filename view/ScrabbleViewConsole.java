/**
 * Package modèle
 */
package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


import scrabble.Joueur;
import scrabble.Lettre;
import scrabble.MessageDErreur;
import scrabble.Plateau;
import scrabble.Sac;
import controller.ScrabbleController;

/**
 * Classe gérant le jeu en console
 * @author Fauconnier/Henriquet
 */
public class ScrabbleViewConsole extends ScrabbleView implements Observer{
	
	/**
	 * Scanner pour récupérer les entrées de l'utilisateurs
	 */
	protected Scanner sc;
	
	/**
	 * Constructeur de la classe ScrabbleViewConsole
	 * @param plateau le plateau de jeu
	 * @param joueur le joueur
	 * @param sac le sac
	 * @param controller le controller
	 */
	public ScrabbleViewConsole(Plateau plateau, Joueur joueur, Sac sac, ScrabbleController controller) {
		super(plateau, joueur, sac, controller);
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();	
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(plateau);
		System.out.println(sac);
		System.out.println(joueur);
		printHelp();
		
		if(MessageDErreur.getMsgDErreur().length() != 0){
			affiche(MessageDErreur.getMsgDErreur());
			MessageDErreur.setMsgDErreur("");
		}
		else {
			MessageDErreur.setMsgDErreur("");
		}
		if(joueur.getFinPartie() == true) {
			afficheGagnant();
		}
		
	}
	
	/**
	 * Affiche le jeu en console (plateau + lettres restantes dans le sac + main/joueur + commandes)
	 */
	private void printPlateauMain(){
		System.out.println(plateau);
		System.out.println(sac);
		System.out.println(joueur);
		printHelp();
	}
	
	/**
	 * Gère la partie en console
	 * @author Fauconnier/Henriquet
	 */
	private class ReadInput implements Runnable {
		public void run() { //TODO modif le code pour que les vérif se passe au niveau du controller
			while(!joueur.getFinPartie()){
				try{
					
					String c = sc.next();
					
					if(joueur.getTourJoueur() == true) {
						switch(c){
						case "M" :
							if(sac.tailleContenuSac() != 0) {
								affiche("Mélanger");
								String label = sc.next();
								controller.melangeMain(label);
								break;
							}
						case "P" :
							affiche("Passer");
							controller.passer();
							break;
						case "J" : 
							affiche("Jouer"); 
							String mot1 = sc.next();
							List<Lettre> saveMain = joueur.copieMainJoueur();
							
							int joker = joueur.detecteJoker(mot1);
							String motJoker = mot1;
							
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
								
								int intPosX = -1;
								int intPosY = -1;
								
								while(true) {
									try {
										intPosX = sc.nextInt();
										break;
									} catch(InputMismatchException e){
										affiche("Erreur, veuillez entrer un nombre valide");
										sc.next(); // Vide le scanner 
										continue;
									}
								}
								
								affiche("Veuillez entrer la position Y");
								
								while(true) {
									try {
										intPosY = sc.nextInt();
										break;
									} catch(InputMismatchException e){
										affiche("Erreur, veuillez entrer un nombre valide");
										sc.next(); // Vide le scanner 
										continue;
									}
								}
								
								affiche("Veuillez entrer l'orientation (h ou v)");
								char orientation = '/';
								
								while(true) {
									orientation = (sc.next()).charAt(0);
									if(orientation == 'h' || orientation == 'v') {
										break;
									} else {
										affiche("L'orientation est incorrecte. (h / v)");
										continue;
									}
								}
								
								controller.poserMot(intPosX, intPosY, orientation, mot1, saveMain, joker, motJoker);
								if(MessageDErreur.getMsgDErreur().length() != 0){
									affiche(MessageDErreur.getMsgDErreur());
									MessageDErreur.setMsgDErreur("");
								}
								else {
									MessageDErreur.setMsgDErreur("");
								}
							}
							else {
								joueur.setMainJoueur(saveMain);
								printPlateauMain();
								affiche(mot1 + " est un mot incorrect");
							}
							break;
						default : 
							affiche("Opération incorrecte");
				}
				} else {
					affiche("C'est le tour de l'autre joueur");
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
	* Affiche le gagnant
	*/
	private void afficheGagnant() {
		affiche("\n---------------------------------------------------------\n");
		if(joueur.getScore() > joueur.getScoreAdverse()) {
				affiche("Vous avez gagné !");
		} else if (joueur.getScore() < joueur.getScoreAdverse()) {
				affiche("Votre adversaire a gagné la partie.");
		} else {
				affiche("Egalité !");
		}
		
		affiche("\n" + "Votre score : " + joueur.getScore());
		affiche("Le score de votre adversaire : " + joueur.getScoreAdverse() + "\n");
		affiche("Vous aviez encore dans votre main : ");
		
		String main = "";
		for(int i = 0; i < joueur.getSizeMainJoueur(); i++) {
			main += joueur.getMainJoueur().get(i).getLabel() + " ";
		}
		affiche(main);
	}
	
	/**
	 * Méthode pour afficher les lettres et les commandes associées
	 */
	private void printHelp(){ // TODO ajouté affichage si sac plein uniquement
		affiche("Pour jouer : J + mot à jouer.");
		if(sac.tailleContenuSac() != 0) {
			affiche("Pour mélanger : M + lettre à mélanger.");
		}
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
