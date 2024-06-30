package Components;

import entities.Entite;

/**
 * Classe représentant une case associée au clan B sur le plateau.
 */
public class CaseClanB extends CaseTraversable {

    private static final long serialVersionUID = 1L;

    /**
     * Constructeur pour initialiser une CaseClanB avec une entité spécifique.
     *
     * @param l ligne de la case sur le plateau
     * @param c colonne de la case sur le plateau
     * @param e entité à placer dans la case
     */
    public CaseClanB(int l, int c, Entite e) {
        super(l, c, e);
    }

    /**
     * Constructeur pour initialiser une CaseClanB vide.
     *
     * @param l ligne de la case sur le plateau
     * @param c colonne de la case sur le plateau
     */
    public CaseClanB(int l, int c) {
        super(l, c);
    }

    /**
     * Retourne une représentation textuelle de la case, montrant l'entité si présente, sinon '-'.
     *
     * @return représentation textuelle de la case
     */
    @Override
    public String toString() {
        if (contenu != null) {
            return contenu.toString();
        }
        return "-";
    }
}
