/**
 * Package contenant tous les tests unitaires
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import scrabble.Joker;

/**
 * @author Fauconnier/Henriquet
 * Test unitaire pour la classe Joker
 */
class JokerTest {

	/**
	 * Teste la méthode permettant le changement du label du joker
	 */
	@Test
	void testSetJoker() {
		Joker joker = new Joker();
		
		joker.setJoker("a");
		assertEquals(joker.getLabel(), 'a');
		
		joker.setJoker("Y");
		assertEquals(joker.getLabel(), 'y');
		
		joker.setJoker("?");
		assertFalse(joker.getLabel() == '?');
	}
}
