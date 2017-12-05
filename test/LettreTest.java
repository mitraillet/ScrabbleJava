/**
 * Package contenant tous les tests unitaires
 */
package test;

import static org.junit.Assert.*;
import org.junit.Test;

import scrabble.Lettre;

/**
 * @author Fauconnier/Henriquet
  *Test unitaire pour la classe Lettre
 */
public class LettreTest {

	/**
	 * Test de la méthode pour getLabel 
	 * Méthode permettant l'obtention du label de la lettre
	 */
	@Test
	public void testGetLabel() {
		Lettre a = new Lettre('a',10);
		Lettre i = new Lettre('i',10);
		Lettre pointInterrogation = new Lettre();
		assertEquals(a.getLabel(), 'a' );
		assertEquals(i.getLabel(), 'i' );
		assertEquals(pointInterrogation.getLabel(), '?' );
	}

	/**
	 * Test de la méthode pour getValeur 
	 * Méthode permettant l'obtention du valeur de la lettre
	 */
	@Test
	public void testGetValeur() {
		Lettre a = new Lettre('a',10);
		Lettre i = new Lettre('i',96);
		Lettre pointInterrogation = new Lettre();
		assertEquals(a.getValeur(), 10 );
		assertEquals(i.getValeur(), 96 );
		assertEquals(pointInterrogation.getValeur(), 0 );
	}

}
