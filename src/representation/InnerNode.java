package representation;

import java.util.ArrayList;

public abstract class InnerNode extends Node{
	protected ArrayList<Node> nodesSuivant;
	
	public InnerNode(ArrayList<Node> nodesSuivant) {
		super();
		this.nodesSuivant = nodesSuivant;
	}
	
	public InnerNode(String nom, String description, ArrayList<Node> nodesSuivant) {
		super(nom, description);
		this.nodesSuivant = nodesSuivant;
	}

}
