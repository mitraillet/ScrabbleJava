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
	
	private Bouton jouerJButton = new Bouton(boutonJouer, boutonJouerHoover);
	private Bouton melangeJButton = new Bouton(boutonMelanger, boutonMelangerHoover);
	private Bouton passerJButton = new Bouton(boutonPasser, boutonPasserHoover);

	private Box buttonBox = Box.createVerticalBox();
	
	public ScrabbleViewGUI(Plateau plateau, Joueur joueur, Sac sac,ScrabbleController controller) {
		super(plateau, joueur, controller);
		this.sac = sac;
		
		fenetreJeu.setSize(1000, 940);
		fenetreJeu.setPreferredSize(new Dimension(1000, 940));
		fenetreJeu.setTitle("Scrabble");
		fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreJeu.setLocationRelativeTo(null);
		fenetreJeu.setResizable(false);
		fenetreJeu.setAlwaysOnTop(true);
		
		updateBouton(sac);

		updatePlateau();

		updateMain();

		container.setBackground(new Color(253, 245, 230));
		
		fenetreJeu.setContentPane(container);
		fenetreJeu.setVisible(true);
	}

	private void updateBouton(Sac sac) {
		buttonBox.add(jouerJButton);
		melangeJButton.addActionListener(new Melanger());
		melangeJButton.setEnabled(sac.tailleContenuSac() != 0);
		buttonBox.add(melangeJButton);
		buttonBox.add(passerJButton);
		
		container.add(buttonBox);
	}
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
				if(plateau.getPlateau(h, j).getLettre() == null) {
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
		container.remove(2);
		container.remove(1);
		container.remove(0);
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
		JFrame fenetreMelange;
		JPanel copyMain;
		JButton melange;
		JButton annuler;
		String label = "";
		
		@Override
		public void actionPerformed(ActionEvent e) {
			fenetreMelange = new JFrame();
			copyMain = new JPanel();
			melange = new JButton("Mélanger");
			annuler = new JButton("Annuler");
			
			fenetreMelange.setSize(500, 160);
			fenetreMelange.setPreferredSize(new Dimension(500, 160));
			fenetreMelange.setTitle("Mélange");
			fenetreMelange.setLocationRelativeTo(null);
			fenetreMelange.setResizable(false);
			fenetreMelange.setAlwaysOnTop(true);
			fenetreMelange.setBackground(new Color(253, 245, 230));
			fenetreMelange.setVisible(true);
			
			melange.addActionListener(new Melange());
			annuler.addActionListener(new Annule());
			
			ajouteMainMelange(copyMain);
			
			copyMain.setBackground(new Color(253, 245, 230));
			copyMain.add(melange);
			copyMain.add(annuler);
			
			fenetreMelange.setContentPane(copyMain);
			
		}
		
		private void ajouteMainMelange(JPanel box) {
			
			Box boxMain = Box.createHorizontalBox();
			
			JPanel mainBox11 = new JPanel(new GridLayout(1,7));	
			
			JCheckBox img;
			
				char labelIMG = joueur.getLabelLettreMain(0);
				event e = new event();
				if(labelIMG == '?') {
					img = new JCheckBox(new ImageIcon("ressource/image/lettre/joker.png", "?"));
					img.setSelectedIcon(new ImageIcon("ressource/image/lettre/jokerSelected.png", "?"));
				}
				else {
					img = new JCheckBox(new ImageIcon("ressource/image/lettre/" + labelIMG + ".png", labelIMG +""));
					img.setSelectedIcon(new ImageIcon("ressource/image/lettre/" + labelIMG + "Selected.png", labelIMG +""));
				}
				
			JCheckBox img1;
			
				labelIMG = joueur.getLabelLettreMain(1);
				event e1 = new event();
				if(labelIMG == '?') {
					img1 = new JCheckBox(new ImageIcon("ressource/image/lettre/joker.png", "?"));
					img1.setSelectedIcon(new ImageIcon("ressource/image/lettre/jokerSelected.png", "?"));
				}
				else {
					img1 = new JCheckBox(new ImageIcon("ressource/image/lettre/" + labelIMG + ".png", labelIMG +""));
					img1.setSelectedIcon(new ImageIcon("ressource/image/lettre/" + labelIMG + "Selected.png", labelIMG +""));
				}

			JCheckBox img2;
			
				labelIMG = joueur.getLabelLettreMain(2);
				event e2 = new event();
				if(labelIMG == '?') {
					img2 = new JCheckBox(new ImageIcon("ressource/image/lettre/joker.png", "?"));
					img2.setSelectedIcon(new ImageIcon("ressource/image/lettre/jokerSelected.png", "?"));
				}
				else {
					img2 = new JCheckBox(new ImageIcon("ressource/image/lettre/" + labelIMG + ".png", labelIMG +""));
					img2.setSelectedIcon(new ImageIcon("ressource/image/lettre/" + labelIMG + "Selected.png", labelIMG +""));
				}
				
			JCheckBox img3;
			
				labelIMG = joueur.getLabelLettreMain(3);
				event e3 = new event();
				
				if(labelIMG == '?') {
					img3 = new JCheckBox(new ImageIcon("ressource/image/lettre/joker.png", "?"));
					img3.setSelectedIcon(new ImageIcon("ressource/image/lettre/jokerSelected.png", "?"));
				}
				else {
					img3 = new JCheckBox(new ImageIcon("ressource/image/lettre/" + labelIMG + ".png", labelIMG +""));
					img3.setSelectedIcon(new ImageIcon("ressource/image/lettre/" + labelIMG + "Selected.png", labelIMG +""));
				}
				
			JCheckBox img4;
			
				labelIMG = joueur.getLabelLettreMain(4);
				event e4 = new event();
				if(labelIMG == '?') {
					img4 = new JCheckBox(new ImageIcon("ressource/image/lettre/joker.png", "?"));
					img4.setSelectedIcon(new ImageIcon("ressource/image/lettre/jokerSelected.png", "?"));
				}
				else {
					img4 = new JCheckBox(new ImageIcon("ressource/image/lettre/" + labelIMG + ".png", labelIMG +""));
					img4.setSelectedIcon(new ImageIcon("ressource/image/lettre/" + labelIMG + "Selected.png", labelIMG +""));
				}
				
			JCheckBox img5;
			
				labelIMG = joueur.getLabelLettreMain(5);
				event e5 = new event();
				if(labelIMG == '?') {
					img5 = new JCheckBox(new ImageIcon("ressource/image/lettre/joker.png", "?"));
					img5.setSelectedIcon(new ImageIcon("ressource/image/lettre/jokerSelected.png", "?"));
				}
				else {
					img5 = new JCheckBox(new ImageIcon("ressource/image/lettre/" + labelIMG + ".png", labelIMG +""));
					img5.setSelectedIcon(new ImageIcon("ressource/image/lettre/" + labelIMG + "Selected.png", labelIMG +""));
				}

			JCheckBox img6;
			
				labelIMG = joueur.getLabelLettreMain(6);
				event e6 = new event();
				if(labelIMG == '?') {
					img6 = new JCheckBox(new ImageIcon("ressource/image/lettre/joker.png", "?"));
					img6.setSelectedIcon(new ImageIcon("ressource/image/lettre/jokerSelected.png", "?"));
				}
				else {
					img6 = new JCheckBox(new ImageIcon("ressource/image/lettre/" + labelIMG + ".png", labelIMG +""));
					img6.setSelectedIcon(new ImageIcon("ressource/image/lettre/" + labelIMG + "Selected.png", labelIMG +""));
				}
			
				img.setBackground(new Color(253, 245, 230));
				img.addItemListener(e);
				img1.setBackground(new Color(253, 245, 230));
				img1.addItemListener(e1);
				img2.setBackground(new Color(253, 245, 230));
				img2.addItemListener(e2);
				img3.setBackground(new Color(253, 245, 230));
				img3.addItemListener(e3);
				img4.setBackground(new Color(253, 245, 230));
				img4.addItemListener(e4);
				img5.setBackground(new Color(253, 245, 230));
				img5.addItemListener(e5);
				img6.setBackground(new Color(253, 245, 230));
				img6.addItemListener(e6);

				mainBox11.add(img);
				mainBox11.add(img1);
				mainBox11.add(img2);
				mainBox11.add(img3);
				mainBox11.add(img4);
				mainBox11.add(img5);
				mainBox11.add(img6);
			
		 	boxMain.add(mainBox11);
		 	box.add(boxMain);
		}
		class event implements ItemListener {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getItemSelectable() != null) {
					label += ((AbstractButton) e.getItemSelectable()).getSelectedIcon().toString();
					System.out.println(label);
				}
				else {
					
					String chNew = "";
					String carAsup = ((AbstractButton) e.getItemSelectable()).getSelectedIcon().toString();
		         
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
			}
			
		}
	}
}