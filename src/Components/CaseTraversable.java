package Components;

import entities.Entite;

/**
 * Classe représentant une case traversable sur le plateau.
 */
public class CaseTraversable extends Case {

    private static final long serialVersionUID = 1L;

    protected Entite contenu; // Entité contenue dans la case (peut être null)

    /**
     * Constructeur de la classe CaseTraversable.
     *
     * @param l ligne de la case
     * @param c colonne de la case
     */
    public CaseTraversable(int l, int c) {
        super(l, c);
    }

    /**
     * Constructeur de la classe CaseTraversable avec une entité initiale.
     *
     * @param l ligne de la case
     * @param c colonne de la case
     * @param e entité initiale contenue dans la case
     */
    public CaseTraversable(int l, int c, Entite e) {
        super(l, c);
        this.contenu = e;
    }

    /**
     * Obtient l'entité contenue dans la case.
     *
     * @return l'entité contenue dans la case, peut être null si la case est vide
     */
    public Entite getContenu() {
        return this.contenu;
    }

    /**
     * Vide la case en retirant toute entité contenue.
     */
    public void vide() {
        contenu = null;
    }

    /**
     * Remplit la case avec une nouvelle entité.
     *
     * @param e nouvelle entité à placer dans la case
     */
    public void setContenu(Entite e) {
        contenu = e;
    }

    /**
     * Vérifie si la case est libre (aucune entité présente).
     *
     * @return true si la case est libre (aucune entité), false sinon
     */
    @Override
    public boolean estLibre() {
        return contenu == null;
    }

    /**
     * Retourne une représentation textuelle de la case, représentant l'entité si présente ou un espace si vide.
     *
     * @return une chaîne représentant la case
     */
    @Override
    public String toString() {
        if (contenu != null) {
            return contenu.toString();
        }
        return " ";
    }
}
