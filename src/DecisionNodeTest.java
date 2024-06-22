/**
 * 
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import representation.DecisionNode;
import representation.Node;

/**
 * 
 */
class DecisionNodeTest {
	
	DecisionNode decisionNode1;
	DecisionNode decisionNode2;
	DecisionNode decisionNode3;
    HashMap<String, Node> nodesSuivant;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		decisionNode2 = new DecisionNode("Noeud2", "C'est le choix 1.");
		decisionNode3 = new DecisionNode("Noeud3", "C'est le choix 2.");
		nodesSuivant = new HashMap<>();
		nodesSuivant.put("choix 1", decisionNode2);
		nodesSuivant.put("choix 2", decisionNode3);
		decisionNode1 = new DecisionNode("Noeud1", "Noeud de départ", nodesSuivant);
	
	}
	
	@Test
    void testDisplay() {
        decisionNode1.display();
    }

	
	//problème avec le test car on veut un int et on lis un String je crois
    @Test
    void testChooseNextValidOption() {
    	String input = "1"; // Simule la sélection de la première option
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
        Node chosenNode = decisionNode1.chooseNext();
        assertEquals(nodesSuivant.get("Noeud2"), chosenNode);
    }

    @Test
    void testChooseNextInvalidOption() {
        String input = "3\n1"; // Simule une entrée invalide 
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
        
        Node chosenNode = decisionNode1.chooseNext();
        assertEquals(nodesSuivant.get("Noeud2"), chosenNode);
    }

    @Test
    void testHandleSpecialCases() {
        // Faire un test plus précis
    }

    @Test
    void testChooseNextWithChoice() {
        Node chosenNode = decisionNode1.chooseNext("Noeud3");
        assertEquals(nodesSuivant.get("Noeud3"), chosenNode);
    }


}


