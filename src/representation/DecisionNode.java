package representation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import entities.Arme;
import entities.Competence;
import entities.Humain;
import entities.Personnage;
import entities.Potion;
import entities.Sorcier;
import entities.SorcierElement;
import entities.SorcierSpirituel;
import entities.Sort;

public class DecisionNode extends InnerNode {

	public DecisionNode(HashMap<String,Node> nodesSuivant) {
		super(nodesSuivant);
		
	}
	public DecisionNode(String nom, String description) {
		super(nom, description);
		
	}
	public DecisionNode(String nom, String description, HashMap<String,Node> nodesSuivant) {
		super(nom, description, nodesSuivant);
		
	}
	
	
	@Override
	public void display() {
		System.out.println(description);
	}

	@Override
	public Node chooseNext() {
		ArrayList<Node> nodeList = new ArrayList<>(nodesSuivant.values());
		ArrayList<String> reliqueList = new ArrayList<>(nodesSuivant.keySet());
		
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < nodeList.size(); i++) {
            System.out.println((i + 1) + ": " + reliqueList.get(i));
        }

        int choix;
        while (true) {
            System.out.print("Choisissez une option : ");
            choix = sc.nextInt();
            if (choix > 0 && choix <= nodeList.size()) {
                break;
            } else {
                System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
        
        String chosenKey = reliqueList.get(choix - 1);
        Node chosenNode = nodeList.get(choix - 1);
        
        handleSpecialCases(chosenKey, chosenNode,this.getNom());
        
        return chosenNode;
    }
    
    private void handleSpecialCases(String chosenKey, Node chosenNode, String nameOfCurrentNode) {
        //if (chosenNode instanceof DecisionNode && chosenKey.startsWith("je suis ")) {
        	if (chosenNode instanceof DecisionNode && nameOfCurrentNode.equals("humain")) {
        		((DecisionNode) chosenNode).description = "vous avez la compétence de " + chosenKey.substring(8) + ", que voulez-vous faire?";
        		try {
                    Competence competence = Competence.valueOf(chosenKey.substring(8));
                    ((Humain)joueur).setCompetence(competence);
                    System.out.println("Compétence " + chosenKey.substring(8) + " ajoutée au joueur.");
                } catch (IllegalArgumentException e) {
                    System.out.println("La compétence " + chosenKey.substring(8) + " n'existe pas.");
                }
        	
        }
        //rajouter pour l'affinite du sorcier des éléments
        	if (chosenNode instanceof DecisionNode && nameOfCurrentNode.equals("missionClanElement")) {
        		((DecisionNode) chosenNode).description = "Tu as donc une affinité avec " + chosenKey.substring(34) + ", très bien. Tes sorts utilisant cet élément auront donc davantage de puissance que les autres, n'hésite pas à les utiliser.";
        	
        		try {
                    String element = chosenKey.substring(37);
                    ((SorcierElement)joueur).setElement(element);
                    System.out.println("Element " + chosenKey.substring(37) + " ajoutée au joueur.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Element " + chosenKey.substring(37) + " n'existe pas.");
                }
        
        	}
        	
        	
        	if (nameOfCurrentNode.equals("GuerisseusePotionAider")) {
        		joueur.ajouterPotion(Potion.ELIXIR_DE_MANA_STANDARD);
        		joueur.ajouterPotion(Potion.POTION_SANTE_STANDARD);
        		joueur.ajouterPotion(Potion.FRUIT_DE_SAGESSE);
        	}
        	
        	if (nameOfCurrentNode.equals("GuerisseusePotionInfo")) {
        		joueur.ajouterPotion(Potion.ELIXIR_DE_MANA_STANDARD);
        		joueur.ajouterPotion(Potion.POTION_SANTE_MINEURE);
        	}
        	
        	if (nameOfCurrentNode.equals("MeilleureArme")) {
        		if (joueur instanceof Sorcier) {
        			joueur.setArme(Arme.BATON_MAGIQUE_AMELIORE);
        		}
        		else {
        			joueur.setArme(Arme.EPEE_LONGUE);
        		}
        	}
        	
        	
        	if (nameOfCurrentNode.equals("maitreClanElementIntro3")) {
        		joueur.ajouterPotion(Potion.ELIXIR_DE_MANA_STANDARD);
        		joueur.ajouterPotion(Potion.POTION_SANTE_STANDARD);
        		joueur.ajouterPotion(Potion.POTION_SANTE_STANDARD);
        	}
        	
        	if (nameOfCurrentNode.equals("maitreClanEnchanteurIntro2")) {
        		joueur.ajouterPotion(Potion.ELIXIR_DE_MANA_STANDARD);
        		joueur.ajouterPotion(Potion.POTION_SANTE_STANDARD);
        		joueur.ajouterPotion(Potion.POTION_SANTE_STANDARD);
        	}
        	
        	
        	if (nameOfCurrentNode.equals("clanElement")) {
        		((Sorcier)joueur).apprendreSort(Sort.BOULE_DE_FEU); 
        		((Sorcier)joueur).apprendreSort(Sort.TREMBLEMENT_DE_TERRE); 
        		((Sorcier)joueur).apprendreSort(Sort.TORNADO); 
        		((Sorcier)joueur).apprendreSort(Sort.TORRENT_DEAU);
        	}
        	
        	if (nameOfCurrentNode.equals("clanEnchanteur")) {
        		((Sorcier)joueur).apprendreSort(Sort.DRAIN_SPIRITUEL); 
        		((Sorcier)joueur).apprendreSort(Sort.ECLAT_DE_CRISTAL); 
        		((Sorcier)joueur).apprendreSort(Sort.EXPLOSION_D_AME); 
        		((Sorcier)joueur).apprendreSort(Sort.LUMIERE_CURATIVE);
        	}
        		
    }
    
	@Override
	public Node chooseNext(String choice) {
		ArrayList<Node> nodeList = new ArrayList<>(nodesSuivant.values());
		ArrayList<String> reliqueList = new ArrayList<>(nodesSuivant.keySet());
		String chosenKey = null ;
        Node chosenNode = null;
        for (int i =0; i< reliqueList.size();i++) {
        	if (reliqueList.get(i).equals(choice)) {
        		chosenKey =  reliqueList.get(i);
                chosenNode = nodeList.get(i);
        	}
        }
        
        
        handleSpecialCases(chosenKey, chosenNode,this.getNom());
        
        return chosenNode;
	}
	
	 public Node chooseNext2(String choice) {
	        if (this instanceof InnerNode) {
	        	handleSpecialCases2(choice);
	            return ((InnerNode) this).getNodesSuivant().get(choice);
	            
	        }
	        return null;
	    }
	 
	 public  void handleSpecialCases2(String chosenKey) {
		 
		 Node chosenNode = ((InnerNode) this).getNodesSuivant().get(chosenKey);
		 
		 
	        //if (chosenNode instanceof DecisionNode && chosenKey.startsWith("je suis ")) {
     	if (chosenNode instanceof DecisionNode && this.nom.equals("humain")) {
     		((DecisionNode) chosenNode).description = "vous avez la compétence de " + chosenKey.substring(8) + ", que voulez-vous faire?";
     		try {
     			//this.joueur= new Humain();
                 Competence competence = Competence.valueOf(chosenKey.substring(8));
                 ((Humain)joueur).setCompetence(competence);
                 System.out.println("Compétence " + chosenKey.substring(8) + " ajoutée au joueur.");
             } catch (IllegalArgumentException e) {
                 System.out.println("La compétence " + chosenKey.substring(8) + " n'existe pas.");
             }
     	
     }
     //rajouter pour l'affinite du sorcier des éléments
     	if (chosenNode instanceof DecisionNode && this.nom.equals("missionClanElement")) {
     		//this.joueur =new SorcierElement();
     		
     		((DecisionNode) chosenNode).description = "Tu as donc une affinité avec " + chosenKey.substring(34) + ", très bien. Tes sorts utilisant cet élément auront donc davantage de puissance que les autres, n'hésite pas à les utiliser.";
     	
     		try {
                 String element = chosenKey.substring(37);
                 ((SorcierElement)joueur).setElement(element);
                 System.out.println("Element " + chosenKey.substring(37) + " ajoutée au joueur.");
             } catch (IllegalArgumentException e) {
                 System.out.println("Element " + chosenKey.substring(37) + " n'existe pas.");
             }
     	
     	
     	}
     	
     	if (this.nom.equals("GuerisseusePotionAider")) {
     		joueur.ajouterPotion(Potion.ELIXIR_DE_MANA_STANDARD);
     		joueur.ajouterPotion(Potion.POTION_SANTE_STANDARD);
     		joueur.ajouterPotion(Potion.FRUIT_DE_SAGESSE);
     	}
     	
     	if (this.nom.equals("GuerisseusePotionInfo")) {
     		joueur.ajouterPotion(Potion.ELIXIR_DE_MANA_STANDARD);
     		joueur.ajouterPotion(Potion.POTION_SANTE_MINEURE);
     	}
     	
     	if (this.nom.equals("MeilleureArme")) {
     		if (joueur instanceof Sorcier) {
     			joueur.setArme(Arme.BATON_MAGIQUE_AMELIORE);
     		}
     		else {
     			joueur.setArme(Arme.EPEE_LONGUE);
     		}
     	}
     	
     	
     	if (this.nom.equals("maitreClanElementIntro3")) {
     		joueur.ajouterPotion(Potion.ELIXIR_DE_MANA_STANDARD);
     		joueur.ajouterPotion(Potion.POTION_SANTE_STANDARD);
     		joueur.ajouterPotion(Potion.POTION_SANTE_STANDARD);
     	}
     	
     	if (this.nom.equals("maitreClanEnchanteurIntro2")) {
     		joueur.ajouterPotion(Potion.ELIXIR_DE_MANA_STANDARD);
     		joueur.ajouterPotion(Potion.POTION_SANTE_STANDARD);
     		joueur.ajouterPotion(Potion.POTION_SANTE_STANDARD);
     	}
     	
     	
     	if (this.nom.equals("clanElement")) {
     		((Sorcier)joueur).apprendreSort(Sort.BOULE_DE_FEU); 
     		((Sorcier)joueur).apprendreSort(Sort.TREMBLEMENT_DE_TERRE); 
     		((Sorcier)joueur).apprendreSort(Sort.TORNADO); 
     		((Sorcier)joueur).apprendreSort(Sort.TORRENT_DEAU);
     	}
     	
     	if (this.nom.equals("clanEnchanteur")) {
     		((Sorcier)joueur).apprendreSort(Sort.DRAIN_SPIRITUEL); 
     		((Sorcier)joueur).apprendreSort(Sort.ECLAT_DE_CRISTAL); 
     		((Sorcier)joueur).apprendreSort(Sort.EXPLOSION_D_AME); 
     		((Sorcier)joueur).apprendreSort(Sort.LUMIERE_CURATIVE);
     	}
     		
 }
 
}