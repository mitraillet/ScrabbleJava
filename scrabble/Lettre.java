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
	char nom;
	
	/**
	 * La valeur de la lettre (score)
	 */
	int valeur;
	
	/**
	 * Le nombre d'instance de la lettre dans le sac
	 */
	int instance;
	
	/**
	 * Constructeur
	 * @param nom le nom de la lettre
	 * @param valeur la valeur de la lettre
	 * @param instance le nombre d'instance dans le sac
	 */
	public Lettre(char nom, int valeur, int instance) {
		this.nom = nom;
		this.valeur = valeur;
		this.instance = instance;
		
		for(int i = 0; i < this.instance; i ++) {
			Sac.contenuSac.add(this);
		}
	}
	
	/**
	 * Constructeur par défaut
	 */
	public Lettre() {
		this.nom = 'a';
		this.valeur = 0;
		this.instance = 0;
	}

}
