package entities;

/**
 * Énumération représentant les différentes potions que les personnages peuvent utiliser.
 * Chaque potion possède un nom, une quantité de PV restaurés, une quantité de MP restaurés et une description.
 */
public enum Potion {
    POTION_SANTE_MINEURE("Potion de Santé Mineure", 20, 0, "Restaure 20 PV"),
    POTION_SANTE_STANDARD("Potion de Santé Standard", 40, 0, "Restaure 40 PV"),
    FRUIT_DE_SAGESSE("Fruit de Sagesse", 0, 30, "Restaure 30 MP"),
    ELIXIR_DE_MANA_STANDARD("Elixir de Mana Standard", 0, 40, "Restaure 40 MP");

    /**
     * Le nom de la potion.
     */
    private final String nom;

    /**
     * La quantité de PV que la potion restaure.
     */
    private final int pvRestores;

    /**
     * La quantité de MP que la potion restaure.
     */
    private final int mpRestores;

    /**
     * La description de la potion.
     */
    private final String description;

    /**
     * Constructeur de l'énumération Potion.
     * 
     * @param nom le nom de la potion
     * @param pvRestores la quantité de PV restaurée par la potion
     * @param mpRestores la quantité de MP restaurée par la potion
     * @param description la description de la potion
     */
    Potion(String nom, int pvRestores, int mpRestores, String description) {
        this.nom = nom;
        this.pvRestores = pvRestores;
        this.mpRestores = mpRestores;
        this.description = description;
    }

    /**
     * Obtient le nom de la potion.
     * 
     * @return le nom de la potion
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtient la quantité de PV restaurée par la potion.
     * 
     * @return la quantité de PV restaurée
     */
    public int getPvRestores() {
        return pvRestores;
    }

    /**
     * Obtient la quantité de MP restaurée par la potion.
     * 
     * @return la quantité de MP restaurée
     */
    public int getMpRestores() {
        return mpRestores;
    }

    /**
     * Obtient la description de la potion.
     * 
     * @return la description de la potion
     */
    public String getDescription() {
        return description;
    }

    /**
     * Utilise la potion sur un personnage, restaurant ses PV ou MP en fonction de la potion.
     * 
     * @param personnage le personnage sur lequel la potion est utilisée
     */
    public void utiliser(Personnage personnage) {
        if (pvRestores > 0) {
            personnage.augmenterPVRestants(pvRestores);
            System.out.println(personnage.getName() + " utilise " + getNom() + " et restaure " + pvRestores + " PV.");
        }
        if (personnage instanceof Sorcier) {
            if (mpRestores > 0) {
                ((Sorcier) personnage).augmenterMPRestant(mpRestores);
                System.out.println(personnage.getName() + " utilise " + getNom() + " et restaure " + mpRestores + " MP.");
            }
        }
    }
}
