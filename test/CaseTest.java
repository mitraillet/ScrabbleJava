/**
 * Test unitaire pour la classe Case
 */
package test;

import static org.junit.Assert.*;

import scrabble.Case;
import scrabble.Lettre;

import org.junit.Test;

public class CaseTest {

	
	/**
	 * Test du constructeur sans paramètre (bonus initialisé à 0)
	 */
	@Test
	public void testCase() {
		//fail("Not yet implemented"); // TODO
		Case caseTest = new Case();
		assertEquals(caseTest.getBonus(), 0);
	}

	
	/**
	 * Test constructeur avec paramètre (pour le bonus)
	 */
	@Test
	public void testCaseInt() {
		//fail("Not yet implemented"); // TODO
		Case caseTest = new Case(3);
		assertEquals(caseTest.getBonus(), 3);
	}

	
	/**
	 * Test getter de Lettre
	 */
	@Test
	public void testGetLettre() {
		//fail("Not yet implemented"); // TODO
		Case caseTest = new Case();
		Lettre testLettre = new Lettre('a', 1);
		caseTest.setLettre(testLettre);
		assertEquals(testLettre, caseTest.getLettre());
	}

	/**
	 * Test du setter de Lettre
	 */
	@Test
	public void testSetLettre() {
		//fail("Not yet implemented"); // TODO
		Case caseTest = new Case();
		Lettre testLettre = new Lettre('a', 1);
		caseTest.setLettre(testLettre);
		assertEquals(caseTest.getLabelCase(), 'a');
	}

	
	/**
	 * Test du getter de bonus
	 */
	@Test
	public void testGetBonus() {
		//fail("Not yet implemented"); // TODO
		Case caseTest2 = new Case();
		assertEquals(caseTest2.getBonus(), 0);
		Case caseTest3 = new Case(3);
		assertEquals(caseTest3.getBonus(), 3);
	}

	/**
	 * Test du setter de bonus
	 */
	@Test
	public void testSetBonus() {
		//fail("Not yet implemented"); // TODO
		Case caseTest = new Case();
		caseTest.setBonus(1);
		assertEquals(caseTest.getBonus(), 1);
	}
	
	
	/**
	 * Test du getter du label de la lettre
	 */
	@Test
	public void testGetLabelCase() {
		//fail("Not yet implemented"); // TODO
		Case caseTest = new Case();
		Lettre lettreTest = new Lettre('b', 1);
		caseTest.setLettre(lettreTest);
		
		assertEquals(caseTest.getLabelCase(), 'b');
	}

	/**
	 * Test du getter de la valeur de la lettre
	 */
	@Test
	public void testGetValeurCase() {
		//fail("Not yet implemented"); // TODO
		Case caseTest = new Case();
		Lettre lettreTest = new Lettre('b', 1);
		caseTest.setLettre(lettreTest);
		
		assertEquals(caseTest.getValeurCase(), 1);
	}

}
