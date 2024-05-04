package entities;

public class Sorcier extends Personnage {
	protected Sort[] sortsConnus = new Sort[6];
	protected int magie;
	protected int nbSortsConnus = 0;
	
	public Sorcier() {
		super();
		attaque = 8;
		vitesse = 15;
		magie = 25;
		sac = new Object[10];
	}
	
	public Sorcier(Direction dir) {
		super(dir);
		attaque = 8;
		vitesse = 15;
		magie = 25;
		sac = new Object[10];
	}
	
	public void apprendreSort(Sort sort) {
		if (nbSortsConnus >= sortsConnus.length) {
			System.out.println("Nombre de sorts connus maximal atteint. Impossible d'en rajouter");
			return;
		}
		sortsConnus[nbSortsConnus] = sort;
		nbSortsConnus++;
	}
	
	public void remplacerSort(Sort newsort, Sort oldsort) {
		for (int i=0; i<sortsConnus.length;i++) {
			if (oldsort == sortsConnus[i]) {
				sortsConnus[i] = newsort;
				break;
			}
		}
	}
	
	public void lancerSort(Sort sorc) { // je n'ai honnetement aucune idée du type de la méthode
		
	}

}