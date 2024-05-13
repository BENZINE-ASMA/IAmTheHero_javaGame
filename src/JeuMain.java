import java.util.Map;
import java.util.Map.Entry;

import entities.Direction;
import entities.Humain;
import entities.Personnage;
import entities.SorcierElement;
import entities.SorcierSpirituel;

import java.util.Scanner;

import representation.ChanceNode;
import representation.DecisionNode;
import representation.InnerNode;
import representation.Node;
import representation.NodesGraph;
import representation.TerminalNode;


public class JeuMain {
	public static String test() {
		return "test";
	}
	public static void main(String[] args) {
		Terrain terrain;
		Personnage p;
		
		Scanner sc = new Scanner(System.in);
		
		NodesGraph graph = new NodesGraph();
		
		graph.addDecisionNode("introduction", "Bonjour et bienvenue à l'auberge de la ville ! Vous pouvez tout faire ici, acheter armes, potions, et même rejoindre les clans de notre contrée ! Je ne vous ai jamais vu ici avant, que puis-je pour vous ?");
		graph.addDecisionNode("explication", "[explications du lore] quel type de sorcier êtes vous ?");
		graph.addDecisionNode("rejoindre", "Quel type de sorcier êtes vous?");
		graph.addDecisionNode("clanB", "Vous êtes désormais dans le clan B");
		graph.addDecisionNode("clanA", "Vous êtes désormais dans le clan A");
		graph.addDecisionNode("humain", "quelle est votre compétence?");
		graph.addDecisionNode("humainAvecCompetence","vous avz comp de " );
		
		graph.addTerminalNode("death", "game over");
		
		//graph.addTerminalNode("mort", "c'est la fin");
		//Est-ce qu'on peut passer en attribut une fonction ?
		//add sommet
		//graph.addArc(null, null, null);
		graph.addArc("introduction", "explication","Est-ce que vous pouvez m'expliquer en quoi consistent les clans?");
		graph.addArc("introduction", "rejoindre","Je suis ici pour rejoindre un clan");
		graph.addArc("explication", "clanA","Je suis un sorcier des éléments");
		graph.addArc("explication", "clanB","Je suis un sorcier spirituel");
		graph.addArc("explication", "humain","Je suis juste un humain...");
		graph.addArc("rejoindre", "clanA","Je suis un sorcier des éléments");
		graph.addArc("rejoindre", "clanB","Je suis un sorcier spirituel");
		graph.addArc("rejoindre", "humain","Je suis un simple humain.");
		graph.addArc("humain", "death", "Je veux finir");
		graph.addArc("clanA", "death", "Je veux finir");
		graph.addArc("clanB", "death", "Je veux finir");
		graph.addArc("humain", "humainAvecCompetence", "je suis ingénieur");
		graph.addArc("humain", "humainAvecCompetence", "je suis combattant");
		graph.addArc("humain", "humainAvecCompetence", "je suis persuasif");
		
		for (Entry<String, Node> entry : graph.getGraph().entrySet()) {
			if (entry.getValue() instanceof InnerNode) {
				System.out.print(entry.getKey() + "   ");
				((InnerNode)entry.getValue()).testDisplay();
				System.out.println("//////////");
				
				
			}
		}
		
		Node currentPlay =  (DecisionNode) graph.getGraph().get("introduction");
		currentPlay.display();
		System.out.println("hiiii" + currentPlay.getNom());
		Node nextNode = currentPlay.chooseNext();
		System.out.println(nextNode.getNom());
		currentPlay =(DecisionNode) nextNode;
		currentPlay.display();
		
		//Terrain terrain = new Terrain("C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame-main\\src\\terrain2.txt");
		if(currentPlay.getNom().equals("rejoindre") || currentPlay.getNom().equals("explication") ) {
			nextNode = currentPlay.chooseNext();
			currentPlay =(DecisionNode) nextNode;
			currentPlay.display();
			switch(currentPlay.getNom()) {
			case "clan A" : p = new SorcierElement();break;
			case "clan B" : p= new SorcierSpirituel();break;
			case "humain" :p= new Humain(); break;
			default: p= new Personnage();
			}
			
			terrain =new Terrain("C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame-main\\src\\terrain2.txt",p);
			nextNode = currentPlay.chooseNext();
			currentPlay =(DecisionNode) nextNode;
		}
		
		while (true) {
			currentPlay.display();
			
			nextNode = currentPlay.chooseNext();
			
			if (nextNode instanceof DecisionNode) {
				currentPlay =(DecisionNode) nextNode;
				
			}else if(nextNode instanceof ChanceNode) {
				currentPlay =(ChanceNode) nextNode;
				
			}
			else if(nextNode instanceof TerminalNode) {
				currentPlay =(TerminalNode) nextNode;
				currentPlay.display();
				break;
				
			}
			else {
				break;
			}

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
}
