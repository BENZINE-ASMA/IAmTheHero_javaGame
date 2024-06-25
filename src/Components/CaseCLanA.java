package Components;
import entities.Entite;

public class CaseCLanA extends CaseTraversable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
