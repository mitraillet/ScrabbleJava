/**
 * Test unitaire pour la classe Lettre
 */
package test;

import static org.junit.Assert.*;
import org.junit.Test;

import scrabble.Lettre;

/**
 * @author  Fauconnier/Henriquet
 *
 */
class LettreTest {

	/**
	 * Test de la méthode pour getLabel 
	 * Méthode permettant l'obtention du label de la lettre
	 */
	@Test
	void testGetLabel() {
		Lettre a = new Lettre('a',10);
		Lettre i = new Lettre('�',10);
		Lettre pointInterrogation = new Lettre();
		assertEquals(a.getLabel(), 'a' );
		assertEquals(i.getLabel(), '�' );
		assertEquals(pointInterrogation.getLabel(), '?' );
	}

	/**
	 * Test de la m�thode pour getValeur 
	 * M�thode permettant l'obtention du valeur de la lettre
	 */
	@Test
	void testGetValeur() {
		Lettre a = new Lettre('a',10);
		Lettre i = new Lettre('�',96);
		Lettre pointInterrogation = new Lettre();
		assertEquals(a.getValeur(), 10 );
		assertEquals(i.getValeur(), 96 );
		assertEquals(pointInterrogation.getValeur(), 0 );
	}

}
