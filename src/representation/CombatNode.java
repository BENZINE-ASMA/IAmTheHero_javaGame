package representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import Interface.FrameComponent;
import entities.EntiteMobile;
import entities.Sorcier;
import entities.SorcierSpirituel;
import entities.Sort;

/**
 * Représente un nœud dans l'arborescence de décision du jeu où se déroule un combat entre le personnage du joueur
 * et une entité mobile (monstre). Ce nœud permet au joueur de s'engager dans le combat, de faire des choix
 * pendant le combat et de gérer les résultats du combat.
 */
public class CombatNode extends InnerNode {
    private static final long serialVersionUID = 1L;
    private EntiteMobile monstre;
    private boolean finished = false;
    private boolean winner;
    private TerminalNode death; // noeud spécifique terminal pour la mort au combat
    transient Scanner sc;

    /**
     * Constructeur d'un CombatNode avec les paramètres donnés.
     *
     * @param nodesSuivant un HashMap des événements suivants où les résultats du combat mènent à des nœuds subséquents
     * @param death        nœud terminal spécifique pour la mort au combat
     * @param monstre      l'entité mobile adversaire dans le combat
     */
    public CombatNode(HashMap<String, Event> nodesSuivant, TerminalNode death, EntiteMobile monstre) {
        super(nodesSuivant);
        this.death = death;
        this.monstre = monstre;
        this.sc = new Scanner(System.in);
    }

    /**
     * Constructeur d'un CombatNode avec les paramètres donnés.
     *
     * @param nom        nom du nœud de combat
     * @param description description du nœud de combat
     * @param death      nœud terminal spécifique pour la mort au combat
     * @param monstre    l'entité mobile adversaire dans le combat
     */
    public CombatNode(String nom, String description, TerminalNode death, EntiteMobile monstre) {
        super(nom, description);
        this.death = death;
        this.monstre = monstre;
        this.sc = new Scanner(System.in);
    }

    /**
     * Obtient l'état de victoire du combat.
     *
     * @return true si le joueur gagne, false sinon
     */
    public boolean getWinner() {
    	return this.winner;
    }
    
    /**
     * Permet au joueur de choisir l'événement suivant ou le nœud suivant en fonction du résultat du combat.
     *
     * @return l'événement ou le nœud suivant en fonction du choix du joueur ou du résultat par défaut
     */
    @Override
    public Event chooseNext() {
        if (!winner) {
            return death;
        }

        ArrayList<Event> nodeList = new ArrayList<>(nodesSuivant.values());
        ArrayList<String> reliqueList = new ArrayList<>(nodesSuivant.keySet());

        for (int i = 0; i < nodeList.size(); i++) {
            System.out.println((i + 1) + ": " + reliqueList.get(i));
        }

        int choix;
        while (true) {
            System.out.print("Choisissez une option : ");
            if (sc.hasNextInt()) {
                choix = sc.nextInt();
                if (choix > 0 && choix <= nodeList.size()) {
                    break;
                } else {
                    System.out.println("Choix invalide, veuillez réessayer.");
                }
            } else {
                System.out.println("Entrée invalide, veuillez entrer un nombre.");
                sc.next(); // Clear the invalid input
            }
        }

        Event chosenNode = nodeList.get(choix - 1);
        return chosenNode;
    }

  
    /**
     * Affiche l'état du combat et les choix du joueur lors du gameplay en mode console.
     */
	@Override
	public void display() {
		System.out.println(description);
		System.out.println("Vos statistiques :");
		if (joueur instanceof Sorcier) {
			System.out.println("Vos MP : " + ((Sorcier)joueur).getMagieRestant() + "/" + ((Sorcier)joueur).getMagieBase());
		}
		System.out.println("Vos PV : " + joueur.getPvRestant() + "/" + joueur.getPvBase());
		System.out.println("Votre attaque avec " + joueur.getArme() + " : " + (joueur.getAttaque()/2 + joueur.getArme().getPointsDegats()));
		System.out.println("Votre vitesse : " + joueur.getVitesse());
		
		
		while (monstre.getPvRestant() > 0 && joueur.getPvRestant() > 0) {
			System.out.println("PV de " + monstre.getName() + ": " + monstre.getPvRestant() + "/" + monstre.getPvBase()+ "\nVos PV : " + joueur.getPvRestant() + "/" + joueur.getPvBase());
			if (joueur instanceof Sorcier) {
				System.out.println("Vos MP : " + ((Sorcier)joueur).getMagieRestant() + "/" + ((Sorcier)joueur).getMagieBase());
			}
			
			if (monstre.getVitesse()>joueur.getVitesse()) {
				System.out.println(monstre.getName() + " vous attaque.");
				monstre.attaquePhysique(joueur);
				System.out.println("Vos PV : " + joueur.getPvRestant() + "/" + joueur.getPvBase());
				if (joueur.getPvRestant() > 0) {
					System.out.println("Que voulez vous faire ?");
					choixAction();
				}
				else {
					this.finished = true;
				}
			}
			else {
				System.out.println("Que voulez vous faire ? ");
				choixAction();
				if (monstre.getPvRestant() > 0) {
					System.out.println(monstre.getName() + " vous attaque.");
					monstre.attaquePhysique(joueur);
					System.out.println("Vos PV : " + joueur.getPvRestant() + "/" + joueur.getPvBase());
					
				}
				else {
					this.finished = true;
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
	
	 /**
     * Gère les actions du joueur pendant le combat en mode console.
     */
    public void choixAction() {
        int sizeChoice;

        if (joueur instanceof Sorcier) {
            sizeChoice = 4;
        } else {
            sizeChoice = 3;
        }

        while (true) {
            System.out.println("1: Regarder dans le sac");
            System.out.println("2: Attaquer à mains nues");
            System.out.println("3: Attaquer avec " + joueur.getArme());
            if (joueur instanceof Sorcier) {
                System.out.println("4: Lancer un sort");
            }

            int choix;
            while (true) {
                System.out.print("Choisissez une option : ");
                if (sc.hasNextInt()) {
                    choix = sc.nextInt();
                    if (choix > 0 && choix <= sizeChoice) {
                        break;
                    } else {
                        System.out.println("Choix invalide, veuillez réessayer.");
                    }
                } else {
                    System.out.println("Entrée invalide, veuillez entrer un nombre.");
                    sc.next(); // Clear the invalid input
                }
            }

            int choix2;
            if (choix == 1) {
                System.out.println("0 : choisir une autre action");
                joueur.afficherPotions();
                while (true) {
                    System.out.print("Choisissez une option : ");
                    if (sc.hasNextInt()) {
                        choix2 = sc.nextInt();
                        if (choix2 >= 0 && choix2 <= joueur.getCapaciteMax()) {
                            break;
                        } else {
                            System.out.println("Choix invalide, veuillez réessayer.");
                        }
                    } else {
                        System.out.println("Entrée invalide, veuillez entrer un nombre.");
                        sc.next(); // Clear the invalid input
                    }
                }

                if (choix2 == 0) {
                    continue;
                } else {
                    joueur.utiliserPotion(joueur.getSac().get(choix2 - 1));
                    break;
                }

            } else if (choix == 2) {
                joueur.attaquePhysique(monstre);
                break;
            } else if (choix == 3) {
                joueur.attaqueArmee(monstre);
                break;
            } else {
                // choisir le sort à utiliser ; ne pas l'utiliser si pas assez de mana
                List<Sort> lsort = ((Sorcier) joueur).getSortsConnus();
                int nbSorts = ((Sorcier) joueur).getNbSortsConnus();

                while (true) {
                    System.out.println("0 : choisir une autre action");
                    ((Sorcier) joueur).afficherSortsConnus();
                    System.out.print("Choisissez une option : ");
                    if (sc.hasNextInt()) {
                        choix2 = sc.nextInt();
                        if (choix2 >= 0 && choix2 <= nbSorts) {
                            if (choix2 > 0) {
                                System.out.println(lsort.get(choix2 - 1).getCoutMana() + " " + ((Sorcier) joueur).getMagieRestant());
                                if (lsort.get(choix2 - 1).getCoutMana() <= ((Sorcier) joueur).getMagieRestant()) {
                                    break;
                                } else {
                                    System.out.println("Pas assez de mana, veuillez réessayer.");
                                    continue;
                                }
                            }
                            break;
                        } else {
                            System.out.println("Choix invalide, veuillez réessayer.");
                        }
                    } else {
                        System.out.println("Entrée invalide, veuillez entrer un nombre.");
                        sc.next(); // Clear the invalid input
                    }
                }

                if (choix2 == 0) {
                    continue;
                } else {
                    ((Sorcier) joueur).lancerSort(monstre, lsort.get(choix2 - 1));
                    break;
                }

            }
        }
    }

    /**
     * Permet au joueur de choisir comment allouer 5 points de compétence après avoir remporté un combat.
     */
    public void chooseReward() {
        int sizeChoice;

        if (joueur instanceof Sorcier) {
            sizeChoice = 4;
        } else {
            sizeChoice = 3;
        }

        System.out.println("Vous gagnez 5 points de compétence, dans quoi voulez-vous les mettre ?");
        System.out.println("1: Points de vie");
        System.out.println("2: Attaque");
        System.out.println("3: Vitesse");
        if (joueur instanceof Sorcier) {
            System.out.println("4: Points de magie");
        }

        int choix;
        while (true) {
            System.out.print("Choisissez une option : ");
            if (sc.hasNextInt()) {
                choix = sc.nextInt();
                if (choix > 0 && choix <= sizeChoice) {
                    break;
                } else {
                    System.out.println("Choix invalide, veuillez réessayer.");
                }
            } else {
                System.out.println("Entrée invalide, veuillez entrer un nombre.");
                sc.next(); // Clear the invalid input
            }
        }

        switch (choix) {
            case 1:
                joueur.augmenterPVBases(5);
                joueur.augmenterPVRestants(5);
                break;
            case 2:
                joueur.augmenterAttaque(5);
                break;
            case 3:
                joueur.augmenterVitesse(5);
                break;
            case 4:
                if (joueur instanceof Sorcier) {
                    ((Sorcier) joueur).augmenterMPBase(5);
                    ((Sorcier) joueur).augmenterMPRestant(5);
                }
                break;
        }

        System.out.println("Modification effectuée.");
    }



	/**
     * Gère l'état du combat et les actions du joueur en utilisant JOptionPane de Swing pour le gameplay en mode interface utilisateur.
     *
     * @param frame le FrameComponent où l'interface utilisateur de combat est affichée
     */
    public void handleCombat(FrameComponent frame) {
    	
        StringBuilder stats = new StringBuilder(description);
        List<Sort> sorts = ((Sorcier)joueur).getSortsConnus();
        
        stats.append("\nVos statistiques :");
        if (joueur instanceof Sorcier) {
            stats.append("\nVos MP : ").append(((Sorcier)joueur).getMagieRestant()).append("/").append(((Sorcier)joueur).getMagieBase());
        }
        stats.append("\nVos PV : ").append(joueur.getPvRestant()).append("/").append(joueur.getPvBase());
        stats.append("\nVotre attaque avec ").append(joueur.getArme()).append(" : ").append(joueur.getAttaque()/2 + joueur.getArme().getPointsDegats());
        stats.append("\nVotre vitesse : ").append(joueur.getVitesse());
        for (int i=0; i<sorts.size(); i++) {
        	stats.append("\n" + sorts.get(i).getNom() + ": coût mana : " + sorts.get(i).getCoutMana() + ", dégats de base : " + sorts.get(i).getDegats() + ", soin de points de vie: " + sorts.get(i).getSoin());
        }
        
        JOptionPane.showMessageDialog(frame, stats.toString());

        while (monstre.getPvRestant() > 0 && joueur.getPvRestant() > 0) {
            String combatStatus = "PV de " + monstre.getName() + ": " + monstre.getPvRestant() + "/" + monstre.getPvBase() +
                                  "\nVos PV : " + joueur.getPvRestant() + "/" + joueur.getPvBase();
            if (joueur instanceof Sorcier) {
                combatStatus += "\nVos MP : " + ((Sorcier)joueur).getMagieRestant() + "/" + ((Sorcier)joueur).getMagieBase();
            }

            JOptionPane.showMessageDialog(frame, combatStatus);

            if (monstre.getVitesse() > joueur.getVitesse()) {
                JOptionPane.showMessageDialog(frame, monstre.getName() + " vous attaque.");
                monstre.attaquePhysique(joueur);
                if (joueur.getPvRestant() > 0) {
                    choixActionUI(frame);
                } else {
                    finished = true;
                }
            } else {
                choixActionUI(frame);
                if (monstre.getPvRestant() > 0) {
                    JOptionPane.showMessageDialog(frame, monstre.getName() + " vous attaque.");
                    monstre.attaquePhysique(joueur);
                } else {
                    finished = true;
                }
            }
        }

        if (joueur.getPvRestant() > 0) {
            winner = true;
            JOptionPane.showMessageDialog(frame, monstre.getName() + " est KO.");
            chooseRewardUI(frame);
            if (joueur instanceof SorcierSpirituel) {
                ((SorcierSpirituel) joueur).addSouls(1);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Vous êtes KO.");
            winner = false;
        }
    }

    
    /**
     * Gère les actions du joueur pendant le combat en utilisant JOptionPane de Swing pour le gameplay en mode interface utilisateur.
     *
     * @param frame le FrameComponent où l'interface utilisateur de combat est affichée
     */
    private void choixActionUI(FrameComponent frame) {
        String[] options;
        if (joueur instanceof Sorcier) {
            options = new String[]{"Regarder dans le sac", "Attaquer à mains nues", "Attaquer avec " + joueur.getArme(), "Lancer un sort"};
        } else {
            options = new String[]{"Regarder dans le sac", "Attaquer à mains nues", "Attaquer avec " + joueur.getArme()};
        }

        int choice = JOptionPane.showOptionDialog(frame, "Que voulez-vous faire ?", "Choix d'action",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                if (joueur.getSac().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Votre sac est vide.");
                } else {
                    int potionChoice = JOptionPane.showOptionDialog(frame, "Choisissez une potion", "Sac",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                            joueur.getSac().toArray(), null);
                    if (potionChoice >= 0) {
                        joueur.utiliserPotion(joueur.getSac().get(potionChoice));
                    }
                }
                break;
            case 1:
                joueur.attaquePhysique(monstre);
                break;
            case 2:
                joueur.attaqueArmee(monstre);
                break;
            case 3:
                if (joueur instanceof Sorcier) {
                    List<Sort> lsort = ((Sorcier)joueur).getSortsConnus();
                    int sortChoice = JOptionPane.showOptionDialog(frame, "Choisissez un sort", "Sorts",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                            lsort.toArray(), null);
                    if (sortChoice >= 0) {
                        ((Sorcier)joueur).lancerSort(monstre, lsort.get(sortChoice));
                    }
                }
                break;
        }
        
    }


    /**
     * Permet au joueur de choisir comment allouer 5 points de compétence après avoir remporté un combat en mode interface utilisateur.
     *
     * @param frame le FrameComponent où l'interface utilisateur de récompense est affichée
     */
    private void chooseRewardUI(FrameComponent frame) {
        String[] options;
        if (joueur instanceof Sorcier) {
            options = new String[]{"Points de vie", "Attaque", "Vitesse", "Points de magie"};
        } else {
            options = new String[]{"Points de vie", "Attaque", "Vitesse"};
        }

        int choice = JOptionPane.showOptionDialog(frame, "Vous gagnez 5 points de compétence, dans quoi voulez-vous les mettre ?", "Récompense",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                joueur.augmenterPVBases(5);
                joueur.augmenterPVRestants(5);
                break;
            case 1:
                joueur.augmenterAttaque(5);
                break;
            case 2:
                joueur.augmenterVitesse(5);
                break;
            case 3:
                if (joueur instanceof Sorcier) {
                    ((Sorcier)joueur).augmenterMPBase(5);
                    ((Sorcier)joueur).augmenterMPRestant(5);
                }
                break;
        }
    }

    /**
     * Permet au joueur de choisir l'événement ou le nœud suivant en fonction du choix du joueur en mode interface utilisateur.
     *
     * @param choice le choix du joueur pour l'événement ou le nœud suivant
     * @return l'événement ou le nœud suivant en fonction du choix du joueur
     */
    @Override
    public Event chooseNext(String choice) {
        // Not used
        return null;
    }

    /**
     * Permet au joueur de choisir l'événement ou le nœud suivant en fonction du choix du joueur en mode interface utilisateur.
     *
     * @param choice le choix du joueur pour l'événement ou le nœud suivant
     * @return l'événement ou le nœud suivant en fonction du choix du joueur
     */
    public Event chooseNext2(String choice) {
    	if (!winner) {
			return death;
		}
        return this.getNodesSuivant().get(choice);
    }

	



	
}