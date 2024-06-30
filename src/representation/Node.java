package representation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import entities.Personnage;

/**
 * Classe abstraite Node représentant un nœud dans un système de représentation d'événements.
 * Implémente l'interface Event pour fournir des méthodes de base pour gérer un événement.
 */
public abstract class Node implements Serializable, Event {
    private static final long serialVersionUID = 1L;
    
    protected static int cpt = 0;
    protected int id;
    protected String nom;
    protected String description;
    protected Personnage joueur;
    protected HashMap<String, Event> nodesSuivant;
    private Set<Class<?>> types = new HashSet<>();

    /**
     * Constructeur par défaut d'un Node.
     * Incrémente le compteur cpt pour attribuer un identifiant unique à chaque instance.
     * Initialise les attributs avec des valeurs par défaut.
     */
    public Node() {
        cpt++;
        this.id = cpt;
        this.nom = "";
        this.description = "";
        this.nodesSuivant = new HashMap<>();
        types.add(this.getClass());
    }

    /**
     * Constructeur d'un Node avec nom et description spécifiés.
     * Incrémente le compteur cpt pour attribuer un identifiant unique à chaque instance.
     * Initialise les attributs avec les valeurs spécifiées.
     * 
     * @param nom le nom du nœud
     * @param description la description du nœud
     */
    public Node(String nom, String description) {
        cpt++;
        this.id = cpt;
        this.nom = nom;
        this.description = description;
        this.nodesSuivant = new HashMap<>();
        types.add(this.getClass());
    }

    @Override
    public Set<Class<?>> getTypes() {
        return types;
    }

    @Override
    public void addTypes(Set<Class<?>> newTypes) {
        types.addAll(newTypes);
    }

    /**
     * Récupère le joueur associé à ce nœud.
     * 
     * @return le joueur associé à ce nœud
     */
    public Personnage getJoueur() {
        return joueur;
    }

    /**
     * Définit le joueur associé à ce nœud.
     * 
     * @param joueur le joueur à associer à ce nœud
     */
    public void setJoueur(Personnage joueur) {
        this.joueur = joueur;
    }

    /**
     * Récupère l'identifiant unique de ce nœud.
     * 
     * @return l'identifiant de ce nœud
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère le nom de ce nœud.
     * 
     * @return le nom de ce nœud
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de ce nœud.
     * 
     * @param nom le nouveau nom de ce nœud
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la description de ce nœud.
     * 
     * @return la description de ce nœud
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description de ce nœud.
     * 
     * @param description la nouvelle description de ce nœud
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Récupère une référence à ce nœud lui-même.
     * 
     * @return une référence à ce nœud
     */
    public Node getNode() {
        return this;
    }

    /**
     * Récupère tous les nœuds suivants associés à ce nœud sous forme de map.
     * 
     * @return une map contenant tous les nœuds suivants associés à ce nœud
     */
    public HashMap<String, Event> getNodesSuivant() {
        return this.nodesSuivant;
    }

    /**
     * Ajoute un événement suivant avec une réplique spécifiée à ce nœud.
     * 
     * @param replique la réplique associée à l'événement suivant
     * @param toAdd l'événement suivant à ajouter
     */
    public void addToNodeSuivant(String replique, Event toAdd) {
        this.nodesSuivant.put(replique, toAdd);
    }

    /**
     * Insère des sauts de ligne dans le texte pour garantir que chaque ligne ne dépasse pas la longueur maximale spécifiée.
     * 
     * @param text le texte à formater
     * @param maxLength la longueur maximale de chaque ligne
     * @return le texte formaté avec des sauts de ligne
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

    /**
     * Méthode abstraite pour afficher cet événement ou nœud.
     * Chaque classe concrète de Node doit implémenter cette méthode.
     */
    public abstract void display();

    /**
     * Méthode abstraite pour choisir le prochain événement à exécuter en fonction d'un choix spécifié.
     * Chaque classe concrète de Node doit implémenter cette méthode.
     * 
     * @param choice le choix spécifié pour déterminer le prochain événement
     * @return le prochain événement à exécuter en fonction du choix
     */
    public abstract Event chooseNext(String choice);

    /**
     * Méthode abstraite pour choisir le prochain événement à exécuter par défaut.
     * Chaque classe concrète de Node doit implémenter cette méthode.
     * 
     * @return le prochain événement à exécuter par défaut
     */
    public abstract Event chooseNext();

    /**
     * Méthode abstraite pour choisir le prochain événement à exécuter avec une deuxième méthode de choix.
     * Ce choix est spécifique à certaines implémentations de Node.
     * Chaque classe concrète de Node doit implémenter cette méthode.
     * 
     * @param choice le choix spécifié pour déterminer le prochain événement
     * @return le prochain événement à exécuter en fonction du choix
     */
    public abstract Event chooseNext2(String choice);
}
