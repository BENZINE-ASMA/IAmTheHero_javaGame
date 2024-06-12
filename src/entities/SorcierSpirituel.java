package entities;
public class SorcierSpirituel extends Personnage {
    public SorcierSpirituel() {
        super();
        this.name = "Sorcier Spirituel";
    }

    @Override
    public void afficherInfos() {
        System.out.println("Vous êtes un Sorcier Spirituel avec des pouvoirs sur l'esprit et l'âme.");
    }
}