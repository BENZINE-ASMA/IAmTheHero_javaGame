package entities;

public abstract class Entite {
	public String name;
	
	public abstract String toString();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
