package Components;

/**
 * Classe représentant une case intraversable sur le plateau.
 */
public class CaseIntraversable extends Case {

    private static final long serialVersionUID = 1L;

    /**
     * Constructeur de la classe CaseIntraversable.
     *
     * @param l ligne de la case
     * @param c colonne de la case
     */
    public CaseIntraversable(int l, int c) {
        super(l, c);
    }

    /**
     * Vérifie si la case est libre.
     *
     * @return toujours false, car une case intraversable n'est jamais libre
     */
    @Override
    public boolean estLibre() {
        return false;
    }

    /**
     * Retourne une représentation textuelle de la case intraversable (#).
     *
     * @return une chaîne représentant la case intraversable
     */
    @Override
    public String toString() {
        return "#";
    }
}
