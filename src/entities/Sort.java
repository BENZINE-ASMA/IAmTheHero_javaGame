package entities;


public enum Sort {
    BOULE_DE_FEU("Boule de Feu", 10, 15, "Le sorcier lance une boule de feu ardente sur l'ennemi."),
    TORRENT_DEAU("Torrent d'Eau", 12, 20, "Le sorcier invoque un puissant torrent d'eau pour balayer ses ennemis."),
    TORNADO("Tornade", 20, 30, "Le sorcier invoque une violente tornade qui souffle les ennemis."),
    MUR_DE_TERRE("Mur de Terre", 15, 0, "Le sorcier crée un mur de terre solide pour se protéger des attaques ennemies."),
    EXPLOSION_D_AME("Explosion d'âme", 15, 20, "Le sorcier libère une explosion d'énergie spirituelle qui blesse les ennemis autour de lui."),
    DRAIN_SPIRITUEL("Drain Spirituel", 10, 15, "Le sorcier lance une attaque spirituelle qui draine les points de vie de l'ennemi."),
    LUMIERE_CURATIVE("Lumière Curative", 20, 0, "Le sort restaure les points de vie des alliés."),
    BOUCLIER_SPIRITUEL("Bouclier Spirituel", 15, 0, "Le sorcier crée un bouclier protecteur fait d'énergie spirituelle pour bloquer les attaques ennemies.");

    private final String nom; // Le nom du sort
    private final int coutMana; // Le coût en mana du sort
    private final int degats; // Les dégâts du sort
    private final String description; // La description du sort

    Sort(String nom, int coutMana, int degats, String description) {
        this.nom = nom;
        this.coutMana = coutMana;
        this.degats = degats;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
