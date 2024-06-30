package Components;

/**
 * Classe représentant une case de village sur le plateau.
 */
public class Village extends CaseTraversable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur pour initialiser un Village vide.
	 *
	 * @param l ligne de la case sur le plateau
	 * @param c colonne de la case sur le plateau
	 */
	public Village(int l, int c) {
		super(l, c);
	}
	
	/**
	 * Retourne une représentation textuelle de la case, montrant toujours '/'.
	 *
	 * @return représentation textuelle de la case
	 */
	@Override
	public String toString() {
		return "/";
	}
}
