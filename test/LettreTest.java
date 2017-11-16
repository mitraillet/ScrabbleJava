/**
 * Test unitaire pour la classe Lettre
 */
package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import scrabble.Lettre;

/**
 * @author  Fauconnier/Henriquet
 *
 */
class LettreTest {

	/**
	 * Test method for {@link scrabble.Lettre#getLabel()}.
	 */
	@Test
	void testGetLabel() {
		Lettre a = new Lettre('a',10);
		Lettre i = new Lettre('î',10);
		Lettre pointInterrogation = new Lettre();
		assertEquals(a.getLabel(), 'a' );
		assertEquals(i.getLabel(), 'î' );
		assertEquals(pointInterrogation.getLabel(), '?' );
	}

	/**
	 * Test method for {@link scrabble.Lettre#setLabel(char)}.
	 */
	@Test
	void testSetLabel() {
		Lettre a = new Lettre('a',10);
		Lettre pointInterrogation = new Lettre();
		a.setLabel('y');
		pointInterrogation.setLabel('@');
		assertEquals(a.getLabel(), 'y' );
		assertEquals(pointInterrogation.getLabel(), '@' );
	}

	/**
	 * Test method for {@link scrabble.Lettre#getValeur()}.
	 */
	@Test
	void testGetValeur() {
		Lettre a = new Lettre('a',10);
		Lettre i = new Lettre('î',96);
		Lettre pointInterrogation = new Lettre();
		assertEquals(a.getValeur(), 10 );
		assertEquals(i.getValeur(), 96 );
		assertEquals(pointInterrogation.getValeur(), 0 );
	}

}
