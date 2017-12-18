package view;

import java.awt.*;

import javax.swing.*;

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Lettre;
import scrabble.Plateau;
import scrabble.Sac;

/**
 * 
 * @author Fauconnier/Henriquet
 * Classe contenant la fenêtre de fin de partie
 */
public class FentreFinDePartie {
	private Joueur joueur;
	private	Font font = new Font("Serif", Font.BOLD, 20);
	private Color color = new Color(253, 245, 230);
	
	/**
	 * Initialise la fenêtre de fin de partie
	 */
	public FentreFinDePartie() {
		JFrame fenetreFinDePartie = new JFrame();
		fenetreFinDePartie.setSize(1050, 990);
		fenetreFinDePartie.setPreferredSize(new Dimension(1050, 990));
		fenetreFinDePartie.setTitle("Fin de la partie");
		fenetreFinDePartie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreFinDePartie.setLocationRelativeTo(null);
		fenetreFinDePartie.setResizable(false);
		
		JPanel container = new JPanel();
		container.setSize(1050, 990);
		container.setPreferredSize(new Dimension(1050, 990));
		Box affichageResteMain = Box.createHorizontalBox();
		
		JLabel msgVictoire = new JLabel();
		msgVictoire.setFont(font);
		
		if(joueur.getNbreTourPasser() == 3 && joueur.getNbreTourPasserAdverse() == 3) { //Si les joueurs passe leur tour
			String msgVictoireString = "<html> Vous et votre adversaire ne pouvez plus jouer. La partie est donc terminées. <br> Résultat: <br>";
			if(joueur.getScore() > joueur.getScoreAdverse()) {
				msgVictoireString += "Vous avez gagné !<br>";
			} 
			else if (joueur.getScore() < joueur.getScoreAdverse()) {
				msgVictoireString += "Votre adversaire a gagné la partie...<br>";
			} 
			else {
				msgVictoireString += "Egalité !<br>";
			}
			msgVictoireString += "Votre score est de " + joueur.getScore() + "points.<br>";
			msgVictoireString += "Le score de votre adversaire est de" + joueur.getScoreAdverse() + "points.<br>";
			msgVictoireString += "</html>";
			
			msgVictoire.setText(msgVictoireString);
			
			affichageResteMain.setPreferredSize(new Dimension(400, 80));
			affichageResteMain.setSize(400, 80);
			
			JLabel resteMainMsg = new JLabel("<html>Vous aviez encore dans votre main : <br></html>");
			JPanel mainPanel = new JPanel(new GridLayout(1,7));
			mainPanel.setBackground(color);
			
			for(int i = 0; i < joueur.getSizeMainJoueur(); i++) {
				JLabel img;
				char labelIMG = joueur.getLabelLettreMain(i);
				
				if(labelIMG == '?') {
					img = new JLabel(new ImageIcon("ressource/image/lettre/joker.png", "joker"));
				}
				else {
					img = new JLabel(new ImageIcon("ressource/image/lettre/" + labelIMG + ".png", labelIMG +""));
				}
				mainPanel.add(img);
			}
			affichageResteMain.add(resteMainMsg);
			affichageResteMain.add(mainPanel);
			
		}
		else {
			String msgVictoireString ;
			if(joueur.getMainJoueur().isEmpty()) { // si la main du joueur est vide
				msgVictoireString = "<html> Vous avez vidé votre main. <br> Résultat: <br>";
			}
			else {
				msgVictoireString = "<html> Vous adversaire a vidé sa main. <br> Résultat: <br>";
			}
			if(joueur.getScore() > joueur.getScoreAdverse()) {
				msgVictoireString += "Vous avez gagné !<br>";
			} 
			else if (joueur.getScore() < joueur.getScoreAdverse()) {
				msgVictoireString += "Votre adversaire a gagné la partie...<br>";
			} 
			else {
				msgVictoireString += "Egalité !<br>";
			}
			msgVictoireString += "Votre score est de " + joueur.getScore() + "points.<br>";
			msgVictoireString += "Le score de votre adversaire est de" + joueur.getScoreAdverse() + "points.<br>";
			msgVictoireString += "</html>";
			
			msgVictoire.setText(msgVictoireString);
			
			affichageResteMain.setPreferredSize(new Dimension(400, 80));
			affichageResteMain.setSize(400, 80);
			if(joueur.getMainJoueur().isEmpty()) {
				JLabel resteMainMsg = new JLabel("<html>Votre adversaire avait encore en main : <br></html>");
				JPanel mainPanel = new JPanel(new GridLayout(1,7));
				mainPanel.setBackground(color);
				
				for(int i = 0; i < joueur.getSizeMainJoueurAdverse(); i++) {
					JLabel img;
					char labelIMG = joueur.getLabelLettreMainAdverse(i);
					
					if(labelIMG == '?') {
						img = new JLabel(new ImageIcon("ressource/image/lettre/joker.png", "joker"));
					}
					else {
						img = new JLabel(new ImageIcon("ressource/image/lettre/" + labelIMG + ".png", labelIMG +""));
					}
					mainPanel.add(img);
				}
				affichageResteMain.add(resteMainMsg);
				affichageResteMain.add(mainPanel);
			}
			else {
				JLabel resteMainMsg = new JLabel("<html>Vous aviez encore en main : <br></html>");
				JPanel mainPanel = new JPanel(new GridLayout(1,7));
				mainPanel.setBackground(color);
				
				for(int i = 0; i < joueur.getSizeMainJoueur(); i++) {
					JLabel img;
					char labelIMG = joueur.getLabelLettreMain(i);
					
					if(labelIMG == '?') {
						img = new JLabel(new ImageIcon("ressource/image/lettre/joker.png", "joker"));
					}
					else {
						img = new JLabel(new ImageIcon("ressource/image/lettre/" + labelIMG + ".png", labelIMG +""));
					}
					mainPanel.add(img);
				}
				affichageResteMain.add(resteMainMsg);
				affichageResteMain.add(mainPanel);
			}
		}
		container.add(msgVictoire);
		container.add(affichageResteMain);
		JLabel remarque = new JLabel();
		remarque.setText("* Si vous fermez cette fenêtre, le jeu s'arrêtera. Pour rejouer, relancer le jeu.");
		container.add(remarque);
		fenetreFinDePartie.setContentPane(container);
	}

}
