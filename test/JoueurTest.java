package test;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import scrabble.Joueur;
import scrabble.Lettre;
import scrabble.Plateau;
import scrabble.Sac;
import scrabble.Case;

public class JoueurTest {

	@Test
	public void testGenerateNumber() {
		Joueur joueur = new Joueur();
		for(int i = 0 ; i < 50; i++) {
			int temp = joueur.generateNumber(0, 15);
			assertTrue( 0 <= temp  && temp <= 15);
		}
	}

	@Test
	public void testSetScore() {
		//fail("Not yet implemented"); // TODO
		Joueur joueur = new Joueur();
		joueur.setScore(80);
		assertEquals(joueur.getScore(), 80);
	}
	
	@Test
	public void testGetScore() {
		//fail("Not yet implemented"); // TODO
		Joueur joueur = new Joueur();
		joueur.setScore(50);
		assertEquals(joueur.getScore(), 50);
	}
	
	@Test
	public void testAddScore() {
		//fail("Not yet implemented"); // TODO
		Joueur joueur = new Joueur();
		
		joueur.addScore(5);
		assertEquals(joueur.getScore(), 5);
		
		joueur.addScore(40);
		assertEquals(joueur.getScore(), 45);
		
	}

	@Test
	public void testGetLettreMain() throws XPathExpressionException {
		Sac sac = new Sac();
		Joueur joueur = new Joueur();
		joueur.pioche(sac);
		for(int i = 0; i < 7; i++) {
			assertFalse(joueur.getLettreMain(i) == null);
		}
	}
	
	@Test
	public void testGetLabelLettreMain() {
		//fail("Not yet implemented"); // TODO
		Joueur joueur = new Joueur();
		List<Lettre> listMain = new ArrayList<Lettre>();
		
		Lettre a = new Lettre('a', 1);
		Lettre z = new Lettre('z', 10);
		Lettre b = new Lettre('b', 2);
		
		listMain.add(a);
		listMain.add(z);
		listMain.add(b);
		
		joueur.setMainJoueur(listMain);
		
		assertEquals(joueur.getLabelLettreMain(1), 'z');
		assertEquals(joueur.getLabelLettreMain(0), 'a');
	}

	@Test
	public void testGetSizeMainJoueur() throws XPathExpressionException {
		Sac sac = new Sac();
		Joueur joueur = new Joueur();
		joueur.pioche(sac);
		
		assertTrue(joueur.getSizeMainJoueur() == 7);
	}

	@Test
	public void testGetMainJoueur() throws XPathExpressionException {
		Sac sac = new Sac();
		Joueur joueur = new Joueur();
		
		assertTrue(joueur.getMainJoueur().isEmpty());
		
		joueur.pioche(sac);
		assertFalse(joueur.getMainJoueur().isEmpty());
	}

	@Test
	public void testSetMainJoueur() {
		Joueur joueur = new Joueur();
		
		List<Lettre> mainTest = new ArrayList<Lettre>();
		
		mainTest.add(new Lettre('a', 15));
		mainTest.add(new Lettre('r', 5));
		mainTest.add(new Lettre('d', 9));
		mainTest.add(new Lettre('t', 105));
		mainTest.add(new Lettre('z', 81));
		mainTest.add(new Lettre('p', 1));
		joueur.setMainJoueur(mainTest);
		
		assertEquals(joueur.getMainJoueur(), mainTest);
	}

	@Test
	public void testPioche() throws XPathExpressionException {
		//Si main pleine pas de changement de taille ni du sac ni de la main
		for(int i = 0; i < 25; i++) {
			Sac sac = new Sac();
			Joueur joueur = new Joueur();
			joueur.pioche(sac);
			int tailleSac = sac.tailleContenuSac();
			int tailleMain = joueur.getSizeMainJoueur();
			joueur.pioche(sac);
			assertEquals(joueur.getSizeMainJoueur(), tailleMain);
			assertEquals(sac.tailleContenuSac(), tailleSac);
		}
		
		Sac sac = new Sac();
		Joueur joueur = new Joueur();

		int tailleSac = sac.tailleContenuSac();
		
		// Vérif si pioche rempli bien la main initialement vide et vide le sac
		assertTrue(joueur.getMainJoueur().isEmpty());
		joueur.pioche(sac);
		assertEquals(sac.tailleContenuSac(), tailleSac - 7);
		assertFalse(joueur.getMainJoueur().isEmpty());

		// Vérif si pioche rempli bien la main qui à perdu des éléments et vide le sac
		joueur.getMainJoueur().remove(2);
		joueur.getMainJoueur().remove(5);
		int tailleMain = joueur.getSizeMainJoueur();
		tailleSac = sac.tailleContenuSac();
		joueur.pioche(sac);
		assertEquals(joueur.getSizeMainJoueur(), tailleMain + 2);
		assertEquals(sac.tailleContenuSac(), tailleSac - 2);
		
		//Vérif si sac presque vide et main manquant des éléments se remplit
		// et le sac empêche que plus ne soit pioché
		// Plus que deux lettres dans le sac
		for(int i = (sac.tailleContenuSac()-1); i > 1; i--) {
			sac.removeLettreDuSac(i);
		}
		// Trois lettres en moins dans la main
		joueur.getMainJoueur().remove(6);
		joueur.getMainJoueur().remove(2);
		joueur.getMainJoueur().remove(0);
		tailleMain = joueur.getSizeMainJoueur();
		tailleSac = sac.tailleContenuSac();
		joueur.pioche(sac);
		assertEquals(joueur.getSizeMainJoueur(), 7 - 1);
		assertEquals(joueur.getSizeMainJoueur(), tailleMain + 2);
		assertEquals(sac.tailleContenuSac(), tailleSac - 2);
		assertTrue(sac.getSac().isEmpty());
		
		//Si Sac vide plus de pioche possible donc main ne change pas
		joueur.getMainJoueur().remove(2);
		joueur.getMainJoueur().remove(0);
		List<Lettre> mainTest = joueur.getMainJoueur();	
		joueur.pioche(sac);
		assertEquals(joueur.getMainJoueur(), mainTest);	
	}

	@Test
	public void testMelanger() throws XPathExpressionException {
		Sac sac = new Sac();
		Joueur joueur = new Joueur();
		List<Lettre> lettreSacTestChangement = new ArrayList<Lettre>();
		List<Lettre> lettreMainTestChangement = new ArrayList<Lettre>();
		//Vérif si main vide
		lettreSacTestChangement.add(sac.getPositionLettreDansSac(83));
		lettreSacTestChangement.add(sac.getPositionLettreDansSac(48));
		lettreSacTestChangement.add(sac.getPositionLettreDansSac(8));
		joueur.melanger(lettreSacTestChangement, sac);
		assertTrue(joueur.getMainJoueur().isEmpty());
		
		//Vérif si main pleine
		joueur.pioche(sac);
		int tailleSac = sac.tailleContenuSac();
		int tailleMain = joueur.getSizeMainJoueur();
		
		lettreMainTestChangement.add(joueur.getLettreMain(5));
		lettreMainTestChangement.add(joueur.getLettreMain(2));
		lettreMainTestChangement.add(joueur.getLettreMain(0));
		joueur.melanger(lettreMainTestChangement, sac);
		assertEquals(joueur.getSizeMainJoueur(), tailleMain);
		assertEquals(sac.tailleContenuSac(), tailleSac);
		
		//Vérif si lettre non comprise dans la main
		List<Lettre> mainTest = joueur.getMainJoueur();
		lettreMainTestChangement = new ArrayList<Lettre>();
		lettreMainTestChangement.add(new Lettre('y', 86));
		joueur.melanger(lettreMainTestChangement, sac);
		assertEquals(joueur.getMainJoueur(), mainTest);

	}

	@Test
	public void testVerifierLettreMain() {
		Joueur joueur = new Joueur();
		Plateau plateau = new Plateau();
		
		String [] mot = "arbre".split("");
		String [] mot2 = "aride".split("");
		
		List<Lettre> motJoue = new ArrayList<Lettre>();
		
		Lettre a = new Lettre('a', 15);
		Lettre r = new Lettre('r', 5);
		Lettre d = new Lettre('d', 9);
		Lettre i = new Lettre('i', 15);
		Lettre z = new Lettre('z', 105);
		Lettre e = new Lettre('e', 1);
		
		List<Lettre> mainTest = new ArrayList<Lettre>();
		mainTest.add(a);
		mainTest.add(r);
		mainTest.add(d);
		mainTest.add(i);
		mainTest.add(z);
		mainTest.add(e);
		mainTest.add(e);
		joueur.setMainJoueur(mainTest);
		
		List<Lettre> motJoueCheck1 = new ArrayList<Lettre>();
		List<Lettre> motJoueCheck2 = new ArrayList<Lettre>();
		
		motJoueCheck1.add(a);
		motJoueCheck1.add(r);
		motJoueCheck1.add(null);
		motJoueCheck1.add(null);
		motJoueCheck1.add(e);
		
		motJoueCheck2.add(a);
		motJoueCheck2.add(r);
		motJoueCheck2.add(i);
		motJoueCheck2.add(d);
		motJoueCheck2.add(e);
		
		int x = 7;
		int y = 7;
		char orientation = 'h';
		
		joueur.verifierLettreMain(x, y, orientation, plateau.plateau, mot, motJoue);
		assertEquals(motJoue, motJoueCheck1);
		
		motJoue.removeAll(motJoue);
		
		joueur.verifierLettreMain(x, y, orientation, plateau.plateau, mot2, motJoue);
		assertEquals(motJoue, motJoueCheck2);
		
	}
	
	@Test
	public void checkCaseVide(){
		fail("not yet implemented");
	}

	@Test
	public void testPoserMotPlateau() throws XPathExpressionException {
		Joueur joueur = new Joueur();
		Plateau plateau = new Plateau();
		Case[][] plateauSave;
		plateauSave = plateau.copyPlateau();
		List<Lettre> saveMain = joueur.getMainJoueur();		
		String [] motArray = "test".split("");
		
		List<Lettre> motJoue = joueur.getMainJoueur();		
		List<Lettre> motMain = joueur.getMainJoueur();		
		
		Lettre t = new Lettre('t', 2);
		Lettre e = new Lettre('e', 1);
		Lettre s = new Lettre('s', 3);
		
		motJoue.add(t);
		motJoue.add(e);
		motJoue.add(s);
		motJoue.add(t);
		
		joueur.poserMotPlateau(7, 7, motJoue, motMain, motArray, plateauSave, saveMain, 'h', plateau.plateau);
		
		assertEquals(plateau.plateau[7][7].getLabelCase(), 't');
		assertEquals(plateau.plateau[8][7].getLabelCase(), 'e');
		assertEquals(plateau.plateau[9][7].getLabelCase(), 's');
		assertEquals(plateau.plateau[10][7].getLabelCase(), 't');
		assertEquals(plateau.plateau[11][7].getLettre(), null);
		
		assertFalse(joueur.poserMotPlateau(13, 13, motJoue, motMain, motArray, plateauSave, saveMain, 'h', plateau.plateau));
		
		joueur.poserMotPlateau(1, 14, motJoue, motMain, motArray, plateauSave, saveMain, 'v', plateau.plateau);
		
		assertEquals(plateau.plateau[1][14].getLabelCase(), 't');
		assertEquals(plateau.plateau[1][13].getLabelCase(), 'e');
		assertEquals(plateau.plateau[1][12].getLabelCase(), 's');
		assertEquals(plateau.plateau[1][11].getLabelCase(), 't');
		assertEquals(plateau.plateau[1][10].getLettre(), null);
		assertEquals(plateau.plateau[2][14].getLettre(), null);
		
		motJoue.removeAll(motJoue);
		motMain.removeAll(motMain);
		
		motJoue.add(s);
		motJoue.add(e);
		motArray = "se".split("");
		
		assertFalse(joueur.poserMotPlateau(7, 7, motJoue, motMain, motArray, plateauSave, saveMain, 'v', plateau.plateau));
	}

	@Test
	public void testViderLaMain() throws XPathExpressionException {
		Sac sac = new Sac();
		Joueur joueur = new Joueur();
		List<Lettre> lettreMainTestChangement = new ArrayList<Lettre>();
		lettreMainTestChangement.add(new Lettre('y', 86));
		lettreMainTestChangement.add(new Lettre('i', 6));
		lettreMainTestChangement.add(new Lettre('t', 50));
		joueur.pioche(sac);
		
		lettreMainTestChangement.add(joueur.getLettreMain(1));
		lettreMainTestChangement.add(joueur.getLettreMain(2));
		lettreMainTestChangement.add(joueur.getLettreMain(0));
		
		joueur.viderLaMain(lettreMainTestChangement, sac);
		assertNotSame(joueur.getLettreMain(1), lettreMainTestChangement.get(0));
		assertNotSame(joueur.getLettreMain(2), lettreMainTestChangement.get(1));
		assertNotSame(joueur.getLettreMain(0), lettreMainTestChangement.get(2));
	}
	
	@Test
	public void testDetecteJoker() {
		Joueur joueur = new Joueur();
		
		String mot = "tes?s";
		assertEquals(joueur.detecteJoker(mot), 1);
		
		mot = "tests";
		assertEquals(joueur.detecteJoker(mot), 0);
		
		mot = "t?s?s";
		assertEquals(joueur.detecteJoker(mot), 2);
	}
	
	@Test
	public void testSetJokerMain() {
		Joueur joueur = new Joueur();
		char joker1 = 't';
		char joker2 = '/';
		
		Lettre a = new Lettre('a', 1);
		Lettre b = new Lettre('b', 2);
		Lettre c = new Lettre('c', 3);
		Lettre t = new Lettre('t', 2);
		Lettre e = new Lettre('e', 1);
		Lettre s = new Lettre('s', 1);
		Lettre $ = new Lettre('?', 0);
		Lettre $2 = new Lettre('?', 0);
		
		List<Lettre> mainJoueur = new ArrayList<Lettre>();
		mainJoueur.add(a);
		mainJoueur.add(b);
		mainJoueur.add(c);
		mainJoueur.add(t);
		mainJoueur.add(e);
		mainJoueur.add(s);
		mainJoueur.add($);
		
		joueur.setMainJoueur(mainJoueur);
		
		String mot = "tes?s";
		
		assertEquals(joueur.setJokerMain(joker1, joker2, mot), "tests");
		
		joker2 = 's';
		mot = "tes??";
		$ = new Lettre('?', 0);
		mainJoueur.removeAll(mainJoueur);
		mainJoueur.add(b);
		mainJoueur.add(c);
		mainJoueur.add(t);
		mainJoueur.add(e);
		mainJoueur.add(s);
		mainJoueur.add($);
		mainJoueur.add($2);
		joueur.setMainJoueur(mainJoueur);
		
		assertEquals(joueur.setJokerMain(joker1, joker2, mot), "tests");
		
	}
	
	@Test
	public void testTestJoker() {
		Joueur joueur = new Joueur();
		
		String mot = "j";
		assertEquals(joueur.testJoker(mot), 'j');
		
		mot = "$";
		assertEquals(joueur.testJoker(mot), '/');
		
		mot = "2";
		assertEquals(joueur.testJoker(mot), '/');
		
		mot = "K";
		assertEquals(joueur.testJoker(mot), 'k');
	}

	@Test
	public void testPasser() {
		fail("Not yet implemented"); // TODO
	}

}
