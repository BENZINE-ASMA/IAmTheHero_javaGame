package entities;

import java.util.ArrayList;
import java.util.List;

public class Sorcier extends Personnage {
	protected List<Sort> sortsConnus = new ArrayList<Sort>();
	protected int magieBase;
	protected int magieRestant;
	protected int nbSortsConnus = 0;
	
	public Sorcier() {
		super();
		attaque = 108;
		vitesse = 15;
		magieBase = 40;
		magieRestant = 40;
		sac = new ArrayList<Potion>();
		capaciteMax = 5; 
		arme = Arme.BATON_MAGIQUE_BOIS;
		pvBase = 60;
		pvRestant = 60;
	}
	
	public Sorcier(Direction dir) {
		super(dir);
		attaque = 108;
		vitesse = 15;
		magieBase = 40;
		magieRestant = 40;
		sac = new ArrayList<Potion>();
		capaciteMax = 5;
		arme = Arme.BATON_MAGIQUE_BOIS;
		pvBase = 60;
		pvRestant = 60;
	}
	
	public void apprendreSort(Sort sort) {
		sortsConnus.add(sort);
		nbSortsConnus++;
	}
	
	
	public List<Sort> getSortsConnus() {
		return sortsConnus;
	}

	public void setSortsConnus(List<Sort> sortsConnus) {
		this.sortsConnus = sortsConnus;
	}
	
	
	public void afficherSortsConnus () {
        System.out.println("Sorts connus :");
        int i=0;
        for (Sort sort : sortsConnus) {
            System.out.println("- " + (i+1) + " "+ sort.getNom() + ": coût mana : " + sort.getCoutMana() + ", dégats : " + sort.getDegats());
            i++;
        }
    }

	public int getMagieBase() {
		return magieBase;
	}

	public void setMagieBase(int magieBase) {
		this.magieBase = magieBase;
	}

	public int getMagieRestant() {
		return magieRestant;
	}

	public void setMagieRestant(int magieRestant) {
		this.magieRestant = magieRestant;
	}

	public int getNbSortsConnus() {
		return nbSortsConnus;
	}

	public void setNbSortsConnus(int nbSortsConnus) {
		this.nbSortsConnus = nbSortsConnus;
	}
	
	public void baisserMP(int valeur) {
		this.magieRestant -= valeur;
	}
	
	public void augmenterMPRestant(int valeur) {
		if (this.magieRestant + valeur > this.magieBase){
			this.magieRestant = this.magieBase;
		}
		else {
			this.magieRestant += valeur;
		}
	}
	
	public void augmenterMPBase(int valeur) {
		this.magieBase += valeur;
	}

	public void lancerSort(EntiteMobile cible, Sort sorc) { // je n'ai honnetement aucune idée du type de la méthode
		System.out.println("Vous utilisez " + sorc.getNom() + "\n" + sorc.getDescription());
		baisserMP(sorc.getCoutMana());
		cible.baisserPV(sorc.getDegats());
	}

}