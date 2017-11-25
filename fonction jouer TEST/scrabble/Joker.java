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
	public void setJoker(String labelString) {
		char labelChar;
		labelString = labelString.toLowerCase();
		String abc = "azertyuiopqsdfghjklmwxcvbn";
		if(abc.contains(labelString)) {
			labelChar = labelString.charAt(0);
			this.setLabel(labelChar);
		}
		else {
			System.out.println("Charact�re non authoris� !");
		}
	}
	
	public Joker() {
		super();
	}

}
