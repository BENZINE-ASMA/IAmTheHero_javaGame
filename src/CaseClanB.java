
public class CaseClanB extends CaseTraversable{

	public CaseClanB(int l, int c, Entite e) {
		super(l, c, e);
		
	}
	public CaseClanB(int l, int c) {
		super(l, c);
		
	}
	
	@Override
	public String toString() {
		if (contenu != null) return contenu.toString();
		return "-";
	}

}
