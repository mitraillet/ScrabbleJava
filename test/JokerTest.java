/**
 * Package contenant tous les tests unitaires
 */
package test;

import static org.junit.Assert.*;
import org.junit.Test;

import scrabble.Joker;

/**
 * @author Fauconnier/Henriquet
 * Test unitaire pour la classe Joker
 */
public class JokerTest {

	/**
	 * Teste la méthode permettant le changement du label du joker
	 */
	@Test
	public void testSetJoker() {
		Joker joker = new Joker();
		
<<<<<<< HEAD
		joker.setJoker('a');
		assertEquals(joker.getLabel(), 'a');
		
		joker.setJoker('Y');
		assertEquals(joker.getLabel(), 'y');
		
		joker.setJoker('?');
=======
		joker.setJoker("a");
		assertEquals(joker.getLabel(), 'a');
		
		joker.setJoker("Y");
		assertEquals(joker.getLabel(), 'y');
		
		joker.setJoker("?");
>>>>>>> master
		assertFalse(joker.getLabel() == '?');
	}
}
