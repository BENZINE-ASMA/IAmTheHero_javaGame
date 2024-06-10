import java.util.Map.Entry;
import entities.*;
import representation.*;
import java.util.Scanner;

public class JeuMain {
    public static String test() {
        return "test";
    }
    
    public static void main(String[] args) {
        Terrain terrain;
        Personnage p = null;  // Ensure this is initialized to null
        
        Scanner sc = new Scanner(System.in);
        
        NodesGraph graph = new NodesGraph();
        
        graph.addDecisionNode("introduction", "Bonjour et bienvenue à l'auberge de la ville ! Vous pouvez tout faire ici, acheter armes, potions, et même rejoindre les clans de notre contrée ! Je ne vous ai jamais vu ici avant, que puis-je pour vous ?");
        graph.addDecisionNode("explication", "[explications du lore] quel type de sorcier êtes vous ?");
        graph.addDecisionNode("rejoindre", "Quel type de sorcier êtes vous?");
        graph.addDecisionNode("clanB", "Vous êtes désormais dans le clan B");
        graph.addDecisionNode("clanA", "Vous êtes désormais dans le clan A");
        graph.addDecisionNode("humain", "quelle est votre compétence?");
        graph.addDecisionNode("humainAvecCompetence", "vous avez la compétence de '', vous voulez rejoindre quel clan");
        graph.addDecisionNode("humainAvecCompetenceSeul", "vous etes desormais seul ");
        
        graph.addTerminalNode("death", "game over");
        
        graph.addArc("introduction", "explication", "Est-ce que vous pouvez m'expliquer en quoi consistent les clans?");
        graph.addArc("introduction", "rejoindre", "Je suis ici pour rejoindre un clan");
        graph.addArc("explication", "clanA", "Je suis un sorcier des éléments");
        graph.addArc("explication", "clanB", "Je suis un sorcier spirituel");
        graph.addArc("explication", "humain", "Je suis juste un humain...");
        graph.addArc("rejoindre", "clanA", "Je suis un sorcier des éléments");
        graph.addArc("rejoindre", "clanB", "Je suis un sorcier spirituel");
        graph.addArc("rejoindre", "humain", "Je suis un simple humain.");
        graph.addArc("clanA", "death", "Je veux finir");
        graph.addArc("clanB", "death", "Je veux finir");
        graph.addArc("humain", "humainAvecCompetence", "je suis ingénieur");
        graph.addArc("humain", "humainAvecCompetence", "je suis combattant");
        graph.addArc("humain", "humainAvecCompetence", "je suis persuasif");
        
        graph.addArc("humainAvecCompetence", "clanA", "je veux rejoindre clan A");
        graph.addArc("humainAvecCompetence", "clanB", "je veux rejoindre clan B");
        graph.addArc("humainAvecCompetence", "humainAvecCompetenceSeul", "je veux rester seul");
        
        for (Entry<String, Node> entry : graph.getGraph().entrySet()) {
            if (entry.getValue() instanceof InnerNode) {
                System.out.print(entry.getKey() + "   ");
                ((InnerNode) entry.getValue()).testDisplay();
                System.out.println("//////////");
            }
        }
        
        Node currentPlay = graph.getGraph().get("introduction");
        currentPlay.display();
        
        Node nextNode = currentPlay.chooseNext();
        System.out.println(nextNode.getNom());
        currentPlay = nextNode;
        currentPlay.display();
        
        if (currentPlay.getNom().equals("rejoindre") || currentPlay.getNom().equals("explication")) {
            nextNode = currentPlay.chooseNext();
            currentPlay = nextNode;
            currentPlay.display();
            
            switch (currentPlay.getNom()) {
                case "clanA": p = new SorcierElement(); break;
                case "clanB": p = new SorcierSpirituel(); break;
                case "humain": 
                	
                    nextNode = currentPlay.chooseNext();
                    currentPlay = nextNode;
                    //currentPlay.display()
                    //System.out.println("this is what m testing "  +currentPlay.getDescription().split("\\.")[0].substring(28));
                    String competence = currentPlay.getDescription().split("\\.")[0].substring(28); 
                    //System.out.println("this is what m testing "  +currentPlay.getDescription() ); 
                    //System.out.println("this is what m testing "  +competence ); 
                    p = new Humain(Competence.valueOf(competence)); 
                   //System.out.println(((Humain) p).getAttaque());
                    break;
                default: p = new Personnage();
            }
            
            
        }
        
        while (true) {
        	//System.out.println("hi");
            currentPlay.display();
            
            nextNode = currentPlay.chooseNext();
            
            if (nextNode instanceof DecisionNode) {
                currentPlay = nextNode;
            } else if (nextNode instanceof ChanceNode) {
                currentPlay = nextNode;
            } else if (nextNode instanceof TerminalNode) {
                currentPlay = nextNode;
                currentPlay.display();
                break;
            } else {
                break;
            }
        }
    }
}
