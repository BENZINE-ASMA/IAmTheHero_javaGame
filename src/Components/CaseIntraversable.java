package Components;

public class CaseIntraversable extends Case {

	public CaseIntraversable(int l, int c) {
		super(l, c);
		
	}

	@Override
	public boolean estLibre() {
		return false;
	}
	
	public String toString() {
		return "#";
	}
	
}
