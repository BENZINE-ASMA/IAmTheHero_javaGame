
public class CaseCLanA extends CaseTraversable{

	public CaseCLanA(int l, int c, Entite e) {
		super(l, c, e);
		
	}
	public CaseCLanA(int l, int c) {
		super(l, c);
		
	}
	
	@Override
	public String toString() {
		if (contenu != null) return contenu.toString();
		return "*";
	}
}
