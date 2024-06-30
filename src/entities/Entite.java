package entities;

/**
 * Classe abstraite représentant une entité avec un nom.
 */
public abstract class Entite {
    /**
     * Le nom de l'entité.
     */
    public String name;
    
    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'entité.
     * 
     * @return une chaîne de caractères représentant l'entité
     */
    public abstract String toString();

    /**
     * Obtient le nom de l'entité.
     * 
     * @return le nom de l'entité
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom de l'entité.
     * 
     * @param name le nouveau nom de l'entité
     */
    public void setName(String name) {
        this.name = name;
    }
}
