/**
 * 
 */

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import representation.DecisionNode;
import representation.Event;
import representation.Node;

/**
 * 
 */
class DecisionNodeTest {
	
	DecisionNode decisionNode1;
	DecisionNode decisionNode2;
	DecisionNode decisionNode3;
    HashMap<String, Event> nodesSuivant;

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

	
    @Test
    void testChooseNextValidOption() {
    	String input = "Noeud2"; // Simule la sélection de la première option
        Event chosenNode = decisionNode1.chooseNext(input);
        assertEquals(nodesSuivant.get("Noeud2"), chosenNode);
    }

    @Test
    void testChooseNextInvalidOption() {
        String input = "Noeud4"; // Simule une entrée invalide 
        Event chosenNode = decisionNode1.chooseNext(input);
        assertNull(chosenNode);
    }


}


