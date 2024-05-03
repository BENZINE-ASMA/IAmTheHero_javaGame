package representation;

import java.util.HashMap;
public class NodesTree {
	private HashMap<String,Node> tree = new HashMap<>();
	
	public HashMap<String,Node> getTree(){
		return this.tree;
	}
	
	public void addDecisionNode(String name, String description) {
		this.tree.put(name,new DecisionNode(name,description));
	}
	public void addChanceNode(String name, String description) {
		this.tree.put(name,new ChanceNode(name,description));
	}
	
	public void addArc(String nameNodeFrom, String nameNodeTo) {
		((InnerNode)this.tree.get(nameNodeFrom)).addToNodeSuivant(this.tree.get(nameNodeTo));
	}

}
