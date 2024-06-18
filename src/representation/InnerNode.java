package representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import entities.Personnage;

public abstract class InnerNode extends Node{
	protected HashMap<String,Node> nodesSuivant;
	
	public InnerNode(HashMap<String,Node> nodesSuivant) {
		super();
		this.nodesSuivant = nodesSuivant;
	}
	
	public InnerNode(String nom, String description) {
		super(nom, description);
		this.nodesSuivant = new HashMap<>();
	}
	public HashMap<String,Node>  getNodesSuivant() {
		return this.nodesSuivant;
	}
	
	
	public void addToNodeSuivant(String replique ,Node toAdd) {
		this.nodesSuivant.put(replique,toAdd);
	}
	
	
	public void testDisplay() {
		for (String entry : nodesSuivant.keySet()) {
			System.out.print(entry + "   " );
		}
	}
			
	
}
