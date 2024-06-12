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

     // Introduction à l'auberge
     // Introduction à l'auberge
        graph.addDecisionNode("introduction", "Bonjour et bienvenue à l'auberge de la ville ! Vous pouvez tout faire ici, acheter armes, potions, et même rejoindre les clans de notre contrée ! Je ne vous ai jamais vu ici avant, que puis-je pour vous ?");
        graph.addDecisionNode("explication", "[explications du lore] quel type de sorcier êtes vous ?");
        graph.addDecisionNode("rejoindre", "Quel type de sorcier êtes vous?");
        graph.addDecisionNode("clanB", "Vous êtes désormais dans le clan B");
        graph.addDecisionNode("clanA", "Vous êtes désormais dans le clan A");
        graph.addDecisionNode("humain", "quelle est votre compétence?");
        graph.addDecisionNode("humainAvecCompetence", "vous avez la compétence de '', vous voulez rejoindre quel clan");
        graph.addDecisionNode("humainAvecCompetenceSeul", "vous etes desormais seul ");

        // Ajout des dialogues avec les maîtres des clans
        graph.addDecisionNode("maitreClanA", "Bienvenue, jeune sorcier. Je suis le Maître de ce clan des Éléments. Nous avons besoin de sorciers talentueux comme toi pour retrouver la Pierre des Éléments.");
        graph.addDecisionNode("maitreClanB", "Bienvenue, jeune sorcier. Je suis le Maître de ce clan Spirituel. Nous avons besoin de sorciers talentueux comme toi pour sécuriser les reliques spirituelles.");

        // Ajout des missions pour chaque clan
        graph.addDecisionNode("missionClanA", "Votre mission est de retrouver la Pierre des Éléments, une relique de grande puissance.");
        graph.addDecisionNode("missionClanB", "Votre mission est de sécuriser les reliques spirituelles, qui sont cruciales pour notre pouvoir.");

        graph.addDecisionNode("combatPierre", "Vous avez retrouvé la Pierre des Éléments, mais elle est gardée par un puissant gardien. Préparez-vous au combat.");
        graph.addDecisionNode("combatReliques", "Vous avez trouvé les reliques spirituelles, mais elles sont protégées par des esprits puissants. Préparez-vous au combat.");

        graph.addDecisionNode("victoirePierre", "Félicitations, vous avez vaincu le gardien et récupéré la Pierre des Éléments. Vous êtes acclamé par votre clan pour votre bravoure et votre compétence.");
        graph.addDecisionNode("defaitePierre", "Vous avez été vaincu par le gardien. Retournez au clan pour vous préparer à nouveau et retenter votre chance.");

        graph.addDecisionNode("victoireReliques", "Félicitations, vous avez vaincu les esprits et sécurisé les reliques spirituelles. Vous êtes acclamé par votre clan pour votre bravoure et votre compétence.");
        graph.addDecisionNode("defaiteReliques", "Vous avez été vaincu par les esprits. Retournez au clan pour vous préparer à nouveau et retenter votre chance.");

        // Interactions supplémentaires pour la mission
        graph.addDecisionNode("introVieux", "En cherchant la pierre magique, vous rencontrez un vieux sage. Voulez-vous lui parler ?");
        graph.addDecisionNode("parlerVieux", "Le vieux sage vous raconte des histoires sur la pierre magique et vous avertit qu'elle est gardée dans un donjon par un puissant monstre. Voulez-vous lui demander où se trouve le donjon ?");
        graph.addDecisionNode("ignorerVieux", "Vous ignorez le vieux sage et continuez votre chemin.");
        graph.addDecisionNode("deuxiemeCombat", "Vous avez trouvé la pierre magique dans le donjon, mais elle est gardée par un puissant monstre. Préparez-vous au combat. Vous ne pouvez pas fuir.");

        graph.addDecisionNode("victoireFinale", "Félicitations, vous avez vaincu le monstre et récupéré la pierre magique. Vous êtes acclamé par votre clan pour votre bravoure et votre compétence.");
        graph.addDecisionNode("defaiteFinale", "Vous avez été vaincu par le monstre. Retournez au clan pour vous préparer à nouveau et retenter votre chance.");
        graph.addDecisionNode("mort", "Vous avez choisi de fuir et avez été rattrapé par le monstre. Vous êtes mort.");

        // Enregistrement à l'auberge
        graph.addArc("introduction", "explication", "Est-ce que vous pouvez m'expliquer en quoi consistent les clans?");
        graph.addArc("introduction", "rejoindre", "Je suis ici pour rejoindre un clan");

        // Explication des clans
        graph.addArc("explication", "clanA", "Je suis un sorcier des éléments");
        graph.addArc("explication", "clanB", "Je suis un sorcier spirituel");
        graph.addArc("explication", "humain", "Je suis juste un humain...");

        // Choix de compétence pour les humains
        graph.addArc("rejoindre", "clanA", "Je suis un sorcier des éléments");
        graph.addArc("rejoindre", "clanB", "Je suis un sorcier spirituel");
        graph.addArc("rejoindre", "humain", "Je suis un simple humain.");
        graph.addArc("humain", "humainAvecCompetence", "je suis ingénieur");
        graph.addArc("humain", "humainAvecCompetence", "je suis combattant");
        graph.addArc("humain", "humainAvecCompetence", "je suis persuasif");

        graph.addArc("humainAvecCompetence", "clanA", "je veux rejoindre clan A");
        graph.addArc("humainAvecCompetence", "clanB", "je veux rejoindre clan B");
        graph.addArc("humainAvecCompetence", "humainAvecCompetenceSeul", "je veux rester seul");

        // Interaction avec le maître des clans
        graph.addArc("clanA", "maitreClanA", "Continuer");
        graph.addArc("clanB", "maitreClanB", "Continuer");

        // Missions spécifiques des clans
        graph.addArc("maitreClanA", "missionClanA", "Oui, je suis prêt à aider et à me préparer.");
        graph.addArc("maitreClanB", "missionClanB", "Oui, je suis prêt à aider et à me préparer.");

        // Missions et combats
        graph.addArc("missionClanA", "combatPierre", "Continuer");
        graph.addArc("missionClanB", "combatReliques", "Continuer");

        graph.addArc("combatPierre", "victoirePierre", "Combattre et vaincre le gardien.");
        graph.addArc("combatPierre", "defaitePierre", "Fuir le combat.");

        graph.addArc("combatReliques", "victoireReliques", "Combattre et vaincre les esprits.");
        graph.addArc("combatReliques", "defaiteReliques", "Fuir le combat.");

        // Interaction avec le vieux sage
        graph.addArc("victoirePierre", "introVieux", "Continuer");
        graph.addArc("introVieux", "parlerVieux", "Parler au vieux sage");
        graph.addArc("introVieux", "ignorerVieux", "Ignorer le vieux sage");

        graph.addArc("parlerVieux", "deuxiemeCombat", "Demander où se trouve le donjon");
        graph.addArc("ignorerVieux", "deuxiemeCombat", "Continuer");

        //avoir un chance node -----------------------------------------------  cest là une stat
        graph.addArc("deuxiemeCombat", "victoireFinale", "Combattre et vaincre le monstre");
        graph.addArc("deuxiemeCombat", "defaiteFinale", "Perdre le combat");

        // Si le joueur a fui le premier combat
        graph.addArc("defaitePierre", "introVieux", "Continuer");
        //graph.addArc("introVieux", "mort", "Parler au vieux sage");
        graph.addArc("introVieux", "mort", "Ignorer le vieux sage");

        Node currentPlay = graph.getGraph().get("introduction");
        while (true) {
            currentPlay.display();
            Node nextNode = currentPlay.chooseNext();
            currentPlay = nextNode;

            if (nextNode.getNom().equals("rejoindreClan") || nextNode.getNom().equals("queteClan")) {
                currentPlay = nextNode;
                nextNode.display();

                switch (nextNode.getNom()) {
                    case "missionPierre":
                        p = new SorcierElement();
                        break;
                    case "defaitePierre":
                        p = new SorcierElement();
                        break;
                    default:
                        p = new Personnage();
                }
            }

            if (currentPlay instanceof TerminalNode) {
                currentPlay.display();
                break;
            }
        }
    }
}
