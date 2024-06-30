package entities;

/**
 * Énumération représentant les différents sorts que peut utiliser un sorcier.
 * Chaque sort possède un nom, un coût en mana, des dégâts, des points de soin, une description et un type (élément).
 */
public enum Sort {
    BOULE_DE_FEU("Boule de Feu", 8, 15, 0, "Le sorcier lance une boule de feu ardente sur l'ennemi.", "feu"),
    TORRENT_DEAU("Torrent d'Eau", 12, 20, 0, "Le sorcier invoque un puissant torrent d'eau pour balayer ses ennemis.", "eau"),
    TORNADO("Tornade", 20, 30, 0, "Le sorcier invoque une violente tornade qui souffle les ennemis.", "air"),
    TREMBLEMENT_DE_TERRE("Tremblement de Terre", 15, 25, 0, "Le sorcier crée un tremblement de terre sous le sol de ses ennemis.", "terre"),
    EXPLOSION_D_AME("Explosion d'âme", 20, 0, 0, "Le sorcier libère une explosion d'énergie spirituelle qui blesse les ennemis autour de lui. Dépend du nombre d'ennemis vaincus.", null),
    DRAIN_SPIRITUEL("Drain Spirituel", 10, 15, 5, "Le sorcier lance une attaque spirituelle qui draine les points de vie de l'ennemi.", null),
    LUMIERE_CURATIVE("Lumière Curative", 20, 0, 40, "Le sort restaure les points de vie des alliés.", null),
    ECLAT_DE_CRISTAL("Eclat de Cristal", 12, 20, 0, "Le sorcier lance des éclats de cristal sur ses ennemis.", null);

    /**
     * Le nom du sort.
     */
    private final String nom;

    /**
     * Le coût en mana du sort.
     */
    private final int coutMana;

    /**
     * Les dégâts du sort.
     */
    private final int degats;

    /**
     * Les points de soin du sort.
     */
    private final int soin;

    /**
     * La description du sort.
     */
    private final String description;

    /**
     * L'élément du sort (feu, eau, air, terre) ou null s'il n'a pas d'élément spécifique.
     */
    private final String type;

    /**
     * Constructeur de l'énumération Sort.
     * 
     * @param nom le nom du sort
     * @param coutMana le coût en mana du sort
     * @param degats les dégâts du sort
     * @param soin les points de soin du sort
     * @param description la description du sort
     * @param type l'élément du sort (peut être null)
     */
    Sort(String nom, int coutMana, int degats, int soin, String description, String type) {
        this.nom = nom;
        this.coutMana = coutMana;
        this.degats = degats;
        this.description = description;
        this.soin = soin;
        this.type = type;
    }

    /**
     * Obtient le nom du sort.
     * 
     * @return le nom du sort
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtient le coût en mana du sort.
     * 
     * @return le coût en mana du sort
     */
    public int getCoutMana() {
        return coutMana;
    }

    /**
     * Obtient les dégâts du sort.
     * 
     * @return les dégâts du sort
     */
    public int getDegats() {
        return degats;
    }

    /**
     * Obtient les points de soin du sort.
     * 
     * @return les points de soin du sort
     */
    public int getSoin() {
        return soin;
    }

    /**
     * Obtient la description du sort.
     * 
     * @return la description du sort
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtient l'élément du sort.
     * 
     * @return l'élément du sort (peut être null)
     */
    public String getType() {
        return type;
    }
}
