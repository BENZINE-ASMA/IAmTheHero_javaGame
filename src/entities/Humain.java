package entities;

public class Humain extends Personnage {
	protected Competence comp;
	
	public Humain(Competence comp) {
		super();
		if (comp.equals(Competence.combattant)) {
			attaque = 20;
		}
		else {
			attaque = 15;
		}
		vitesse = 15;
		this.comp = comp;
		sac = new Object[15];
	}
	
	public Humain() {
		super();
		vitesse = 15;
		sac = new Object[15];
	}
	
	public Humain(Direction dir, Competence comp) {
		if (comp.equals(Competence.combattant)) {
			attaque = 20;
		}
		else {
			attaque = 15;
		}
		vitesse = 15;
		this.comp = comp;
		sac = new Object[15];
	}
}