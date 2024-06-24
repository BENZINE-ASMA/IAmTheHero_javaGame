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
	public Event chooseNext() {
		return this;
	}

	@Override
	public Event chooseNext(String choice) {
		return null;
	}

	@Override
	public Event chooseNext2(String choice) {
		return null;
	}
	
	

}
