package entities;

/**
 * Classe représentant un sorcier élémentaire, un type de sorcier avec des affinités pour un élément spécifique.
 */
public class SorcierElement extends Sorcier {
    private static final long serialVersionUID = 1L;

    /**
     * L'élément d'affinité du sorcier (terre, eau, feu, air).
     */
    private String element;

    /**
     * Constructeur par défaut d'un sorcier élémentaire.
     */
    public SorcierElement() {
        super();
        this.name = "Sorcier des Éléments";
    }

    /**
     * Affiche les informations sur le sorcier élémentaire.
     */
    @Override
    public void afficherInfos() {
        System.out.println("Vous êtes un Sorcier des Éléments avec des pouvoirs sur la terre, l'eau, le feu et l'air.");
    }

    /**
     * Obtient l'élément d'affinité du sorcier.
     * 
     * @return l'élément d'affinité
     */
    public String getElement() {
        return element;
    }

    /**
     * Définit l'élément d'affinité du sorcier.
     * 
     * @param element le nouvel élément d'affinité
     */
    public void setElement(String element) {
        this.element = element;
    }

    /**
     * Lance un sort sur une entité mobile cible. Si le sort est de l'élément d'affinité du sorcier,
     * un bonus de puissance est appliqué.
     * 
     * @param cible la cible du sort
     * @param sorc le sort à lancer
     */
    @Override
    public void lancerSort(EntiteMobile cible, Sort sorc) {
        System.out.println("Vous utilisez " + sorc.getNom() + "\n" + sorc.getDescription());

        int degatSort = sorc.getDegats();

        if (element.equals(sorc.getType())) {
            System.out.println("Vous utilisez un sort de votre élément d'affinité. Vous avez un bonus de puissance.");
            degatSort = (int) (sorc.getDegats() * 1.5);
        }

        if (degatSort != 0) {
            System.out.println(sorc.getNom() + " fait " + degatSort + " points de dégât à votre adversaire");
        }
        if (sorc.getSoin() != 0) {
            System.out.println("Vous regagnez " + sorc.getSoin() + " points de vie.");
        }

        baisserMP(sorc.getCoutMana());
        cible.baisserPV(degatSort);
        augmenterPVRestants(sorc.getSoin());
    }
}
