/**
 * Instanciation du joker objet étendu de Lettre
 */
package scrabble;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Joker extends Lettre {

	/**
	 * Permet de définir la valeur du label du joker 
	 * et ce seulement si c'est une lettre
	 * @param label de la lettre de type String pour plus de flexibilité
	 */
	public void setJoker(String labelString) {
		char labelChar;
		labelString = labelString.toLowerCase();
		String abc = "azertyuiopqsdfghjklmwxcvbn";
		if(abc.contains(labelString)) {
			labelChar = labelString.charAt(0);
			this.setLabel(labelChar);
		}
		else {
			System.out.println("Caractère non authorisé !");
		}
	}
	
	public Joker() {
		super();
	}

}
