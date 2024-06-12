package entities;

public class Humain extends Personnage {
    protected Competence comp;

    public Humain(Competence comp) {
        super();
        setCompetence(comp);
        vitesse = 15;
        this.name = "Humain";
        sac = new Object[15];
    }

    public Humain() {
        super();
        vitesse = 15;
        sac = new Object[15];
    }

    public Humain(Direction dir, Competence comp) {
        super(dir);
        setCompetence(comp);
        vitesse = 15;
        sac = new Object[15];
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
         
        @Override
        public void afficherInfos() {
            System.out.println("Vous êtes un humain avec la compétence de " + this.comp);
        }

    
}
