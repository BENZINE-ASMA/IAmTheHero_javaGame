package representation;

import java.io.Serializable;

public class NodeUtil implements Serializable {
	private static final long serialVersionUID = 1L;
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
