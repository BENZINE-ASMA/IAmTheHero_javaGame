package representation;

public abstract class Node {
	protected static int cpt = 0;
	protected int id;
	protected String nom;
	protected String description;
	
	
	public Node() {
		cpt ++;
		this.id = cpt;
		this.nom = "";
		this.description = "";
	}
	
	public Node(String nom, String description) {
		cpt ++;
		this.id = cpt;
		this.nom = nom;
		this.description = description;
	}
	
	public abstract void display();

	
	public abstract Node chooseNext();
}
