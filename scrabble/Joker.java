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
	 * Permet de dÃ©finir la valeur du libellÃ©
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
			System.out.println("Charactère non authorisé !");
		}
	}
	
	public Joker() {
		super();
	}

}
