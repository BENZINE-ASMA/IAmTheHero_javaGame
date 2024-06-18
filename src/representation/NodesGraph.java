package representation;

import java.util.HashMap;
import java.util.Map.Entry;

import entities.EntiteMobile;
import entities.Personnage;
public class NodesGraph {
	private HashMap<String,Node> graph = new HashMap<>();
	
	public HashMap<String,Node> getGraph(){
		return this.graph;
	}
	
	public void addDecisionNode(String name, String description ) {
		this.graph.put(name,new DecisionNode(name,description));
	}
	public void addChanceNode(String name, String description) {
		this.graph.put(name,new ChanceNode(name,description));
	}
	
	public void addTerminalNode(String name, String description) {
		this.graph.put(name,new TerminalNode(name,description));
	}
	
	public void addCombatNode(String name, String description, String death, EntiteMobile monstre, Personnage joueur) {
		TerminalNode end = (TerminalNode) this.graph.get(death);
		this.graph.put(name,new CombatNode(name,description, end, monstre, joueur));
	}
	
	public void addArc(String nameNodeFrom, String nameNodeTo,String repliqueNodeTo) {
		
		((InnerNode)this.graph.get(nameNodeFrom)).addToNodeSuivant(repliqueNodeTo, this.graph.get(nameNodeTo));
		}	
	
		
			
	}
	


/// add deleteARC

