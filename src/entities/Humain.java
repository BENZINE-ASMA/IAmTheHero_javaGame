package entities;

import java.util.ArrayList;

/**
 * Classe représentant un humain, un type de personnage avec des compétences spécifiques.
 */
public class Humain extends Personnage {
    private static final long serialVersionUID = 1L;

    /**
     * La compétence spécifique de l'humain.
     */
    protected Competence comp;

    /**
     * Constructeur d'un humain avec une compétence spécifique.
     * 
     * @param comp la compétence de l'humain
     */
    public Humain(Competence comp) {
        super();
        setCompetence(comp);
        this.vitesse = 15;
        this.name = "Humain";
        this.sac = new ArrayList<Potion>();
        this.capaciteMax = 10;
        this.arme = Arme.EPEE_BOIS;
        this.pvBase = 70;
        this.pvRestant = 70;
    }

    /**
     * Constructeur par défaut d'un humain.
     */
    public Humain() {
        super();
        this.vitesse = 15;
        this.sac = new ArrayList<Potion>();
        this.capaciteMax = 10;
        this.arme = Arme.EPEE_BOIS;
        this.pvBase = 70;
        this.pvRestant = 70;
    }

    /**
     * Constructeur d'un humain avec une direction et une compétence spécifiques.
     * 
     * @param dir la direction initiale de l'humain
     * @param comp la compétence de l'humain
     */
    public Humain(Direction dir, Competence comp) {
        super(dir);
        setCompetence(comp);
        this.vitesse = 15;
        this.sac = new ArrayList<Potion>();
        this.capaciteMax = 10;
        this.arme = Arme.EPEE_BOIS;
        this.pvBase = 70;
        this.pvRestant = 70;
    }

    /**
     * Définit la compétence de l'humain.
     * 
     * @param comp la nouvelle compétence
     */
    public void setCompetence(Competence comp) {
        this.comp = comp;
        if (comp.equals(Competence.combattant)) {
            this.setAttaque(20);
        } else {
            this.setAttaque(15);
        }
    }

    /**
     * Obtient la compétence de l'humain.
     * 
     * @return la compétence de l'humain
     */
    public Competence getCompetence() {
        return comp;
    }

    /**
     * Affiche les compétences du joueur.
     */
    public void afficherCompetences() {
        System.out.println("Compétence du joueur : " + comp);
    }

    /**
     * Affiche les informations sur l'humain.
     */
    @Override
    public void afficherInfos() {
        System.out.println("Vous êtes un humain avec la compétence de " + this.comp);
    }
}
