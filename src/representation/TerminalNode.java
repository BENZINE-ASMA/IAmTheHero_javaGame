package representation;

import entities.Personnage;

public class TerminalNode extends Node {
	private static final long serialVersionUID = 1L;
	
	public TerminalNode(String nom, String description) {
		super(nom, description);
	}

	@Override
	public void display() {
		System.out.println(insertLineBreaks(description,150));
		
	}

	@Override
	public Event chooseNext() {
		return this;
	}

	@Override
	public Event chooseNext(String choice) {
		return this;
	}

	@Override
	public Event chooseNext2(String choice) {
		return this;
	}

	
	
	

}
