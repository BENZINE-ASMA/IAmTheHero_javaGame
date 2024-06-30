package entities;

/**
 * Énumération représentant différentes armes disponibles dans le jeu.
 */
public enum Arme {
    BATON_MAGIQUE_BOIS("Bâton Magique en Bois", "Un bâton magique rudimentaire taillé dans du bois.", 5),
    BATON_MAGIQUE_AMELIORE("Bâton Magique Amélioré", "Un bâton magique renforcé avec des runes mystiques.", 10),
    EPEE_BOIS("Épée en Bois", "Une épée simple faite de bois.", 10),
    EPEE_LONGUE("Épée Longue", "Une épée à deux mains forgée avec soin.", 20);

    private final String nom; // Le nom de l'arme
    private final String description; // La description de l'arme
    private final int pointsDegats; // Les points de dégâts de l'arme

    /**
     * Constructeur de l'énumération Arme.
     * 
     * @param nom le nom de l'arme
     * @param description la description de l'arme
     * @param pointsDegats les points de dégâts de l'arme
     */
    Arme(String nom, String description, int pointsDegats) {
        this.nom = nom;
        this.description = description;
        this.pointsDegats = pointsDegats;
    }

    /**
     * Retourne le nom de l'arme.
     * 
     * @return le nom de l'arme
     */
    public String getName() {
        return nom;
    }

    /**
     * Retourne la description de l'arme.
     * 
     * @return la description de l'arme
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne les points de dégâts de l'arme.
     * 
     * @return les points de dégâts de l'arme
     */
    public int getPointsDegats() {
        return pointsDegats;
    }
}
