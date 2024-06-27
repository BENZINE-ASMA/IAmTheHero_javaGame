import entities.Personnage;
import entities.SorcierElement;
import entities.SorcierSpirituel;
import entities.Humain;
import representation.NodesGraph;
import representation.CombatNode;
import representation.Event;
import representation.TerminalNode;
import java.util.Scanner;

public class JeuMain {

    public static void majCombatNode(Personnage p, String nodeName, NodesGraph graph) {
        CombatNode cn = (CombatNode)((graph.getGraph()).get(nodeName));
        cn.setJoueur(p);
    }

    public static void main(String[] args) {
        NodesGraph graph = JeuConsole.creerJeu();
        Personnage p = null;
        Scanner sc = new Scanner(System.in);
        
        Event currentPlay = graph.getGraph().get("introduction");
        currentPlay.display();

        Event nextNode = currentPlay.chooseNext();
        currentPlay = nextNode;
        currentPlay.display();

        if (currentPlay.getNom().equals("rejoindre") || currentPlay.getNom().equals("explication")) {
            nextNode = currentPlay.chooseNext();
            currentPlay = nextNode;

            switch (currentPlay.getNom()) {
                case "clanElement": p = new SorcierElement(); break;
                case "clanEnchanteur": p = new SorcierSpirituel(); break;
                case "humain": p = new Humain(); break;
            }
        }

        // Mise à jour des nœuds avec le joueur choisi
        for (Event node : graph.getGraph().values()) {
            node.setJoueur(p);
        }

        while (true) {
            currentPlay.display();
            nextNode = currentPlay.chooseNext();
            currentPlay = nextNode;

            if (nextNode instanceof TerminalNode) {
                currentPlay.display();
                break;
            }
        }
    }
}
