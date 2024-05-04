
public class Donjon extends CaseTraversable {
	public Donjon(int l, int c, Entite e) {
		super(l, c, e);
		
	}
	public Donjon(int l, int c) {
		super(l, c);
		
	}
	
	@Override
	public String toString() {
		if (contenu != null) return contenu.toString();
		return "|";
	}
}
