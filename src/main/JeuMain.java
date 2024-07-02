package main;
import entities.Personnage;
import entities.SorcierElement;
import entities.SorcierSpirituel;
import entities.Humain;
import representation.NodesGraph;
import representation.CombatNode;
import representation.Event;
import representation.TerminalNode;
import java.util.Scanner;

/**
 * Classe principale représentant le point d'entrée du jeu.
 */
public class JeuMain {


    /**
     * Méthode principale exécutant le jeu.
     *
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        NodesGraph graph = JeuConsole.creerJeu(); // Initialisation du graphe de jeu
        Personnage p = null; // Initialisation du personnage du joueur à null
        Scanner sc = new Scanner(System.in); // Initialisation du scanner pour la saisie utilisateur
        
        Event currentPlay = graph.getGraph().get("introduction"); // Récupération du premier événement à jouer
        currentPlay.display(); // Affichage de l'événement actuel

        Event nextNode = currentPlay.chooseNext(); // Sélection du prochain noeud à explorer
        currentPlay = nextNode; // Passage à l'événement suivant
        currentPlay.display(); // Affichage de l'événement actuel

        // Sélection du type de personnage en fonction du choix de l'utilisateur
        if (currentPlay.getNom().equals("rejoindre") || currentPlay.getNom().equals("explication")) {
            nextNode = currentPlay.chooseNext(); // Sélection du prochain noeud à explorer après le choix
            currentPlay = nextNode; // Passage à l'événement suivant

            switch (currentPlay.getNom()) {
                case "clanElement": p = new SorcierElement(); break; // Création d'un sorcier élémentaliste
                case "clanEnchanteur": p = new SorcierSpirituel(); break; // Création d'un sorcier spirituel
                case "humain": p = new Humain(); break; // Création d'un personnage humain
            }
        }

        // Mise à jour des nœuds avec le joueur choisi
        for (Event node : graph.getGraph().values()) {
            node.setJoueur(p);
        }

        // Boucle principale du jeu
        while (true) {
            currentPlay.display(); // Affichage de l'événement actuel
            nextNode = currentPlay.chooseNext(); // Sélection du prochain noeud à explorer
            currentPlay = nextNode; // Passage à l'événement suivant

            if (nextNode instanceof TerminalNode) { // Vérification si le noeud est terminal
                currentPlay.display(); // Affichage du dernier événement
                break; // Sortie de la boucle
            }
        }
    }
}
