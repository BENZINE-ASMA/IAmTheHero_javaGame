package representation;

import java.util.HashMap;

/**
 * Classe abstraite InnerNode représentant un nœud interne dans un système de représentation d'événements.
 * Étend la classe abstraite Node et définit le comportement général des nœuds internes.
 */
public abstract class InnerNode extends Node {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur d'un InnerNode avec un ensemble de nœuds suivants spécifié.
     * 
     * @param nodesSuivant une HashMap contenant les nœuds suivants associés à ce nœud interne
     */
    public InnerNode(HashMap<String, Event> nodesSuivant) {
        super();
        this.nodesSuivant = nodesSuivant;
    }

    /**
     * Constructeur d'un InnerNode avec nom et description spécifiés.
     * Initialise l'ensemble des nœuds suivants à une nouvelle HashMap.
     * 
     * @param nom le nom du nœud interne
     * @param description la description associée au nœud interne
     */
    public InnerNode(String nom, String description) {
        super(nom, description);
        this.nodesSuivant = new HashMap<>();
    }

    /**
     * Constructeur d'un InnerNode avec nom, description et un ensemble de nœuds suivants spécifié.
     * 
     * @param nom le nom du nœud interne
     * @param description la description associée au nœud interne
     * @param nodesSuivant une HashMap contenant les nœuds suivants associés à ce nœud interne
     */
    public InnerNode(String nom, String description, HashMap<String, Event> nodesSuivant) {
        super(nom, description);
        this.nodesSuivant = nodesSuivant;
    }
}
