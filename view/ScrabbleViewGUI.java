/**
 * Package view gérant les vues
 */
package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Observable;

import javax.swing.*;

import controller.ScrabbleController;
import scrabble.Joueur;
import scrabble.Lettre;
import scrabble.MessageDErreur;
import scrabble.Plateau;
import scrabble.Sac;

/**
 * Classe contenant toute l'interface Graphique du Scrabble
 * @author Fauconnier/Henriquet
 */
public class ScrabbleViewGUI extends ScrabbleView implements ActionListener{
	
	private JFrame fenetreJeu = new JFrame();
	private FenetreFinDePartie FenetreFinDePartie;
	private JPanel container = new JPanel();
	
	private ImageIcon plateauIMG = new ImageIcon("ressource/image/plateau/PlateauScrabble.png");
	private ImageIcon sacIMG = new ImageIcon("ressource/image/sacScrabble.png");
	
	private ImageIcon boutonJouer = new ImageIcon("ressource/image/BoutonJouer.png");
	private ImageIcon boutonJouerHoover = new ImageIcon("ressource/image/BoutonJouerHoover.png");
	private ImageIcon boutonJouerDisabled = new ImageIcon("ressource/image/BoutonJouerDisabled.png");
	
	private ImageIcon boutonMelanger = new ImageIcon("ressource/image/BoutonMelanger.png");
	private ImageIcon boutonMelangerHoover = new ImageIcon("ressource/image/BoutonMelangerHoover.png");
	private ImageIcon boutonMelangerDisabled = new ImageIcon("ressource/image/BoutonMelangerDisabled.png");
	
	private ImageIcon boutonPasser = new ImageIcon("ressource/image/BoutonPasser.png");
	private ImageIcon boutonPasserHoover = new ImageIcon("ressource/image/BoutonPasserHoover.png");
	private ImageIcon boutonPasserDisabled = new ImageIcon("ressource/image/BoutonPasserDisabled.png");
	
	private Bouton jouerJButton;
	private Bouton melangeJButton;
	private Bouton passerJButton;
	
	private	Font font = new Font("Serif", Font.BOLD, 20);
	private Color color = new Color(253, 245, 230);
	
	/**
	 * Constructeur de l'interface graphique
	 * @param plateau le plateau créant de part ce qu'il contient le tableau graphique 
	 * @param joueur le joueur qui joue
	 * @param sac le sac dans lequel sont piochées toutes les lettres
	 * @param controller le controller qui va permettre les vérifications
	 */
	public ScrabbleViewGUI(Plateau plateau, Joueur joueur, Sac sac, ScrabbleController controller) {
		super(plateau, joueur, sac, controller);
		
		fenetreJeu.setSize(1050, 990);
		fenetreJeu.setPreferredSize(new Dimension(1050, 990));
		fenetreJeu.setTitle("Scrabble");
		fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreJeu.setLocationRelativeTo(null);
		fenetreJeu.setResizable(false);
		
		updateBouton();

		updatePlateau();

		setEspaceVide();
		
		updateScore();
		
		updateMain();

		updateSac();
		
		container.setBackground(color);
		
		fenetreJeu.setContentPane(container);
		fenetreJeu.setVisible(true);
	}
	
	/**
	 * Création des boutons
	 */
	private void updateBouton() {
		Box buttonBox = Box.createVerticalBox();
		jouerJButton = new Bouton(boutonJouer, boutonJouerHoover, boutonJouerDisabled);
		melangeJButton = new Bouton(boutonMelanger, boutonMelangerHoover, boutonMelangerDisabled);
		passerJButton = new Bouton(boutonPasser, boutonPasserHoover, boutonPasserDisabled);
		jouerJButton.addActionListener(new Jouer());
		jouerJButton.setEnabled(joueur.getTourJoueur());
		buttonBox.add(jouerJButton);
		melangeJButton.addActionListener(new Melanger());
		melangeJButton.setEnabled(sac.tailleContenuSac() != 0 && joueur.getTourJoueur());
		buttonBox.add(melangeJButton);
		passerJButton.addActionListener(new Passer());
		passerJButton.setEnabled(joueur.getTourJoueur());
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
		plateauGraphicBackground.setPreferredSize(new Dimension(836, 836));
		plateauGraphicBackground.setSize(836, 836);
		
		JPanel plateauGraphic = new JPanel(new GridLayout(16,16));
		plateauGraphic.setPreferredSize(new Dimension(836, 836));
		plateauGraphic.setSize(836, 836);
		
	    int j = 14;
	    for (int i = 0; i < 16; i ++) {
	    	JLabel vide = new JLabel();
	    	vide.setOpaque(false);
			vide.setBackground(new Color(0,0,0,0));
			plateauGraphic.add(vide);
	    }
	    for(int i = 0; i < 15; i++) {
	    	JLabel vide = new JLabel();
	    	vide.setOpaque(false);
			vide.setBackground(new Color(0,0,0,0));
			plateauGraphic.add(vide);
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
					if(plateau.getCase(h, j).getValeurCase() == 0) {
						pic = new JLabel(new ImageIcon("ressource/image/lettre/joker/"+ labelLettre +".png", labelLettre +""));
					}
					else {
						pic = new JLabel(new ImageIcon("ressource/image/lettre/"+ labelLettre +".png", labelLettre +""));
					}
					plateauGraphic.add(pic);
				}
			}
			j--;
		}
	    plateauGraphic.setOpaque(false);
	    plateauGraphic.setBackground(new Color(0,0,0,0));
	    layeredPane.add(plateauGraphicBackground, JLayeredPane.DEFAULT_LAYER);
	    layeredPane.add(plateauGraphic, JLayeredPane.DRAG_LAYER);
	    layeredPane.setSize(836, 836);
	    layeredPane.setPreferredSize(new Dimension(836, 836));
		container.add(layeredPane);
	}
	
	/**
	 * Crée un espace vide pour aligner tout ce qui se trouve en dessous du plateau 
	 * avec ce dernier
	 */
	private void setEspaceVide() {
		JPanel espaceVide = new JPanel();
		espaceVide.setBackground(new Color(0,0,0,0));
		espaceVide.setPreferredSize(new Dimension(170, 20));
		espaceVide.setSize(170, 20);
		container.add(espaceVide);
	}
	/**
	 * Création et mise à jour du score
	 */
	private void updateScore() {
		Box scoreBox = Box.createVerticalBox();
		String scoreMsg = "Votre score : " + joueur.getScore();
		String scoreMsgAdv = "Score de l'adversaire : " + joueur.getScoreAdverse();
		JLabel scoreAffiche = new JLabel (scoreMsg);
		JLabel scoreAfficheAdv = new JLabel(scoreMsgAdv);

		scoreAffiche.setFont(font);
		scoreAfficheAdv.setFont(font);
		
		scoreBox.setPreferredSize(new Dimension(250, 60));
		scoreBox.setSize(250, 60);
		scoreBox.setBackground(new Color(0,0,0,0));

		scoreBox.add(scoreAffiche);
		scoreBox.add(scoreAfficheAdv);
		container.add(scoreBox);
	}
	
	/**
	 *  Création et mise à jour de la main
	 */
	private void updateMain() {
		
		Box boxMain = Box.createHorizontalBox();
		boxMain.setPreferredSize(new Dimension(400, 80));
		boxMain.setSize(400, 80);
		
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
	 	boxMain.add(mainPanel);
	 	container.add(boxMain);
	}
	

	/**
	 * Affiche un sac et le nombre de lettre restante dans le sac
	 */
	private void updateSac() {
		JLayeredPane layeredPaneSac = new JLayeredPane();
		
		JPanel sacGraphicBackground = new JPanel();
		sacGraphicBackground.setLayout(new BorderLayout(00,0));
		sacGraphicBackground.add(new JLabel(sacIMG));
		sacGraphicBackground.setPreferredSize(new Dimension(80, 80));
		sacGraphicBackground.setSize(80, 80);
		
		JPanel sacGraphic = new JPanel();
		String sacTailleString = sac.tailleContenuSac() + "";
		JLabel sacTaille = new JLabel (sacTailleString);
		
		sacTaille.setFont(font);
		sacTaille.setHorizontalAlignment(JLabel.CENTER);
		sacTaille.setVerticalAlignment(JLabel.CENTER);
		sacTaille.setBackground(new Color(0,0,0,0));
		sacTaille.setPreferredSize(new Dimension(80, 80));
		
		sacGraphic.add(sacTaille);
		sacGraphic.setOpaque(false);
		sacGraphic.setBackground(new Color(0,0,0,0));
		sacGraphic.setPreferredSize(new Dimension(80, 80));
		sacGraphic.setSize(80, 80);
		
		layeredPaneSac.add(sacGraphicBackground, JLayeredPane.DEFAULT_LAYER);
		layeredPaneSac.add(sacGraphic, JLayeredPane.DRAG_LAYER);
		layeredPaneSac.setPreferredSize(new Dimension(80, 80));
		layeredPaneSac.setSize(80, 80);

		// Pour faire un espace vide
		Box boxbag = Box.createHorizontalBox();
		JPanel vide = new JPanel();
		vide.setPreferredSize(new Dimension(20, 80));
		vide.setSize(20, 80);
		vide.setBackground(new Color(0,0,0,0));
		boxbag.add(vide);
		
		boxbag.add(layeredPaneSac);
		boxbag.setPreferredSize(new Dimension(100, 80));
		boxbag.setSize(100, 80);
		container.add(boxbag);
		
	}
	
	@Override
	public void affiche(String msg) {
		JLabel msgLab = new JLabel(msg);
		msgLab.setFont(font);
		JOptionPane optionPane = new JOptionPane(msgLab);
		optionPane.setIcon(new ImageIcon("ressource/image/erreur.png"));
		JDialog dialog = optionPane.createDialog("Erreur");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);

	}
	
	@Override
	public void update(Observable o, Object arg) {
		container.removeAll();
		updateBouton();
		updatePlateau();
		setEspaceVide();
		updateScore();
		updateMain();
		updateSac();
		fenetreJeu.pack();
		
		if(MessageDErreur.getMsgDErreur().length() != 0){
			affiche(MessageDErreur.getMsgDErreur());
			MessageDErreur.setMsgDErreur("");
		}
		else {
			MessageDErreur.setMsgDErreur("");
		}
		
		if(joueur.getFinPartie()) {
			FenetreFinDePartie = new FenetreFinDePartie();
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//Vide car pas d'action à y implémenter
	}
	
	/**
	 * 
	 * @author Fauconnier/Henriquet
	 * Classe créant la fenêtre pour jouer en GUI
	 */
	class Jouer implements ActionListener {
		JFrame fenetreJoue;
		JButton jouer;
		JButton annulerJoue;
		//formulaire
		JPanel form;
		JPanel formContainer;
		JComboBox<String> orientation;
		JTextField motJoue;
	 	JComboBox<Integer>  x;
	 	JComboBox<Integer>  y;
	 	
	 	// Joker
	 	JFrame jokerFenetre;
		JPanel jokerJPanel;
		char joker1 = '/';
		char joker2 = '/';
		JLabel joker1Label;
		JLabel joker2Label;
		JTextField joker1TxtF;
		JTextField joker2TxtF;
		String mot;
		int joker;
		boolean jokerFenetreIsUp = false;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			jouerJButton.setEnabled(false);
			melangeJButton.setEnabled(false);
			passerJButton.setEnabled(false);
			fenetreJoue = new JFrame();
			form = new JPanel(new GridLayout(8,2));
			formContainer = new JPanel();
			jouer = new JButton("Jouer");
			annulerJoue = new JButton("Annuler");

			fenetreJoue.setUndecorated(true);
			fenetreJoue.setSize(350, 220);
			fenetreJoue.setPreferredSize(new Dimension(350, 220));
			Toolkit tk = Toolkit.getDefaultToolkit();
		    Dimension screenSize = tk.getScreenSize();
		    int screenHeight = screenSize.height;
		    int screenWidth = screenSize.width;
		    fenetreJoue.setLocation(screenWidth / 8, screenHeight /2 - 220);
			fenetreJoue.setAlwaysOnTop(true);
			fenetreJoue.setVisible(true);
			
			jouer.addActionListener(new Joue());
			annulerJoue.addActionListener(new AnnuleJoue());
			
			creationForm();
			
			form.setBackground(color);
			form.add(new JLabel());
			form.add(new JLabel());
			form.add(jouer);
			form.add(annulerJoue);
			
			formContainer.setBackground(color);
			formContainer.add(form);
			fenetreJoue.setContentPane(formContainer);
		}
		/**
		 * Crée la partie formulaire pour jouer
		 */
		public void creationForm() {		
			
			JLabel motJoueLabel = new JLabel("Le mot à jouer : ");
			motJoue = new JTextField();
			motJoue.addKeyListener(new KeyAdapter() {
				  public void keyReleased(KeyEvent k) {
					    if(motJoue.getText().length() != 0) {
					    	if(motJoue.getText().contains("?") || k.getKeyChar() == '?') {
					    		joker = 0;
					    		joker = joueur.detecteJoker(motJoue.getText());
								
								if(joker == 1) {
						    		joker1Label.setVisible(true);
									joker1TxtF.setVisible(true);
									joker2Label.setVisible(false);
									joker2TxtF.setVisible(false);
									joker2TxtF.setText("");

								}
								else if(joker == 2) {
						    		joker1Label.setVisible(true);
									joker1TxtF.setVisible(true);
									joker2Label.setVisible(true);
									joker2TxtF.setVisible(true);
								}
					    	}
					    	else {
					    		joker1Label.setVisible(false);
								joker2Label.setVisible(false);
								joker1TxtF.setVisible(false);
								joker2TxtF.setVisible(false);
								joker1TxtF.setText("");
								joker2TxtF.setText("");

					    	}
					    }
					    else {
				    		joker1Label.setVisible(false);
							joker2Label.setVisible(false);
							joker1TxtF.setVisible(false);
							joker2TxtF.setVisible(false);
							joker1TxtF.setText("");
							joker2TxtF.setText("");		

				    	}
					  }
					});
			
			joker1Label = new JLabel("Premier Joker :");
			joker1Label.setVisible(false);
			joker2Label = new JLabel("Deuxième Joker :");
			joker2Label.setVisible(false);
			joker1TxtF = new JTextField();
			joker1TxtF.setVisible(false);
			joker2TxtF = new JTextField();
			joker2TxtF.setVisible(false);
			
			JLabel xLabel = new JLabel("Position horizontale : ");
			x = new JComboBox<Integer> ();
			JLabel yLabel = new JLabel("Position verticale : ");
			y = new JComboBox<Integer> ();
			for(int i = 0 ; i < 15; i++){
				x.addItem(i);
				y.addItem(i);
			}
			
			JLabel sens = new JLabel("Orientation du mot : ");
			orientation = new JComboBox<String>();
			orientation.addItem("Horizontal");
			orientation.addItem("Vertical");
			
			form.add(motJoueLabel);
			form.add(motJoue);
			form.add(joker1Label);
			form.add(joker1TxtF);
			form.add(joker2Label);
			form.add(joker2TxtF);
			
			form.add(xLabel);
			form.add(x);
			form.add(yLabel);
			form.add(y);
			form.add(sens);
			form.add(orientation);
		    
		}
	    
		/**
		 * 
		 * @author Fauconnier/Henriquet
		 * Classe du bouton jouer
		 */
		class Joue implements ActionListener{
			
			/**
			 * Réecriture de la methode actionPerformed pour joue
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Lettre> saveMainJoueur = joueur.copieMainJoueur();
				
				char orient;
				int abscisse = (int)x.getSelectedItem();
				int ordonnee = (int)y.getSelectedItem();
				if(orientation.getSelectedItem() == "Horizontal") {
					orient = 'h';
				}
				else {
					orient = 'v';
				}
				if(motJoue.getText().length() != 0) {
					mot = motJoue.getText();
					String motJoker = mot;
					joker = joueur.detecteJoker(motJoue.getText());
					if(joker != 0) {
						if(joker == 1) {
							joker1 = joker1TxtF.getText().charAt(0);
						}
						else if(joker == 2) {
							joker1 = joker1TxtF.getText().charAt(0);
							joker2 = joker2TxtF.getText().charAt(0);
						}
						mot = joueur.setJokerMain(joker1, joker2, mot);
					}
					controller.poserMot(abscisse, ordonnee, orient, mot, saveMainJoueur, joker, motJoker);
					if(MessageDErreur.getMsgDErreur().length() != 0){
						affiche(MessageDErreur.getMsgDErreur());
						MessageDErreur.setMsgDErreur("");
					}
					else {
						fenetreJoue.removeAll();
						fenetreJoue.dispose();
						MessageDErreur.setMsgDErreur("");
					}
				}
			}
			
		}
		
		/**
		 * 
		 * @author Fauconnier/Henriquet
		 * Classe du bouton annule pour fermer la fenêtre
		 */
		class AnnuleJoue implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				fenetreJoue.removeAll();
				fenetreJoue.dispose();
				jouerJButton.setEnabled(true);
				melangeJButton.setEnabled(true);
				passerJButton.setEnabled(true);
				if(jokerFenetreIsUp) {
					jokerFenetre.removeAll();
					jokerFenetre.dispose();
				}
			}
		}
	}
	
	/**
	 * 
	 * @author Fauconnier/Henriquet
	 * Classe pour le bouton melanger qui va ouvrir une fenêtre pour pouvoir mélanger la main
	 */
	class Melanger implements ActionListener{
		JWindow fenetreMelange;
		JPanel copyMain;
		JLabel text;
		JButton melange;
		JButton annuler;
		String label = "";

		/**
		 * Réecriture de la méthode actionPerformed pour l'utilisation de melanger
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
				jouerJButton.setEnabled(false);
				melangeJButton.setEnabled(false);
				passerJButton.setEnabled(false);
				fenetreMelange = new JWindow();
				copyMain = new JPanel();
				text = new JLabel("Choisissez les lettres à remélanger dans le sac.\n");
				melange = new JButton("Mélanger");
				annuler = new JButton("Annuler");
				
				fenetreMelange.setSize(500, 150);
				fenetreMelange.setPreferredSize(new Dimension(500, 150));
				fenetreMelange.setLocationRelativeTo(null);
				fenetreMelange.setAlwaysOnTop(true);
				fenetreMelange.setVisible(true);	
				
				copyMain.setBackground(color);
				
				text.setFont(font);
				text.setPreferredSize(new Dimension(450, 40));
				text.setHorizontalAlignment(0);
				
				copyMain.add(text);
				
				ajouteMainMelange(copyMain);
				
				melange.addActionListener(new Melange());
				annuler.addActionListener(new Annule());

				copyMain.add(melange);
				copyMain.add(annuler);
				
				fenetreMelange.setContentPane(copyMain);
		}
		/**
		 * Crée une copie de la main utilisable pour le mélange
		 * et instancie des JCheckBox personnalisées
		 * @param box contenant graphique de la copie de la  main
		 * 
		 */
		private void ajouteMainMelange(JPanel box) {
			
			JPanel boxMain = new JPanel(new GridLayout(1,7));	
			
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
			
				img.setBackground(color);
				img.addItemListener(e);
				
				boxMain.add(img);
			}
		 	boxMain.setBackground(color);
		 	box.add(boxMain);
		}
		/**
		 * 
		 * @author Fauconnier/Henriquet
		 * Classe permettant l'interaction avec les JCheckBox personnalisées
		 */
		class event implements ItemListener {

			/**
			 * Redéfinition de l'action de itemStateChanged
			 */
			@Override
			public void itemStateChanged(ItemEvent e) {
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
		 * @author Fauconnier/Henriquet
		 * Classe du bouton annule pour fermer la fenêtre
		 */
		class Annule implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				fenetreMelange.removeAll();
				fenetreMelange.dispose();
				label = "";
				jouerJButton.setEnabled(true);
				melangeJButton.setEnabled(true);
				passerJButton.setEnabled(true);
			}
		}
		/**
		 * 
		 * @author Fauconnier/Henriquet
		 * Permettra de mélanger la main suivant les lettres choisies
		 */
		class Melange implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(label.length() != 0){
					controller.melangeMain(label);
					fenetreMelange.removeAll();
					fenetreMelange.dispose();
					label = "";
				}
				else {
					affiche("Veuillez faire votre sélection.");
				}
			}
			
		}
	}
	/**
	 * @author Fauconnier/Henriquet
	 * Class permettant la création d'une fenêtre pour pouvoir passer
	 */
	class Passer implements ActionListener{

		JWindow fenetrePasser;
		JPanel container;
		JLabel text;
		JButton passer;
		JButton annuler;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			jouerJButton.setEnabled(false);
			melangeJButton.setEnabled(false);
			passerJButton.setEnabled(false);
			fenetrePasser = new JWindow();
			container = new JPanel();
			text = new JLabel("Êtes-vous sûr de vouloir passer votre tour?\n");
			passer = new JButton("Passer");
			annuler = new JButton("Annuler");
			
			fenetrePasser.setSize(400, 80);
			fenetrePasser.setPreferredSize(new Dimension(400, 80));
			fenetrePasser.setLocationRelativeTo(null);
			fenetrePasser.setAlwaysOnTop(true);
			fenetrePasser.setVisible(true);
			
			text.setFont(font);
			text.setHorizontalAlignment(0);
			
			passer.addActionListener(new Passe());
			annuler.addActionListener(new Annule());
			
			
			container.setBackground(color);
			container.add(text);
			container.add(passer);
			container.add(annuler);
			
			fenetrePasser.setContentPane(container);
		}
		
		/**
		 * 
		 * @author Fauconnier/Henriquet
		 * Classe du bouton Passer
		 */
		class Passe implements ActionListener{
			//TODO
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.passer();
				fenetrePasser.removeAll();
				fenetrePasser.dispose();
			}
		}
		/**
		 * 
		 * @author Fauconnier/Henriquet
		 * Classe du bouton annule pour fermer la fenêtre
		 */
		class Annule implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				fenetrePasser.removeAll();
				fenetrePasser.dispose();
				jouerJButton.setEnabled(true);
				melangeJButton.setEnabled(true);
				passerJButton.setEnabled(true);
			}
		}
		
	}
	
	/**
	 * 
	 * @author Fauconnier/Henriquet
	 * Classe contenant la fenêtre de fin de partie
	 */
	public class FenetreFinDePartie {
		JFrame fenetreFinDePartie;
		private	Font font = new Font("Serif", Font.BOLD, 20);
		private Color color = new Color(253, 245, 230);
		
		/**
		 * Initialise la fenêtre de fin de partie
		 */
		public FenetreFinDePartie() {
			fenetreFinDePartie = new JFrame();
			fenetreFinDePartie.setSize(550, 330);
			fenetreFinDePartie.setPreferredSize(new Dimension(550, 330));
			fenetreFinDePartie.setTitle("Fin de la partie");
			fenetreFinDePartie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fenetreFinDePartie.setLocationRelativeTo(null);
			fenetreFinDePartie.setResizable(false);
			fenetreFinDePartie.setVisible(true);
			
			JPanel finDePartieJPanel = new JPanel();
			finDePartieJPanel.setSize(550, 330);
			finDePartieJPanel.setPreferredSize(new Dimension(550, 330));
			Box affichageResteMain = Box.createHorizontalBox();
			affichageResteMain.setPreferredSize(new Dimension(400, 55));
			affichageResteMain.setSize(400, 55);
			
			JLabel msgVictoire = new JLabel();
			msgVictoire.setFont(font);
			JLabel resteMainMsg;
			
			if(joueur.getNbreTourPasser() >= 3 && joueur.getNbreTourPasserAdverse() >= 3) { //Si les joueurs passe leur tour
				String msgVictoireString = "<html> Vous et votre adversaire ne pouvez plus jouer.<br> La partie est donc terminées. <br> Résultat: ";
				if(joueur.getScore() > joueur.getScoreAdverse()) {
					msgVictoireString += "Vous avez gagné !<br>";
				} 
				else if (joueur.getScore() < joueur.getScoreAdverse()) {
					msgVictoireString += "Votre adversaire a gagné la partie...<br>";
				} 
				else {
					msgVictoireString += "Egalité !<br>";
				}
				msgVictoireString += "Votre score est de " + joueur.getScore() + " points.<br>";
				msgVictoireString += "Le score de votre adversaire est de " + joueur.getScoreAdverse() + " points.<br>";
				msgVictoireString += "</html>";
				
				msgVictoire.setText(msgVictoireString);
				
				
				resteMainMsg = new JLabel("<html>Vous aviez encore dans votre main : <br></html>");
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
				affichageResteMain.add(mainPanel);
				
			}
			else {
				String msgVictoireString ;
				if(joueur.getMainJoueur().isEmpty()) { // si la main du joueur est vide
					msgVictoireString = "<html> Vous avez vidé votre main. <br> Résultat: ";
				}
				else {
					msgVictoireString = "<html> Vous adversaire a vidé sa main. <br> Résultat: ";
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
				
				if(joueur.getMainJoueur().isEmpty()) {
					resteMainMsg = new JLabel("<html>Votre adversaire avait encore en main : <br></html>");
					JPanel mainPanel = new JPanel(new GridLayout(1,7));
					mainPanel.setBackground(color);
					
					for(int i = 0; i < joueur.getMainJoueurAdverse().size(); i++) {
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
					affichageResteMain.add(mainPanel);
				}
				else {
					resteMainMsg = new JLabel("<html>Vous aviez encore en main : <br></html>");
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
					affichageResteMain.add(mainPanel);
				}
			}
			finDePartieJPanel.add(msgVictoire);
			resteMainMsg.setFont(font);
			finDePartieJPanel.add(resteMainMsg);
			finDePartieJPanel.add(affichageResteMain);
			finDePartieJPanel.setBackground(color);
			JLabel remarque = new JLabel();
			remarque.setText("<html>* Si vous fermez cette fenêtre, le jeu s'arrêtera.<br> Pour rejouer, relancer le jeu.</html>");
			remarque.setFont(font);
			finDePartieJPanel.add(remarque);
			finDePartieJPanel.setBackground(color);
			fenetreFinDePartie.setContentPane(finDePartieJPanel);
		}

	}

}