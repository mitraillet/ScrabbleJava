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
<<<<<<< HEAD
		assertEquals(plateauTest.getPlateau().length, 15);
=======
		assertEquals(plateauTest.plateau.length, 15);
>>>>>>> master
	}

	@Test
	public void testInitCasePlateau() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		Case caseTest = new Case(6);
		plateauTest.initCasePlateau(caseTest, 0, 0);
<<<<<<< HEAD
		assertEquals(plateauTest.getPlateauBonus(0, 0), 6);
=======
		assertEquals(plateauTest.plateau[0][0].getBonus(), 6);
>>>>>>> master
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
		Plateau plateauTest = new Plateau();
		
		List<Lettre> motJoue = new ArrayList<Lettre>();
		List<Lettre> motMain = new ArrayList<Lettre>();
		
		Lettre m = new Lettre('m', 2);
		Lettre o = new Lettre('o', 1);
		Lettre t = new Lettre('t', 1);
		Lettre s = new Lettre('s', 1);
		Lettre e = new Lettre('e', 1);
		Lettre u = new Lettre('u', 1);
		
		motJoue.add(m);
		motJoue.add(o);
		motJoue.add(t);
		
		motMain.add(m);
		motMain.add(o);
		motMain.add(t);
		
<<<<<<< HEAD
		plateauTest.getCase(7, 7).setLettre(m);
		plateauTest.getCase(8, 7).setLettre(o);
		plateauTest.getCase(9, 7).setLettre(t);
		
		plateauTest.getCase(8, 6).setLettre(u);
		
		System.out.println(plateauTest);
		
		assertEquals(plateauTest.verificationPeripherique(7, 7, 'h', motMain, motJoue), true); 
		
		plateauTest.getCase(10, 7).setLettre(s);
=======
		plateauTest.plateau[7][7].setLettre(m);
		plateauTest.plateau[8][7].setLettre(o);
		plateauTest.plateau[9][7].setLettre(t);
		
		plateauTest.plateau[8][6].setLettre(u);
		
		assertEquals(plateauTest.verificationPeripherique(7, 7, 'h', motMain, motJoue), true); 
		
		plateauTest.plateau[10][7].setLettre(s);
>>>>>>> master
		
		motMain.removeAll(motMain);
		motMain.add(s);
		motJoue.removeAll(motJoue);
		
		for(int i = 0; i < 3; i++) {
			motJoue.add(null);
		}
		
		motJoue.add(s);
		
		assertEquals(plateauTest.verificationPeripherique(7, 7, 'h', motMain, motJoue), true);
		
		motJoue.remove(s);
		motJoue.add(null);
		motJoue.add(e);
		
		motMain.removeAll(motMain);
		motMain.add(e);
		
<<<<<<< HEAD
		plateauTest.getCase(11, 7).setLettre(e);
=======
		plateauTest.plateau[11][7].setLettre(e);
>>>>>>> master
		
		assertEquals(plateauTest.verificationPeripherique(7, 7, 'h', motMain, motJoue), false);
		
		motMain.removeAll(motMain);
		motJoue.removeAll(motJoue);
		
		motMain.add(t);
		motMain.add(o);
		motMain.add(u);
		motMain.add(s);
		
		motJoue.add(t);
		motJoue.add(o);
		motJoue.add(u);
		motJoue.add(s);
		
<<<<<<< HEAD
		plateauTest.getCase(2, 10).setLettre(t);
		plateauTest.getCase(2, 9).setLettre(o);
		plateauTest.getCase(2, 8).setLettre(u);
		plateauTest.getCase(2, 7).setLettre(s);
=======
		plateauTest.plateau[2][10].setLettre(t);
		plateauTest.plateau[2][9].setLettre(o);
		plateauTest.plateau[2][8].setLettre(u);
		plateauTest.plateau[2][7].setLettre(s);
>>>>>>> master
		
		assertEquals(plateauTest.verificationPeripherique(2, 10, 'v', motMain, motJoue), false);
		
		motMain.removeAll(motMain);
		motJoue.removeAll(motJoue);
		
		motJoue.add(m);
		motJoue.add(o);
		motJoue.add(u);
		
		motMain.add(o);
		motMain.add(u);
		
<<<<<<< HEAD
		plateauTest.getCase(7, 6).setLettre(o);
		plateauTest.getCase(7, 5).setLettre(u);
		
		plateauTest.getCase(11, 7).setLettre(null);
=======
		plateauTest.plateau[7][6].setLettre(o);
		plateauTest.plateau[7][5].setLettre(u);
		
		plateauTest.plateau[11][7].setLettre(null);
>>>>>>> master
		
		assertEquals(plateauTest.verificationPeripherique(7, 7, 'v', motMain, motJoue), true);

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
		
<<<<<<< HEAD
		plateauTest.getCase(9, 7).setLettre(s);
		plateauTest.getCase(8, 7).setLettre(e);
		plateauTest.getCase(7, 7).setLettre(s);
		plateauTest.getCase(7, 8).setLettre(t);
		plateauTest.getCase(7, 9).setLettre(o);
		plateauTest.getCase(7, 10).setLettre(m);
		
		List<Case> returnList = new ArrayList<Case>();
		returnList.add(plateauTest.getCase(7, 10));
		returnList.add(plateauTest.getCase(7, 9));
		returnList.add(plateauTest.getCase(7, 8));
=======
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
>>>>>>> master
		
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
		
<<<<<<< HEAD
		plateauTest.getCase(9, 7).setLettre(s);
		plateauTest.getCase(8, 7).setLettre(e);
		plateauTest.getCase(7, 7).setLettre(s);
		plateauTest.getCase(7, 8).setLettre(t);
		plateauTest.getCase(7, 9).setLettre(o);
		plateauTest.getCase(7, 10).setLettre(m);
		
		List<Case> returnList = new ArrayList<Case>();
		returnList.add(plateauTest.getCase(7, 9));
		returnList.add(plateauTest.getCase(7, 8));
		returnList.add(plateauTest.getCase(7, 7));
=======
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
>>>>>>> master
		
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
		
<<<<<<< HEAD
		plateauTest.getCase(10, 5).setLettre(s);
		plateauTest.getCase(10, 6).setLettre(e);
		plateauTest.getCase(10, 7).setLettre(s);
		plateauTest.getCase(9, 7).setLettre(t);
		plateauTest.getCase(8, 7).setLettre(o);
		plateauTest.getCase(7, 7).setLettre(m);
		
		List<Case> returnList = new ArrayList<Case>();
		returnList.add(plateauTest.getCase(7, 7));
		returnList.add(plateauTest.getCase(8, 7));
		returnList.add(plateauTest.getCase(9, 7));
=======
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
>>>>>>> master
		
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
		
<<<<<<< HEAD
		plateauTest.getCase(10, 5).setLettre(s);
		plateauTest.getCase(10, 6).setLettre(e);
		plateauTest.getCase(10, 7).setLettre(s);
		plateauTest.getCase(9, 7).setLettre(t);
		plateauTest.getCase(8, 7).setLettre(o);
		plateauTest.getCase(7, 7).setLettre(m);
		
		List<Case> returnList = new ArrayList<Case>();
		returnList.add(plateauTest.getCase(8, 7));
		returnList.add(plateauTest.getCase(9, 7));
		returnList.add(plateauTest.getCase(10, 7));
=======
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
>>>>>>> master
		
		assertEquals(plateauTest.checkDroite(7, 7), returnList);
	}

	@Test
	public void testCheckHautBas() {
		Plateau plateauTest = new Plateau();
		List<Lettre> motJoue = new ArrayList<Lettre>();
		
		Lettre l1 = new Lettre('j', 1);
		Lettre l2 = new Lettre('o', 1);
		Lettre l3 = new Lettre('u', 1);
		Lettre l4 = new Lettre('e', 1);
		Lettre l5 = new Lettre('r', 2);
		Lettre l6 = new Lettre('x', 10);
		
<<<<<<< HEAD
		plateauTest.getCase(7, 7).setLettre(l1);
		plateauTest.getCase(8, 7).setLettre(l2);
		plateauTest.getCase(9, 7).setLettre(l3);
		plateauTest.getCase(10, 7).setLettre(l4);
		plateauTest.getCase(11, 7).setLettre(l5);
=======
		plateauTest.plateau[7][7].setLettre(l1);
		plateauTest.plateau[8][7].setLettre(l2);
		plateauTest.plateau[9][7].setLettre(l3);
		plateauTest.plateau[10][7].setLettre(l4);
		plateauTest.plateau[11][7].setLettre(l5);
>>>>>>> master
		
		motJoue.add(l1);
		motJoue.add(l2);
		motJoue.add(l3);
		motJoue.add(l4);
		motJoue.add(l5);
		
		assertEquals(plateauTest.checkHautBas(8, 7, 1, motJoue), true);
		
<<<<<<< HEAD
		plateauTest.getCase(8, 6).setLettre(l3);
		
		assertEquals(plateauTest.checkHautBas(8, 7, 1, motJoue), true);
		
		plateauTest.getCase(8, 8).setLettre(l5);
		
		assertEquals(plateauTest.checkHautBas(8, 7, 1, motJoue), false);
		
		plateauTest.getCase(8, 5).setLettre(l6);
=======
		plateauTest.plateau[8][6].setLettre(l3);
		
		assertEquals(plateauTest.checkHautBas(8, 7, 1, motJoue), true);
		
		plateauTest.plateau[8][8].setLettre(l5);
		
		assertEquals(plateauTest.checkHautBas(8, 7, 1, motJoue), false);
		
		plateauTest.plateau[8][5].setLettre(l6);
>>>>>>> master
		
		assertEquals(plateauTest.checkHautBas(8, 7, 1, motJoue), true);
	}

	@Test
	public void testCheckGaucheDroite() {
		Plateau plateauTest = new Plateau();
		List<Lettre> motJoue = new ArrayList<Lettre>();
		
		Lettre l1 = new Lettre('j', 1);
		Lettre l2 = new Lettre('o', 1);
		Lettre l3 = new Lettre('u', 1);
		Lettre l4 = new Lettre('e', 1);
		Lettre l5 = new Lettre('r', 2);
		Lettre l6 = new Lettre('x', 10);
		
<<<<<<< HEAD
		plateauTest.getCase(7, 7).setLettre(l1);
		plateauTest.getCase(7, 6).setLettre(l2);
		plateauTest.getCase(7, 5).setLettre(l3);
		plateauTest.getCase(7, 4).setLettre(l4);
		plateauTest.getCase(7, 3).setLettre(l5);
=======
		plateauTest.plateau[7][7].setLettre(l1);
		plateauTest.plateau[7][6].setLettre(l2);
		plateauTest.plateau[7][5].setLettre(l3);
		plateauTest.plateau[7][4].setLettre(l4);
		plateauTest.plateau[7][3].setLettre(l5);
>>>>>>> master
		
		motJoue.add(l1);
		motJoue.add(l2);
		motJoue.add(l3);
		motJoue.add(l4);
		motJoue.add(l5);
		
		assertEquals(plateauTest.checkGaucheDroite(7, 6, 1, motJoue), true);
		
<<<<<<< HEAD
		plateauTest.getCase(8, 6).setLettre(l3);
		
		assertEquals(plateauTest.checkGaucheDroite(7, 6, 1, motJoue), true);
		
		plateauTest.getCase(6, 6).setLettre(l5);
		
		assertEquals(plateauTest.checkGaucheDroite(7, 6, 1, motJoue), false);
		
		plateauTest.getCase(9, 6).setLettre(l6);
=======
		plateauTest.plateau[8][6].setLettre(l3);
		
		assertEquals(plateauTest.checkGaucheDroite(7, 6, 1, motJoue), true);
		
		plateauTest.plateau[6][6].setLettre(l5);
		
		assertEquals(plateauTest.checkGaucheDroite(7, 6, 1, motJoue), false);
		
		plateauTest.plateau[9][6].setLettre(l6);
>>>>>>> master
		
		assertEquals(plateauTest.checkGaucheDroite(7, 6, 1, motJoue), true);
	}
	
	//----------------------------------------------------------------------------------------------------

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
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		Lettre a = new Lettre('a', 1);
		Lettre z = new Lettre('z', 10);
		Lettre v = new Lettre('v', 4);
		
		Case case1 = new Case(0, a);
		Case case2 = new Case(2, z);
		Case case3 = new Case(4, v);
		
		List<Case> listCase = new ArrayList<Case>();
		listCase.add(case1);
		assertEquals(plateauTest.getScoreToList(listCase), 1);
		
		listCase.add(case2);
		listCase.add(case3);
		
		assertEquals(plateauTest.getScoreToList(listCase), 15);
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
	public void testGetCase() {
		Plateau plateauTest = new Plateau();
<<<<<<< HEAD
		Case testCase = new Case();
		
		assertEquals(plateauTest.getCase(10, 5).getBonus(), testCase.getBonus());
=======
		Lettre a = new Lettre('a', 0);
		
		plateauTest.plateau[10][5].setLettre(a);
		Case testCase = plateauTest.plateau[10][5];
		
		assertEquals(plateauTest.getCase(10, 5), testCase);
>>>>>>> master
	}
	
	@Test
	public void testGetPlateauBonus() {
		Plateau plateauTest = new Plateau();
		
		assertEquals(plateauTest.getPlateauBonus(7, 7), 5);
		assertEquals(plateauTest.getPlateauBonus(7, 8), 0);
		assertEquals(plateauTest.getPlateauBonus(7, 0), 4);
	}
	
	@Test
	public void testGetPlateauLabel() {
		Plateau plateauTest = new Plateau();
		Lettre b = new Lettre('b', 2);
<<<<<<< HEAD
		plateauTest.getCase(7, 7).setLettre(b);
=======
		plateauTest.plateau[7][7].setLettre(b);
>>>>>>> master
		
		assertEquals(plateauTest.getPlateauLabel(7, 7), 'b');
		
	}
	
	@Test
<<<<<<< HEAD
	public void testGetPlateauLettre() {
		Plateau plateauTest = new Plateau();
		
		Lettre test = new Lettre('$', 17);
		plateauTest.getCase(7, 8).setLettre(test);
		
		assertEquals(plateauTest.getPlateauLettre(7, 8), test);
	}
	
	@Test
=======
>>>>>>> master
	public void testCopyPlateau() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		Plateau plateauCopie = new Plateau();
<<<<<<< HEAD
		plateauCopie.setPlateau(plateauTest.copyPlateau());
=======
		plateauCopie.plateau = plateauTest.copyPlateau();
>>>>>>> master
		assertEquals(plateauTest.toString(), plateauCopie.toString());
		
	}
	
	@Test
	public void testConvertListCaseToListLettre() {
		Plateau plateauTest = new Plateau();
		Lettre a = new Lettre('a', 0);
		Lettre v = new Lettre('v', 4);
		Lettre z = new Lettre('z', 10);
		
		Case case1 = new Case(0, a);
		Case case2 = new Case(5, v);
		Case case3 = new Case(3, z);
		
		List<Case> listCase = new ArrayList<Case>();
		List<Lettre> listLettre = new ArrayList<Lettre>();
		
		listCase.add(case1);
		listCase.add(case2);
		listCase.add(case3);
		
		listLettre.add(a);
		listLettre.add(v);
		listLettre.add(z);
		
		assertEquals(plateauTest.convertListCaseToListLettre(listCase), listLettre);
		
	}

	@Test
	public void testCheckPremierMot() {
		//fail("Not yet implemented"); s
		Joueur joueur = new Joueur();
		Plateau plateauTest = new Plateau();
		List<Lettre> motMain = new ArrayList<Lettre>();
		
		
		Lettre m = new Lettre('m', 1);
		Lettre o = new Lettre('o', 1);
		Lettre t = new Lettre('t', 1);
		Lettre s = new Lettre('s', 1);
		
		motMain.add(m);
		motMain.add(o);
		motMain.add(t);
		motMain.add(s);
		
<<<<<<< HEAD
		plateauTest.getCase(5, 7).setLettre(m);
		plateauTest.getCase(6, 7).setLettre(o);
		plateauTest.getCase(7, 7).setLettre(t);
		plateauTest.getCase(8, 7).setLettre(s);
		
		List<Lettre> motJoue = new ArrayList<Lettre>();
		motJoue.addAll(motMain);
		
		
		assertEquals(plateauTest.checkPremierMot(5, 7, 'h', joueur, motMain, motJoue), true);
		
		Plateau plateauTest2 = new Plateau();
		plateauTest2.getCase(5, 8).setLettre(m);
		plateauTest2.getCase(6, 8).setLettre(o);
		plateauTest2.getCase(7, 8).setLettre(t);
		plateauTest2.getCase(8, 8).setLettre(s);
		assertEquals(plateauTest2.checkPremierMot(5, 8, 'h', joueur, motMain, motJoue), false);
=======
		plateauTest.plateau[5][7].setLettre(m);
		plateauTest.plateau[6][7].setLettre(o);
		plateauTest.plateau[7][7].setLettre(t);
		plateauTest.plateau[8][7].setLettre(s);
		
		
		assertEquals(plateauTest.checkPremierMot(5, 7, 'h', joueur, motMain), true);
		
		Plateau plateauTest2 = new Plateau();
		plateauTest2.plateau[5][8].setLettre(m);
		plateauTest2.plateau[6][8].setLettre(o);
		plateauTest2.plateau[7][8].setLettre(t);
		plateauTest2.plateau[8][8].setLettre(s);
		assertEquals(plateauTest2.checkPremierMot(5, 8, 'h', joueur, motMain), false);
>>>>>>> master
	}
	
	@Test
	public void testCalculScore() {
		Plateau plateauTest = new Plateau();
		
		Lettre s = new Lettre('s', 1);
		Lettre e = new Lettre('e', 1);
		Lettre l = new Lettre('l', 1);
		Lettre n = new Lettre('n', 1);
		
<<<<<<< HEAD
		plateauTest.getCase(7, 5).setLettre(s);
		plateauTest.getCase(8, 5).setLettre(e);
		plateauTest.getCase(9, 5).setLettre(l);
		plateauTest.getCase(10, 5).setLettre(l);
		plateauTest.getCase(11, 5).setLettre(e);
		
		plateauTest.getCase(10, 4).setLettre(e);
		plateauTest.getCase(11, 4).setLettre(n);
=======
		plateauTest.plateau[7][5].setLettre(s);
		plateauTest.plateau[8][5].setLettre(e);
		plateauTest.plateau[9][5].setLettre(l);
		plateauTest.plateau[10][5].setLettre(l);
		plateauTest.plateau[11][5].setLettre(e);
		
		plateauTest.plateau[10][4].setLettre(e);
		plateauTest.plateau[11][4].setLettre(n);
>>>>>>> master
		
		List<Lettre> motMain = new ArrayList<Lettre>();
		List<Lettre> motJoue = new ArrayList<Lettre>();
		
		motMain.add(e);
		motMain.add(n);
		
		motJoue.add(e);
		motJoue.add(n);
<<<<<<< HEAD
		
		System.out.println(plateauTest);
		
		int tempScore = plateauTest.calculScore(10, 4, 'h', motMain, motJoue);
		
		assertEquals(tempScore, 10);
=======
		plateauTest.motJoue = motJoue;
		
		System.out.println(plateauTest);
		
		plateauTest.calculScore(10, 4, 'h', motMain);
		
		assertEquals(plateauTest.tempScore, 10);
>>>>>>> master
		
	}
	
	@Test
	public void testCalculScorePeripherique() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
<<<<<<< HEAD
		List<Lettre> motJoue = new ArrayList<Lettre>();
=======
>>>>>>> master
		
		Lettre m = new Lettre('m', 2);
		Lettre o = new Lettre('o', 1);
		Lettre t = new Lettre('t', 1);
		Lettre z = new Lettre('z', 10);
		
<<<<<<< HEAD
		plateauTest.getCase(9, 10).setLettre(z);
		plateauTest.getCase(10, 9).setLettre(m);
		plateauTest.getCase(10, 8).setLettre(o);
		plateauTest.getCase(10, 10).setLettre(t);
		
		motJoue.add(t);
		motJoue.add(m);
		motJoue.add(o);
		
		System.out.println(plateauTest);
		
		int tempScore = plateauTest.calculScorePeripherique(10, 10, 'v', motJoue);
		
		assertEquals(tempScore, 22);
=======
		plateauTest.plateau[9][10].setLettre(z);
		plateauTest.plateau[10][9].setLettre(m);
		plateauTest.plateau[10][8].setLettre(o);
		plateauTest.plateau[10][10].setLettre(t);
		
		plateauTest.motJoue.add(t);
		plateauTest.motJoue.add(m);
		plateauTest.motJoue.add(o);
		
		System.out.println(plateauTest);
		
		plateauTest.calculScorePeripherique(10, 10, 'v');
		
		assertEquals(plateauTest.tempScore, 22);
>>>>>>> master
	}

	@Test
	public void testCalculScoreMot() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		
		Lettre m = new Lettre('m', 2);
		Lettre o = new Lettre('o', 1);
		Lettre t = new Lettre('t', 1);
		Lettre z = new Lettre('z', 10);
		
		List<Lettre> motMain = new ArrayList<Lettre>();
		
		motMain.add(z);
		motMain.add(m);
<<<<<<< HEAD
		
		List<Lettre> lettreDouble = new ArrayList<Lettre>();
		List<Lettre> lettreTriple = new ArrayList<Lettre>();
		List<Lettre> lettreScore = new ArrayList<Lettre>();

		lettreDouble.add(z);
		lettreTriple.add(m);
		lettreTriple.add(o);
		lettreScore.add(t);
		
		int tempScore = plateauTest.calculScoreMot(motMain, lettreDouble, lettreTriple, lettreScore);
		
		assertEquals(tempScore, 30);
	}
	
	@Test
	public void testSetScoreJoueur() {
		Joueur joueur = new Joueur();
		Plateau plateauTest = new Plateau();
		
		plateauTest.setScoreJoueur(joueur, 30);
		
		assertEquals(joueur.getScore(), 30);
		
		
		plateauTest.setScoreJoueur(joueur, 17);
		
		assertEquals(joueur.getScore(), 47);
=======

		plateauTest.lettreDouble.add(z);
		plateauTest.lettreTriple.add(m);
		plateauTest.lettreTriple.add(o);
		plateauTest.lettreScore.add(t);
		
		plateauTest.calculScoreMot(motMain);
		
		assertEquals(plateauTest.tempScore, 30);
	}
	
	@Test
	public void testDeleteBonus() {
		Plateau plateauTest = new Plateau();
		
		List<Lettre> motJoueTest = new ArrayList<Lettre>();
		Lettre s = new Lettre('s', 1);
		Lettre e = new Lettre('e', 1);
		motJoueTest.add(s);
		motJoueTest.add(e);
		
		plateauTest.motJoue = motJoueTest;
		plateauTest.plateau[7][7].setLettre(s);
		plateauTest.plateau[8][7].setLettre(e);
		
		assertEquals(plateauTest.plateau[7][7].getBonus(), 5);
		
		plateauTest.deleteBonus(7, 7, 'h');
		
		assertEquals(plateauTest.plateau[7][7].getBonus(), 0);
>>>>>>> master
		
	}
	
	@Test
<<<<<<< HEAD
	public void checkCaseRemplie(){
		//fail("not yet implemented");
		
		Plateau plateauTest = new Plateau();
		Lettre x = new Lettre('x', 10);
		
		plateauTest.getPlateau()[7][7].setLettre(x);
		
		assertEquals(plateauTest.checkCaseRemplie(7, 7, 'h', 0), true);
		assertEquals(plateauTest.checkCaseRemplie(7, 7, 'h', 1), false);
=======
	public void testSetScoreJoueur() {
		Joueur joueur = new Joueur();
		Plateau plateauTest = new Plateau();
		
		plateauTest.tempScore = 30;
		plateauTest.setScoreJoueur(joueur);
		
		assertEquals(joueur.getScore(), 30);
		
		plateauTest.tempScore = 17;
		plateauTest.setScoreJoueur(joueur);
		
		assertEquals(joueur.getScore(), 47);
>>>>>>> master
		
	}
	

	@Test
	public void testToString() {
		//fail("Not yet implemented"); 
		Plateau plateauTest = new Plateau();
		Lettre testLettre = new Lettre('w', 10);
<<<<<<< HEAD
		plateauTest.getCase(8, 7).setLettre(testLettre);
		String affichage = "14|4| | |1| | | |4| | | |1| | |4|\n";
		affichage +=	 "13| |3| | | |2| | | |2| | | |3| |\n";
		affichage +=	 "12| | |3| | | |1| |1| | | |3| | |\n";
		affichage +=	 "11|1| | |3| | | |1| | | |3| | |1|\n";
		affichage +=	 "10| | | | |3| | | | | |3| | | | |\n";
		affichage +=	 "9 | |2| | | |2| | | |2| | | |2| |\n";
		affichage +=	 "8 | | |1| | | |1| |1| | | |1| | |\n";
		affichage +=	 "7 |4| | |1| | | |5|w| | |1| | |4|\n";
		affichage +=	 "6 | | |1| | | |1| |1| | | |1| | |\n";
		affichage +=	 "5 | |2| | | |2| | | |2| | | |2| |\n";
		affichage +=	 "4 | | | | |3| | | | | |3| | | | |\n";
		affichage +=	 "3 |1| | |3| | | |1| | | |3| | |1|\n";
		affichage +=	 "2 | | |3| | | |1| |1| | | |3| | |\n";
		affichage += "1 | |3| | | |2| | | |2| | | |3| |\n";
		affichage += "0 |4| | |1| | | |4| | | |1| | |4|\n";
=======
		plateauTest.plateau[8][7].setLettre(testLettre);
		String affichage = "14|4|0|0|1|0|0|0|4|0|0|0|1|0|0|4|\n";
		affichage +=	 "13|0|3|0|0|0|2|0|0|0|2|0|0|0|3|0|\n";
		affichage +=	 "12|0|0|3|0|0|0|1|0|1|0|0|0|3|0|0|\n";
		affichage +=	 "11|1|0|0|3|0|0|0|1|0|0|0|3|0|0|1|\n";
		affichage +=	 "10|0|0|0|0|3|0|0|0|0|0|3|0|0|0|0|\n";
		affichage +=	 "9 |0|2|0|0|0|2|0|0|0|2|0|0|0|2|0|\n";
		affichage +=	 "8 |0|0|1|0|0|0|1|0|1|0|0|0|1|0|0|\n";
		affichage +=	 "7 |4|0|0|1|0|0|0|5|w|0|0|1|0|0|4|\n";
		affichage +=	 "6 |0|0|1|0|0|0|1|0|1|0|0|0|1|0|0|\n";
		affichage +=	 "5 |0|2|0|0|0|2|0|0|0|2|0|0|0|2|0|\n";
		affichage +=	 "4 |0|0|0|0|3|0|0|0|0|0|3|0|0|0|0|\n";
		affichage +=	 "3 |1|0|0|3|0|0|0|1|0|0|0|3|0|0|1|\n";
		affichage +=	 "2 |0|0|3|0|0|0|1|0|1|0|0|0|3|0|0|\n";
		affichage += "1 |0|3|0|0|0|2|0|0|0|2|0|0|0|3|0|\n";
		affichage += "0 |4|0|0|1|0|0|0|4|0|0|0|1|0|0|4|\n";
>>>>>>> master
		affichage += "  |0|1|2|3|4|5|6|7|8|9|1011121314\n";
		assertEquals(plateauTest.toString(), affichage);
	}

}
