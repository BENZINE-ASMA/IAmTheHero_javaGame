package representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
		System.out.println(nodeList);
		
		Random random = new Random();
		int indice = random.nextInt(nodeList.size());
		System.out.println(nodeList.size());
		System.out.println(indice);
		Node chosenNode = nodeList.get(indice);
		return chosenNode;
	}

	@Override
	public Node chooseNext(String choice) {
		// TODO Auto-generated method stub
		return null;
	}

}
