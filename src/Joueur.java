
public class Joueur extends EntiteMobile{
	protected Direction dir;
	
	public Joueur() {
		super(Direction.nord);
	}
	public Joueur(Direction dir) {
		super(dir);
	}

	@Override
	public String toString() {
		return ("J");
	}
	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	


	
	
}