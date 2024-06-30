package Components;

import java.io.Serializable;

/**
 * Classe abstraite représentant une case générique du plateau.
 */
public abstract class Case implements Serializable {
    private static final long serialVersionUID = 1L;
    public final int lig; // Ligne de la case
    public final int col; // Colonne de la case

    /**
     * Constructeur de la classe Case.
     *
     * @param l ligne de la case
     * @param c colonne de la case
     */
    public Case(int l, int c) {
        this.lig = l;
        this.col = c;
    }

    /**
     * Méthode abstraite pour vérifier si la case est libre.
     *
     * @return true si la case est libre, false sinon
     */
    public abstract boolean estLibre();
}
