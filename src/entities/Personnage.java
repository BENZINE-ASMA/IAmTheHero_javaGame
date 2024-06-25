package entities;
import java.io.Serializable;

import java.util.List;

public class Personnage extends EntiteMobile  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<Potion> sac;
    protected int capaciteMax;
	protected int clan; // 0 -> no clan, 1 -> clan Element, 2 -> clan Spirituel
    protected Arme arme = null;

    public Personnage() {
        super(Direction.nord);
    }

    public Personnage(Direction dir) {
        super(dir);
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return ("J");
    }

    public void setDirection(Direction dir) {
        this.dir = dir;
    }

    public void attaqueArmee() {
        // Implementation
    }

    public String afficheComp() {
        return "this is a test";
    }

	public void afficherInfos() {
	
		// TODO Auto-generated method stub
		
	}

	public List<Potion> getSac() {
		return sac;
	}

	public void setSac(List<Potion> sac) {
		this.sac = sac;
	}

	public int getClan() {
		return clan;
	}

	public void setClan(int clan) {
		this.clan = clan;
	}

	public Arme getArme() {
		return arme;
	}

	public void setArme(Arme arme) {
		this.arme = arme;
	}
	
	
	public int getCapaciteMax() {
		return capaciteMax;
	}

	public void setCapaciteMax(int capaciteMax) {
		this.capaciteMax = capaciteMax;
	}

	public boolean ajouterPotion(Potion potion) {
        if (sac.size() < capaciteMax) {
            sac.add(potion);
            return true;
        } else {
            System.out.println("Le sac est plein. Vous ne pouvez pas ajouter plus d'objets.");
            return false;
        }
    }
	
	public void afficherPotions() {
        System.out.println("Potions dans le sac :");
        int i=0;
        for (Potion potion : sac) {
            System.out.println((i+1) + ": "+ potion.getNom() + ": " + potion.getDescription());
            i++;
        }
    }

	public void utiliserPotion(Potion potion) {
		sac.remove(potion);
		potion.utiliser(this);
	}
	
	public void attaqueArmee(EntiteMobile cible) {
		System.out.println(name + " attaque " + cible.getName() + " avec " + arme.getName() +".\nCela lui fait perdre " + (arme.getPointsDegats()+ this.attaque/2) + " PV.");
		cible.baisserPV((arme.getPointsDegats()+ this.attaque/2));
	}
	
	
	
	
	
}
