package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant un sorcier, un type de personnage avec des capacités magiques.
 */
public abstract class Sorcier extends Personnage {
    private static final long serialVersionUID = 1L;

    /**
     * Liste des sorts connus par le sorcier.
     */
    protected List<Sort> sortsConnus = new ArrayList<Sort>();

    /**
     * Points de magie de base du sorcier.
     */
    protected int magieBase;

    /**
     * Points de magie restants du sorcier.
     */
    protected int magieRestant;

    /**
     * Nombre de sorts connus par le sorcier.
     */
    protected int nbSortsConnus = 0;

    /**
     * Constructeur par défaut d'un sorcier.
     */
    public Sorcier() {
        super();
        this.attaque = 20;
        this.vitesse = 15;
        this.magieBase = 40;
        this.magieRestant = 40;
        this.sac = new ArrayList<Potion>();
        this.capaciteMax = 5;
        this.arme = Arme.BATON_MAGIQUE_BOIS;
        this.pvBase = 60;
        this.pvRestant = 60;
    }

    /**
     * Constructeur d'un sorcier avec une direction spécifique.
     * 
     * @param dir la direction initiale du sorcier
     */
    public Sorcier(Direction dir) {
        super(dir);
        this.attaque = 20;
        this.vitesse = 15;
        this.magieBase = 40;
        this.magieRestant = 40;
        this.sac = new ArrayList<Potion>();
        this.capaciteMax = 5;
        this.arme = Arme.BATON_MAGIQUE_BOIS;
        this.pvBase = 60;
        this.pvRestant = 60;
    }

    /**
     * Apprend un nouveau sort au sorcier.
     * 
     * @param sort le sort à apprendre
     */
    public void apprendreSort(Sort sort) {
        sortsConnus.add(sort);
        nbSortsConnus++;
    }

    /**
     * Obtient la liste des sorts connus par le sorcier.
     * 
     * @return la liste des sorts connus
     */
    public List<Sort> getSortsConnus() {
        return sortsConnus;
    }

    /**
     * Définit la liste des sorts connus par le sorcier.
     * 
     * @param sortsConnus la nouvelle liste des sorts connus
     */
    public void setSortsConnus(List<Sort> sortsConnus) {
        this.sortsConnus = sortsConnus;
    }

    /**
     * Affiche les sorts connus par le sorcier.
     */
    public void afficherSortsConnus() {
        System.out.println("Sorts connus :");
        int i = 0;
        for (Sort sort : sortsConnus) {
            System.out.println((i + 1) + " " + sort.getNom() + ": coût mana : " + sort.getCoutMana() + ", dégâts de base : " + sort.getDegats() + ", soin de points de vie : " + sort.getSoin());
            i++;
        }
    }

    /**
     * Obtient les points de magie de base du sorcier.
     * 
     * @return les points de magie de base
     */
    public int getMagieBase() {
        return magieBase;
    }

    /**
     * Définit les points de magie de base du sorcier.
     * 
     * @param magieBase les nouveaux points de magie de base
     */
    public void setMagieBase(int magieBase) {
        this.magieBase = magieBase;
    }

    /**
     * Obtient les points de magie restants du sorcier.
     * 
     * @return les points de magie restants
     */
    public int getMagieRestant() {
        return magieRestant;
    }

    /**
     * Définit les points de magie restants du sorcier.
     * 
     * @param magieRestant les nouveaux points de magie restants
     */
    public void setMagieRestant(int magieRestant) {
        this.magieRestant = magieRestant;
    }

    /**
     * Obtient le nombre de sorts connus par le sorcier.
     * 
     * @return le nombre de sorts connus
     */
    public int getNbSortsConnus() {
        return nbSortsConnus;
    }

    /**
     * Réduit les points de magie restants du sorcier.
     * 
     * @param valeur la valeur à réduire
     */
    public void baisserMP(int valeur) {
        this.magieRestant -= valeur;
    }

    /**
     * Augmente les points de magie restants du sorcier.
     * 
     * @param valeur la valeur à augmenter
     */
    public void augmenterMPRestant(int valeur) {
        if (this.magieRestant + valeur > this.magieBase) {
            this.magieRestant = this.magieBase;
        } else {
            this.magieRestant += valeur;
        }
    }

    /**
     * Augmente les points de magie de base du sorcier.
     * 
     * @param valeur la valeur à augmenter
     */
    public void augmenterMPBase(int valeur) {
        this.magieBase += valeur;
    }

    /**
     * Lance un sort sur une entité mobile cible.
     * 
     * @param cible la cible du sort
     * @param sorc le sort à lancer
     */
    public abstract void lancerSort(EntiteMobile cible, Sort sorc);
}
