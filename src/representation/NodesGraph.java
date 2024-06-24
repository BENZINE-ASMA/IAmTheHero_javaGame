package representation;

import java.util.HashMap;
import java.util.Map.Entry;

import entities.EntiteMobile;
import entities.Personnage;
public class NodesGraph {
	private HashMap<String,Event> graph = new HashMap<>();
	
	public HashMap<String,Event> getGraph(){
		return this.graph;
	}
	
	public void addNode(String name, Event node) {
		this.graph.put(name, node);
	}
	
	/*
	public void addDecisionNode(String name, String description ) {
		this.graph.put(name,new DecisionNode(name,description));
	}
	

	public void addDecisionNode(String name, Node noeud ) {
		this.graph.put(name,noeud);
	}

	
	public void addChanceNode(String name, String description) {
		this.graph.put(name,new ChanceNode(name,description));
	}
	
	public void addTerminalNode(String name, String description) {
		this.graph.put(name,new TerminalNode(name,description));
	}
	
	public void addCombatNode(String name, String description, String death, EntiteMobile monstre) {
		TerminalNode end = (TerminalNode) this.graph.get(death);
		this.graph.put(name,new CombatNode(name,description, end, monstre));
	}
	*/
	public void addArc(String nameNodeFrom, String nameNodeTo,String repliqueNodeTo) {
		
		(this.graph.get(nameNodeFrom)).addToNodeSuivant(repliqueNodeTo, this.graph.get(nameNodeTo));
		}	
	
		
			
	}
	


/// add deleteARC

