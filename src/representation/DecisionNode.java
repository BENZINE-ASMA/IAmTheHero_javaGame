package representation;
import java.util.ArrayList;
import java.util.Scanner;

public class DecisionNode extends InnerNode {

	public DecisionNode(ArrayList<Node> nodesSuivant) {
		super(nodesSuivant);
		
	}
	public DecisionNode(String nom, String description) {
		super(nom, description);
		
	}
	@Override
	public void display() {
		System.out.println(description);
	}
	
	@Override
	
	public Node chooseNext() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < nodesSuivant.size(); i++) {
            System.out.println((i + 1) + ": " + nodesSuivant.get(i).nom);
        }

        int choix;
        while (true) {
            System.out.print("Choisissez une option : ");
            choix = sc.nextInt();
            if (choix > 0 && choix <= nodesSuivant.size()) {
                break;
            } else {
                System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
        return nodesSuivant.get(choix - 1);
    }

}
