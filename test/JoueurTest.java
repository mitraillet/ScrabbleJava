package test;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import scrabble.Joueur;
import scrabble.Lettre;
import scrabble.Sac;

class JoueurTest {

	@Test
	void testGenerateNumber() {
		Joueur joueur = new Joueur();
		for(int i = 0 ; i < 50; i++) {
			int temp = joueur.generateNumber(0, 15);
			assertTrue( 0 <= temp  && temp <= 15);
		}
	}

	@Test
	void testSetScore() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetLettreMain() throws XPathExpressionException {
		Sac sac = new Sac();
		Joueur joueur = new Joueur();
		joueur.pioche(sac);
		for(int i = 0; i < 7; i++) {
			assertFalse(joueur.getLettreMain(i) == null);
		}
	}

	@Test
	void testGetSizeMainJoueur() throws XPathExpressionException {
		Sac sac = new Sac();
		Joueur joueur = new Joueur();
		joueur.pioche(sac);
		
		assertTrue(joueur.getSizeMainJoueur() == 7);
	}

	@Test
	void testGetMainJoueur() throws XPathExpressionException {
		Sac sac = new Sac();
		Joueur joueur = new Joueur();
		
		assertTrue(joueur.getMainJoueur().isEmpty());
		
		joueur.pioche(sac);
		assertFalse(joueur.getMainJoueur().isEmpty());
	}

	@Test
	void testSetMainJoueur() {
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
	void testJouer() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testPioche() throws XPathExpressionException {
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
	void testMelanger() throws XPathExpressionException {
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
	void testVerifierLettreMain() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testPoserMotPlateau() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testViderLaMain() throws XPathExpressionException {
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
	void testPasser() {
		fail("Not yet implemented"); // TODO
	}

}
