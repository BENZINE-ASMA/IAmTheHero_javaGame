
public class EntiteMobile extends Entite{
	protected Direction dir;
	
	public EntiteMobile(Direction dir) {
		this.dir = dir;
	}
	@Override
	public String toString() {
		return null;
	}
	public Direction getDirection() {
		return this.dir;
	}
}
