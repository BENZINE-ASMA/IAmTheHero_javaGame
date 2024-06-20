package representation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import entities.EntiteMobile;
import entities.Personnage;
import entities.Sorcier;
import entities.SorcierSpirituel;
import entities.Sort;

public class CombatNode extends InnerNode {
	private EntiteMobile monstre;
	private boolean finished = false;
	private boolean winner;
	private TerminalNode death; //noeud spécifique terminal pour la mort au combat
	
	
	public CombatNode(HashMap<String, Node> nodesSuivant, TerminalNode death, EntiteMobile monstre) {
		super(nodesSuivant);
		this.death = death;
		this.monstre = monstre;
		//this.joueur = joueur;
	}
	
	public CombatNode(String nom, String description, TerminalNode death, EntiteMobile monstre) {
		super(nom, description);
		this.death = death;
		this.monstre = monstre;
		//this.joueur = joueur;
	}
	
	
	/*
	public Personnage getJoueur() {
		return joueur;
	}

	public void setJoueur(Personnage joueur) {
		this.joueur = joueur;
	}
	*/

	@Override
	public Node chooseNext() {
		if (!winner) {
			return death;
		}
		
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
        
        return chosenNode;

	}
	
	public void choixAction() {
		Scanner sc = new Scanner(System.in);
        int sizeChoice;
		
		if (joueur instanceof Sorcier) {
			sizeChoice = 4;
		}
		else {
			sizeChoice = 3;
		}
		

        while (true) {  // permet de revenir en arrière sur ses choix si pas d'objet adapté ou de sort avec assez de mana
        	
        	System.out.println("1: Regarder dans le sac");
    		System.out.println("2: Attaquer à mains nues");
    		System.out.println("3: Attaquer avec " + joueur.getArme());
    		if (joueur instanceof Sorcier){
    			System.out.println("4: Lancer un sort");
    		}
        	
        	int choix;
            while (true) {
                System.out.print("Choisissez une option : ");
                choix = sc.nextInt();
                if (choix > 0 && choix <= sizeChoice) {
                    break;
                } else {
                    System.out.println("Choix invalide, veuillez réessayer.");
                }
            }
            
            int choix2;
            if (choix == 1) {
            	System.out.println("0 : choisir une autre action");
            	joueur.afficherPotions();
            	while (true) {
                    System.out.print("Choisissez une option : ");
                    choix2 = sc.nextInt();
                    if (choix2 >= 0 && choix2 <= joueur.getCapaciteMax()) {
                        break;
                    } else {
                        System.out.println("Choix invalide, veuillez réessayer.");
                    }
                }
            	
            	if (choix2 == 0) {
            		continue;
            	}
            	else {
            		joueur.utiliserPotion(joueur.getSac().get(choix2-1));
            		break;
            	}
            	
            }
            else if (choix == 2){
            	joueur.attaquePhysique(monstre);
            	break;
            }
            else if (choix == 3) {
            	joueur.attaqueArmee(monstre);
            	break;
            }
            else {
            	//choisir le sort à utiliser ; ne pas l'utiliser si pas assez de mana
            	List<Sort> lsort = ((Sorcier)joueur).getSortsConnus();
            	int nbSorts = ((Sorcier)joueur).getNbSortsConnus();
            	
            	
            	while (true) {
                    System.out.println("0 : choisir une autre action");
                	((Sorcier)joueur).afficherSortsConnus();
                	System.out.print("Choisissez une option : ");
                    choix2 = sc.nextInt();
                    if (choix2 >= 0 && choix2 <= nbSorts) {
                        if (choix2 > 0) {
                        	System.out.println(lsort.get(choix2-1).getCoutMana() + " " + ((Sorcier)joueur).getMagieRestant());
                        	if (lsort.get(choix2-1).getCoutMana()<= ((Sorcier)joueur).getMagieRestant()) {
                        		break;
                        	}
                        	else {
                        		System.out.println("Pas assez de mana, veuillez réessayer.");
                        		continue;
                        	}
                        }
                        break;
                    } 
                    
                    else {
                        System.out.println("Choix invalide, veuillez réessayer.");
                    }
                }
            	
            	if (choix2 == 0) {
            		continue;
            	}
            	else {
            		((Sorcier)joueur).lancerSort(monstre, lsort.get(choix2-1));
            		break;
            	}
            	
            }
        }
		
        
	}
	
	
	public void chooseReward() {
		Scanner sc = new Scanner(System.in);
        int sizeChoice;
		
		if (joueur instanceof Sorcier) {
			sizeChoice = 4;
		}
		else {
			sizeChoice = 3;
		}
		
		System.out.println("Vous gagnez 5 points de compétence, dans quoi voulez-vous les mettre ?");
		System.out.println("1: Points de vie");
		System.out.println("2: Attaque");
		System.out.println("3: Vitesse");
		if (joueur instanceof Sorcier){
			System.out.println("4: Points de magie");
		}
    	
    	int choix;
        while (true) {
            System.out.print("Choisissez une option : ");
            choix = sc.nextInt();
            if (choix > 0 && choix <= sizeChoice) {
                break;
            } else {
                System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
        
        switch(choix) {
        case 1: joueur.augmenterPVBases(5); joueur.augmenterPVRestants(5); break;
        case 2: joueur.augmenterAttaque(5); break;
        case 3: joueur.augmenterVitesse(5); break;
        case 4: ((Sorcier)joueur).augmenterMPBase(5); ((Sorcier)joueur).augmenterMPRestant(5); break;
        }
        
        System.out.println("Modification effectuée.");
	}

	@Override
	public void display() {
		System.out.println(description);
		
		while (monstre.getPvRestant() > 0 && joueur.getPvRestant() > 0) {
			System.out.println("PV de " + monstre.getName() + ": " + monstre.getPvRestant() + "\nVos PV : " + joueur.getPvRestant());
			if (joueur instanceof Sorcier) {
				System.out.println("Vos MP : " + ((Sorcier)joueur).getMagieRestant());
			}
			
			if (monstre.getVitesse()>joueur.getVitesse()) {
				System.out.println(monstre.getName() + " vous attaque.");
				monstre.attaquePhysique(joueur);
				if (joueur.getPvRestant() > 0) {
					System.out.println("Que voulez vous faire ?");
					choixAction();
				}
				else {
					finished = true;
				}
			}
			else {
				System.out.println("Que voulez vous faire ? ");
				choixAction();
				if (monstre.getPvRestant() > 0) {
					System.out.println(monstre.getName() + " vous attaque.");
					monstre.attaquePhysique(joueur);
				}
				else {
					finished = true;
				}
			}
		
	
		}
		
		if (joueur.getPvRestant() > 0) {
			winner = true;
			System.out.println(monstre.getName() + " est KO.");
			chooseReward();
			if (joueur instanceof SorcierSpirituel) {
				((SorcierSpirituel) joueur).addSouls(1);
			}
			
		}
		else {
			System.out.println("Vous êtes KO.");
			winner = false;
		}
	}

	@Override
	public Node chooseNext(String choice) {
		// TODO Auto-generated method stub
		return null;
	}
	 public Node chooseNext2(String choice) {
	        if (this instanceof InnerNode) {
	            return ((InnerNode) this).getNodesSuivant().get(choice);
	            
	        }
	        return null;
	    }

}
