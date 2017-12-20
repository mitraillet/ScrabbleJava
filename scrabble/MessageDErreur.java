package scrabble;

public final class MessageDErreur {
	
	private static MessageDErreur instance = new MessageDErreur();
	private static String msgDErreur;
	public static MessageDErreur getlnstance() {
		return instance;
	}
	
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