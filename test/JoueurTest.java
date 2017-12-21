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
<<<<<<< HEAD
import scrabble.Joker;
=======
>>>>>>> master

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
<<<<<<< HEAD
		
		
=======
>>>>>>> master
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
<<<<<<< HEAD
		
		//Vérif si plusieurs fois la même lettre
		Lettre e = new Lettre('e' ,1);
		Lettre r = new Lettre('r', 1);
		Lettre a = new Lettre('a', 1);
		Lettre v = new Lettre('v', 1);
		Lettre k = new Lettre('k', 1);
		
		joueur.getMainJoueur().removeAll(joueur.getMainJoueur());
		
		joueur.getMainJoueur().add(e);
		joueur.getMainJoueur().add(e);
		joueur.getMainJoueur().add(r);
		joueur.getMainJoueur().add(a);
		joueur.getMainJoueur().add(v);
		joueur.getMainJoueur().add(e);
		joueur.getMainJoueur().add(k);
		
		lettreMainTestChangement.removeAll(lettreMainTestChangement);
		lettreMainTestChangement.add(r);
		lettreMainTestChangement.add(a);
		lettreMainTestChangement.add(v);
		lettreMainTestChangement.add(k);
		lettreMainTestChangement.add(k);
		lettreMainTestChangement.add(k);
		lettreMainTestChangement.add(k);
		
		lettreSacTestChangement.removeAll(lettreSacTestChangement);
		lettreSacTestChangement.add(k);
		lettreSacTestChangement.add(k);
		lettreSacTestChangement.add(k);
		lettreSacTestChangement.add(k);
		lettreSacTestChangement.add(k);
		
		List<Lettre> lettreEnleve = new ArrayList<Lettre>();
		lettreEnleve.add(e);
		lettreEnleve.add(e);
		lettreEnleve.add(e);
		
		sac.setSac(lettreSacTestChangement);
		sac.getSac().removeAll(lettreEnleve);
		
		joueur.melanger(lettreEnleve, sac);
		
		for(int f = 0; f < joueur.getSizeMainJoueur(); f++) {
			System.out.println(joueur.getMainJoueur().get(f).getLabel());
		}
		
		for(int f = 0; f < sac.getSac().size(); f++) {
			System.out.println(sac.getSac().get(f).getLabel());
		}
=======
>>>>>>> master

	}

	@Test
	public void testVerifierLettreMain() {
		Joueur joueur = new Joueur();
<<<<<<< HEAD
		Plateau plateau = new Plateau();
=======
>>>>>>> master
		
		String [] mot = "arbre".split("");
		String [] mot2 = "aride".split("");
		
<<<<<<< HEAD
		String [] motJoker1 = mot;
		String [] motJoker2 = mot2;
		
=======
>>>>>>> master
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
		
<<<<<<< HEAD
		int x = 7;
		int y = 7;
		char orientation = 'h';
		
		joueur.verifierLettreMain(x, y, orientation, plateau, mot, motJoker1, motJoue, 0);
=======
		joueur.verifierLettreMain(mot, motJoue);
>>>>>>> master
		assertEquals(motJoue, motJoueCheck1);
		
		motJoue.removeAll(motJoue);
		
<<<<<<< HEAD
		joueur.verifierLettreMain(x, y, orientation, plateau, mot2, motJoker2, motJoue, 0);
=======
		joueur.verifierLettreMain(mot2, motJoue);
>>>>>>> master
		assertEquals(motJoue, motJoueCheck2);
		
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
		
<<<<<<< HEAD
		joueur.poserMotPlateau(7, 7, motJoue, motMain, motArray, plateauSave, saveMain, 'h', plateau.getPlateau());
		
		assertEquals(plateau.getPlateauLabel(7, 7), 't');
		assertEquals(plateau.getPlateauLabel(8, 7), 'e');
		assertEquals(plateau.getPlateauLabel(9, 7), 's');
		assertEquals(plateau.getPlateauLabel(10, 7), 't');
		assertEquals(plateau.getPlateauLettre(11, 7), null);
		
		assertFalse(joueur.poserMotPlateau(13, 13, motJoue, motMain, motArray, plateauSave, saveMain, 'h', plateau.getPlateau()));
		
		joueur.poserMotPlateau(1, 14, motJoue, motMain, motArray, plateauSave, saveMain, 'v', plateau.getPlateau());
		
		assertEquals(plateau.getPlateauLabel(1, 14), 't');
		assertEquals(plateau.getPlateauLabel(1, 13), 'e');
		assertEquals(plateau.getPlateauLabel(1, 12), 's');
		assertEquals(plateau.getPlateauLabel(1, 11), 't');
		assertEquals(plateau.getPlateauLettre(1, 10), null);
		assertEquals(plateau.getPlateauLettre(2, 14), null);
=======
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
>>>>>>> master
		
		motJoue.removeAll(motJoue);
		motMain.removeAll(motMain);
		
		motJoue.add(s);
		motJoue.add(e);
		motArray = "se".split("");
		
<<<<<<< HEAD
		assertFalse(joueur.poserMotPlateau(7, 7, motJoue, motMain, motArray, plateauSave, saveMain, 'v', plateau.getPlateau()));
=======
		assertFalse(joueur.poserMotPlateau(7, 7, motJoue, motMain, motArray, plateauSave, saveMain, 'v', plateau.plateau));
>>>>>>> master
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
<<<<<<< HEAD
	public void testCopieMainJoueur() {
		Joueur joueur = new Joueur();
		
		Lettre a = new Lettre('a', 1);
		Lettre b = new Lettre('b', 2);
		Lettre c = new Lettre('c', 3);
		Lettre t = new Lettre('t', 2);
		Lettre e = new Lettre('e', 1);
		Lettre s = new Lettre('s', 1);
		Joker $ = new Joker();
		
		List<Lettre> mainJoueur = new ArrayList<Lettre>();
		mainJoueur.add(a);
		mainJoueur.add(b);
		mainJoueur.add(c);
		mainJoueur.add(t);
		mainJoueur.add(e);
		mainJoueur.add(s);
		mainJoueur.add($);
		
		joueur.setMainJoueur(mainJoueur);
		
		assertEquals(joueur.getMainJoueur().get(0), joueur.copieMainJoueur().get(0));
		assertEquals(joueur.getMainJoueur().get(1), joueur.copieMainJoueur().get(1));
		assertEquals(joueur.getMainJoueur().get(2), joueur.copieMainJoueur().get(2));
		assertEquals(joueur.getMainJoueur().get(3), joueur.copieMainJoueur().get(3));
		assertEquals(joueur.getMainJoueur().get(4), joueur.copieMainJoueur().get(4));
		assertEquals(joueur.getMainJoueur().get(5), joueur.copieMainJoueur().get(5));
		assertEquals(joueur.getMainJoueur().get(6).getLabel(), joueur.copieMainJoueur().get(6).getLabel());
	}
	
	@Test
	public void testScoreFinPartie() {
		Joueur joueur = new Joueur();
		Joueur joueur2 = new Joueur();
		
		List<Lettre> main = new ArrayList<Lettre>();
		List<Lettre> mainAdverseTest = new ArrayList<Lettre>();
		mainAdverseTest.add(new Lettre('z', 10));
		mainAdverseTest.add(new Lettre('a', 1));
		mainAdverseTest.add(new Lettre('b', 2));
		
		joueur.setMainJoueur(main);
		joueur.setMainJoueurAdverse(mainAdverseTest);
		
		joueur.setScore(128);
		joueur.setScoreAdverse(135);
		
		joueur.setFinPartie(true);
		
		assertEquals(joueur.getScore(), 141);
	
		joueur2.setMainJoueur(mainAdverseTest);
		joueur2.setMainJoueurAdverse(main);
		joueur2.setScore(135);
		joueur2.setScoreAdverse(138);
		
		joueur2.setFinPartie(true);
		
		assertEquals(joueur2.getScore(), 122);
		
	}
	
	@Test
	public void testGetFinPartie() {
		Joueur joueur = new Joueur();
		joueur.setFinPartie(false);
		
		assertEquals(joueur.getFinPartie(), false);
	}
	
	@Test
	public void testSetFinPartie() {
		Joueur joueur = new Joueur();
		
		List<Lettre> main = new ArrayList<Lettre>();
		List<Lettre> mainAdverseTest = new ArrayList<Lettre>();
		mainAdverseTest.add(new Lettre('z', 10));
		mainAdverseTest.add(new Lettre('a', 1));
		mainAdverseTest.add(new Lettre('b', 2));
		joueur.setScore(128);
		joueur.setScoreAdverse(135);
		
		joueur.setMainJoueur(main);
		joueur.setMainJoueurAdverse(mainAdverseTest);
		
		joueur.setFinPartie(false);
		
		assertEquals(joueur.getFinPartie(), false);
		
		joueur.setFinPartie(true);
		
		assertEquals(joueur.getFinPartie(), true);
		
		assertEquals(joueur.getScore(), 141);
		
		joueur.setFinPartie(true); //Pas de recalcul du score si 2 fois fin de partie
		
		assertEquals(joueur.getScore(), 141);
	}
	
	@Test
	public void testPasser() {
		Joueur joueur = new Joueur();
		joueur.passer();
		
		assertFalse(joueur.getTourJoueur());
		
		joueur.passer();
		
		assertEquals(joueur.getNbreTourPasser(), 2);
=======
	public void testPasser() {
		fail("Not yet implemented"); // TODO
>>>>>>> master
	}

}
