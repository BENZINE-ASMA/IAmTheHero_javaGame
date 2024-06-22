package Interface;

import java.io.IOException;

import entities.Personnage;
import entities.Sorcier;
import representation.Node;

public interface GameInterface<T extends Personnage> {
    void displayCurrentNode();
    Node chooseNextNode(String choice);
    void saveGame(String playerName) throws IOException;
    void loadGame(String playerName) throws IOException, ClassNotFoundException;
    T getPlayer();
    void setPlayer(T player);
}