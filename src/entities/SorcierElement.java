package entities;

public class SorcierElement extends Personnage {
    public SorcierElement() {
        super();
        this.name = "Sorcier des Éléments";
    }

    @Override
    public void afficherInfos() {
        System.out.println("Vous êtes un Sorcier des Éléments avec des pouvoirs sur la terre, l'eau, le feu et l'air.");
    }
}
