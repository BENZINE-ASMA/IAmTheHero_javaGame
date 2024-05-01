package representation;

import java.util.ArrayList;

public class ChanceNode extends InnerNode {
	
	public ChanceNode(ArrayList<Node> nodesSuivant) {
		super(nodesSuivant);
	}
	
	public ChanceNode(String nom, String description, ArrayList<Node> nodesSuivant) {
		super(nom, description, nodesSuivant);
	}

	@Override
	public void display() {
		System.out.println(description);
		
	}

	@Override
	public Node chooseNext() {
		// TODO Auto-generated method stub
		return null;
	}

}
