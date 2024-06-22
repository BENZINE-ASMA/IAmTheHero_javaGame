package representation;

import java.io.Serializable;
import java.util.Objects;

import entities.Personnage;

public abstract class Node implements Serializable, Event {
    private static final long serialVersionUID = 1L;
	protected static int cpt = 0;
	protected int id;
	protected String nom;
	protected String description;
	protected Personnage joueur;
	
	
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
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(nom, node.nom);
    }
	
	public Personnage getJoueur() {
		return joueur;
	}
	
	public void setJoueur(Personnage joueur) {
		this.joueur = joueur;
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
	
	public abstract Node chooseNext2(String choice);
	

	public abstract void display();
	
	public abstract Node chooseNext(String choice);
	public abstract Node chooseNext();
}
