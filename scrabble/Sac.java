/**
 * 
 */
package scrabble;

/**
 * @author Fauconnier/Henriquet
 *
 */
public class Sac {
	
	private Lettre[] sac = new Lettre[102];
	
	/**
	 * @return le sac
	 */
	public Lettre[] getSac() {
		return sac;
	}
	/**
	 * @param ajoute les lettres au sac 
	 */
	public void setSac(Lettre[] sac) {
		this.sac = sac;
	}
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
	public void remplissageSac() {
		/*
		 * chercher comment remplir automatique le sac
		 */
	}
	/**
	 * M�thode li� a la m�thode remelange de joueur
	 * pour pouvoir changer certaines lettres
	 * Appel de la m�thode pioche
	 */
	public void melangeMain() {
		
	}
}
