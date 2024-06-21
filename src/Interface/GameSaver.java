package Interface;

import java.io.Serializable;

import entities.Personnage;
import representation.Node;

public class GameSaver  implements Serializable{
	private static final long uuid = 1L;
	
	private Personnage personnage;
	private Node currentPlay;
	private Terrain terrain;
	
	public GameSaver (Personnage p, Node cp, Terrain t) {
		this.personnage =p;
		this.currentPlay =cp;
		this.terrain = t;
	}
	

    public Personnage getPersonnage() {
        return personnage;
    }

    public Node getCurrentPlay() {
        return currentPlay;
    }

    public Terrain getTerrain() {
        return terrain;
    }


}
