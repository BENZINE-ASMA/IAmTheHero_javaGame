package Components;

public class CaseIntraversable extends Case {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
