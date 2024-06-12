package representation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DecisionNode extends InnerNode {

	public DecisionNode(HashMap<String,Node> nodesSuivant) {
		super(nodesSuivant);
		
	}
	public DecisionNode(String nom, String description) {
		super(nom, description);
		
	}
	@Override
	public void display() {
		System.out.println(description);
	}

	@Override
	
	public Node chooseNext() {
		ArrayList<Node> nodeList = new ArrayList<>(nodesSuivant.values());
		ArrayList<String> reliqueList = new ArrayList<>(nodesSuivant.keySet());
		
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < nodeList.size(); i++) {
            System.out.println((i + 1) + ": " + reliqueList.get(i));
        }

        int choix;
        while (true) {
            System.out.print("Choisissez une option : ");
            choix = sc.nextInt();
            if (choix > 0 && choix <= nodeList.size()) {
                break;
            } else {
                System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
        
        String chosenKey = reliqueList.get(choix - 1);
        Node chosenNode = nodeList.get(choix - 1);
        
        handleSpecialCases(chosenKey, chosenNode,this.getNom());
        
        return chosenNode;
    }
    
    private void handleSpecialCases(String chosenKey, Node chosenNode, String nameOfCurrentNode) {
        //if (chosenNode instanceof DecisionNode && chosenKey.startsWith("je suis ")) {
        	if (chosenNode instanceof DecisionNode && nameOfCurrentNode.equals("humain")) {
        		((DecisionNode) chosenNode).description = "vous avez la compétence de " + chosenKey.substring(7) + ". vous voulez rejoindre quel clan?";
        }
        //rajouter pour l'affinite du sorcier des éléments
        	if (chosenNode instanceof DecisionNode && nameOfCurrentNode.equals("missionClanElement")) {
        		((DecisionNode) chosenNode).description = "Tu as donc une affinité avec " + chosenKey.substring(34) + ", très bien. Tes sorts utilisant cet élément auront donc davantage de puissance que les autres, n'hésite pas à les utiliser.";
        	}
        	
        	
    }
}