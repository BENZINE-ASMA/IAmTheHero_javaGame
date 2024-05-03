import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import representation.ChanceNode;
import representation.DecisionNode;
import representation.InnerNode;
import representation.Node;
import representation.NodesTree;

public class JeuMain {
	public static void main(String[] args) {
		
		Terrain terrain = new Terrain("C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\terrain1.txt");
		Scanner sc = new Scanner(System.in);
		
		NodesTree tree = new NodesTree();
		tree.addDecisionNode("intro", "hi tu chosisis");
		tree.addDecisionNode("clanB", "te s dans le clan B");
		tree.addDecisionNode("clanA", " tes dans le clan A");
		tree.addChanceNode("death", "game over");
		
		tree.addArc("intro", "clanA");
		tree.addArc("intro", "death");
		tree.addArc("clanA", "death");
		tree.addArc("clanA", "clanB");
		
		for (Entry<String, Node> entry : tree.getTree().entrySet()) {
			if (entry.getValue() instanceof InnerNode) {
				System.out.print(entry.getKey() + "   ");
				((InnerNode)entry.getValue()).testDisplay();
				System.out.println("//////////");
				
				
			}
		}
		
		InnerNode currentPlay =  (DecisionNode) tree.getTree().get("intro");
		while (true) {
			currentPlay.display();
			InnerNode nextNode = (InnerNode) currentPlay.chooseNext();
			if (nextNode instanceof DecisionNode) {
				currentPlay =(DecisionNode) nextNode;
				
			}else if(nextNode instanceof ChanceNode) {
				currentPlay =(ChanceNode) nextNode;
				
			}
			else {
				break;
			}
		}
		//while (true) {
		//	terrain.affiche();
			/*
			String input = sc.nextLine().toLowerCase();
			Direction direction = switch (input) {
			case "n" -> Direction.nord;
			case "s" -> Direction.sud;
			case "e" -> Direction.est;
			case "o" -> Direction.ouest;
			default -> null;
			};
			System.out.println(direction);
			
			if (direction != null) { terrain.movePlayer(direction);}
	
	*/
	
	//}

}
}
