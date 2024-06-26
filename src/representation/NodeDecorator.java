package representation;

import java.io.Serializable;
import java.util.HashMap;

import Interface.PanelComponent;
import entities.Personnage;

public abstract class NodeDecorator implements Event, Serializable {
	protected Event decoratedNode;
	 private static final long serialVersionUID = 1L;

    public NodeDecorator(Event decoratedNode) {
        this.decoratedNode = decoratedNode;
    }
    
    public abstract int playDecorator();
    
    public abstract int playDecorator(PanelComponent panel, int cpt);

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
	
	public abstract void display2();
	
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

}
