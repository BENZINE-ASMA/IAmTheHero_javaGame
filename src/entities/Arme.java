package entities;

public enum Arme {
    BATON_MAGIQUE_BOIS("Bâton Magique en Bois", "Un bâton magique rudimentaire taillé dans du bois.", 5),
    BATON_MAGIQUE_AMELIORE("Bâton Magique Amélioré", "Un bâton magique renforcé avec des runes mystiques.", 10),
    EPEE_BOIS("Épée en Bois", "Une épée simple faite de bois.", 10),
    EPEE_LONGUE("Épée Longue", "Une épée à deux mains forgée avec soin.", 20);

    private final String nom;
    private final String description;
    private final int pointsDegats;

    Arme(String nom, String description, int pointsDegats) {
        this.nom = nom;
        this.description = description;
        this.pointsDegats = pointsDegats;
    }

    public String getName() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getPointsDegats() {
        return pointsDegats;
    }
}
