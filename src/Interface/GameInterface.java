package Interface;

import java.io.IOException;

import entities.Personnage;
import representation.Node;

public interface GameInterface {
    void displayCurrentNode();
    Node chooseNextNode(String choice);
    void saveGame(String playerName) throws IOException;
    void loadGame(String playerName) throws IOException, ClassNotFoundException;
    Personnage getPlayer();
    void setPlayer(Personnage player);
}