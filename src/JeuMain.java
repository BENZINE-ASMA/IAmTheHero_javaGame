import java.util.Map.Entry;

import Interface.Terrain;
import entities.*;
import representation.*;
import java.util.Scanner;

public class JeuMain {
    public static String test() {
        return "test";
    }
    
    public static void majCombatNode(Personnage p, String nodeName, NodesGraph graph) {
    	CombatNode cn = (CombatNode)((graph.getGraph()).get(nodeName));
    	cn.setJoueur(p);
    }

    public static void main(String[] args) {
        Terrain terrain;
        Personnage p = null;  // Ensure this is initialized to null

        Scanner sc = new Scanner(System.in);

        NodesGraph graph = new NodesGraph();
        
        
        EntiteMobile gobelin1 = new EntiteMobile(50,50,10,14);
        gobelin1.setName("Geodfroy");
        EntiteMobile gobelin2 = new EntiteMobile(50,50,10,14);
        gobelin1.setName("Gontrant");
        EntiteMobile slime1 = new EntiteMobile(30,30,5,8);
        gobelin1.setName("Slime");
        EntiteMobile chimere = new EntiteMobile(110,110,25,20);
        gobelin1.setName("Chimère");
        
        
        //Noeud terminal de mort lors d'un combat
        graph.addTerminalNode("mortCombat", "Vous êtes mort bravement au combat.");

     // Introduction à l'auberge
     // Introduction à l'auberge
        graph.addDecisionNode("introduction", "Bonjour et bienvenue à l'auberge de la ville ! Vous pouvez tout faire ici, acheter armes, potions, et même rejoindre les clans de notre contrée ! Je ne vous ai jamais vu ici avant, que puis-je pour vous ?");
        graph.addDecisionNode("explication1", "Bien sûr! Dans notre région, il y a deux puissants clans qui se disputent depuis des années : les sorciers des éléments et les sorciers spirituels. Les sorciers des éléments manipulent les forces naturelles telles que le feu, l'eau ou la terre, tandis que les sorciers spirituels se concentrent sur la manipulation de l'énergie spirituelle et des âmes.");
        graph.addDecisionNode("explication2", "Ces deux clans sont engagés dans une lutte de pouvoir perpétuelle, chacun cherchant à étendre son influence et affirmer sa supprématie. C'est une période de tension constante, et beaucoup craignent que cela ne conduise à un conflit ouvert un jour. Dis moi, quel sorcier es tu? ");
        graph.addDecisionNode("rejoindre", "Bien sûr! Vous pouvez rejoindre le clan des sorciers spirituels et celui des éléments. Quel type de sorcier êtes vous?");
        graph.addDecisionNode("clanSpirituel", "[Vous signez des documents obscurs]. Très bien, vous êtes désormais dans le clan spirituel! Vous allez y être téléporté, au plaisir de vous revoir !");
        graph.addDecisionNode("clanElement", "[Vous signez des documents obscurs]. Très bien, vous êtes désormais dans le clan des éléments! Vous allez y être emmené directement. Merci d'être passé par chez nous et bonne chance!");
        graph.addDecisionNode("humain", "quelle est votre compétence?");
        graph.addDecisionNode("humainAvecCompetence", "vous avez la compétence de '', vous voulez rejoindre quel clan");
        graph.addDecisionNode("humainAvecCompetenceSeul", "vous etes desormais seul ");

        // Ajout des dialogues avec les maîtres des clans
        graph.addDecisionNode("maitreClanElementIntro1", "Bienvenue, jeune sorcier. Je suis le Maître de ce clan, et je suis honoré de te rencontrer. Notre clan des Sorciers des Éléments lutte depuis des siècles pour maintenir notre position dominante sur ces terres. Nous devons nous assurer que nos pouvoirs ne tombent pas entre de mauvaises mains, notamment celles des Sorciers Spirituels, nos ennemis jurés.");
        graph.addDecisionNode("maitreClanElementIntro2", "Nous sommes sur le point d'accomplir une quête cruciale qui renforcera notre position et nous donnera un avantage sur nos adversaires. Pour cela, nous avons besoin de sorciers talentueux comme toi. Mais avant de te confier cette responsabilité, il est essentiel que tu te prépares adéquatement.");
        graph.addDecisionNode("maitreClanElementIntro3", "Je te conseille de parler à l'Aubergiste et à notre Historien, ils ont des peut-être des informations qui pourraient nous être utiles. N'hésite pas à combattre les monstres que tu vois, tu manques encore de puissance et une catastrophe arriverait si leur nombre était trop élevé.");
        graph.addDecisionNode("maitreClanSpirituel", "Bienvenue, jeune sorcier. Je suis le Maître de ce clan Spirituel. Nous avons besoin de sorciers talentueux comme toi pour sécuriser les reliques spirituelles.");

        
        //Noeud de fin si refus de la quête principale
        graph.addTerminalNode("demission", "Je ne vois pas ce que tu aurais à faire au sein de notre clan si tu ne participes pas à notre objectif principal. Je me vois dans l'obligation de te congédier. Peut-être que nos chemins se croiseront un jour de nouveau.");
        
        // Ajout des missions pour chaque clan
        graph.addDecisionNode("missionClanElement", "Excellent choix. Ta mission est de retrouver la Pierre des Éléments, une relique de grande puissance. Avant de parler davantage de la mission, parle moi un peu de toi. Quel est l'élement qui te fait vibrer, celui avec lequel tu as le plus d'affinité?");
        graph.addDecisionNode("missionClanSpirituel", "Votre mission est de sécuriser les reliques spirituelles, qui sont cruciales pour notre pouvoir.");

        // Choix Affinite pour Sorcier Element
        graph.addDecisionNode("affiniteElement", "Tu as donc une affinité avec '', très bien. Tes sorts utilisant cet élément auront donc davantage de puissance que les autres, n'hésite pas à les utiliser.");
        
  
        // Choix d'action post introduction
        // Choix dans l'auberge
        graph.addDecisionNode("AubergePostIntroElem", "L'auberge n'a pas changé depuis votre venue tout à l'heure. L'aubergiste vous accueille : Vous voilà ! Que puis-je pour vous ?");
        graph.addDecisionNode("AubergePierreElem", "C'est une relique légendaire, censée renfermer la puissance brute des éléments. On dit qu'elle a été perdue il y a des siècles... Vous devriez parler à l'historien de la Bibliothèque des Anciens, il en sait sûrement plus.");
        graph.addDecisionNode("AubergeEmplacementBibliotheque", "La Bibliothèque des Anciens se trouve à l'Est de la ville, non loin d'ici. Vous devriez pouvoir voir l'emplacement de l'historien sur votre carte.");
        graph.addDecisionNode("AubergePersonnesUtiles", "L'historien est sans doute la personne qui s'y connait le mieux. Vous pourriez tout de même aller voir la guérisseuse au sud du village pour récupérer des potions spéciales pour vous protéger des dangers des donjons.");
  
        // Choix dans la bibliotheque
        graph.addDecisionNode("Bibliothèque", "bibliothèque");
        graph.addDecisionNode("BibliothequePostIntroElem", "Vous entrez dans une petite bibliothèque renfermant des livres poussiéreux. L'historien vous accueille et vous demande la raison de votre visite.");
        graph.addDecisionNode("BibliothequePierreElem", "Historien: La Pierre Élémentaire est une relique ancienne. Elle amplifie les pouvoirs de ceux qui la possèdent. Elle pourrait être cachée dans les Grottes au Sud ou dans la Forêt à l'Est. ");
        graph.addDecisionNode("BibliothequeLegende", "Historien: Le Grimoire Ancien contient des sorts puissants oubliés depuis longtemps. Il pourrait être cachée dans les Grottes au Sud ou dans la Forêt à l'Est.");
        
        graph.addCombatNode("Donjon", "En vous promenant vous tombez sur un monstre dangereux, un gobelin ! Il vous attaque.", "mortCombat", gobelin1, p);
        
        //Aller dans la forêt
        graph.addDecisionNode("Foret", "En entrant dans la Forêt des Murmures, vous êtes enveloppé par une canopée dense et lumineuse. Les arbres chuchotent des secrets anciens à chaque souffle de vent. Par où aller?");
        graph.addChanceNode("ForetChemin", ""); //Est ce que le chemin choisi sera le bon?
        graph.addDecisionNode("ForetMauvaisChemin", "Vous vous perdez dans la forêt. Que voulez-vous faire?");
        graph.addTerminalNode("MortForet", "Vous ne connaissez pas la forêt et continuez à avancer malgré tout. Des plantes carnivores vous attrapent et vous mangent.");
        graph.addDecisionNode("ForetBonChemin", "Vous trouvez votre chemin.");
        graph.addChanceNode("ForetMonstre", ""); //Quel monstre va être choisi?
        graph.addCombatNode("CombatSlime1", "Cependant vous tombez nez à nez avec un slime qui vous attaque!", "mortCombat", slime1, p);
        graph.addCombatNode("CombatGobelin2", "Cependant vous tombez nez à nez avec un gobelin qui vous attaque!", "mortCombat", gobelin2, p);
        graph.addCombatNode("CombatChimere", "Vous voyez la Pierre juste devant vos yeux. Une créature surgit alors, une chimère protégeant la pierre qui fonce sur vous!", "mortCombat", chimere, p);
        graph.addDecisionNode("CombatChimereGagne", "La Pierre scintille devant vous. Que voulez-vous en faire?");
        graph.addDecisionNode("DetruirePierre", "La Pierre est brisée en mille morceaux à vos pieds. Qu'avez-vous fait...?\nVous quittez la forêt, qui semble plus sombre que lorsque vous êtes arrivé.e");
        
        //Ramener Pierre à son clan : fin de l'histoire
        graph.addTerminalNode("PierrePourClan", "Vous ramenez la Pierre au clan des éléments. Le maitre du clan vous remercie. Il a un regard triomphant, et quelque peu sournois. Votre clan survivra, le destin de l'autre semble arriver à son terme.");
        
        
        //Aller dans la grotte
        graph.addDecisionNode("Grotte","La Grotte des Ombres, sombre et froide, résonne de murmures mystérieux. Que voulez-vous faire?");
        graph.addChanceNode("Explorer", "Vous cherchez dans la grotte");
        graph.addDecisionNode("ArmeMeilleure", "Vous trouvez une *version de l'arme améliorée*. Vous la gardez avec vous. Vous décidez d'avancer");
        graph.addDecisionNode("GrotteRien", "Vous ne voyez rien d'intéressant pour l'instant...Autant continuer");
        graph.addTerminalNode("GrotteHorde", "Vous vous avancez dans la grande salle. Malheureusement pour vous, c'est le campement des gobelins, et des dizaines vous attaquent d'un coup... Vous succombez rapidement de vos blessures");
        graph.addDecisionNode("GrottePetitChemin", "Vous avancez dans un petit chemin sinueux");
        
        graph.addDecisionNode("combatPierre", "Vous avez retrouvé la Pierre des Éléments, mais elle est gardée par un puissant gardien. Préparez-vous au combat.");
        graph.addDecisionNode("combatReliques", "Vous avez trouvé les reliques spirituelles, mais elles sont protégées par des esprits puissants. Préparez-vous au combat.");

        graph.addDecisionNode("victoirePierre", "Félicitations, vous avez vaincu le gardien et récupéré la Pierre des Éléments. Vous êtes acclamé par votre clan pour votre bravoure et votre compétence.");
        graph.addDecisionNode("defaitePierre", "Vous avez été vaincu par le gardien. Retournez au clan pour vous préparer à nouveau et retenter votre chance.");

        graph.addDecisionNode("victoireReliques", "Félicitations, vous avez vaincu les esprits et sécurisé les reliques spirituelles. Vous êtes acclamé par votre clan pour votre bravoure et votre compétence.");
        graph.addDecisionNode("defaiteReliques", "Vous avez été vaincu par les esprits. Retournez au clan pour vous préparer à nouveau et retenter votre chance.");

        
        //Noeud principal de retour
        graph.addDecisionNode("Choix", "Que voulez vous faire?");
        
        
        // Interactions supplémentaires pour la mission
        graph.addDecisionNode("introVieux", "En cherchant la pierre magique, vous rencontrez un vieux sage. Voulez-vous lui parler ?");
        graph.addDecisionNode("parlerVieux", "Le vieux sage vous raconte des histoires sur la pierre magique et vous avertit qu'elle est gardée dans un donjon par un puissant monstre. Voulez-vous lui demander où se trouve le donjon ?");
        graph.addDecisionNode("ignorerVieux", "Vous ignorez le vieux sage et continuez votre chemin.");
        graph.addDecisionNode("deuxiemeCombat", "Vous avez trouvé la pierre magique dans le donjon, mais elle est gardée par un puissant monstre. Préparez-vous au combat. Vous ne pouvez pas fuir.");

        graph.addDecisionNode("victoireFinale", "Félicitations, vous avez vaincu le monstre et récupéré la pierre magique. Vous êtes acclamé par votre clan pour votre bravoure et votre compétence.");
        graph.addDecisionNode("defaiteFinale", "Vous avez été vaincu par le monstre. Retournez au clan pour vous préparer à nouveau et retenter votre chance.");
        graph.addDecisionNode("mort", "Vous avez choisi de fuir et avez été rattrapé par le monstre. Vous êtes mort.");

        // Enregistrement à l'auberge
        graph.addArc("introduction", "explication1", "Est-ce que vous pouvez m'expliquer en quoi consistent les clans?");
        graph.addArc("explication1", "explication2", "Continuer");
        graph.addArc("introduction", "rejoindre", "Je suis ici pour rejoindre un clan");

        // Explication des clans
        graph.addArc("explication2", "clanElement", "Je suis un sorcier des éléments");
        graph.addArc("explication2", "clanSpirituel", "Je suis un sorcier spirituel");
        graph.addArc("explication2", "humain", "Je suis juste un humain...");

        // Choix de compétence pour les humains
        graph.addArc("rejoindre", "clanElement", "Je suis un sorcier des éléments");
        graph.addArc("rejoindre", "clanSpirituel", "Je suis un sorcier spirituel");
        graph.addArc("rejoindre", "humain", "Je suis un simple humain.");
        graph.addArc("humain", "humainAvecCompetence", "je suis ingénieur");
        graph.addArc("humain", "humainAvecCompetence", "je suis combattant");
        graph.addArc("humain", "humainAvecCompetence", "je suis persuasif");

        graph.addArc("humainAvecCompetence", "clanElement", "je veux rejoindre clan des éléments");
        graph.addArc("humainAvecCompetence", "clanSpirituel", "je veux rejoindre clan spirituel");
        graph.addArc("humainAvecCompetence", "humainAvecCompetenceSeul", "je veux rester seul");

        // Interaction avec le maître des clans
        graph.addArc("clanElement", "maitreClanElementIntro1", "Se rendre voir le maitre du clan");
        graph.addArc("maitreClanElementIntro1", "maitreClanElementIntro2", "Continuer");
        graph.addArc("clanSpirituel", "maitreClanSpirituel", "Continuer");

        
        
        // Missions spécifiques des clans
        graph.addArc("maitreClanElementIntro2", "missionClanElement", "Oui, je suis prêt à aider et à me préparer.");
        graph.addArc("maitreClanSpirituel", "missionClanSpirituel", "Oui, je suis prêt à aider et à me préparer.");
        
        graph.addArc("maitreClanElementIntro2", "demission", "Cette mission me semble trop conséquente.");

        //choix de l'affinité pour le clan des éléments
        graph.addArc("missionClanElement", "affiniteElement", "J'ai une affinité très forte avec le feu");
        graph.addArc("missionClanElement", "affiniteElement", "J'ai une affinité très forte avec l' eau");
        graph.addArc("missionClanElement", "affiniteElement", "J'ai une affinité très forte avec la terre");
        graph.addArc("missionClanElement", "affiniteElement", "J'ai une affinité très forte avec l' air");
        
        graph.addArc("affiniteElement", "maitreClanElementIntro3", "Ecouter le maitre du clan");
        
        // Debut de la quete principale
        graph.addArc("maitreClanElementIntro3", "AubergePostIntroElem", "Se rendre à l'auberge.");
        graph.addArc("maitreClanElementIntro3", "Bibliothèque", "Se rendre à la bibliothèque chercher l'historien.");
        graph.addArc("maitreClanElementIntro3", "Donjon", "Chercher un combat.");
        
        
        graph.addArc("AubergePostIntroElem", "AubergePierreElem", "Savez-vous où je pourrais trouver des indices sur la Pierre Elementaire?");
        graph.addArc("AubergePierreElem", "AubergeEmplacementBibliotheque", "Où puis-je trouver la Bibliothèque des Anciens?");
        graph.addArc("AubergePostIntroElem", "AubergePersonnesUtiles", "Y a-t-il des personnes en ville qui en savent plus sur ces légendes?");
        
        
        
        // Missions et combats pas direct changer
        graph.addArc("Donjon", "combatPierre", "Continuer");
        graph.addArc("missionClanSpirituel", "combatReliques", "Continuer");

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

        /*Node currentPlay = graph.getGraph().get("introduction");
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
        */
        
        System.out.println(gobelin1.getPvRestant());
        
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
            System.out.println("hello there" + currentPlay.getNom());

            switch (currentPlay.getNom()) {
                case "clanElement": p = new SorcierElement(); break;
                case "clanSpirituel": p = new SorcierSpirituel(); break;
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
        
        majCombatNode(p, "Donjon", graph);

        while (true) {
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
            } else if (nextNode instanceof CombatNode) {
            	currentPlay = nextNode;
            }
            
            else {
                break;
            }
        }
        
        
    }
}
