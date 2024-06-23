package representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import entities.Personnage;
import entities.SorcierElement;
import entities.SorcierSpirituel;

public class ChanceNode extends InnerNode {
	
	public ChanceNode(HashMap<String,Node> nodesSuivant) {
		super(nodesSuivant);
	}
	
	public ChanceNode(String nom, String description) {
		super(nom, description);
	}

	@Override
	public void display() {
		System.out.println(description);
		
	}

	@Override
	public Node chooseNext() {
		ArrayList<Node> nodeList = new ArrayList<>(nodesSuivant.values());
		ArrayList<String> repliqueList = new ArrayList<>(nodesSuivant.keySet());

		Random random = new Random();
		int indice = random.nextInt(nodeList.size());
		Node chosenNode = nodeList.get(indice);
		String chosenKey = repliqueList.get(indice);
        
        handleSpecialCases(chosenKey, chosenNode,this.getNom());
		
		return chosenNode;
	}

	@Override
	public Node chooseNext(String choice) {
		// TODO Auto-generated method stub
		return null;
	}
	 public Node chooseNext2(String choice) {
	        if (this instanceof InnerNode) {
	        	ArrayList<Node> nodeList = new ArrayList<>(nodesSuivant.values());
	        	ArrayList<String> repliqueList = new ArrayList<>(nodesSuivant.keySet());

	    		Random random = new Random();
	    		int indice = random.nextInt(nodeList.size());
	    		Node chosenNode = nodeList.get(indice);
	    		String chosenKey = repliqueList.get(indice);
	            
	            handleSpecialCases(chosenKey, chosenNode,this.getNom());
	    		
	    		return chosenNode;
	            
	        }
	        
	        return null;
	    }
	 
	 
	private void handleSpecialCases(String chosenKey, Node chosenNode, String nameOfCurrentNode) {
		if (chosenNode instanceof TerminalNode && nameOfCurrentNode.equals("DetruirePierre") && ("FinPaix".equals(chosenNode.getNom()))) {
			if (joueur instanceof SorcierElement) {
				((TerminalNode) chosenNode).description = "";
			}
			if (joueur instanceof SorcierSpirituel) {
				((TerminalNode) chosenNode).description = "";
			}
	
		}
	}
	

}
