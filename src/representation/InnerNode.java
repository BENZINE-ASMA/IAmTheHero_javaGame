package representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import entities.Personnage;

public abstract class InnerNode extends Node{
	//protected HashMap<String,Event> nodesSuivant;
	private static final long serialVersionUID = 1L;
	
	public InnerNode(HashMap<String,Event> nodesSuivant) {
		super();
		this.nodesSuivant = nodesSuivant;
	}
	
	public InnerNode(String nom, String description) {
		super(nom, description);
		this.nodesSuivant = new HashMap<>();
	}
	
	public InnerNode(String nom, String description, HashMap<String,Event> nodesSuivant) {
		super(nom, description);
		this.nodesSuivant = nodesSuivant;
	}
	
	/*
	public HashMap<String,Event>  getNodesSuivant() {
		return this.nodesSuivant;
	}
	
	
	public void addToNodeSuivant(String replique ,Event toAdd) {
		this.nodesSuivant.put(replique,toAdd);
	}
	
	
	public void testDisplay() {
		for (String entry : nodesSuivant.keySet()) {
			System.out.print(entry + "   " );
		}
	}
	*/
			
	
}
