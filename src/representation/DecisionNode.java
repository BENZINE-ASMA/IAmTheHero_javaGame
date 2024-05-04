package representation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DecisionNode extends InnerNode {

	public DecisionNode(HashMap<String,Node> nodesSuivant) {
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
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	@Override
	
	public Node chooseNext() {
        Scanner sc = new Scanner(System.in);
        for (Node node : nodesSuivant.values()) {
            System.out.println(node.nom);
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
        //return nodesSuivant.values().;
    }
    */

}
