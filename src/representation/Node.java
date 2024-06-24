package representation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

import entities.Personnage;

public abstract class Node implements Serializable, Event {
    private static final long serialVersionUID = 1L;
	protected static int cpt = 0;
	protected int id;
	protected String nom;
	protected String description;
	protected Personnage joueur;
	protected HashMap<String,Event> nodesSuivant;
	
	
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

	
	public Personnage getJoueur() {
		return joueur;
	}
	
	public void setJoueur(Personnage joueur) {
		this.joueur = joueur;
	}
	

	public int getId() {
		return id;
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
	
	public Node getNode() {
		return this;
	}
	
	public HashMap<String,Event>  getNodesSuivant() {
		return this.nodesSuivant;
	}
	
	
	public void addToNodeSuivant(String replique ,Event toAdd) {
		this.nodesSuivant.put(replique,toAdd);
	}
	
	public abstract Event chooseNext2(String choice);
	

	public abstract void display();
	
	public abstract Event chooseNext(String choice);
	public abstract Event chooseNext();
}
