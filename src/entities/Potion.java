package entities;

public enum Potion {
    POTION_SANTE_MINEURE("Potion de Santé Mineure", 20, 0, "Restaure 20 PV"),
    POTION_SANTE_STANDARD("Potion de Santé Standard", 40, 0, "Restaure 40 PV"),
    FRUIT_DE_SAGESSE("Fruit de Sagesse", 0, 30, "Restaure 30 MP"),
    ELIXIR_DE_MANA_STANDARD("Elixir de Mana Standard", 0, 40, "Restaure 40 MP");

    private final String nom;
    private final int pvRestores;
    private final int mpRestores;
    private final String description;

    Potion(String nom, int pvRestores, int mpRestores, String description) {
        this.nom = nom;
        this.pvRestores = pvRestores;
        this.mpRestores = mpRestores;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public int getPvRestores() {
        return pvRestores;
    }

    public int getMpRestores() {
        return mpRestores;
    }

    public String getDescription() {
        return description;
    }
    
    public void utiliser(Personnage personnage) {
        if (pvRestores > 0) {
            personnage.augmenterPVRestants(pvRestores);
            System.out.println(personnage.getName() + " utilise " + getNom() + " et restaure " + pvRestores + " PV.");
        }
        if (mpRestores > 0) {
            personnage.augmenterPVRestants(mpRestores);
            System.out.println(personnage.getName() + " utilise " + getNom() + " et restaure " + mpRestores + " MP.");
        }
    }
}
