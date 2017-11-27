/**
 * Instanciation du joker objet �tendu de Lettre
 */
package scrabble;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Joker extends Lettre {

	/**
	 * Permet de définir la valeur du libellé
	 */
	public boolean setJoker(String labelString) {
		char labelChar;
		labelString = labelString.toLowerCase();
		String abc = "azertyuiopqsdfghjklmwxcvbn";
		if(abc.contains(labelString)) {
			labelChar = labelString.charAt(0);
			this.setLabel(labelChar);
			return true;
		}
		else {
			System.out.println("Charactère non authorisé !");
			return false;
		}
	}
	
	public Joker() {
		super();
	}

}
