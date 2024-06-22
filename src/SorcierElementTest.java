/**
 * 
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.EntiteMobile;
import entities.Potion;
import entities.SorcierElement;
import entities.Sort;

/**
 * 
 */
class SorcierElementTest {


	SorcierElement sorcier;
    Sort sort;
    EntiteMobile cible;
    Potion potion;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		sorcier = new SorcierElement();
		sort = Sort.BOULE_DE_FEU;
		cible = new EntiteMobile(30,30,10,8);
		sorcier.setElement("feu");
		potion = Potion.POTION_SANTE_MINEURE;
	}
	
	@Test
	void TestgetElement() {
		assertEquals("feu", sorcier.getElement());
	}
	
	@Test
    void testAjouterPotion() {
        boolean result = sorcier.ajouterPotion(potion);
        assertTrue(result);
        assertEquals(1, sorcier.getSac().size());
    }
	
	@Test
    void testUtiliserPotion() {
		sorcier.baisserPV(10); // PV restants = 60
        sorcier.ajouterPotion(potion);
        sorcier.utiliserPotion(potion);
        assertEquals(0, sorcier.getSac().size());
        assertEquals(60, sorcier.getPvRestant());
    }
	
	
	@Test
    void testAttaqueArmee() {
        sorcier.attaqueArmee(cible);
        assertEquals(15, cible.getPvRestant());
    }

    @Test
    void testApprendreSort() {
        sorcier.apprendreSort(sort);
        assertEquals(1, sorcier.getNbSortsConnus());
        assertTrue(sorcier.getSortsConnus().contains(sort));
    }

    @Test
    void testBaisserMP() {
        sorcier.baisserMP(10);
        assertEquals(30, sorcier.getMagieRestant());
    }

    @Test
    void testAugmenterMPRestant() {
        sorcier.baisserMP(10); // Magie restante = 30
        sorcier.augmenterMPRestant(5); // Magie restante = 35
        assertEquals(35, sorcier.getMagieRestant());
        sorcier.augmenterMPRestant(10); // Magie restante = 40 (car max de magie de base)
        assertEquals(40, sorcier.getMagieRestant());
    }

    @Test
    void testAugmenterMPBase() {
        sorcier.augmenterMPBase(10);
        assertEquals(50, sorcier.getMagieBase());
    }

    @Test
    void testLancerSort() {
        sorcier.apprendreSort(sort);
        int initialMP = sorcier.getMagieRestant();
        int initialPV = cible.getPvRestant();
        sorcier.lancerSort(cible, sort);
        assertEquals(initialMP - sort.getCoutMana(), sorcier.getMagieRestant());
        assertEquals(initialPV - (int)(sort.getDegats()*1.5), cible.getPvRestant());
    }

    @Test
    void testSetAndGetSortsConnus() {
        List<Sort> sorts = new ArrayList<>();
        sorts.add(sort);
        sorcier.setSortsConnus(sorts);
        assertEquals(sorts, sorcier.getSortsConnus());
    }

    @Test
    void testSetAndGetMagieBase() {
        sorcier.setMagieBase(50);
        assertEquals(50, sorcier.getMagieBase());
    }

    @Test
    void testSetAndGetMagieRestant() {
        sorcier.setMagieRestant(50);
        assertEquals(50, sorcier.getMagieRestant());
    }


    @Test
    void testAfficherSortsConnus() {
        sorcier.apprendreSort(sort);
        sorcier.afficherSortsConnus();
    }


}
