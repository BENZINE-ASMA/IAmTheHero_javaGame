package representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import entities.Personnage;
import entities.SorcierElement;
import entities.SorcierSpirituel;

public class ChanceNode extends InnerNode {
	
	 private static final long serialVersionUID = 1L;


	public ChanceNode(HashMap<String,Event> nodesSuivant) {
		super(nodesSuivant);
	}
	
	public ChanceNode(String nom, String description) {
		super(nom, description);
	}

	@Override
	public void display() {
		System.out.println(insertLineBreaks(description,150));
		
	}

	@Override
	public Event chooseNext() {
		ArrayList<Event> nodeList = new ArrayList<>(nodesSuivant.values());
		ArrayList<String> repliqueList = new ArrayList<>(nodesSuivant.keySet());

		Random random = new Random();
		int indice = random.nextInt(nodeList.size());
		Event chosenNode = nodeList.get(indice);
		String chosenKey = repliqueList.get(indice);
        
        handleSpecialCases(chosenKey, chosenNode,this.getNom());
		
		return chosenNode;
	}

	@Override
	public Event chooseNext(String choice) {
		// TODO Auto-generated method stub
		return null;
	}

	 public Event chooseNext3() {
		 System.out.println("heelo1");
	        if (this instanceof InnerNode) {
	        	ArrayList<Event> nodeList = new ArrayList<>(nodesSuivant.values());
	        	ArrayList<String> repliqueList = new ArrayList<>(nodesSuivant.keySet());

	    		Random random = new Random();
	    		int indice = random.nextInt(nodeList.size());
	    		Event chosenNode = nodeList.get(indice);
	    		String chosenKey = repliqueList.get(indice);
	            
	            handleSpecialCases(chosenKey, chosenNode,this.getNom());
	    		System.out.println(" the chosen isss "+ chosenNode + "   " + chosenNode.getDescription()+ " ++++ " + chosenNode.getNom());
	    		return chosenNode;
	            
	        }
	        
	        return null;
	    }
	 
	 
	 private void handleSpecialCases(String chosenKey, Event chosenNode, String nameOfCurrentNode) {
			if (chosenNode instanceof TerminalNode && nameOfCurrentNode.equals("DetruirePierre") && ("FinPaix".equals(chosenNode.getNom()))) {
				if (joueur instanceof SorcierElement) {
					chosenNode.setDescription("Le Maître du Clan, extrêmement déçu par votre décision, vous informe que la Pierre était leur seule chance contre les Enchanteurs. Les deux clans devront envisager la paix. Vous perdez tout prestige et êtes renvoyé.e. Vous devrez poursuivre vos aventures seul.e. FIN");
				}
				if (joueur instanceof SorcierSpirituel) {
					chosenNode.setDescription("Le Maître du Clan aurait préféré une domination totale, mais au moins la menace des Sorciers des Éléments est écartée. La paix est envisageable. Vous restez dans le clan et vivrez de nombreuses aventures avec vos camarades. FIN");
				}
		
			}
			
			if (chosenNode instanceof TerminalNode && nameOfCurrentNode.equals("DetruirePierre") && ("FinConflit".equals(chosenNode.getNom()))) {
				if (joueur instanceof SorcierElement) {
					chosenNode.setDescription("Le Maître du Clan s'affole en apprenant la nouvelle. Refusant toute paix ou alliance, il vous considère comme un traître et lance son sort le plus puissant : Immolation. Vous mourrez dans d'atroces souffrances. FIN");
				}
				if (joueur instanceof SorcierSpirituel) {
					chosenNode.setDescription("Le Maître du Clan est soulagé que ce pouvoir ne tombe pas entre les mains des Sorciers Élémentaires, mais il aurait aimé en finir avec ce conflit. Déçu de vous, il vous exclut du clan. Vous devrez continuer votre aventure seul.e, sur un territoire en conflit. FIN");
				}
		
			}
			
		}

	@Override
	public Event chooseNext2(String choice) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
