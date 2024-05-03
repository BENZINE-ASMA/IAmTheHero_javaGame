package representation;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class InnerNode extends Node{
	protected ArrayList<Node> nodesSuivant;
	
	public InnerNode(ArrayList<Node> nodesSuivant) {
		super();
		this.nodesSuivant = nodesSuivant;
	}
	
	public InnerNode(String nom, String description) {
		super(nom, description);
		this.nodesSuivant = new ArrayList<>();
	}
	
	public void  addToNodeSuivant(Node toAdd) {
		this.nodesSuivant.add(toAdd);
	}
	
	public void testDisplay() {
		for (Node n : nodesSuivant) {
			System.out.print(n.nom +"   ");
		}
	}

}
