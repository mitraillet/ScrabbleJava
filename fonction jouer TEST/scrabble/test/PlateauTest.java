package test;

import static org.junit.Assert.*;

import org.junit.Test;

import scrabble.Case;
import scrabble.Plateau;

public class PlateauTest {

	@Test
	public void testPlateau() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testInitCasePlateau() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVerification() {
		//fail("Not yet implemented");
		Plateau plateauTest = new Plateau();
		assertEquals(plateauTest.verification("mot"), true);
		assertEquals(plateauTest.verification("mots"), true);
		assertEquals(plateauTest.verification("joue"), true);
		assertEquals(plateauTest.verification("jouee"), true);
		assertEquals(plateauTest.verification("jouees"), true);
		assertEquals(plateauTest.verification("ah"), true);
		
		assertEquals(plateauTest.verification("aha"), false);
		assertEquals(plateauTest.verification("simon"), false);
		assertEquals(plateauTest.verification("Paris"), false);
		assertEquals(plateauTest.verification("Churchill"), false);
	}

	@Test
	public void testVerificationPeripherique() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCheckHaut() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCheckBas() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCheckGauche() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCheckDroite() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCheckHautBas() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCheckGaucheDroite() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetLabelToList() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetScoreToList() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetMotToList() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCopyPlateau() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCheckPremierMot() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCalculScore() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

}
