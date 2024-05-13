package representation;

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

}
