package Interface;

import java.io.Serializable;

import entities.Personnage;
import representation.Event;

/**
 * Cette classe représente un objet de sauvegarde de jeu, contenant des informations
 * sur le personnage, le tour de jeu actuel et le terrain.
 *
 * @param <T> le type du personnage à sauvegarder
 */
public class GameSaver<T extends Personnage> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T personnage;
    private Event currentPlay;
    private Terrain terrain;

    /**
     * Constructeur de la classe GameSaver.
     *
     * @param p le personnage à sauvegarder
     * @param cp le tour de jeu actuel
     * @param t le terrain de jeu
     */
    public GameSaver(T p, Event cp, Terrain t) {
        this.personnage = p;
        this.currentPlay = cp;
        this.terrain = t;
    }

    /**
     * Retourne le personnage sauvegardé.
     *
     * @return le personnage sauvegardé
     */
    public T getPersonnage() {
        return personnage;
    }

    /**
     * Retourne le tour de jeu actuel sauvegardé.
     *
     * @return le tour de jeu actuel
     */
    public Event getCurrentPlay() {
        return currentPlay;
    }

    /**
     * Retourne le terrain sauvegardé.
     *
     * @return le terrain sauvegardé
     */
    public Terrain getTerrain() {
        return terrain;
    }
}
