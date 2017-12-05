/**
 * Package contenant tous les tests unitaires
 */
package test;

import static org.junit.Assert.*;
import org.junit.Test;

import javax.xml.xpath.XPathExpressionException;

import scrabble.Lettre;
import scrabble.Sac;

/**
 * @author Fauconnier/Henriquet
 * Test unitaire pour la classe Sac
 */
public class SacTest {

	/**
	 * Test method for {@link scrabble.Sac#getPositionLettreDansSac(int)}.
	 * @throws XPathExpressionException 
	 */
	@Test
	public void testGetPositionLettreDansSac() throws XPathExpressionException {
    	Sac sac = new Sac();
		assertEquals(sac.getPositionLettreDansSac(1).getLabel(), 'a' );
		assertEquals(sac.getPositionLettreDansSac(1).getValeur(), 1 );
		assertEquals(sac.getPositionLettreDansSac(52).getLabel(), 'm' );
		assertEquals(sac.getPositionLettreDansSac(52).getValeur(), 2 );
		assertEquals(sac.getPositionLettreDansSac(101).getLabel(), '?' );
		assertEquals(sac.getPositionLettreDansSac(101).getValeur(), 0 );
	}

	/**
	 * Test method for {@link scrabble.Sac#removeLettreDuSac(int)}.
	 * @throws XPathExpressionException 
	 */
	@Test
	public void testRemoveLettreDuSac() throws XPathExpressionException {
		Sac sac = new Sac();
		int tailleAvantSupp = sac.getSac().size();
		char labelLettreAvantSup = sac.getPositionLettreDansSac(54).getLabel();
		sac.removeLettreDuSac(15);
		assertEquals(sac.getSac().size(), tailleAvantSupp - 1);
		assertFalse(sac.getPositionLettreDansSac(54).getLabel() == labelLettreAvantSup);
	}

	/**
	 * Test method for {@link scrabble.Sac#addLettreAuSac(scrabble.Lettre)}.
	 * @throws XPathExpressionException 
	 */
	@Test
	public void testAddLettreAuSac() throws XPathExpressionException {
		Sac sac = new Sac();
		Lettre ajout = new Lettre('a', 15);
		sac.addLettreAuSac(ajout);
		assertEquals(sac.getPositionLettreDansSac(sac.getSac().size() - 1), ajout);
	}

	/**
	 * Test method for {@link scrabble.Sac#tailleContenuSac()}.
	 * @throws XPathExpressionException 
	 */
	@Test
	public void testTailleContenuSac() throws XPathExpressionException {
		Sac sac = new Sac();
		assertEquals(sac.tailleContenuSac(), sac.getSac().size());
	}

}
