package representation;

import java.io.Serializable;

/**
 * Classe utilitaire pour manipuler les nœuds dans l'arbre d'événements.
 */
public class NodeUtil implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Recherche récursivement un nœud d'un type spécifique dans l'arbre d'événements.
     *
     * @param <T>  le type du nœud recherché
     * @param node le nœud de départ pour la recherche
     * @param type la classe du type de nœud recherché
     * @return le nœud trouvé du type spécifié, ou null s'il n'est pas trouvé
     */
    public static <T> T findNodeOfType(Event node, Class<T> type) {
        if (type.isInstance(node)) {
            return type.cast(node);
        }
        if (node instanceof NodeDecorator) {
            return findNodeOfType(((NodeDecorator) node).getDecoratedNode(), type);
        }
        return null;
    }
}
