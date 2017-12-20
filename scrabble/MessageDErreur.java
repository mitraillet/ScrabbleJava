/**
 * Package Modèle
 */
package scrabble;

/**
 * Classe gérant les messages d'erreur
 */
public final class MessageDErreur {
	
	/**
	 * Création du message d'erreur
	 */
	private static MessageDErreur instance = new MessageDErreur();
	
	/**
	 * Contient le message d'erreur le plus pertinent
	 */
	private static String msgDErreur;
	
	/**
	 * Retourne le MessageDErreur
	 * @return l'instance de MessageDErreur
	 */
	public static MessageDErreur getlnstance() {
		return instance;
	}
	
	/**
	 * Récupère le message d'erreur
	 */
	private MessageDErreur() {
		MessageDErreur.msgDErreur ="";
	}

	/**
	 * Méthode permettant de récupérer le msgDErreur
	 * @return le msgDErreur qui contient l'erreur de manipulation dans le scrabble
	 */
	public static String getMsgDErreur() {
		return msgDErreur;
	}

	/**
	 * Méthode permettant de modifier msgDErreur
	 * @param msgDErreur le msgDErreur a modifié
	 */
	public static void setMsgDErreur(String msgDErreur) {
		MessageDErreur.msgDErreur = msgDErreur;
	}
}