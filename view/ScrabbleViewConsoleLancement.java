package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScrabbleViewConsoleLancement {
	
	static Scanner scan;
	
	public List<Object> setSocket() {
		scan = new Scanner(System.in);
		String bool;
		Boolean okServeur = false;
		Boolean estServeur = null;
		String ip = "localhost";
		List<Object> listRetour = new ArrayList<Object>();
		
		while(!okServeur) {
			System.out.println("Taper true pour vous connectez en tant que serveur, false pour le client");
			bool = scan.next();
			if(bool.equals("true")) {
				System.out.println("Vous êtes le serveur");
				okServeur = true;
				estServeur = true;
			
			} else if(bool.equals("false")) {
				System.out.println("Vous êtes le client");
				okServeur = true;
				estServeur = false;
				while(true) {
					System.out.println("Veuillez entrer l'adresse du serveur : ");
					ip = scan.next();
					System.out.println("Tentative de connexion au serveur...");
					break;
				}
			
			} else {
				System.out.println("Commande non reconnue...");
				okServeur = false;
			}
		}
		listRetour.add(estServeur);
		listRetour.add(ip);
		return listRetour;
	}
	
}
