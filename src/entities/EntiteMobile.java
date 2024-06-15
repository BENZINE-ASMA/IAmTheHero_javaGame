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
	
	public EntiteMobile(int pvBase, int pvRestant, int attaque, int vitesse) {
		this.pvBase = pvBase;
		this.pvRestant = pvRestant;
		this.attaque = attaque;
		this.vitesse = vitesse;
		this.dir = Direction.nord;
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
	
	public void baisserPV(int valeur) {
		this.pvRestant -= valeur;
	}
	
	public void augmenterPVRestants(int valeur) {
		if (this.pvRestant + valeur > this.pvBase){
			this.pvRestant = this.pvBase;
		}
		else {
			this.pvRestant += valeur;
		}
	}
	
	public void augmenterPVBases(int valeur) {
		this.pvBase += valeur;
	}
	
	public void augmenterAttaque(int valeur) {
		this.attaque += valeur;
	}
	
	public void augmenterVitesse(int valeur) {
		this.vitesse += valeur;
	}


	@Override
	public String toString() {
		return null;
	}
	public Direction getDirection() {
		return this.dir;
	}
	
	public void attaquePhysique(EntiteMobile cible) {
		System.out.println(name + " attaque " + cible.getName() + " à mains nues.\nCela lui fait perdre " + this.attaque/2 + " PV.");
		cible.baisserPV(attaque/2);
	}
}