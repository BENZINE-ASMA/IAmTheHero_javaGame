package Interface;

import java.io.Serializable;

import entities.Personnage;
import representation.Node;

public class GameSaver<T extends Personnage> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private T personnage;
	private Node currentPlay;
	private Terrain terrain;
	
	public GameSaver (T p, Node cp, Terrain t) {
		this.personnage = p;
		this.currentPlay =cp;
		this.terrain = t;
	}
	

    public T getPersonnage() {
        return personnage;
    }

    public Node getCurrentPlay() {
        return currentPlay;
    }

    public Terrain getTerrain() {
        return terrain;
    }


}
