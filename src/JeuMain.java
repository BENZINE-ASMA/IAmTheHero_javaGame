import java.util.Scanner;

import Interface.Terrain;
import entities.Competence;
import entities.EntiteMobile;
import entities.Humain;
import entities.Personnage;
import entities.Potion;
import entities.Sorcier;
import entities.SorcierElement;
import entities.SorcierSpirituel;
import entities.Sort;
import representation.ChanceNode;
import representation.CombatNode;
import representation.DecisionNode;
import representation.Node;
import representation.NodesGraph;
import representation.TerminalNode;
import representation.Event;
import representation.ImageNode;
import representation.SoundNode;

/* Divers soucis/améliorations
 * Le ChanceNode ne fonctionne pas bien 
*/


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
        gobelin2.setName("Gontrant");
        EntiteMobile gobelin3 = new EntiteMobile(50,50,10,14);
        gobelin3.setName("Zappy");
        EntiteMobile slime1 = new EntiteMobile(30,30,5,8);
        slime1.setName("Sundae");
        EntiteMobile chimere = new EntiteMobile(110,110,25,20);
        chimere.setName("Chimère");
        EntiteMobile queenslime = new EntiteMobile(90,90,6,6);
        queenslime.setName("Reine des Slimes");
        
        
        TerminalNode death =  new TerminalNode ("mortCombat", "Vous êtes mort bravement au combat.");
        //Noeud terminal de mort lors d'un combat
        graph.addNode("mortCombat", death);
    

        graph.addNode("introduction", new ImageNode (new SoundNode(new DecisionNode("introduction", "Bonjour et bienvenue à l'auberge de la ville ! Vous pouvez tout faire ici, acheter armes, potions, et même rejoindre les clans de notre contrée ! Je ne vous ai jamais vu ici avant, que puis-je pour vous ?"), "C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\musique1.wav" ), "C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\taverne.jpg"));
        
        //graph.addNode("introduction", new SoundNode(new DecisionNode("introduction", "Bonjour et bienvenue à l'auberge de la ville ! Vous pouvez tout faire ici, acheter armes, potions, et même rejoindre les clans de notre contrée ! Je ne vous ai jamais vu ici avant, que puis-je pour vous ?"), "C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\musique1.wav"));
        graph.addNode("explication", new DecisionNode("explication", "Bien sûr! Dans notre région, il y a deux puissants clans qui se disputent depuis des années : les sorciers des éléments et les sorciers Enchanteurs. Les sorciers des éléments manipulent les forces naturelles telles que le feu, l'eau ou la terre, tandis que les sorciers Enchanteurs se concentrent sur la manipulation de l'énergie Enchanteurle et des âmes. Ces deux clans sont engagés dans une lutte de pouvoir perpétuelle, chacun cherchant à étendre son influence et affirmer sa supprématie. C'est une période de tension constante, et beaucoup craignent que cela ne conduise à un conflit ouvert un jour. Dis moi, quel sorcier es tu?"));
        graph.addNode("rejoindre", new DecisionNode("rejoindre", "Bien sûr! Vous pouvez rejoindre le clan des sorciers Enchanteurs et celui des éléments. Quel type de sorcier êtes vous?"));

        graph.addNode("clanEnchanteur", new DecisionNode("clanEnchanteur","[Vous signez des documents obscurs]. Très bien, vous êtes désormais dans le clan Enchanteur! Vous allez y être téléporté, au plaisir de vous revoir !"));

        graph.addNode("clanElement", new DecisionNode("clanElement", "[Vous signez des documents obscurs]. Très bien, vous êtes désormais dans le clan des éléments! Vous allez y être emmené directement. Merci d'être passé par chez nous et bonne chance!"));
        graph.addNode("humain", new DecisionNode("humain", "quelle est votre compétence?"));
        graph.addNode("humainAvecCompetence", new DecisionNode("humainAvecCompetence", "vous avez la compétence de '', que voulez-vous faire?"));
        graph.addNode("finHumain", new TerminalNode("finHumain", "Vous ne pouvez pas rejoindre de clan, mais vous allez très bien vous entendre avec les membres du village ! L'aventure est réservée aux sorciers mais notre vie est elle aussi passionnante.\nVous vous installez dans cette nouvelle ville et vous faites de nouveaux amis. Vous vous mariez, avez des enfants, et vivez votre vie normale heureux.se jusqu'à votre mort paisible.\nFIN"));

        // Dialogue avec le maître du clan des éléments
        graph.addNode("maitreClanElementIntro1", new DecisionNode("maitreClanElementIntro1", "Bienvenue, jeune sorcier. Je suis le Maître de ce clan, et je suis honoré de te rencontrer. Notre clan des Sorciers des Éléments lutte depuis des siècles pour maintenir notre position dominante sur ces terres. Nous devons nous assurer que nos pouvoirs ne tombent pas entre de mauvaises mains, notamment celles des Sorciers Enchanteurs, nos ennemis jurés."));
        graph.addNode("maitreClanElementIntro2", new DecisionNode("maitreClanElementIntro2", "Nous sommes sur le point d'accomplir une quête cruciale qui renforcera notre position et nous donnera un avantage sur nos adversaires. Pour cela, nous avons besoin de sorciers talentueux comme toi. Mais avant de te confier cette responsabilité, il est essentiel que tu te prépares adéquatement."));
        graph.addNode("maitreClanElementIntro3", new DecisionNode("maitreClanElementIntro3", "Je te conseille de parler à l'Aubergiste et à notre Historien, ils ont des peut-être des informations qui pourraient nous être utiles. N'hésite pas à combattre les monstres que tu vois avant d'y aller, tu manques encore de puissance. Prends ces potions avec toi, elles pourraient d'être utiles."));

        // Dialogue avec le maître du clan des enchanteurs
        graph.addNode("maitreClanEnchanteurIntro1", new DecisionNode("maitreClanEnchanteurIntro1", "Bienvenue, jeune sorcier. Je suis le Maître de ce clan Enchanteur. Nous avons besoin de sorciers talentueux comme toi pour sécuriser nos reliques. Nos ennemis, les Sorciers des Elements, semblent déterminés à récupérer la Pierre des Elements pour leur accorder un pouvoir tout puissant. Nous ne pouvons pas les laisser faire, encore plus quand nous savons que notre clan la possédait il y a de cela quelques siècles. Vas tu nous rejoindre?"));
        graph.addNode("refusQuete", new DecisionNode("refusQuete", "En es-tu sûr.e ? C'est le destin du monde qui en dépend"));
        graph.addNode("maitreClanEnchanteurIntro2", new DecisionNode("maitreClanEnchanteurIntro2", "Je te conseille de parler à l'Aubergiste et à l'Historien dans le village. Avant cela, n'hésite pas à combattre pour t'améliorer. Les sorciers Enchanteurs ont une puissance décuplée pour chaque ennemi terrassé. Prends ces quelques potions avec toi, et bon voyage."));

        // Nœud de fin si refus de la quête principale
        graph.addNode("demission", new TerminalNode("demission", "Je ne vois pas ce que tu aurais à faire au sein de notre clan si tu ne participes pas à notre objectif principal. Je me vois dans l'obligation de te congédier. Peut-être que nos chemins se croiseront un jour de nouveau."));

        // Ajout des missions pour chaque clan
        graph.addNode("missionClanElement", new DecisionNode("missionClanElement", "Excellent choix. Ta mission est de retrouver la Pierre des Éléments, une relique de grande puissance. Avant de parler davantage de la mission, parle moi un peu de toi. Quel est l'élement qui te fait vibrer, celui avec lequel tu as le plus d'affinité?"));
        graph.addNode("missionClanEnchanteur", new DecisionNode("missionClanEnchanteur", "Ta mission est de récupérer notre dû, la Pierre Elementaire, renfermant un grand pouvoir."));

     // Choix Affinité pour Sorcier Élement
        graph.addNode("affiniteElement", new DecisionNode("affiniteElement", "Tu as donc une affinité avec '', très bien. Tes sorts utilisant cet élément auront donc davantage de puissance que les autres, n'hésite pas à les utiliser."));

        // Choix d'action post introduction
        // Choix dans l'auberge
        graph.addNode("AubergePostIntro", new DecisionNode("AubergePostIntro", "L'auberge n'a pas changé depuis votre venue tout à l'heure. Alors que vous alliez voir l'aubergiste, un homme semblant assez alcoolisé vient vous parler. Il vous propose de vous offrir une récompense si vous le battez dans son domaine de prédilection, la pétanque"));
        graph.addNode("PartiePetanque", new ChanceNode("PartiePetanque", "Vous jouez avec l'homme."));
        graph.addNode("MeilleureArme", new DecisionNode("MeilleureArme", "Vous êtes un as de la pétanque, tirez et pointez comme si vous faisiez ça depuis votre enfance. L'homme reconnait sa défaite, et vous offre un bâton magique qui semble en bien meilleur état que le votre."));
        graph.addNode("PartiePerdue", new DecisionNode("PartiePerdue", "Vous faites tomber la boule sur vos pieds et criez de douleur. Vous ne gagnez pas ce match."));
        graph.addNode("AubergisteParler", new DecisionNode("AubergisteParler", "L'aubergiste vous reconnait et vous demande ce que vous rechercher."));
        graph.addNode("AubergePierreElem", new DecisionNode("AubergePierreElem", "C'est une relique légendaire, censée renfermer la puissance brute des éléments. On dit qu'elle a été perdue il y a des siècles... Vous devriez parler à l'historien de la Bibliothèque des Anciens, il en sait sûrement plus."));
        graph.addNode("AubergeEmplacementBibliotheque", new DecisionNode("AubergeEmplacementBibliotheque", "La Bibliothèque des Anciens se trouve à l'Est de la ville, non loin d'ici. Vous devriez pouvoir voir l'emplacement de l'historien sur votre carte."));
        graph.addNode("AubergePersonnesUtiles", new DecisionNode("AubergePersonnesUtiles", "L'historien est sans doute la personne qui s'y connait le mieux. Vous pourriez tout de même aller voir la guérisseuse au sud du village pour récupérer des potions spéciales pour vous protéger des dangers des donjons."));
        graph.addNode("AubergePotions", new DecisionNode("AubergePotions", "Si vous voulez vous préparer pour votre aventure, allez voir la guérisseuse. Elle aura peut-être des potions pour vous, si elle vous trouve suffisamment sympathique."));

     // Choix dans la bibliothèque
        graph.addNode("BibliothequePostIntro", new DecisionNode("BibliothequePostIntro", "Vous entrez dans une petite bibliothèque renfermant des livres poussiéreux. L'historien vous accueille et vous demande la raison de votre visite."));
        graph.addNode("BibliothequePierreElem", new DecisionNode("BibliothequePierreElem", "Historien: La Pierre Élémentaire est une relique ancienne. Elle amplifie les pouvoirs de ceux qui la possèdent. Après avoir effectué mes recherches, je pense qu'elle se trouve dans le Sanctuaire au nord est du village. Cependant il est rempli de créatures dangereuses, et je n'ai pas la force nécessaire pour m'y aventurer. Peut-être y parviendrez-vous?"));

        graph.addNode("CarteCombat1", new CombatNode("CarteCombat1", "En vous promenant vous tombez sur un monstre dangereux, un gobelin ! Il vous attaque.", death, gobelin1));
        graph.addNode("CarteCombat2", new CombatNode("CarteCombat2", "En voici un deuxième! Préparez-vous au combat.", death, gobelin3));

     // Aller dans la forêt
        graph.addNode("Foret", new DecisionNode("Foret", "En entrant dans le Sanctuaire, vous sentez l'atmosphère s'alourdir. En inspectant autour de vous, vous voyez une étendue d'arbres à perte de vue, qui semblent chuchoter des secrets anciens à chaque souffle de vent."));
        graph.addNode("ForetChemin", new ChanceNode("ForetChemin", "")); // Est ce que le chemin choisi sera le bon?
        graph.addNode("ForetMauvaisChemin", new DecisionNode("ForetMauvaisChemin", "Vous vous perdez dans la forêt. Que voulez-vous faire?"));
        graph.addNode("MortForet", new TerminalNode("MortForet", "Vous ne connaissez pas la forêt et continuez à avancer malgré tout. Des plantes carnivores vous attrapent et vous mangent."));
        graph.addNode("ForetBonChemin", new DecisionNode("ForetBonChemin", "Vous trouvez votre chemin."));
        graph.addNode("ForetMonstre", new ChanceNode("ForetMonstre", "Vous entendez du bruit.")); // Quel monstre va être choisi?
        graph.addNode("CombatSlime1", new CombatNode("CombatSlime1", "Vous tombez nez à nez avec un slime qui vous attaque!", death, slime1));
        graph.addNode("CombatGobelin2", new CombatNode("CombatGobelin2", "Vous tombez nez à nez avec un gobelin qui vous attaque!", death, gobelin2));
        graph.addNode("CombatChimere", new CombatNode("CombatChimere", "Vous voyez la Pierre juste devant vos yeux. Une créature surgit alors, une chimère protégeant la pierre qui fonce sur vous!", death, chimere));
        graph.addNode("CombatChimereGagne", new DecisionNode("CombatChimereGagne", "La Pierre scintille devant vous. Que voulez-vous en faire?"));
        graph.addNode("DetruirePierre", new ChanceNode("DetruirePierre", "La Pierre est brisée en mille morceaux à vos pieds. La forêt elle même semble vous reprocher votre action, semblant plus sombre et menaçante qu'à votre arrivée. Vous quittez la forêt."));

        // Ramener Pierre à son clan ou à l'autre : fin de l'histoire
        graph.addNode("PierrePourClanElement", new TerminalNode("PierrePourClanElement", "A écrire selon la classe du joueur."));
        graph.addNode("PierrePourClanEnchanteur", new TerminalNode("PierrePourClanEnchanteur", "A écrire selon la classe du joueur."));

        // Pierre détruite : fin de l'histoire
        graph.addNode("FinPaix", new TerminalNode("FinPaix", "A écrire selon la classe du joueur."));
        graph.addNode("FinConflit", new TerminalNode("FinConflit", "A écrire selon la classe du joueur."));

        // Choix avec guérisseuse
        graph.addNode("Guerisseuse", new DecisionNode("Guerisseuse", "Vous voyez une maison et voyez la guérisseusse s'occuper de son jardin. Elle vous voit : Bonjour, que faites-vous ici?" ));
        graph.addNode("GuerisseuseContreGuerre", new DecisionNode("GuerisseuseContreGuerre", "Et pourquoi voulez-vous des informations sur cette Pierre? Vous aussi vous voulez la récupérer ? Pour pouvoir faire encore plus de mal autour de vous? Non merci"));
        graph.addNode("GuerisseuseFinDiscussion", new DecisionNode("GuerisseuseFinDiscussion", "Nous avons suffisamment discuté. Au revoir."));
        graph.addNode("GuerisseusePlusInfo", new ChanceNode("GuerisseusePlusInfo", "Je ne souhaite pas faire de mal, mais j'ai besoin de ces informations"));
        graph.addNode("GuerisseusePotionInfo", new DecisionNode("GuerisseusePotionInfo", "Elle se radoucit : Je n'ai pas plus d'informations à donner. Mais je vous crois et je vous donne ces potions. J'espère qu'elles seront utiles dans votre quête."));
        graph.addNode("GuerisseuseAider", new DecisionNode("GuerisseuseAider", "Est-ce que vous pourriez aider...? Oui je crois bien. Notre source d'eau dégage d'étranges énergies depuis plusieurs jours. Allez à la rivière enquêter, et revenez vers moi."));
        graph.addNode("GuerisseusePotionAider", new DecisionNode("GuerisseusePotionAider", "Je sens que l'eau s'est purifiée. Je vous remercie pour votre aide. Je n'ai malheuresement pas plus d'informations sur la Pierre. Prenez donc ces potions et acceptez ma reconnaissance."));

        // Quête de la rivière
        graph.addNode("Source", new DecisionNode("Source", "Vous arrivez à la rivière. Plus que d'étranges énergies, l'apparence de l'eau est tout à fait étrange, légèrement verte."));
        graph.addNode("SourceEauMagie", new ChanceNode("SourceEauMagie", "Vous utilisez votre magie pour purifier l'eau."));
        graph.addNode("SlimeGeant", new CombatNode("SlimeGeant", "C'était un slime gigantesque qui polluait l'eau et la rendait gluante! Il n'apprécie pas votre agitation et attaque.", death, queenslime));
        graph.addNode("SourceProblemeResolu", new DecisionNode("SourceProblemeResolu", "L'eau redevient d'un bleu azur digne des piscines les plus chlorées."));
  

        // Enregistrement à l'auberge
        graph.addArc("introduction", "explication", "Est-ce que vous pouvez m'expliquer en quoi consistent les clans?");
        graph.addArc("introduction", "rejoindre", "Je suis ici pour rejoindre un clan");

        // Explication des clans
        graph.addArc("explication", "clanElement", "Je suis un sorcier des éléments");
        graph.addArc("explication", "clanEnchanteur", "Je suis un sorcier Enchanteur");
        graph.addArc("explication", "humain", "Je suis juste un humain...");

        // Choix de compétence pour les humains
        graph.addArc("rejoindre", "clanElement", "Je suis un sorcier des éléments");
        graph.addArc("rejoindre", "clanEnchanteur", "Je suis un sorcier Enchanteur");
        graph.addArc("rejoindre", "humain", "Je suis un simple humain.");
        graph.addArc("humain", "humainAvecCompetence", "je suis ingénieur");
        graph.addArc("humain", "humainAvecCompetence", "je suis combattant");
        graph.addArc("humain", "humainAvecCompetence", "je suis persuasif");

        graph.addArc("humainAvecCompetence", "finHumain", "Je veux rejoindre clan des Eléments");
        graph.addArc("humainAvecCompetence", "finHumain", "Je veux rejoindre clan des Enchanteur");
        graph.addArc("humainAvecCompetence", "finHumain", "Je ne veux pas rejoindre de clan.");

        
        //Introduction du clan des éléments
        graph.addArc("missionClanElement", "affiniteElement", "J'ai une affinité très forte avec le feu");
        graph.addArc("missionClanElement", "affiniteElement", "J'ai une affinité très forte avec l' eau");
        graph.addArc("missionClanElement", "affiniteElement", "J'ai une affinité très forte avec la terre");
        graph.addArc("missionClanElement", "affiniteElement", "J'ai une affinité très forte avec l' air");
        graph.addArc("affiniteElement", "maitreClanElementIntro3", "Ecouter le maitre du clan");
        graph.addArc("maitreClanElementIntro3", "AubergePostIntro", "Se rendre à l'auberge.");
        graph.addArc("maitreClanElementIntro3", "BibliothequePostIntro", "Se rendre à la bibliothèque chercher l'historien.");
        graph.addArc("maitreClanElementIntro3", "CarteCombat1", "Chercher un combat.");
        graph.addArc("clanElement", "maitreClanElementIntro1", "Se rendre voir le maitre du clan");
        graph.addArc("maitreClanElementIntro1", "maitreClanElementIntro2", "Continuer");
        graph.addArc("maitreClanElementIntro2", "missionClanElement", "Oui, je suis prêt à aider et à me préparer.");
        graph.addArc("maitreClanElementIntro2", "demission", "Cette mission me semble trop conséquente.");

        
        //Introduction du clan des enchanteurs
        graph.addArc("clanEnchanteur", "maitreClanEnchanteurIntro1", "Se rendre dans le clan des Enchanteurs.");
        graph.addArc("maitreClanEnchanteurIntro1", "missionClanEnchanteur", "Oui, je suis prêt à aider et à me préparer.");
        graph.addArc("maitreClanEnchanteurIntro1", "refusQuete", "La tâche me parait trop complexe...");
        graph.addArc("refusQuete", "missionClanEnchanteur", "Très bien, j'accepte de vous aider");
        graph.addArc("refusQuete", "demission", "Désolé.e mais trouvez une autre personne.");
        graph.addArc("missionClanEnchanteur", "maitreClanEnchanteurIntro2", "Ecouter le maitre du clan.");
        graph.addArc("maitreClanEnchanteurIntro2", "AubergePostIntro", "Se rendre à l'auberge.");
        graph.addArc("maitreClanEnchanteurIntro2", "BibliothequePostIntro", "Se rendre à la bibliothèque chercher l'historien.");
        graph.addArc("maitreClanEnchanteurIntro2", "CarteCombat1", "Chercher un combat");
        

        // combats sur la carte
        graph.addArc("CarteCombat1", "CarteCombat2", "Chercher un autre combat.");
        graph.addArc("CarteCombat1", "AubergePostIntro", "Se rendre à l'auberge.");
        graph.addArc("CarteCombat1", "BibliothequePostIntro", "Aller à la bibliothèque chercher l'historien.");
        graph.addArc("CarteCombat2", "AubergePostIntro", "Se rendre à l'auberge.");
        graph.addArc("CarteCombat2", "BibliothequePostIntro", "Aller à la bibliothèque chercher l'historien.");
 
        // Auberge quête 
        graph.addArc("AubergePostIntro", "PartiePetanque", "Accepter de jouer.");
        graph.addArc("AubergePostIntro", "AubergisteParler", "Refuser de jouer et aller voir l'aubergiste.");
        graph.addArc("PartiePetanque", "MeilleureArme", "Vous êtes un as de la pétanque, tirez et pointez comme si vous faisiez ça depuis votre enfance.");
        graph.addArc("PartiePetanque", "PartiePerdue", "Vous perdez.");
        
        graph.addArc("PartiePerdue", "AubergisteParler", " Vous essayez d'oublier ce moment et allez voir l'aubergiste.");
        graph.addArc("MeilleureArme", "AubergisteParler", "Vous vous dirigez vers l'aubergiste, fièr.e de votre victoire.");
        
        graph.addArc("AubergisteParler", "AubergePersonnesUtiles", "Y a-t-il des personnes en ville qui s'y connaissent sur les légendes?");
        graph.addArc("AubergisteParler", "AubergePotions", "Où pourrais-je me préparer pour mon aventure?");
        
        graph.addArc("AubergePersonnesUtiles", "AubergePierreElem", "Et vous, en savez vous plus sur la Pierrre Elementaire?");
        graph.addArc("AubergePierreElem", "AubergeEmplacementBibliotheque", "Où puis-je trouver la bibliothèque?");
        graph.addArc("AubergePierreElem", "BibliothequePostIntro", "Aller à la bibliothèque.");
        graph.addArc("AubergePierreElem", "Guerisseuse", "Aller voir la guérisseuse.");
        graph.addArc("AubergeEmplacementBibliotheque", "BibliothequePostIntro", "Aller à la bibliothèque.");
        graph.addArc("AubergeEmplacementBibliotheque", "Guerisseuse", "Aller voir la guérisseuse.");
        
        graph.addArc("AubergePotions", "AubergeEmplacementBibliotheque", "Sauriez-vous où se trouve l'historien?");
        graph.addArc("AubergePotions", "Guerisseuse", "Aller voir la guérisseuse.");
        
        
        //Bibliothèque quête
        graph.addArc("BibliothequePostIntro", "BibliothequePierreElem", "Pourriez-vous me donner des informations sur la Pierre des éléments?");
        graph.addArc("BibliothequePierreElem", "Foret", "Aller dans la forêt");
        
        //Parler à la guérisseuse
        graph.addArc("AubergePersonnesUtiles", "Guerisseuse", "Aller voir la guérisseuse.");
        graph.addArc("Guerisseuse", "GuerisseuseContreGuerre", "Bonjour, auriez-vous des informations sur la Pierre des Elements?");
        graph.addArc("Guerisseuse", "GuerisseuseAider", "Bonjour à vous, je viens d'arriver dans ce pays, pourrais-je vous aider d'une quelconque manière?");
        graph.addArc("GuerisseuseContreGuerre", "GuerisseusePlusInfo", "Je vous promet que je ne veux aucun mal.");
        graph.addArc("GuerisseusePlusInfo", "GuerisseuseFinDiscussion", "Les promesses ne suffisent pas.");
        graph.addArc("GuerisseusePlusInfo", "GuerisseusePotionInfo", "Les promesses suffisent.");
        graph.addArc("GuerisseuseContreGuerre", "GuerisseuseAider", "Que puis-je faire pour vous aider?");

        graph.addArc("GuerisseuseFinDiscussion", "BibliothequePostIntro", "Aller à la bibliothèque.");
        
        //Aider la guerisseuse
        graph.addArc("GuerisseuseAider", "Source", "Partir à la rivière.");
        graph.addArc("Source", "BibliothequePostIntro", "Rebrousser chemin et aller à la bibliothèque.");
        graph.addArc("Source", "SourceEauMagie", "Utiliser sa magie pour purifier l'eau.");
        graph.addArc("Source", "SlimeGeant", "Donner un coup dans l'eau.");
        graph.addArc("SourceEauMagie", "SlimeGeant", "Gros Slime.");
        graph.addArc("SlimeGeant", "SourceProblemeResolu", "Inspecter l'eau.");
        graph.addArc("SourceProblemeResolu", "GuerisseusePotionAider", "Aller voir la guérisseuse pour avoir sa récompense.");
        graph.addArc("SourceProblemeResolu", "BibliothequePostIntro", "Plus de temps à perdre, aller à la bibliothèque.");
        
        graph.addArc("GuerisseusePotionAider", "BibliothequePostIntro", "Aller à la bibliothèque.");
        
        
        
        // Forêt quête principale
        graph.addArc("Foret", "ForetChemin", "Avancer.");
        graph.addArc("ForetChemin", "ForetMauvaisChemin", "Vous marchez.");
        graph.addArc("ForetChemin", "ForetBonChemin", "Marcher.");
        graph.addArc("ForetMauvaisChemin", "MortForet", "Vous décidez de continuer dans cette direction.");
        graph.addArc("ForetMauvaisChemin", "ForetBonChemin", "Vous revenez sur vos pas et empruntez un autre chemin.");
        graph.addArc("ForetBonChemin", "ForetMonstre", "Vous continuez à explorer.");
        graph.addArc("ForetMonstre", "CombatSlime1", "Un bruit étrange semble parvenir d'un buisson non loin.");
        graph.addArc("ForetMonstre", "CombatGobelin2", "Vous entendez des branches craquer juste derrière vous.");
        graph.addArc("CombatSlime1", "CombatChimere", "Vous êtes fatigué.e après votre combat mais continuez à avancer.");
        graph.addArc("CombatGobelin2", "CombatChimere", "Vous êtes fatigué.e après votre combat mais continuez à avancer.");
        graph.addArc("CombatChimere", "CombatChimereGagne", "S'approcher de la Pierre.");
        
        graph.addArc("CombatChimereGagne", "PierrePourClanElement", "Apporter la Pierre au maitre du clan des Sorciers des éléments.");
        graph.addArc("CombatChimereGagne", "DetruirePierre", "Briser la Pierre.");
 
        graph.addArc("CombatChimereGagne", "PierrePourClanEnchanteur", "Apporter la Pierre au maitre du clan des Enchanteurs.");
        graph.addArc("CombatChimereGagne", "DetruirePierre", "Briser la Pierre.");
        graph.addArc("DetruirePierre", "FinPaix", "Vous expliquez au maitre du clan que vous avez détruit la Pierre.");
        graph.addArc("DetruirePierre", "FinConflit", "Vous expliquez au maitre du clan que vous avez détruit la Pierre.");
        
      
         
        Event currentPlay = graph.getGraph().get("introduction");
        currentPlay.display();

        Event nextNode = currentPlay.chooseNext();
        currentPlay = nextNode;
        currentPlay.display();

        if (currentPlay.getNom().equals("rejoindre") || currentPlay.getNom().equals("explication")) {
            nextNode = currentPlay.chooseNext();
            currentPlay = nextNode;
            //currentPlay.display();

            switch (currentPlay.getNom()) {
                case "clanElement": p = new SorcierElement(); break;
                case "clanEnchanteur": p = new SorcierSpirituel(); break;
                case "humain": p = new Humain(); break;
                	
            }


        }
        
        
     // Mise à jour des nœuds avec le joueur choisi
        for (Event node : graph.getGraph().values()) {
            node.setJoueur(p);
        }

        while (true) {
            currentPlay.display();

            nextNode = currentPlay.chooseNext();
            
            currentPlay = nextNode;
            
            if (nextNode instanceof TerminalNode) {
            	currentPlay.display();
            	break;
            }

        }
        
        
    }
}
