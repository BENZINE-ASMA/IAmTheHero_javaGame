package entities;

public class EntiteMobile extends Entite{
	protected Direction dir;
	protected int pvBase;
	protected int pvRestant;
	protected int attaque;
	protected int vitesse;
	
	public EntiteMobile(Direction dir) {
		this.dir = dir;
	}
	
	
	public Direction getDir() {
		return dir;
	}


	public void setDir(Direction dir) {
		this.dir = dir;
	}


	public int getPvBase() {
		return pvBase;
	}
	
	public void setPvBase(int pvBase) {
		this.pvBase = pvBase;
	}


	public int getPvRestant() {
		return pvRestant;
	}

	public void setPvRestant(int pvRestant) {
		this.pvRestant = pvRestant;
	}


	public int getAttaque() {
		return attaque;
	}

	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}


	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}


	@Override
	public String toString() {
		return null;
	}
	public Direction getDirection() {
		return this.dir;
	}
	
	public int attaquePhysique() {
		return attaque; //faire une formule pour calculer les dégats causés (plus tard)
	}
}