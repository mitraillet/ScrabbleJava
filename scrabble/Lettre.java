/**
 * 
 */
package scrabble;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Lettre {

	/**
	 * Le libellé du caractère
	 */
	char label;
	
	/**
	 * La valeur de la lettre (score)
	 */
	int valeur;
	
	/**
	 * Constructeur
	 * @param label le label de la lettre
	 * @param valeur la valeur de la lettre
	 * @param instance le nombre de fois que la lettre se trouve dans le sac
	 */
	public Lettre(char label, int valeur, int instance) {
		this.label = label;
		this.valeur = valeur;
		
		for(int i = 0; i < instance; i ++) {
			Sac.contenuSac.add(this);
		}
	}
	
	/**
	 * Constructeur par défaut
	 */
	public Lettre() {
		this.label = 'a';
		this.valeur = 0;
	}

}
