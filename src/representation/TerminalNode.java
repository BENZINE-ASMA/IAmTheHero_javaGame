package representation;


/**
 * Classe TerminalNode représentant un nœud terminal dans un système de représentation d'événements.
 * Un TerminalNode affiche simplement sa description et ne permet pas de choisir un événement suivant.
 * Étend la classe abstraite Node.
 */
public class TerminalNode extends Node {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur d'un TerminalNode avec nom et description spécifiés.
     * 
     * @param nom le nom du nœud terminal
     * @param description la description associée au nœud terminal
     */
    public TerminalNode(String nom, String description) {
        super(nom, description);
    }

    /**
     * Affiche la description du nœud terminal avec des sauts de ligne insérés pour une meilleure lisibilité.
     */
    @Override
    public void display() {
        System.out.println(insertLineBreaks(description, 150));
    }

    /**
     * Retourne ce nœud terminal comme prochain événement à exécuter.
     * 
     * @return ce nœud terminal lui-même
     */
    @Override
    public Event chooseNext() {
        return this;
    }

    /**
     * Retourne ce nœud terminal comme prochain événement à exécuter, indépendamment du choix spécifié.
     * 
     * @param choice le choix spécifié (non utilisé dans cette implémentation)
     * @return ce nœud terminal lui-même
     */
    @Override
    public Event chooseNext(String choice) {
        return this;
    }

    /**
     * Retourne ce nœud terminal comme prochain événement à exécuter, indépendamment du choix spécifié.
     * 
     * @param choice le choix spécifié (non utilisé dans cette implémentation)
     * @return ce nœud terminal lui-même
     */
    @Override
    public Event chooseNext2(String choice) {
        return this;
    }
}
