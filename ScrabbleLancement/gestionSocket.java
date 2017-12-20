package ScrabbleLancement;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import scrabble.Plateau;
import scrabble.Case;
import scrabble.Joueur;
import scrabble.Lettre;
import scrabble.MessageDErreur;
import scrabble.Sac;

/**
 * Gère les interactions avec les sockets
 * @author Fauconnier/Henriquet
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
	 * Le sac de jeu
	 */
	Sac sac;
	
	/**
	 * Le constructeur de la classe
	 * @param plateau le plateau de jeu
	 */
	public gestionSocket(Plateau plateau, Sac sac) {
		this.plateau = plateau;
		this.sac = sac;
	}
	
	/**
	 * Détermine le socket à initialiser, serveur ou client
	 * @param estServeur true si serveur, false pour client
	 * @param port le port
	 * @param addr l'adresse
	 * @throws IOException Gère les exceptions
	 */
	public void setSocket(boolean estServeur, int port, String ip) throws IOException {	
		if(estServeur) {
			this.setServeur(port, ip);
		} else {
			this.setClient(port, ip);
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
		System.out.println("Votre adresse ip : " + InetAddress.getLocalHost ().getHostAddress());
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
		System.out.println("Connexion établie");
	}
	
	/**
	 * Envoie les données à l'autre joueur
	 * @param objet les données à envoyer
	 */
	public void envoyerDonnee(Joueur joueur, Plateau plateau, Sac sac) {
		try {
			objectOut.reset();
			
			objectOut.writeBoolean(joueur.getFinPartie());
			objectOut.writeInt(joueur.getScore());
			objectOut.writeInt(joueur.getNbreTourPasser());
			objectOut.writeObject(plateau.getPlateau());
			objectOut.writeObject(sac.getSac());
			objectOut.writeObject(joueur.getMainJoueur());
			
			objectOut.flush();
			
		} catch (IOException e) {
			System.out.println("Erreur lors de l'envoi des données.");
		}
	}
	
	/**
	 * Reçois les données de l'autres joueur
	 * @return true si des données on été reçues, sinon false
	 */
	public void recevoirDonnee(Joueur joueur, Plateau plateau, Sac sac) {
		try {
			while(true) {
				
				boolean finPartie = (boolean) objectIn.readBoolean();
				
				int scoreAdverse = (int) objectIn.readInt();
				
				int tourPasser = (int) objectIn.readInt();
				
				Case[][] plateauActuel = (Case[][]) objectIn.readObject();
				
				@SuppressWarnings("unchecked") //On est sûr que le paramètre est une liste de lettre
				List<Lettre> sacActuel = (List<Lettre>) objectIn.readObject();
				
				@SuppressWarnings("unchecked") //On est sûr que le paramètre est une liste de lettre
				List<Lettre> mainAdverse = (List<Lettre>) objectIn.readObject();
				
				joueur.setFinPartie(finPartie);
				joueur.setScoreAdverse(scoreAdverse);
				joueur.setNbreTourPasserAdverse(tourPasser);
				plateau.setPlateau(plateauActuel);
				sac.setSac(sacActuel);
				joueur.setMainJoueurAdverse(mainAdverse);
				
				if(plateau.getPlateau()[7][7].getLettre() != null){
					plateau.setDebutPartie(true);
				}
				
				if(finPartie == true) {
					joueur.setTourJoueur(false);
					this.envoyerDonnee(joueur, plateau, sac);
					break;
				} else {
					joueur.setTourJoueur(true);
					MessageDErreur.setMsgDErreur("C'est à votre tour !");
					System.out.println("C'est à votre tour !");
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Erreur lors de la réception des données.");
			this.fermerConnexion();
		}
	}
	
	/**
	 * Reçois les données de l'autres joueur
	 * @return true si des données on été reçues, sinon false
	 */
	@SuppressWarnings("unused")
	public void recevoirSac(Joueur joueur) {
		try {
			boolean finPartie = (boolean) objectIn.readBoolean();
			int scoreAdverse = (int) objectIn.readInt();
			int tourPasser = (int) objectIn.readInt();
			
			Case[][] plateauActuel = (Case[][]) objectIn.readObject();
				
			@SuppressWarnings("unchecked") //On est sûr que le paramètre est une liste de lettre
			List<Lettre> sacActuel = (List<Lettre>) objectIn.readObject();
			
			@SuppressWarnings("unchecked") //On est sûr que le paramètre est une liste de lettre
			List<Lettre> mainAdverse = (List<Lettre>) objectIn.readObject();
				
			sac.setSac(sacActuel);
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Erreur lors de la réception du sac.");
		}
	}
	
	/**
	 * Ferme la connexion
	 */
	public void fermerConnexion() {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("Erreur lors de la fermeture de la connexion.");
		}
	}
}
