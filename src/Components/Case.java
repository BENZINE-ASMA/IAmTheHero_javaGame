package Components;

import java.io.Serializable;

public abstract class Case implements Serializable {
    private static final long serialVersionUID = 1L;
	public  final int lig, col;
	public Case(int l, int c) {
		this.lig = l;
		this.col = c;
	}
	public abstract boolean estLibre();
}
