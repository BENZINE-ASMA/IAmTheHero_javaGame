package entities;


public enum Sort {
    BOULE_DE_FEU("Boule de Feu", 8, 15, 0, "Le sorcier lance une boule de feu ardente sur l'ennemi.", "feu"),
    TORRENT_DEAU("Torrent d'Eau", 12, 20, 0, "Le sorcier invoque un puissant torrent d'eau pour balayer ses ennemis.", "eau"),
    TORNADO("Tornade", 20, 30, 0, "Le sorcier invoque une violente tornade qui souffle les ennemis.", "air"),
    TREMBLEMENT_DE_TERRE("Mur de Terre", 15, 25, 0, "Le sorcier crée un tremblement de terre sous le sol de ses ennemis.", "terre"),
    EXPLOSION_D_AME("Explosion d'âme", 20, 0, 0, "Le sorcier libère une explosion d'énergie spirituelle qui blesse les ennemis autour de lui. Dépend du nombre d'ennemis vaincus.", null),
    DRAIN_SPIRITUEL("Drain Spirituel", 10, 15, 5, "Le sorcier lance une attaque spirituelle qui draine les points de vie de l'ennemi.", null),
    LUMIERE_CURATIVE("Lumière Curative", 20, 0, 40, "Le sort restaure les points de vie des alliés.", null),
    ECLAT_DE_CRISTAL("Eclat de Cristal", 12, 20, 0, "Le sorcier lance des éclats de crital sur ses ennemis.", null);

    private final String nom; // Le nom du sort
    private final int coutMana; // Le coût en mana du sort
    private final int degats; // Les dégâts du sort
    private final int soin; // Les points de soin du sort
    private final String description; // La description du sort
    private final String type; // élément du sort

    Sort(String nom, int coutMana, int degats, int soin, String description, String type) {
        this.nom = nom;
        this.coutMana = coutMana;
        this.degats = degats;
        this.description = description;
        this.soin = soin;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public int getCoutMana() {
        return coutMana;
    }

    public int getDegats() {
        return degats;
    }
    
    public int getSoin() {
    	return soin;
    }
    
    public String getDescription() {
        return description;
    }
   
    public String getType() {
    	return type;
    }
}
