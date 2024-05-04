import entities.Entite;

public class CaseTraversable extends Case {
	protected Entite contenu;
	public CaseTraversable(int l, int c) {
		super(l, c);
		
	}
	public CaseTraversable(int l, int c, Entite e) {
		super(l, c);
		this.contenu = e;
	}
	public Entite getContenu() {
		return this.contenu;
	}
	public void vide() { //vider la case
		contenu = null;
	}
	public void setContenu(Entite e) { //Remplir la case
		contenu = e;
	}
	

	@Override
	public boolean estLibre() {
		return contenu == null;
	}
	
	public String toString() {
		if (contenu != null) return contenu.toString();
		return " ";
	}
	
}

