package test;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import scrabble.Case;
import scrabble.Lettre;
import scrabble.Plateau;
import scrabble.Joueur;

public class PlateauTest {

	@Test
	public void testPlateau() {
		Plateau plateauTest = new Plateau();
	}

	@Test
	public void testInitCasePlateau() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		Case caseTest = new Case(6);
		plateauTest.initCasePlateau(caseTest, 0, 0);
		assertEquals(plateauTest.plateau[0][0].getBonus(), 6);
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
		assertEquals(plateauTest.verification("belgique"), false);
		assertEquals(plateauTest.verification("churchill"), false);
		assertEquals(plateauTest.verification("bruhmok"), false);
	}

	@Test
	public void testVerificationPeripherique() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCheckHaut() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		
		Lettre m = new Lettre('m', 1);
		Lettre o = new Lettre('o', 1);
		Lettre t = new Lettre('t', 1);
		Lettre s = new Lettre('s', 1);
		Lettre e = new Lettre('e', 1);
		
		plateauTest.plateau[9][7].setLettre(s);
		plateauTest.plateau[8][7].setLettre(e);
		plateauTest.plateau[7][7].setLettre(s);
		plateauTest.plateau[7][8].setLettre(t);
		plateauTest.plateau[7][9].setLettre(o);
		plateauTest.plateau[7][10].setLettre(m);
		
		List<Case> returnList = new ArrayList<Case>();
		returnList.add(plateauTest.plateau[7][10]);
		returnList.add(plateauTest.plateau[7][9]);
		returnList.add(plateauTest.plateau[7][8]);
		
		assertEquals(plateauTest.checkHaut(7, 7), returnList);
		
	}

	@Test
	public void testCheckBas() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		
		Lettre m = new Lettre('m', 1);
		Lettre o = new Lettre('o', 1);
		Lettre t = new Lettre('t', 1);
		Lettre s = new Lettre('s', 1);
		Lettre e = new Lettre('e', 1);
		
		plateauTest.plateau[9][7].setLettre(s);
		plateauTest.plateau[8][7].setLettre(e);
		plateauTest.plateau[7][7].setLettre(s);
		plateauTest.plateau[7][8].setLettre(t);
		plateauTest.plateau[7][9].setLettre(o);
		plateauTest.plateau[7][10].setLettre(m);
		
		List<Case> returnList = new ArrayList<Case>();
		returnList.add(plateauTest.plateau[7][9]);
		returnList.add(plateauTest.plateau[7][8]);
		returnList.add(plateauTest.plateau[7][7]);
		
		assertEquals(plateauTest.checkBas(7, 10), returnList);
	}

	@Test
	public void testCheckGauche() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		
		Lettre m = new Lettre('m', 1);
		Lettre o = new Lettre('o', 1);
		Lettre t = new Lettre('t', 1);
		Lettre s = new Lettre('s', 1);
		Lettre e = new Lettre('e', 1);
		
		plateauTest.plateau[10][5].setLettre(s);
		plateauTest.plateau[10][6].setLettre(e);
		plateauTest.plateau[10][7].setLettre(s);
		plateauTest.plateau[9][7].setLettre(t);
		plateauTest.plateau[8][7].setLettre(o);
		plateauTest.plateau[7][7].setLettre(m);
		
		List<Case> returnList = new ArrayList<Case>();
		returnList.add(plateauTest.plateau[7][7]);
		returnList.add(plateauTest.plateau[8][7]);
		returnList.add(plateauTest.plateau[9][7]);
		
		assertEquals(plateauTest.checkGauche(10, 7), returnList);
	}

	@Test
	public void testCheckDroite() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		
		Lettre m = new Lettre('m', 1);
		Lettre o = new Lettre('o', 1);
		Lettre t = new Lettre('t', 1);
		Lettre s = new Lettre('s', 1);
		Lettre e = new Lettre('e', 1);
		
		plateauTest.plateau[10][5].setLettre(s);
		plateauTest.plateau[10][6].setLettre(e);
		plateauTest.plateau[10][7].setLettre(s);
		plateauTest.plateau[9][7].setLettre(t);
		plateauTest.plateau[8][7].setLettre(o);
		plateauTest.plateau[7][7].setLettre(m);
		
		List<Case> returnList = new ArrayList<Case>();
		returnList.add(plateauTest.plateau[8][7]);
		returnList.add(plateauTest.plateau[9][7]);
		returnList.add(plateauTest.plateau[10][7]);
		
		assertEquals(plateauTest.checkDroite(7, 7), returnList);
	}

	@Test
	public void testCheckHautBas() {
		//fail("Not yet implemented"); // TODO
		Plateau plateauTest = new Plateau();
		List<Lettre> motJoue = new ArrayList<Lettre>();
		
		Lettre l1 = new Lettre('j', 1);
		Lettre l2 = new Lettre('o', 1);
		Lettre l3 = new Lettre('u', 1);
		Lettre l4 = new Lettre('e', 1);
		Lettre l5 = new Lettre('r', 2);
		
		plateauTest.plateau[10][7].setLettre(l1);
		plateauTest.plateau[9][7].setLettre(l2);
		plateauTest.plateau[8][7].setLettre(l3);
		plateauTest.plateau[7][7].setLettre(l4);
		
		motJoue.add(l1);
		motJoue.add(l2);
		motJoue.add(l3);
		motJoue.add(l4);
		
		plateauTest.plateau[6][7].setLettre(l5);

		motJoue.add(l5);
		
		assertEquals(plateauTest.checkHautBas(8, 7, 2, motJoue), true);
	}

	@Test
	public void testCheckGaucheDroite() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetLabelToList() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		List<Case> testList = new ArrayList<Case>();
		Lettre a = new Lettre('a', 0);
		Lettre f = new Lettre('f', 1);
		Lettre z = new Lettre('z', 10);
		Case c1 = new Case(2, a);
		Case c2 = new Case(5, f);
		Case c3 = new Case(0, z);
		testList.add(c1);
		testList.add(c2);
		testList.add(c3);
		assertEquals(plateauTest.getLabelToList(testList), "afz");
	}

	@Test
	public void testGetScoreToList() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetMotToList() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		List<Lettre> testList = new ArrayList<Lettre>();
		Lettre a = new Lettre('a', 0);
		Lettre f = new Lettre('f', 1);
		Lettre z = new Lettre('z', 10);
		testList.add(a);
		testList.add(f);
		testList.add(z);
		assertEquals(plateauTest.getMotToList(testList), "afz");
	}

	@Test
	public void testCopyPlateau() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		Plateau plateauCopie = new Plateau();
		plateauCopie.plateau = plateauTest.copyPlateau();
		assertEquals(plateauTest.toString(), plateauCopie.toString());
		
	}

	@Test
	public void testCheckPremierMot() {
		//fail("Not yet implemented"); s
		Joueur joueur = new Joueur();
		Plateau plateauTest = new Plateau();
		Lettre m = new Lettre('m', 1);
		Lettre o = new Lettre('o', 1);
		Lettre t = new Lettre('t', 1);
		Lettre s = new Lettre('s', 1);
		plateauTest.plateau[5][7].setLettre(m);
		plateauTest.plateau[6][7].setLettre(o);
		plateauTest.plateau[7][7].setLettre(t);
		plateauTest.plateau[8][7].setLettre(s);
		assertEquals(plateauTest.checkPremierMot(5, 7, 'h', joueur), true);
		
		Plateau plateauTest2 = new Plateau();
		plateauTest2.plateau[5][8].setLettre(m);
		plateauTest2.plateau[6][8].setLettre(o);
		plateauTest2.plateau[7][8].setLettre(t);
		plateauTest2.plateau[8][8].setLettre(s);
		assertEquals(plateauTest2.checkPremierMot(5, 8, 'h', joueur), false);
	}

	@Test
	public void testCalculScore() {
		//fail("Not yet implemented"); // TODO
		Plateau plateauTest = new Plateau();
		Lettre m = new Lettre('m', 1);
		Lettre o = new Lettre('o', 1);
		Lettre t = new Lettre('t', 1);
		Lettre s = new Lettre('s', 1);
		
		List<Lettre> listLettre = new ArrayList<Lettre>();
		listLettre.add(m);
		listLettre.add(o);
		listLettre.add(t);
		listLettre.add(s);
		
		for(int i = 0; i < 4; i++) {
			plateauTest.plateau[7][0 + i].setLettre(listLettre.get(i));
			plateauTest.calculScore(7, 0 + i);
		}
		
		assertEquals(plateauTest.score, 5); //Pas de prise en compte des mots double/triple
		
	}

	@Test
	public void testToString() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		String affichage = "14|4|0|0|1|0|0|0|4|0|0|0|1|0|0|4|\n";
		affichage +=	 "13|0|3|0|0|0|2|0|0|0|2|0|0|0|3|0|\n";
		affichage +=	 "12|0|0|3|0|0|0|1|0|1|0|0|0|3|0|0|\n";
		affichage +=	 "11|1|0|0|3|0|0|0|1|0|0|0|3|0|0|1|\n";
		affichage +=	 "10|0|0|0|0|3|0|0|0|0|0|3|0|0|0|0|\n";
		affichage +=	 "9 |0|2|0|0|0|2|0|0|0|2|0|0|0|2|0|\n";
		affichage +=	 "8 |0|0|1|0|0|0|1|0|1|0|0|0|1|0|0|\n";
		affichage +=	 "7 |4|0|0|1|0|0|0|5|0|0|0|1|0|0|4|\n";
		affichage +=	 "6 |0|0|1|0|0|0|1|0|1|0|0|0|1|0|0|\n";
		affichage +=	 "5 |0|2|0|0|0|2|0|0|0|2|0|0|0|2|0|\n";
		affichage +=	 "4 |0|0|0|0|3|0|0|0|0|0|3|0|0|0|0|\n";
		affichage +=	 "3 |1|0|0|3|0|0|0|1|0|0|0|3|0|0|1|\n";
		affichage +=	 "2 |0|0|3|0|0|0|1|0|1|0|0|0|3|0|0|\n";
		affichage += "1 |0|3|0|0|0|2|0|0|0|2|0|0|0|3|0|\n";
		affichage += "0 |4|0|0|1|0|0|0|4|0|0|0|1|0|0|4|\n";
		affichage += "  |0|1|2|3|4|5|6|7|8|9|1011121314\n";
		assertEquals(plateauTest.toString(), affichage);
	}

}
