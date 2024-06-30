package entities;

/**
 * Classe représentant un sorcier spirituel, un type de sorcier avec des pouvoirs sur l'esprit et l'âme.
 */
public class SorcierSpirituel extends Sorcier {
    private static final long serialVersionUID = 1L;

    /**
     * Nombre d'âmes collectées par le sorcier spirituel.
     */
    private int souls = 0;

    /**
     * Constructeur par défaut d'un sorcier spirituel.
     */
    public SorcierSpirituel() {
        super();
        this.name = "Sorcier Spirituel";
    }

    /**
     * Obtient le nombre d'âmes collectées par le sorcier spirituel.
     * 
     * @return le nombre d'âmes
     */
    public int getSouls() {
        return souls;
    }

    /**
     * Ajoute des âmes au sorcier spirituel.
     * 
     * @param souls le nombre d'âmes à ajouter
     */
    public void addSouls(int souls) {
        this.souls += souls;
    }

    /**
     * Utilise toutes les âmes collectées par le sorcier spirituel.
     */
    public void useSouls() {
        this.souls = 0;
    }

    /**
     * Affiche les informations sur le sorcier spirituel.
     */
    @Override
    public void afficherInfos() {
        System.out.println("Vous êtes un Sorcier Spirituel avec des pouvoirs sur l'esprit et l'âme.");
    }

    /**
     * Lance un sort sur une entité mobile cible. Si le sort est une explosion d'âme,
     * la puissance du sort est augmentée en fonction du nombre d'âmes collectées.
     * 
     * @param cible la cible du sort
     * @param sorc le sort à lancer
     */
    @Override
    public void lancerSort(EntiteMobile cible, Sort sorc) {
        System.out.println("Vous utilisez " + sorc.getNom() + "\n" + sorc.getDescription());

        int degatsSort = sorc.getDegats();

        if (Sort.EXPLOSION_D_AME.equals(sorc)) {
            System.out.println("Vous avez " + souls + " âmes pour rendre votre sort plus puissant que vous utilisez toutes.");
            degatsSort = 15 * souls;
            useSouls();
        }

        if (degatsSort != 0) {
            System.out.println(sorc.getNom() + " fait " + degatsSort + " points de dégât à votre adversaire");
        }
        if (sorc.getSoin() != 0) {
            System.out.println("Vous regagnez " + sorc.getSoin() + " points de vie.");
        }

        baisserMP(sorc.getCoutMana());
        cible.baisserPV(degatsSort);
        augmenterPVRestants(sorc.getSoin());
    }
}
