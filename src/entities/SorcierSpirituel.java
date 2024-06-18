package entities;
public class SorcierSpirituel extends Sorcier {
	private int souls=0;
	
    public SorcierSpirituel() {
        super();
        this.name = "Sorcier Spirituel";
    }
    
    

    public int getSouls() {
		return souls;
	}


	public void addSouls(int souls) {
		this.souls += souls;
	}
	
	public void useSouls() {
		this.souls = 0;
	}



	@Override
    public void afficherInfos() {
        System.out.println("Vous êtes un Sorcier Spirituel avec des pouvoirs sur l'esprit et l'âme.");
    }
	
	@Override
	public void lancerSort(EntiteMobile cible, Sort sorc) { 
		System.out.println("Vous utilisez " + sorc.getNom() + "\n" + sorc.getDescription());
	
		int degatsSort = sorc.getDegats();
		
		if (Sort.EXPLOSION_D_AME.equals(sorc)) {
			System.out.println("Vous avez " + souls + " âmes pour rendre votre sort plus puissant que vous utilisez toutes.");
			degatsSort = 15 * souls;
			useSouls();
			
		}
	
		if (degatsSort != 0)	{
			System.out.println(sorc.getNom() + " fait " + degatsSort + " points de dégat à votre adversaire");
		}
		if (sorc.getSoin() != 0) {
			System.out.println("Vous regagnez " + sorc.getSoin() + " points de vie.");
		}
		
		baisserMP(sorc.getCoutMana());
		cible.baisserPV(degatsSort);
		augmenterPVRestants(sorc.getSoin());
	}
}