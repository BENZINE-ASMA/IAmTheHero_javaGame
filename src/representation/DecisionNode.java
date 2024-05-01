package representation;

import java.util.ArrayList;
import java.util.Scanner;

public class DecisionNode extends InnerNode {

	public DecisionNode(ArrayList<Node> nodesSuivant) {
		super(nodesSuivant);
		
	}
	
	public DecisionNode(String nom, String description, ArrayList<Node> nodesSuivant) {
		super(nom, description, nodesSuivant);
		
	}
	
	@Override
	public void display() {
		System.out.println(description);
	}
	
	@Override
	public Node chooseNext() { //comment faire avec le scanner ? 
		Scanner sc = new Scanner(System.in);
		for (int i=1; i<=nodesSuivant.size(); i++) {
			System.out.println(i + ": " + nodesSuivant.get(i-1).nom);
		}
		sc.close();
		int choix = sc.nextInt();
		return nodesSuivant.get(choix-1);
	}

}
