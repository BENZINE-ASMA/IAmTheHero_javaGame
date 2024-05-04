import java.util.Map;
import java.util.Map.Entry;

import entities.Direction;

import java.util.Scanner;

import representation.ChanceNode;
import representation.DecisionNode;
import representation.InnerNode;
import representation.Node;
import representation.NodesGraph;

public class JeuMain {
	public static void main(String[] args) {
		
		Terrain terrain = new Terrain("C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\terrain1.txt");
		Scanner sc = new Scanner(System.in);
		
		NodesGraph graph = new NodesGraph();
		
		graph.addDecisionNode("introduction", "Bonjour et bienvenue à l'auberge de la ville ! Vous pouvez tout faire ici, acheter armes, potions, et même rejoindre les clans de notre contrée ! Je ne vous ai jamais vu ici avant, que puis-je pour vous ?");
		graph.addDecisionNode("clanB", "te s dans le clan B");
		graph.addDecisionNode("clanA", " tes dans le clan A");
		graph.addChanceNode("death", "game over");
		
		//add sommet
		//graph.addArc(null, null, null);
		graph.addArc("introduction", "clanA","bonjour je veux caln A");
		graph.addArc("introduction", "death","je veux mourrir");
		graph.addArc("clanA", "death","je veux mourrir");
		graph.addArc("clanA", "clanB","je change clan");
		
		for (Entry<String, Node> entry : graph.getGraph().entrySet()) {
			if (entry.getValue() instanceof InnerNode) {
				System.out.print(entry.getKey() + "   ");
				((InnerNode)entry.getValue()).testDisplay();
				System.out.println("//////////");
				
				
			}
		}
		/*
		InnerNode currentPlay =  (DecisionNode) graph.getGraph().get("introduction");
		while (true) {
			currentPlay.display();
			InnerNode nextNode = (InnerNode) currentPlay.chooseNext();
			/*
			if (nextNode instanceof DecisionNode) {
				currentPlay =(DecisionNode) nextNode;
				
			}else if(nextNode instanceof ChanceNode) {
				currentPlay =(ChanceNode) nextNode;
				
			}
			else {
				break;
			}*/

		/*
		while (true) {
			terrain.affiche();
			
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
	
		}*/
	


}
}
