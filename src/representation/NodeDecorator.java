package representation;

import java.io.Serializable;
import java.util.HashMap;

import entities.Personnage;

public abstract class NodeDecorator implements Event, Serializable {
	protected Event decoratedNode;
	 private static final long serialVersionUID = 1L;

    public NodeDecorator(Event decoratedNode) {
        this.decoratedNode = decoratedNode;
    }

    @Override
    public void display() {
        decoratedNode.display();
    }

    @Override
    public Event chooseNext() {
        return decoratedNode.chooseNext();
    }
   
    @Override
    public Event chooseNext(String choice) {
    	return decoratedNode.chooseNext(choice);
    }
    
    
	public Event chooseNext2(String choice) {
		return decoratedNode.chooseNext2(choice);
	}
	
	
	public Node getNode() {
		return decoratedNode.getNode();
	}
	
	
	public int getId() {
		return decoratedNode.getNode().getId();
	}
	
	public String getNom() {
		return decoratedNode.getNode().getNom();
	}
	
	public String getDescription() {
		return decoratedNode.getNode().getDescription();
	}
	
	public void setDescription(String description) {
		decoratedNode.getNode().setDescription(description);
	}
	
	public void setNom(String nom) {
		decoratedNode.getNode().setNom(nom);
	}
	
	public Personnage getJoueur() {
		return decoratedNode.getNode().joueur;
	}
	
	public void setJoueur(Personnage joueur) {
		decoratedNode.getNode().joueur = joueur;
	}
	
	public void addToNodeSuivant(String replique ,Event toAdd) {
		decoratedNode.getNode().nodesSuivant.put(replique,toAdd);
	}
	
	public HashMap<String,Event>  getNodesSuivant() {
		return decoratedNode.getNode().nodesSuivant;
	}
}
