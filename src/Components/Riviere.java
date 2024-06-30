package Components;

/**
 * Classe représentant une case de rivière sur le plateau.
 */
public class Riviere extends CaseTraversable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur pour initialiser une Rivière vide.
	 *
	 * @param l ligne de la case sur le plateau
	 * @param c colonne de la case sur le plateau
	 */
	public Riviere(int l, int c) {
		super(l, c);
	}
	
	/**
	 * Retourne une représentation textuelle de la case, montrant toujours '@'.
	 *
	 * @return représentation textuelle de la case
	 */
	@Override
	public String toString() {
		return "@";
	}

}
