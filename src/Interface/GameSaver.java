package Interface;

import java.io.Serializable;

import entities.Personnage;
import representation.Event;
import representation.Node;

public class GameSaver<T extends Personnage> implements Serializable{
	private static final long uuid = 1L;
	
	private T personnage;
	private Event currentPlay;
	private Terrain terrain;
	
	public GameSaver (T p, Event cp, Terrain t) {
		this.personnage = p;
		this.currentPlay = cp;
		this.terrain = t;
	}
	

    public T getPersonnage() {
        return personnage;
    }

    public Event getCurrentPlay() {
        return currentPlay;
    }

    public Terrain getTerrain() {
        return terrain;
    }


}
