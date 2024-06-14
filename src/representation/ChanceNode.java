package representation;

import java.util.ArrayList;
import java.util.HashMap;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node chooseNext(String choice) {
		// TODO Auto-generated method stub
		return null;
	}

}
