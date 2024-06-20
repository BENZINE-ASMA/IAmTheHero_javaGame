package representation;

import entities.Personnage;

public class TerminalNode extends Node {
	
	public TerminalNode(String nom, String description) {
		super(nom, description);
	}

	@Override
	public void display() {
		System.out.println(description);
		
	}

	@Override
	public Node chooseNext() {
		return this;
	}

	@Override
	public Node chooseNext(String choice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node chooseNext2(String choice) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
