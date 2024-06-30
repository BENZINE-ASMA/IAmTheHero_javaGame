package Interface;

import java.io.IOException;

import entities.Personnage;
import representation.Event;

/**
 * Interface définissant les méthodes pour gérer un jeu.
 *
 * @param <T> Type générique représentant le joueur (doit étendre Personnage)
 */
public interface GameInterface<T extends Personnage> {

    /**
     * Affiche le noeud actuel du jeu.
     */
    void displayCurrentNode();

    /**
     * Permet au joueur de choisir le prochain noeud en fonction de l'option choisie.
     *
     * @param choice l'option choisie par le joueur
     * @return l'événement associé au choix fait par le joueur
     */
    Event chooseNextNode(String choice);

    /**
     * Sauvegarde l'état actuel du jeu pour le joueur donné.
     *
     * @param playerName le nom du joueur pour lequel sauvegarder la partie
     * @throws IOException si une erreur d'entrée/sortie survient lors de la sauvegarde
     */
    void saveGame(String playerName) throws IOException;

    /**
     * Charge une partie précédemment sauvegardée pour le joueur donné.
     *
     * @param playerName le nom du joueur pour lequel charger la partie
     * @throws IOException si une erreur d'entrée/sortie survient lors du chargement
     * @throws ClassNotFoundException si la classe du joueur chargé n'est pas trouvée
     */
    void loadGame(String playerName) throws IOException, ClassNotFoundException;

    /**
     * Récupère le joueur actuel du jeu.
     *
     * @return le joueur actuel
     */
    T getPlayer();

    /**
     * Définit le joueur actuel du jeu.
     *
     * @param player le joueur à définir
     */
    void setPlayer(T player);
}
