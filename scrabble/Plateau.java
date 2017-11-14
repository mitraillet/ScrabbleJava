/**
 * 
 */
package scrabble;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashSet;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Plateau {

	HashSet<String> dictionnaire = new HashSet<String>();
	
	/**
	 * Itération d'un dictionnaire pour trouver concordance 
	 * avec le mot entré par le joueur et vérifier s'il existe
	 */
	public void verification() {
		try {
			File dico = new File("ressource/dictionnaire.txt"); //Path du dictionnaire.txt
			BufferedReader br = new BufferedReader(new FileReader(dico)); //Crétation du buffer
			String line; //Variable pour les lignes
			while ((line = br.readLine()) != null) { //Pour chaque ligne éxécute la boucle
					this.dictionnaire.add(line);
			}
			System.out.println(this.dictionnaire.size());
			br.close(); //Ferme le fichier
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * Recherche des mots périphériques é celui placé par le joueur
	 * qui se seraient créés et appel de la méthode vérification
	 */
	public void verificationPeripherique() {
		// nom à changer surement
	}
	/**
	 * Calcul du score en prenant compte les bonus
	 * Utilisation des valeurs de chaques lettres pondérées avec le bonus
	 * et ajout dans la classe Joueur.score 
	 */
	public void calculScore() {
		// Enorme point d'interrogation sur ce que l'on doit faire et comment on peut le calculer
	}
}
