package entities;

public class SorcierElement extends Sorcier {
    private String element;
    private static final long serialVersionUID = 1L;
	
	public SorcierElement() {
        super();
        this.name = "Sorcier des Éléments";
    }

    @Override
    public void afficherInfos() {
        System.out.println("Vous êtes un Sorcier des Éléments avec des pouvoirs sur la terre, l'eau, le feu et l'air.");
    }

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}
	
	
	@Override
	public void lancerSort(EntiteMobile cible, Sort sorc) { // je n'ai honnetement aucune idée du type de la méthode
		System.out.println("Vous utilisez " + sorc.getNom() + "\n" + sorc.getDescription());
		
		int degatSort = sorc.getDegats();
		
		if (element.equals(sorc.getType())){
			System.out.println("Vous utilisez un sort de votre élément d'affinité. Vous avez un bonus de puissance.");
			degatSort = (int) (sorc.getDegats() * 1.5);
		}

		if (degatSort != 0)	{
			System.out.println(sorc.getNom() + " fait " + degatSort + " points de dégat à votre adversaire");
		}
		if (sorc.getSoin() != 0) {
			System.out.println("Vous regagnez " + sorc.getSoin() + " points de vie.");
		}
		
		baisserMP(sorc.getCoutMana());
		cible.baisserPV(degatSort);
		augmenterPVRestants(sorc.getSoin());
	}
    
    
    
}
