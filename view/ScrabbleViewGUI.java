package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.*;

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Plateau;
import scrabble.Sac;

public class ScrabbleViewGUI extends ScrabbleView implements ActionListener{
	
	private Sac sac;
	
	private JFrame fenetreJeu = new JFrame();
	private JPanel container = new JPanel();
	private JLabel message = new JLabel(" ");
	
	private ImageIcon plateauIMG = new ImageIcon("ressource/image/plateau/PlateauScrabblePerso.png");
	private ImageIcon boutonJouer = new ImageIcon("ressource/image/BoutonJouer.png");
	private ImageIcon boutonJouerHoover = new ImageIcon("ressource/image/BoutonJouerHoover.png");
	private ImageIcon boutonMelanger = new ImageIcon("ressource/image/BoutonMelanger.png");
	private ImageIcon boutonMelangerHoover = new ImageIcon("ressource/image/BoutonMelangerHoover.png");
	private ImageIcon boutonPasser = new ImageIcon("ressource/image/BoutonPasser.png");
	private ImageIcon boutonPasserHoover = new ImageIcon("ressource/image/BoutonPasserHoover.png");
	
	public ScrabbleViewGUI(Plateau plateau, Joueur joueur, Sac sac,ScrabbleController controller) {
		super(plateau, joueur, controller);
		this.sac = sac;
		
		fenetreJeu.setSize(1000, 940);
		fenetreJeu.setPreferredSize(new Dimension(1000, 940));
		fenetreJeu.setTitle("Scrabble");
		fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreJeu.setLocationRelativeTo(null);
		fenetreJeu.setResizable(false);
		
		updateBouton(sac);

		updatePlateau();
		
		updateScore();

		updateMain();

		container.setBackground(new Color(253, 245, 230));
		
		fenetreJeu.setContentPane(container);
		fenetreJeu.setVisible(true);
	}
	
	/**
	 * Création des boutons
	 * @param sac utilisé pour disabled les boutons
	 */
	private void updateBouton(Sac sac) {
		Box buttonBox = Box.createVerticalBox();
		Bouton jouerJButton = new Bouton(boutonJouer, boutonJouerHoover);
		Bouton melangeJButton = new Bouton(boutonMelanger, boutonMelangerHoover);
		Bouton passerJButton = new Bouton(boutonPasser, boutonPasserHoover);
		buttonBox.add(jouerJButton);
		melangeJButton.addActionListener(new Melanger());
		melangeJButton.setEnabled(sac.tailleContenuSac() != 0);
		buttonBox.add(melangeJButton);
		buttonBox.add(passerJButton);
		
		container.add(buttonBox);
	}
	
	/**
	 *  Création et mise à jour du plateau
	 */
	private void updatePlateau() {
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		JPanel plateauGraphicBackground = new JPanel();
		plateauGraphicBackground.setLayout(new BorderLayout(0,0));
		plateauGraphicBackground.add(new JLabel(plateauIMG));
		plateauGraphicBackground.setPreferredSize(new Dimension(784, 784));
		plateauGraphicBackground.setSize(784, 784);
		
		JPanel plateauGraphic = new JPanel(new GridLayout(15,15));
		plateauGraphic.setPreferredSize(new Dimension(784, 784));
		plateauGraphic.setSize(784, 784);
		
	    int j = 14;
	    for(int i = 0; i < 15; i++) {
			for(int h = 0; h < 15; h++) {
				JLabel pic;
				if(plateau.getCase(h, j).getLettre() == null) {
					pic = new JLabel();
					pic.setOpaque(false);
					pic.setBackground(new Color(0,0,0,0));
					plateauGraphic.add(pic);
				}
				else{
					char labelLettre = plateau.getPlateauLabel(h, j);
					pic = new JLabel(new ImageIcon("ressource/image/lettre/"+ labelLettre +".png", labelLettre +""));
					plateauGraphic.add(pic);
				}
			}
			j--;
		}
	    plateauGraphic.setOpaque(false);
	    plateauGraphic.setBackground(new Color(0,0,0,0));
	    layeredPane.add(plateauGraphicBackground, JLayeredPane.DEFAULT_LAYER);
	    layeredPane.add(plateauGraphic, JLayeredPane.DRAG_LAYER);
	    layeredPane.setSize(784, 784);
	    layeredPane.setPreferredSize(new Dimension(784, 784));
		container.add(layeredPane);
	}
	/**
	 * Création et mise à jour du score
	 */
	private void updateScore() {
		
	}
	
	/**
	 *  Création et mise à jour de la main
	 */
	private void updateMain() {
		
		Box boxMain = Box.createHorizontalBox();
		boxMain.setPreferredSize(new Dimension(400, 80));
		boxMain.setSize(400, 80);
		
		JPanel mainBox11 = new JPanel(new GridLayout(1,7));
		mainBox11.setBackground(new Color(253, 245, 230));
		for(int i = 0; i < joueur.getSizeMainJoueur(); i++) {
			JLabel img;
			char labelIMG = joueur.getLabelLettreMain(i);
			
			if(labelIMG == '?') {
				img = new JLabel(new ImageIcon("ressource/image/lettre/joker.png", "joker"));
			}
			else {
				img = new JLabel(new ImageIcon("ressource/image/lettre/" + labelIMG + ".png", labelIMG +""));
			}
			mainBox11.add(img);
		}
	 	boxMain.add(mainBox11);
	 	container.add(boxMain);
	}

	@Override
	public void affiche(String msg) {
		message.setText(msg);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		container.removeAll();
		updateBouton(sac);
		updatePlateau();
		updateMain();
		fenetreJeu.pack();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * @author Mitraillet
	 * Classe pour le bouton melanger qui va ouvrir une fenêtre pour pouvoir mélanger la main
	 */
	class Melanger implements ActionListener{
		JWindow fenetreMelange;
		JPanel copyMain;
		JButton melange;
		JButton annuler;
		String label = "";
		Boolean isUp = false;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isUp) {
				fenetreMelange = new JWindow();
				copyMain = new JPanel();
				melange = new JButton("Mélanger");
				annuler = new JButton("Annuler");
				
				fenetreMelange.setSize(500, 100);
				fenetreMelange.setPreferredSize(new Dimension(500, 100));
				fenetreMelange.setLocationRelativeTo(null);
				fenetreMelange.setAlwaysOnTop(true);
				fenetreMelange.setVisible(true);
				
				melange.addActionListener(new Melange());
				annuler.addActionListener(new Annule());
				
				ajouteMainMelange(copyMain);
				
				copyMain.setBackground(new Color(253, 245, 230));
				copyMain.add(melange);
				copyMain.add(annuler);
				
				fenetreMelange.setContentPane(copyMain);
				isUp = true;
			}
		}
		
		private void ajouteMainMelange(JPanel box) {
			
			Box boxMain = Box.createHorizontalBox();
			
			JPanel mainBox11 = new JPanel(new GridLayout(1,7));	
			
			JCheckBox img;
			
			for(int i =0; i < joueur.getSizeMainJoueur(); i++) {
				char labelIMG = joueur.getLabelLettreMain(i);
				event e = new event();
				if(labelIMG == '?') {
					img = new JCheckBox(new ImageIcon("ressource/image/lettre/joker.png", "?"));
					img.setSelectedIcon(new ImageIcon("ressource/image/lettre/jokerSelected.png", "?"));
				}
				else {
					img = new JCheckBox(new ImageIcon("ressource/image/lettre/" + labelIMG + ".png", labelIMG +""));
					img.setSelectedIcon(new ImageIcon("ressource/image/lettre/" + labelIMG + "Selected.png", labelIMG +""));
				}
			
				img.setBackground(new Color(253, 245, 230));
				img.addItemListener(e);
				

				mainBox11.add(img);
			}
		 	boxMain.add(mainBox11);
		 	box.add(boxMain);
		}
		class event implements ItemListener {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == 1) {
					label += ((AbstractButton) e.getItemSelectable()).getSelectedIcon().toString();
				}
				else {
					
					String chNew = "";
					String carAsup = ((AbstractButton) e.getItemSelectable()).getIcon().toString();
		         
					int inSup = label.indexOf(carAsup);
		      	
		            chNew = label.substring(0,inSup) + label.substring(inSup +1);
					label = chNew;
				}
			}
		}
		
		/**
		 * 
		 * @author Mitraillet
		 * Classe du bouton annule pour fermer la fenêtre
		 */
		class Annule implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				fenetreMelange.removeAll();
				fenetreMelange.dispose();
				label = "";
				isUp = false;
			}
		}
		/**
		 * 
		 * @author Mitraillet
		 * Permettra de mélanger la main suivant les lettres choisies
		 */
		class Melange implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.melangeMain(label);
				fenetreMelange.removeAll();
				fenetreMelange.dispose();
				label = "";
				isUp = false;
			}
			
		}
	}
}