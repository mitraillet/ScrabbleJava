package ScrabbleLancement;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import scrabble.Plateau;
import scrabble.Joueur;

/**
 * Gère les interactions avec les sockets
 * @author Fauconnier/Henriquet
 *
 */
public class gestionSocket {
	/**
	 * Le socket du serveur
	 */
	ServerSocket s;
	
	/**
	 * Le socket
	 */
	Socket socket;
	
	/**
	 * Le flux de sortie
	 */
	ObjectOutputStream objectOut;
	
	/**
	 * Le flux d'entrée
	 */
	ObjectInputStream objectIn;
	
	/**
	 * Le plateau de jeu
	 */
	Plateau plateau;
	
	/**
	 * Le constructeur de la classe
	 * @param plateau le plateau de jeu
	 */
	public gestionSocket(Plateau plateau) {
		this.plateau = plateau;
	}
	
	/**
	 * Détermine le socket à initialiser, serveur ou client
	 * @param estServeur true si serveur, false pour client
	 * @param port le port
	 * @param addr l'adresse
	 * @throws IOException Gère les exceptions
	 */
	public void setSocket(boolean estServeur, int port, String addr) throws IOException {
		if(estServeur) {
			this.setServeur(port, addr);
		} else {
			this.setClient(port, addr);
		}
	}
	
	/**
	 * Initialise le serveur
	 * @param port le port
	 * @param addr l'adresse
	 * @throws IOException Gère les exceptions
	 */
	public void setServeur(int port, String addr) throws IOException {
		s = new ServerSocket(port);
		System.out.println("En attente du joueur 2...");
		socket = s.accept();
		System.out.println("Joueur 2 Connecté");
		
		objectOut = new ObjectOutputStream(socket.getOutputStream());
		objectIn = new ObjectInputStream(socket.getInputStream());
	}
	
	/**
	 * Initialise le client
	 * @param port le port
	 * @param addr l'adresse
	 * @throws IOException Gère les exceptions
	 */
	public void setClient(int port, String addr) throws IOException {
		socket = new Socket(addr, port); 
		
		objectOut = new ObjectOutputStream(socket.getOutputStream());
		objectIn = new ObjectInputStream(socket.getInputStream());
		
		System.out.println(objectOut);
	}
	
	/**
	 * Envoie les données à l'autre joueur
	 * @param objet les données à envoyer
	 */
	public void envoyerDonnee(Joueur joueur, Plateau objet) {
		try {
			objectOut.reset();
			objectOut.writeObject(objet);
			objectOut.flush();
			//joueur.setTourJoueur(false);
		} catch (IOException e) {
			
		}
	}
	
	/**
	 * Reçois les données de l'autres joueur
	 * @return true si des données on été reçues, sinon false
	 */
	public void recevoirDonnee(Joueur joueur) {
		try {
			while(true) {
				Plateau plateauTest = (Plateau) objectIn.readObject();
				plateau.setPlateau(plateauTest.getPlateau());
				joueur.setTourJoueur(true);
				plateau.debutPartie = true;
				System.out.println("C'est à votre tour !");
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ferme la connexion
	 */
	public void fermerConnexion() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
