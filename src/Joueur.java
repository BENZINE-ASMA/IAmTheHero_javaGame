
public class Joueur extends Entite{
	protected Direction dir;
	
	public Joueur() {
		this.dir = Direction.nord;
	}
	public Joueur(Direction dir) {
		this.dir = dir;
	}

	@Override
	public String toString() {
		return ("J");
	}
	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	


	
	
}