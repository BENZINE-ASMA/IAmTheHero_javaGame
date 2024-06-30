package representation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import Interface.PanelComponent;
import entities.Personnage;

/**
 * Classe abstraite représentant un décorateur de nœud pour enrichir les fonctionnalités d'un nœud existant.
 * Implémente l'interface Event et Serializable pour la gestion des événements et la sérialisation.
 */
public abstract class NodeDecorator implements Event, Serializable {
    protected Event decoratedNode;
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur permettant de décorer un nœud existant avec de nouvelles fonctionnalités.
     *
     * @param decoratedNode le nœud à décorer
     */
    public NodeDecorator(Event decoratedNode) {
        this.decoratedNode = decoratedNode;
        this.decoratedNode.addTypes(Set.of(this.getClass()));
    }

    /**
     * Méthode abstraite pour exécuter des actions spécifiques liées au décorateur.
     *
     * @return un entier représentant un résultat spécifique au décorateur
     */
    public abstract int playDecorator();

    /**
     * Méthode abstraite pour exécuter des actions spécifiques liées au décorateur avec interaction graphique.
     *
     * @param panel le composant de panneau à utiliser pour l'interaction
     * @param cpt   un compteur d'images
     * @return un entier représentant un résultat spécifique au décorateur
     */
    public abstract int playDecorator(PanelComponent panel, int cpt);

    /**
     * Affiche le nœud décoré en appelant la méthode display() du nœud décoré.
     */
    @Override
    public void display() {
        decoratedNode.display();
    }

    /**
     * Retourne l'ensemble des types associés au nœud décoré.
     *
     * @return un ensemble de classes représentant les types du nœud décoré
     */
    @Override
    public Set<Class<?>> getTypes() {
        return decoratedNode.getTypes();
    }

    /**
     * Ajoute de nouveaux types au nœud décoré.
     *
     * @param newTypes un ensemble de nouvelles classes à ajouter comme types au nœud décoré
     */
    @Override
    public void addTypes(Set<Class<?>> newTypes) {
        decoratedNode.addTypes(newTypes);
    }

    /**
     * Permet de choisir le prochain événement à exécuter à partir du nœud décoré.
     *
     * @return l'événement suivant à exécuter
     */
    @Override
    public Event chooseNext() {
        return decoratedNode.chooseNext();
    }

    /**
     * Permet de choisir le prochain événement à exécuter à partir du nœud décoré en fonction d'un choix spécifique.
     *
     * @param choice le choix spécifique pour déterminer l'événement suivant
     * @return l'événement suivant à exécuter en fonction du choix donné
     */
    @Override
    public Event chooseNext(String choice) {
        return decoratedNode.chooseNext(choice);
    }

    /**
     * Permet de choisir le prochain événement à exécuter à partir du nœud décoré en fonction d'un choix spécifique.
     *
     * @param choice le choix spécifique pour déterminer l'événement suivant
     * @return l'événement suivant à exécuter en fonction du choix donné
     */
    public Event chooseNext2(String choice) {
        return decoratedNode.chooseNext2(choice);
    }

    /**
     * Obtient le nœud sous-jacent du décorateur.
     *
     * @return le nœud sous-jacent du décorateur
     */
    public Node getNode() {
        return decoratedNode.getNode();
    }

    /**
     * Obtient le nœud décoré.
     *
     * @return le nœud décoré
     */
    public Event getDecoratedNode() {
        return decoratedNode;
    }

    /**
     * Obtient l'identifiant du nœud décoré.
     *
     * @return l'identifiant du nœud décoré
     */
    public int getId() {
        return decoratedNode.getNode().getId();
    }

    /**
     * Obtient le nom du nœud décoré.
     *
     * @return le nom du nœud décoré
     */
    public String getNom() {
        return decoratedNode.getNode().getNom();
    }

    /**
     * Obtient la description du nœud décoré.
     *
     * @return la description du nœud décoré
     */
    public String getDescription() {
        return decoratedNode.getNode().getDescription();
    }

    /**
     * Définit la description du nœud décoré.
     *
     * @param description la nouvelle description à définir pour le nœud décoré
     */
    public void setDescription(String description) {
        decoratedNode.getNode().setDescription(description);
    }

    /**
     * Définit le nom du nœud décoré.
     *
     * @param nom le nouveau nom à définir pour le nœud décoré
     */
    public void setNom(String nom) {
        decoratedNode.getNode().setNom(nom);
    }

    /**
     * Obtient le personnage joueur associé au nœud décoré.
     *
     * @return le personnage joueur associé au nœud décoré
     */
    public Personnage getJoueur() {
        return decoratedNode.getNode().joueur;
    }

    /**
     * Définit le personnage joueur associé au nœud décoré.
     *
     * @param joueur le nouveau personnage joueur à associer au nœud décoré
     */
    public void setJoueur(Personnage joueur) {
        decoratedNode.getNode().joueur = joueur;
    }

    /**
     * Ajoute un nouvel événement au nœud suivant du nœud décoré avec une nouvelle réplique.
     *
     * @param replique la réplique à ajouter comme clé pour l'événement à ajouter
     * @param toAdd    l'événement à ajouter comme valeur associée à la réplique
     */
    public void addToNodeSuivant(String replique, Event toAdd) {
        decoratedNode.getNode().nodesSuivant.put(replique, toAdd);
    }

    /**
     * Obtient le HashMap des nœuds suivants du nœud décoré.
     *
     * @return le HashMap des nœuds suivants du nœud décoré
     */
    public HashMap<String, Event> getNodesSuivant() {
        return decoratedNode.getNode().nodesSuivant;
    }

    /**
     * Affiche des informations supplémentaires spécifiques au décorateur.
     */
    public abstract void display2();

    /**
     * Insère des sauts de ligne dans une chaîne de texte pour formater correctement l'affichage.
     *
     * @param text      le texte à formater avec des sauts de ligne
     * @param maxLength la longueur maximale avant d'insérer un saut de ligne
     * @return le texte formaté avec des sauts de ligne insérés
     */
    public String insertLineBreaks(String text, int maxLength) {
        StringBuilder formattedText = new StringBuilder();
        int length = text.length();
        int start = 0;

        while (start < length) {
            int end = Math.min(start + maxLength, length);
            if (end < length) {
                // Si nous ne sommes pas à la fin du texte, trouvons le dernier espace avant la limite
                int lastSpace = text.lastIndexOf(' ', end);
                if (lastSpace > start) {
                    end = lastSpace;
                }
            }
            // Ajouter le segment de texte au résultat
            formattedText.append(text, start, end);
            // Ajouter un saut de ligne s'il ne s'agit pas de la fin du texte
            if (end < length) {
                formattedText.append("\n");
            }
            start = end + 1; // Recommencer après l'espace (ou après la limite si aucun espace trouvé)
        }

        return formattedText.toString();
    }
}
