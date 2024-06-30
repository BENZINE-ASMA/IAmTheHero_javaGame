package representation;

import java.util.HashMap;
import java.util.Map.Entry;

import entities.EntiteMobile;
import entities.Personnage;

/**
 * Représente un graphe de nœuds où chaque nœud est un événement.
 */
public class NodesGraph {
    private HashMap<String, Event> graph = new HashMap<>();

    /**
     * Obtient le graphe des nœuds.
     *
     * @return le graphe sous forme de HashMap
     */
    public HashMap<String, Event> getGraph() {
        return this.graph;
    }

    /**
     * Ajoute un nœud au graphe.
     *
     * @param name le nom du nœud
     * @param node l'événement associé au nœud
     */
    public void addNode(String name, Event node) {
        this.graph.put(name, node);
    }

    /**
     * Ajoute un arc entre deux nœuds du graphe.
     *
     * @param nameNodeFrom  le nom du nœud de départ
     * @param nameNodeTo    le nom du nœud d'arrivée
     * @param repliqueNodeTo la réplique associée au nœud d'arrivée
     */
    public void addArc(String nameNodeFrom, String nameNodeTo, String repliqueNodeTo) {
        this.graph.get(nameNodeFrom).addToNodeSuivant(repliqueNodeTo, this.graph.get(nameNodeTo));
    }

    /**
     * Supprime un arc entre deux nœuds du graphe.
     *
     * @param nameNodeFrom  le nom du nœud de départ
     * @param repliqueNodeTo la réplique associée au nœud d'arrivée
     */
    public void deleteArc(String nameNodeFrom, String repliqueNodeTo) {
        Event nodeFrom = this.graph.get(nameNodeFrom);
        if (nodeFrom != null) {
            nodeFrom.getNode().nodesSuivant.remove(repliqueNodeTo);
        }
    }
}
