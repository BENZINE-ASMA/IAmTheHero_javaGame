package entities;

public class Personnage extends EntiteMobile {
    protected Object[] sac;
    protected int clan; // 0 -> no clan, 1 -> clan Element, 2 -> clan Spirituel
    protected int attaque;
    protected int vitesse;

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
}
