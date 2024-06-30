/**
 * Classe de test pour la classe DecisionNode.
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import representation.DecisionNode;
import representation.Event;

/**
 * Classe de test pour les méthodes de la classe DecisionNode.
 */
class DecisionNodeTest {
    
    DecisionNode decisionNode1;
    DecisionNode decisionNode2;
    DecisionNode decisionNode3;
    HashMap<String, Event> nodesSuivant;

    /**
     * Initialise les objets DecisionNode avant chaque test.
     *
     * @throws java.lang.Exception si une erreur survient lors de l'initialisation
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

    /**
     * Teste la méthode display() de DecisionNode.
     */
    @Test
    void testDisplay() {
        decisionNode1.display();
    }

    /**
     * Teste la méthode chooseNext() de DecisionNode avec une option valide.
     */
    @Test
    void testChooseNextValidOption() {
        String input = "choix 1"; // Simule la sélection de la première option
        Event chosenNode = decisionNode1.chooseNext(input);
        assertEquals(nodesSuivant.get("choix 1"), chosenNode);
    }

    /**
     * Teste la méthode chooseNext() de DecisionNode avec une option invalide.
     */
    @Test
    void testChooseNextInvalidOption() {
        String input = "choix 3"; // Simule une entrée invalide 
        Event chosenNode = decisionNode1.chooseNext(input);
        assertNull(chosenNode);
    }
}
