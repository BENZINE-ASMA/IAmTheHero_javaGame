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
	
	
	
	public static int getCpt() {
		return cpt;
	}

	public static void setCpt(int cpt) {
		Node.cpt = cpt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	 public Node chooseNext2(String choice) {
	        if (this instanceof InnerNode) {
	            return ((InnerNode) this).getNodesSuivant().get(choice);
	        }
	        return null;
	    }
	

	public abstract void display();
	
	 	public abstract Node chooseNext(String choice);
	    public abstract Node chooseNext();
}
