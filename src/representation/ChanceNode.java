package representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import entities.Personnage;
import entities.SorcierElement;
import entities.SorcierSpirituel;

public class ChanceNode extends InnerNode {
	
	 private static final long serialVersionUID = 1L;


	public ChanceNode(HashMap<String,Event> nodesSuivant) {
		super(nodesSuivant);
	}
	
	public ChanceNode(String nom, String description) {
		super(nom, description);
	}

	@Override
	public void display() {
		System.out.println(insertLineBreaks(description,150));
		
	}

	@Override
	public Event chooseNext() {
		ArrayList<Event> nodeList = new ArrayList<>(nodesSuivant.values());
		ArrayList<String> repliqueList = new ArrayList<>(nodesSuivant.keySet());

		Random random = new Random();
		int indice = random.nextInt(nodeList.size());
		Event chosenNode = nodeList.get(indice);
		String chosenKey = repliqueList.get(indice);
        
        handleSpecialCases(chosenKey, chosenNode,this.getNom());
		
		return chosenNode;
	}

	@Override
	public Event chooseNext(String choice) {
		// TODO Auto-generated method stub
		return null;
	}

	 public Event chooseNext3() {
		 System.out.println("heelo1");
	        if (this instanceof InnerNode) {
	        	ArrayList<Event> nodeList = new ArrayList<>(nodesSuivant.values());
	        	ArrayList<String> repliqueList = new ArrayList<>(nodesSuivant.keySet());

	    		Random random = new Random();
	    		int indice = random.nextInt(nodeList.size());
	    		Event chosenNode = nodeList.get(indice);
	    		String chosenKey = repliqueList.get(indice);
	            
	            handleSpecialCases(chosenKey, chosenNode,this.getNom());
	    		System.out.println(" the chosen isss "+ chosenNode + "   " + chosenNode.getDescription()+ " ++++ " + chosenNode.getNom());
	    		return chosenNode;
	            
	        }
	        
	        return null;
	    }
	 
	 
	private void handleSpecialCases(String chosenKey, Event chosenNode, String nameOfCurrentNode) {
		if (chosenNode instanceof TerminalNode && nameOfCurrentNode.equals("DetruirePierre") && ("FinPaix".equals(chosenNode.getNom()))) {
			if (joueur instanceof SorcierElement) {
				((TerminalNode) chosenNode).description = "";
			}
			if (joueur instanceof SorcierSpirituel) {
				((TerminalNode) chosenNode).description = "";
			}
	
		}
	}

	@Override
	public Event chooseNext2(String choice) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
