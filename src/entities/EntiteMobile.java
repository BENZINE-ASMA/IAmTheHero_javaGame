package entities;

import java.io.Serializable;

/**
 * Classe représentant une entité mobile pouvant se déplacer dans une direction spécifique.
 */
public class EntiteMobile extends Entite implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * La direction actuelle de l'entité.
     */
    protected Direction dir;
    /**
     * Les points de vie de base de l'entité.
     */
    protected int pvBase;
    /**
     * Les points de vie restants de l'entité.
     */
    protected int pvRestant;
    /**
     * Les points d'attaque de l'entité.
     */
    protected int attaque;
    /**
     * La vitesse de l'entité.
     */
    protected int vitesse;
    
    /**
     * Constructeur de l'entité mobile avec une direction spécifique.
     * 
     * @param dir la direction initiale de l'entité
     */
    public EntiteMobile(Direction dir) {
        this.dir = dir;
    }
    
    /**
     * Constructeur de l'entité mobile avec des attributs spécifiques.
     * 
     * @param pvBase les points de vie de base
     * @param pvRestant les points de vie restants
     * @param attaque les points d'attaque
     * @param vitesse la vitesse
     */
    public EntiteMobile(int pvBase, int pvRestant, int attaque, int vitesse) {
        this.pvBase = pvBase;
        this.pvRestant = pvRestant;
        this.attaque = attaque;
        this.vitesse = vitesse;
        this.dir = Direction.nord;
    }
    
    /**
     * Obtient la direction actuelle de l'entité.
     * 
     * @return la direction actuelle
     */
    public Direction getDir() {
        return dir;
    }

    /**
     * Définit la direction de l'entité.
     * 
     * @param dir la nouvelle direction
     */
    public void setDir(Direction dir) {
        this.dir = dir;
    }

    /**
     * Obtient les points de vie de base de l'entité.
     * 
     * @return les points de vie de base
     */
    public int getPvBase() {
        return pvBase;
    }

    /**
     * Définit les points de vie de base de l'entité.
     * 
     * @param pvBase les nouveaux points de vie de base
     */
    public void setPvBase(int pvBase) {
        this.pvBase = pvBase;
    }

    /**
     * Obtient les points de vie restants de l'entité.
     * 
     * @return les points de vie restants
     */
    public int getPvRestant() {
        return pvRestant;
    }

    /**
     * Définit les points de vie restants de l'entité.
     * 
     * @param pvRestant les nouveaux points de vie restants
     */
    public void setPvRestant(int pvRestant) {
        this.pvRestant = pvRestant;
    }

    /**
     * Obtient les points d'attaque de l'entité.
     * 
     * @return les points d'attaque
     */
    public int getAttaque() {
        return attaque;
    }

    /**
     * Définit les points d'attaque de l'entité.
     * 
     * @param attaque les nouveaux points d'attaque
     */
    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    /**
     * Obtient la vitesse de l'entité.
     * 
     * @return la vitesse
     */
    public int getVitesse() {
        return vitesse;
    }

    /**
     * Définit la vitesse de l'entité.
     * 
     * @param vitesse la nouvelle vitesse
     */
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    /**
     * Réduit les points de vie restants de l'entité.
     * 
     * @param valeur la valeur de la réduction
     */
    public void baisserPV(int valeur) {
        this.pvRestant -= valeur;
    }

    /**
     * Augmente les points de vie restants de l'entité.
     * 
     * @param valeur la valeur de l'augmentation
     */
    public void augmenterPVRestants(int valeur) {
        if (this.pvRestant + valeur > this.pvBase) {
            this.pvRestant = this.pvBase;
        } else {
            this.pvRestant += valeur;
        }
    }

    /**
     * Augmente les points de vie de base de l'entité.
     * 
     * @param valeur la valeur de l'augmentation
     */
    public void augmenterPVBases(int valeur) {
        this.pvBase += valeur;
    }

    /**
     * Augmente les points d'attaque de l'entité.
     * 
     * @param valeur la valeur de l'augmentation
     */
    public void augmenterAttaque(int valeur) {
        this.attaque += valeur;
    }

    /**
     * Augmente la vitesse de l'entité.
     * 
     * @param valeur la valeur de l'augmentation
     */
    public void augmenterVitesse(int valeur) {
        this.vitesse += valeur;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'entité mobile.
     * 
     * @return une chaîne de caractères représentant l'entité mobile
     */
    @Override
    public String toString() {
        return null;
    }

    /**
     * Obtient la direction actuelle de l'entité.
     * 
     * @return la direction actuelle
     */
    public Direction getDirection() {
        return this.dir;
    }

    /**
     * Effectue une attaque physique sur une autre entité mobile.
     * 
     * @param cible la cible de l'attaque
     */
    public void attaquePhysique(EntiteMobile cible) {
        System.out.println(name + " attaque " + cible.getName() + " à mains nues.\nCela lui fait perdre " + this.attaque / 2 + " PV.");
        cible.baisserPV(attaque / 2);
    }
}
