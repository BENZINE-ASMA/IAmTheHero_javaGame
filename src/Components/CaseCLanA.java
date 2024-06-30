package Components;

import entities.Entite;

/**
 * Classe représentant une case associée au clan A sur le plateau.
 */
public class CaseCLanA extends CaseTraversable {

    private static final long serialVersionUID = 1L;

    /**
     * Constructeur pour initialiser une CaseCLanA avec une entité spécifique.
     *
     * @param l ligne de la case sur le plateau
     * @param c colonne de la case sur le plateau
     * @param e entité à placer dans la case
     */
    public CaseCLanA(int l, int c, Entite e) {
        super(l, c, e);
    }

    /**
     * Constructeur pour initialiser une CaseCLanA vide.
     *
     * @param l ligne de la case sur le plateau
     * @param c colonne de la case sur le plateau
     */
    public CaseCLanA(int l, int c) {
        super(l, c);
    }

    /**
     * Retourne une représentation textuelle de la case, montrant l'entité si présente, sinon '*'.
     *
     * @return représentation textuelle de la case
     */
    @Override
    public String toString() {
        if (contenu != null) {
            return contenu.toString();
        }
        return "*";
    }
}
