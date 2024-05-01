package representation;

public class TerminalNode extends Node {

	@Override
	public void display() {
		System.out.println(description);
		
	}

	@Override
	public Node chooseNext() {
		return this;
	}

}
