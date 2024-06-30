package entities;

import java.io.Serializable;
import java.util.List;

/**
 * Classe représentant un personnage avec des caractéristiques spécifiques.
 */
public class Personnage extends EntiteMobile {
    private static final long serialVersionUID = 1L;

    /**
     * Le sac contenant les potions du personnage.
     */
    protected List<Potion> sac;
    /**
     * La capacité maximale du sac.
     */
    protected int capaciteMax;
    /**
     * Le clan auquel appartient le personnage.
     * 0 -> no clan, 1 -> clan Element, 2 -> clan Enchanteur
     */
    protected int clan;
    /**
     * L'arme équipée par le personnage.
     */
    protected Arme arme = null;

    /**
     * Constructeur par défaut du personnage.
     */
    public Personnage() {
        super(Direction.nord);
    }
    /**
     * Constructeur du personnage avec une direction spécifique.
     * 
     * @param dir la direction initiale du personnage
     */
    public Personnage(Direction dir) {
        super(dir);
    }

    /**
     * Obtient la direction actuelle du personnage.
     * 
     * @return la direction actuelle
     */
    @Override
    public Direction getDir() {
        return dir;
    }

    /**
     * Définit la direction du personnage.
     * 
     * @param dir la nouvelle direction
     */
    @Override
    public void setDir(Direction dir) {
        this.dir = dir;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du personnage.
     * 
     * @return une chaîne de caractères représentant le personnage
     */
    @Override
    public String toString() {
        return "J";
    }

    /**
     * Définit la direction du personnage.
     * 
     * @param dir la nouvelle direction
     */
    public void setDirection(Direction dir) {
        this.dir = dir;
    }


    /**
     * Affiche les informations du personnage.
     */
    public void afficherInfos() {
        // TODO Auto-generated method stub
    }

    /**
     * Obtient le sac contenant les potions du personnage.
     * 
     * @return la liste des potions dans le sac
     */
    public List<Potion> getSac() {
        return sac;
    }

    /**
     * Définit le sac contenant les potions du personnage.
     * 
     * @param sac la nouvelle liste de potions
     */
    public void setSac(List<Potion> sac) {
        this.sac = sac;
    }

    /**
     * Obtient le clan auquel appartient le personnage.
     * 
     * @return le clan du personnage
     */
    public int getClan() {
        return clan;
    }

    /**
     * Définit le clan auquel appartient le personnage.
     * 
     * @param clan le nouveau clan
     */
    public void setClan(int clan) {
        this.clan = clan;
    }

    /**
     * Obtient l'arme équipée par le personnage.
     * 
     * @return l'arme équipée
     */
    public Arme getArme() {
        return arme;
    }

    /**
     * Définit l'arme équipée par le personnage.
     * 
     * @param arme la nouvelle arme
     */
    public void setArme(Arme arme) {
        this.arme = arme;
    }

    /**
     * Obtient la capacité maximale du sac.
     * 
     * @return la capacité maximale
     */
    public int getCapaciteMax() {
        return capaciteMax;
    }

    /**
     * Définit la capacité maximale du sac.
     * 
     * @param capaciteMax la nouvelle capacité maximale
     */
    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    /**
     * Ajoute une potion au sac du personnage.
     * 
     * @param potion la potion à ajouter
     * @return true si la potion a été ajoutée, false sinon
     */
    public boolean ajouterPotion(Potion potion) {
        if (sac.size() < capaciteMax) {
            sac.add(potion);
            return true;
        } else {
            System.out.println("Le sac est plein. Vous ne pouvez pas ajouter plus d'objets.");
            return false;
        }
    }

    /**
     * Affiche les potions présentes dans le sac.
     */
    public void afficherPotions() {
        System.out.println("Potions dans le sac :");
        int i = 0;
        for (Potion potion : sac) {
            System.out.println((i + 1) + ": " + potion.getNom() + ": " + potion.getDescription());
            i++;
        }
    }

    /**
     * Utilise une potion du sac.
     * 
     * @param potion la potion à utiliser
     */
    public void utiliserPotion(Potion potion) {
        sac.remove(potion);
        potion.utiliser(this);
    }

    /**
     * Effectue une attaque armée sur une autre entité mobile.
     * 
     * @param cible la cible de l'attaque
     */
    public void attaqueArmee(EntiteMobile cible) {
        System.out.println(name + " attaque " + cible.getName() + " avec " + arme.getName() + ".\nCela lui fait perdre " + (arme.getPointsDegats() + this.attaque / 2) + " PV.");
        cible.baisserPV((arme.getPointsDegats() + this.attaque / 2));
    }
}


   
