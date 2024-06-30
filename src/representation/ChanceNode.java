package representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Classe ChanceNode représentant un nœud permettant de choisir aléatoirement parmi plusieurs options de nœuds suivants.
 * Étend la classe abstraite InnerNode et implémente la logique de choix aléatoire pour déterminer le prochain nœud à suivre.
 */
public class ChanceNode extends InnerNode {
    
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur d'un ChanceNode avec un ensemble de nœuds suivants spécifié.
     * 
     * @param nodesSuivant une HashMap contenant les nœuds suivants associés à ce nœud de chance
     */
    public ChanceNode(HashMap<String, Event> nodesSuivant) {
        super(nodesSuivant);
    }

    /**
     * Constructeur d'un ChanceNode avec nom et description spécifiés.
     * Initialise l'ensemble des nœuds suivants à une nouvelle HashMap.
     * 
     * @param nom le nom du nœud de chance
     * @param description la description associée au nœud de chance
     */
    public ChanceNode(String nom, String description) {
        super(nom, description);
    }

    /**
     * Affiche la description du nœud de chance en insérant des sauts de ligne pour une meilleure lisibilité.
     */
    @Override
    public void display() {
        System.out.println(insertLineBreaks(description, 150));
    }

    /**
     * Sélectionne de manière aléatoire le prochain nœud à suivre parmi les nœuds disponibles.
     * 
     * @return l'Event représentant le nœud choisi aléatoirement
     */
    @Override
    public Event chooseNext() {
        ArrayList<Event> nodeList = new ArrayList<>(nodesSuivant.values());
        ArrayList<String> repliqueList = new ArrayList<>(nodesSuivant.keySet());

        Random random = new Random();
        int indice = random.nextInt(nodeList.size());
        Event chosenNode = nodeList.get(indice);

        return chosenNode;
    }

    /**
     * Méthode non implémentée pour choisir le prochain nœud basé sur un choix spécifique.
     * 
     * @param choice le choix spécifique pour déterminer le nœud suivant
     * @return null car cette fonctionnalité n'est pas encore implémentée
     */
    @Override
    public Event chooseNext(String choice) {
        return null; // Non implémenté
    }

    /**
     * Méthode spécifique à ChanceNode pour choisir aléatoirement un nœud suivant.
     * 
     * @return l'Event représentant le nœud choisi aléatoirement
     */
    public Event chooseNext3() {
        if (this instanceof InnerNode) {
            ArrayList<Event> nodeList = new ArrayList<>(nodesSuivant.values());
            Random random = new Random();
            int indice = random.nextInt(nodeList.size());
            return nodeList.get(indice);
        }
        return null;
    }

    /**
     * Méthode non implémentée pour choisir le prochain nœud basé sur un deuxième choix spécifique.
     * 
     * @param choice le deuxième choix spécifique pour déterminer le nœud suivant
     * @return null car cette fonctionnalité n'est pas encore implémentée
     */
    @Override
    public Event chooseNext2(String choice) {
        return null; // Non implémenté
    }
}
