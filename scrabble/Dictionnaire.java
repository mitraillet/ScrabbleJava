/**
 * Package Modèle
 */
package scrabble;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * Classe initialisant et gérant le dictionnaire
 * @author Fauconnier/Henriquet
 */
public class Dictionnaire {

	/**
	* dictionnaire Contient tous les mots de la langues française 
	*/
	public HashSet<String> dictionnaire = new HashSet<String>();
	
	/**
	 * Itération d'un dictionnaire pour trouver concordance 
	 * avec le mot entré par le joueur et vérifier s'il existe
	 * @param mot Le mot à vérifier s'il est bien présent dans le dictionnaire
	 * @return true si mot est dans le dictionnaire sinon false
	 */
	public boolean verification(String mot) {
		return dictionnaire.contains(mot);
	}
	
	/**
	 * @return le dictionnaire
	 */
	protected HashSet<String> getDictionnaire() {
		return dictionnaire;
	}
	
	
	/**
	 * Construis le dictionnaire sur base du fichier dictionnaire.txt
	 */
	private void construireDico() {
		try {
			File dico = new File("ressource/dictionnaire.txt"); //Path du dictionnaire.txt
			BufferedReader br = new BufferedReader(new FileReader(dico)); //CrÃ©ation du buffer
			String line; //Variable pour les lignes
			while ((line = br.readLine()) != null) { //Pour chaque ligne Ã©xÃ©cute la boucle
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
	 * Construis le dictionnaire sur base du fichier dictionnaire.txt
	 */
	public Dictionnaire() {
		this.dictionnaire = new HashSet<String>();
		construireDico();
	}
}