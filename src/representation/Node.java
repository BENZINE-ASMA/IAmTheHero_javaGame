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
		this.nodesSuivant = new HashMap<>();
	}
	
	public Node(String nom, String description) {
		cpt ++;
		this.id = cpt;
		this.nom = nom;
		this.description = description;
		this.nodesSuivant = new HashMap<>();
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
	

	public abstract void display();
	
	   public String insertLineBreaks(String text, int maxLength) {
	        StringBuilder formattedText = new StringBuilder();
	        int length = text.length();
	        int start = 0;
	        

	        while (start < length) {
	            int end = Math.min(start + maxLength, length);
	            if (end < length) {
	                // Si nous ne sommes pas à la fin du texte, trouvons le dernier espace avant la limite
	                int lastSpace = text.lastIndexOf(' ', end);
	                if (lastSpace > start) {
	                    end = lastSpace;
	                }
	            }
	            // Ajouter le segment de texte au résultat
	            formattedText.append(text, start, end);
	            // Ajouter un saut de ligne s'il ne s'agit pas de la fin du texte
	            if (end < length) {
	                formattedText.append("\n");
	            }
	            start = end + 1; // Recommencer après l'espace (ou après la limite si aucun espace trouvé)
	        }

	        return formattedText.toString();
	    }
	
	
	public abstract Event chooseNext(String choice);
	
	public abstract Event chooseNext();

	public abstract Event chooseNext2(String choice);
}
