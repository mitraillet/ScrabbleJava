/**
 * 
 */
package scrabble;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Sac {
	
	//private Lettre[] sac = new Lettre[102];
	static List<Lettre> contenuSac = new ArrayList<Lettre>();
	
	/**
	 * @return le sac
	 */
	//public Lettre[] getSac() {
	public List<Lettre> getSac() {
		return contenuSac;
	}
	/**
	 * @param ajoute les lettres au sac 
	 */
	/*public void setSac(Lettre[] sac) {
	public List<Lettre> setSac() {
		this.sac = sac;
	}*/
	
	/**
	 * M�thode li� � la m�thode pioche du joueur 
	 * en vue de lui compl�ter sa main ou de la lui remplir compl�tement
	 */
	public void pioche() {
		/*
		 * ajout de lettres dans la main de joueur de tel sorte que nmbre de lettre
		 * dans sa main soit �gal � 0 et pioche de lettre random
		 */
	}
	/**
	 * M�thode en vue du remplissage du sac avec un nombre coh�rent des diff�rentes lettres
	 * utilisation de ... pour le remplissage
	 */
	public void remplissageSac(Lettre l) {
		/*
		 * chercher comment remplir automatique le sac
		 */
		contenuSac.add(l);
	}
	/**
	 * M�thode li� a la m�thode remelange de joueur
	 * pour pouvoir changer certaines lettres
	 * Appel de la m�thode pioche
	 */
	public void melangeMain() {
		
	}
}
