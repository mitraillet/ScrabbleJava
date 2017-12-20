/**
 * Package view gérant les vues
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Vue qui gère l'initialisation des sockets en GUI
 * @author Fauconnier
 */
public class ScrabbleViewGUILancement {
		List<Object> listRetour ;
		Boolean okServeur = false;
		Boolean estServeur = null;
		private	Font font = new Font("Serif", Font.BOLD, 20);
		private Color color = new Color(253, 245, 230);
		
	public ScrabbleViewGUILancement() {
		JFrame fenetreLancement = new JFrame();
		fenetreLancement.setSize(350, 270);
		fenetreLancement.setPreferredSize(new Dimension(350, 270));
		fenetreLancement.setTitle("Lancement du jeu");
		fenetreLancement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreLancement.setLocationRelativeTo(null);
		fenetreLancement.setResizable(false);
		fenetreLancement.setVisible(true);
		fenetreLancement.setBackground(color);

		JPanel container = new JPanel(new GridLayout(7, 1));
		container.setSize(350, 250);
		container.setPreferredSize(new Dimension(350, 250));
		container.setBackground(color);
		
		JPanel boxJButton = new JPanel(new GridLayout(1, 2));
		JLabel msgEstServeur = new JLabel("Êtes-vous?");
		msgEstServeur.setFont(font);
		msgEstServeur.setHorizontalAlignment(JLabel.CENTER);
		JButton ouiJButton = new JButton("Le serveur");
		JButton nonJButton = new JButton("Le client");
		JLabel msgServeur = new JLabel("Vous êtes le serveur");
		msgServeur.setFont(font);
		msgServeur.setHorizontalAlignment(JLabel.CENTER);
		JLabel msgAddDuServeur = new JLabel();
		msgAddDuServeur.setFont(font);
		msgAddDuServeur.setHorizontalAlignment(JLabel.CENTER);
		try {
			msgAddDuServeur.setText("Votre adresse ip : " + InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		JLabel msgAttenteServeur = new JLabel("En attente de l'autre joueur...");
		msgAttenteServeur.setFont(font);
		msgAttenteServeur.setHorizontalAlignment(JLabel.CENTER);
		JLabel msgClient = new JLabel("Vous êtes le client");
		msgClient.setFont(font);
		msgClient.setHorizontalAlignment(JLabel.CENTER);
		JLabel msgAddServeur = new JLabel("Veuillez entrer l'adresse du serveur : ");
		msgAddServeur.setFont(font);
		msgAddServeur.setHorizontalAlignment(JLabel.CENTER);
		
		JTextField addServeurJTextField = new JTextField();
		JPanel vide = new JPanel();
		vide.setBackground(new Color(0,0,0,0));
		JButton sendIpServer = new JButton("Confirmer l'adresse du serveur");
		sendIpServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String adresseServeur = msgAddServeur.getText();
				if(adresseServeur.length() != 0) {
					//TODO something ^^
				}
				else {
					//affiche("Veuillez entrez une adresse IP");
				}
			}
			
		});
		
		String ip = "localhost";
		listRetour = new ArrayList<Object>();
		container.add(msgEstServeur);
		
		ouiJButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				container.add(msgServeur);
				container.add(msgAddDuServeur);
				container.add(msgAttenteServeur);
				okServeur = true;
				estServeur = true;
				ouiJButton.setEnabled(false);
				nonJButton.setEnabled(false);
				fenetreLancement.pack();
			}
			
		});
		
		nonJButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				container.add(msgClient);
				okServeur = true;
				estServeur = false;
				ouiJButton.setEnabled(false);
				nonJButton.setEnabled(false);
				container.add(msgAddServeur);
				container.add(addServeurJTextField);
				container.add(vide);
				container.add(sendIpServer);
				fenetreLancement.pack();
			}
			
		});
		boxJButton.add(ouiJButton);
		boxJButton.add(nonJButton);
		container.add(boxJButton);
		
		listRetour.add(estServeur);
		listRetour.add(ip);
		fenetreLancement.setContentPane(container);
	}

	public static void main(String [] agrs) {
		new ScrabbleViewGUILancement();
	}
}
