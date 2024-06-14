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
		attaque = 8;
		vitesse = 15;
		magieBase = 30;
		magieRestant = 30;
		sac = new ArrayList<Potion>();
		capaciteMax = 5; 
		arme = Arme.BATON_MAGIQUE_BOIS;
		pvBase = 50;
		pvRestant = 50;
	}
	
	public Sorcier(Direction dir) {
		super(dir);
		attaque = 8;
		vitesse = 15;
		magieBase = 30;
		magieRestant = 30;
		sac = new ArrayList<Potion>();
		capaciteMax = 5;
		arme = Arme.BATON_MAGIQUE_BOIS;
		pvBase = 50;
		pvRestant = 50;
	}
	
	public void apprendreSort(Sort sort) {
		if (nbSortsConnus >= sortsConnus.size()) {
			System.out.println("Nombre de sorts connus maximal atteint. Impossible d'en rajouter");
			return;
		}
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
            System.out.println("- " + (i+1) + sort.getNom() + ": coût mana :" + sort.getCoutMana() + ", dégats :" + sort.getDegats());
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

	public void lancerSort(EntiteMobile cible, Sort sorc) { // je n'ai honnetement aucune idée du type de la méthode
		System.out.println("Vous utilisez" + sorc.getNom() + "\n" + sorc.getDescription());
		baisserMP(sorc.getCoutMana());
		cible.baisserPV(sorc.getDegats());
	}

}