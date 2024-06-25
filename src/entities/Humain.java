package entities;

import java.util.ArrayList;

public class Humain extends Personnage {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Competence comp;

    public Humain(Competence comp) {
        super();
        setCompetence(comp);
        vitesse = 15;
        this.name = "Humain";
        sac = new ArrayList<Potion>();
		capaciteMax = 10; 
		arme = Arme.EPEE_BOIS;
		pvBase = 70;
		pvRestant = 70;
    }

    public Humain() {
        super();
        vitesse = 15;
        sac = new ArrayList<Potion>();
		capaciteMax = 10; 
		arme = Arme.EPEE_BOIS;
		pvBase = 70;
		pvRestant = 70;
    }

    public Humain(Direction dir, Competence comp) {
        super(dir);
        setCompetence(comp);
        vitesse = 15;
        sac = new ArrayList<Potion>();
		capaciteMax = 10; 
		arme = Arme.EPEE_BOIS;
		pvBase = 70;
		pvRestant = 70;
    }

    public void setCompetence(Competence comp) {
        this.comp = comp;
        if (comp.equals(Competence.combattant)) {
        	this.setAttaque(20);
            
        } else {
        	this.setAttaque(15);
        }
    }

    public Competence getCompetence() {
        return comp;
    }
    

    // Pour afficher les compétences du joueur
    public void afficherCompetences() {
        System.out.println("Compétence du joueur : " + comp);
    }
         
        @Override
        public void afficherInfos() {
            System.out.println("Vous êtes un humain avec la compétence de " + this.comp);
        }

    
}
