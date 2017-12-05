package ScrabbleLancement;

import javax.xml.xpath.XPathExpressionException;

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Plateau;
import scrabble.Sac;
import view.ScrabbleViewGUI;
//import view.GUISave;

import view.ScrabbleViewConsole;

public class Jeu {

	public Jeu() throws XPathExpressionException {
		// TODO Auto-generated constructor stub
		Plateau plateau = new Plateau();
		Joueur joueur = new Joueur();
		Sac sac = new Sac();
		joueur.pioche(sac);
		
		ScrabbleController controller = new ScrabbleController(plateau, joueur, sac);
		ScrabbleViewConsole console = new ScrabbleViewConsole(plateau, joueur, controller);
		ScrabbleViewGUI GUI = new ScrabbleViewGUI(plateau, joueur, sac, controller);
		
		controller.addView(console);
		controller.addView(GUI);
	}
	public static void main(String args[]) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Jeu();
				} catch (XPathExpressionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
