/**
 * L'interface Event définit les méthodes nécessaires pour gérer un événement dans un système de représentation.
 * Un événement peut être affiché, choisir le prochain événement à exécuter, et permet d'obtenir et de définir ses attributs.
 * Elle inclut également des méthodes pour travailler avec le joueur associé et les nœuds suivants de l'événement.
 */
package representation;

import java.util.HashMap;
import java.util.Set;

import entities.Personnage;

public interface Event {
    
    /**
     * Affiche cet événement.
     */
    public void display();
    
    /**
     * Insère des sauts de ligne dans le texte pour garantir que chaque ligne ne dépasse pas la longueur maximale spécifiée.
     * 
     * @param text le texte à formater
     * @param maxLength la longueur maximale de chaque ligne
     * @return le texte formaté avec des sauts de ligne
     */
    public String insertLineBreaks(String text, int maxLength);
    
    /**
     * Choisis le prochain événement à exécuter par défaut.
     * 
     * @return le prochain événement à exécuter
     */
    public Event chooseNext();
    
    /**
     * Choisis le prochain événement à exécuter en fonction du choix spécifié.
     * 
     * @param choice le choix spécifié
     * @return le prochain événement à exécuter en fonction du choix
     */
    public Event chooseNext(String choice);
    
    /**
     * Variante de chooseNext(String choice) avec un nom différent pour une utilisation spécifique.
     * 
     * @param choice le choix spécifié
     * @return le prochain événement à exécuter en fonction du choix
     */
    public Event chooseNext2(String choice);
    
    /**
     * Récupère l'identifiant de cet événement.
     * 
     * @return l'identifiant de cet événement
     */
    public int getId();
    
    /**
     * Récupère le nom de cet événement.
     * 
     * @return le nom de cet événement
     */
    public String getNom();
    
    /**
     * Récupère la description de cet événement.
     * 
     * @return la description de cet événement
     */
    public String getDescription();
    
    /**
     * Définit la description de cet événement.
     * 
     * @param description la nouvelle description de cet événement
     */
    public void setDescription(String description);
    
    /**
     * Définit le nom de cet événement.
     * 
     * @param nom le nouveau nom de cet événement
     */
    public void setNom(String nom);
    
    /**
     * Récupère le nœud associé à cet événement, sans le décorateur.
     * 
     * @return le nœud associé à cet événement
     */
    public Node getNode();
    
    /**
     * Récupère le joueur associé à cet événement.
     * 
     * @return le joueur associé à cet événement
     */
    public Personnage getJoueur();
    
    /**
     * Définit le joueur associé à cet événement.
     * 
     * @param joueur le nouveau joueur associé à cet événement
     */
    public void setJoueur(Personnage joueur);
    
    /**
     * Ajoute un événement suivant avec une réplique spécifiée.
     * 
     * @param replique la réplique à associer à l'événement suivant
     * @param toAdd l'événement suivant à ajouter
     */
    public void addToNodeSuivant(String replique, Event toAdd);
    
    /**
     * Récupère tous les événements suivants associés à cet événement sous forme de map.
     * 
     * @return une map contenant tous les événements suivants associés à cet événement
     */
    public HashMap<String, Event> getNodesSuivant();
    
    /**
     * Récupère les différents types par lesquels le nœud de cet événement passe.
     * 
     * @return un ensemble contenant les différents types par lesquels passe le nœud de cet événement
     */
    public Set<Class<?>> getTypes();
    
    /**
     * Ajoute de nouveaux types à ceux déjà associés à cet événement.
     * 
     * @param newTypes les nouveaux types à ajouter
     */
    public void addTypes(Set<Class<?>> newTypes);
}
