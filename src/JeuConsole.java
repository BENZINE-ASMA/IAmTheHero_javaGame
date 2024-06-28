

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


	public class JeuConsole {
		 public static NodesGraph creerJeu() {
			 NodesGraph graph = new NodesGraph();
			 String baseFolder = "C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\";

		        
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
		        
		        TerminalNode death = new TerminalNode("mortCombat", "Vous êtes mort bravement au combat.");
		        graph.addNode("mortCombat", death);
	        
		        graph.addNode("introduction", new SoundNode(new DecisionNode("introduction", "Vous arrivez dans l'auberge du village. L'aubergiste vous accueille et vous propose de rejoindre un des clans présentes sur le territoire."), baseFolder + "musique1.wav"));

		        graph.addNode("explication", new DecisionNode("explication", "Dans la région, deux puissants clans se disputent depuis des années : les sorciers des éléments manipulent feu, eau, terre, tandis que les sorciers Enchanteurs maîtrisent l'énergie et les âmes. Une lutte de pouvoir constante menace d'éclater en conflit ouvert."));

		        graph.addNode("rejoindre", new DecisionNode("rejoindre", "Vous pouvez rejoindre le clan des sorciers Enchanteurs ou celui des éléments. Quel type de sorcier êtes-vous ?"));

		        graph.addNode("clanEnchanteur", new DecisionNode("clanEnchanteur", "Très bien, vous êtes désormais dans le clan Enchanteur ! L'aubergiste vous indique que le clan des Enchanteurs se trouve au Sud-Est du village."));

		        graph.addNode("clanElement", new DecisionNode("clanElement", "Très bien, vous êtes désormais dans le clan des éléments ! L'aubergiste vous indique que le clan des Eléments se trouve au Nord-Est du village."));

		        graph.addNode("humain", new DecisionNode("humain", "Quelle est votre compétence ?"));

		        graph.addNode("humainAvecCompetence", new DecisionNode("humainAvecCompetence", "Vous avez la compétence de '', que voulez-vous faire ?"));

		        graph.addNode("finHumain", new TerminalNode("finHumain", "Vous ne pouvez pas rejoindre de clan, mais vous vous entendez bien avec les villageois ! Vous vous installez, faites des amis, fondez une famille, et menez une vie heureuse jusqu'à une mort paisible. FIN"));

		        graph.addNode("maitreClanElementIntro1", new DecisionNode("maitreClanElementIntro1", "Vous êtes accueilli par le Maître du Clan des Éléments. Ce dernier se présente comme le gardien de leur pouvoir ancestral, luttant depuis des siècles pour maintenir leur suprématie face aux Sorciers Enchanteurs, leurs rivaux jurés."));

		        graph.addNode("maitreClanElementIntro2", new DecisionNode("maitreClanElementIntro2", "Il vous explique que leur clan est sur le point d'accomplir une quête cruciale pour renforcer leur position et prendre l'avantage sur leurs adversaires. Il insiste sur le danger et la nécessité d'une bonne préparation. "));

		        graph.addNode("maitreClanElementIntro3", new DecisionNode("maitreClanElementIntro3", "Le Maître vous conseille de vous informer auprès de l'Aubergiste et de l'Historien du village, soulignant qu'ils pourraient détenir des informations vitales. Il encourage également à combattre des monstres pour gagner en puissance, et vous offre des potions."));

		        graph.addNode("maitreClanEnchanteurIntro1", new DecisionNode("maitreClanEnchanteurIntro1", "Le Maître du Clan Enchanteur vous accueille, exprimant le besoin urgent de protéger les reliques des Sorciers des Éléments. Il évoque la menace que représente la Pierre des Éléments, une source de pouvoir convoitée par leurs ennemis depuis des siècles."));

		        graph.addNode("refusQuete", new DecisionNode("refusQuete", "Il vous informe que ce choix pourrait avoir des conséquences désastreuses sur le monde."));

		        graph.addNode("maitreClanEnchanteurIntro2", new DecisionNode("maitreClanEnchanteurIntro2", "Il vous recommande de vous entretenir avec l'Aubergiste et l'Historien du village pour recueillir des informations. Il vous informe que le sort Explosion d'Ames gagne en puissance avec chaque ennemi vaincu, mais qu'à chaque utilisation, le nombre d'âme revient à zéro. Il vous offre des potions pour renforcer vos pouvoirs lors des combats."));

		        graph.addNode("demission", new TerminalNode("demission", "Le Maître du Clan regrette votre refus de participer à leur mission cruciale. Il exprime sa déception et vous exclu du clan. Vous continuerez désormais vos aventures seul.e. FIN"));

		        graph.addNode("missionClanElement", new DecisionNode("missionClanElement", "Votre mission est de retrouver la Pierre des Éléments, une relique de grande puissance. Avant de parler davantage de la mission, parles un peu de toi. Avec quel élément as-tu le plus d'affinité ?"));

		        graph.addNode("missionClanEnchanteur", new DecisionNode("missionClanEnchanteur", "Ta mission est de récupérer la Pierre Élémentaire, renfermant un grand pouvoir."));

		        graph.addNode("affiniteElement", new DecisionNode("affiniteElement", "Tu as donc une affinité avec '', très bien. Tes sorts utilisant cet élément auront donc davantage de puissance que les autres. N'hésite pas à les utiliser."));

		        graph.addNode("AubergePostIntro", new DecisionNode("AubergePostIntro", "L'auberge n'a pas changé depuis votre dernière visite. Alors que vous vous apprêtiez à parler à l'aubergiste, un homme armé d'une bière s'approche de vous. Il vous propose une récompense si vous le battez à son jeu favori, la pétanque."));

		        graph.addNode("PartiePetanque", new ChanceNode("PartiePetanque", "Une partie de pétanque est engagée avec l'homme."));

		        graph.addNode("MeilleureArme", new DecisionNode("MeilleureArme", "Votre maîtrise surprend l'homme, qui admet sa défaite et vous offre un bâton magique en bien meilleur état que le vôtre."));

		        graph.addNode("PartiePerdue", new DecisionNode("PartiePerdue", "Malheureusement, vous faites tomber la boule sur vos pieds, criant de douleur. Vous ne gagnez pas ce match."));

		        graph.addNode("AubergisteParler", new DecisionNode("AubergisteParler", "L'aubergiste vous reconnaît et vous demande ce que vous recherchez."));

		        graph.addNode("AubergePierreElem", new DecisionNode("AubergePierreElem", "La Pierre des Éléments est une relique légendaire, réputée pour renfermer la puissance brute des éléments. On dit qu'elle a été perdue il y a des siècles... L'historien de la Bibliothèque des Anciens en sait sûrement plus."));

		        graph.addNode("AubergeEmplacementBibliotheque", new DecisionNode("AubergeEmplacementBibliotheque", "La Bibliothèque des Anciens se trouve à l'Est de la ville, à proximité d'ici. Vous devriez voir l'emplacement de l'historien sur votre carte."));
		        graph.addNode("AubergePersonnesUtiles", new DecisionNode("AubergePersonnesUtiles", "L'historien est l'expert incontesté en la matière. Vous pourriez également visiter la guérisseuse au sud du village pour des potions spéciales avant de vous aventurer dans les donjons."));
		        graph.addNode("AubergePotions", new DecisionNode("AubergePotions", "Pour vous préparer, consultez la guérisseuse. Elle pourrait vous proposer des potions si elle vous apprécie."));

		        graph.addNode("BibliothequePostIntro", new DecisionNode("BibliothequePostIntro", "Vous entrez dans une petite bibliothèque renfermant des livres poussiéreux. L'historien vous accueille et vous demande la raison de votre visite."));
		        graph.addNode("BibliothequePierreElem", new DecisionNode("BibliothequePierreElem", "Historien : La Pierre Élémentaire est une relique ancienne. Elle amplifie les pouvoirs de ceux qui la possèdent. Après avoir effectué mes recherches, je pense qu'elle se trouve dans le Sanctuaire au nord-est du village. Cependant, il est rempli de créatures dangereuses, et je n'ai pas la force nécessaire pour m'y aventurer. Peut-être y parviendrez-vous?"));

		        graph.addNode("CarteCombat1", new SoundNode(new CombatNode("CarteCombat1", "En vous promenant, vous tombez sur un monstre dangereux, un gobelin ! Il vous attaque.", death, gobelin1), baseFolder + "combat.wav"));
		        graph.addNode("CarteCombat2", new SoundNode(new CombatNode("CarteCombat2", "En voici un deuxième ! Préparez-vous au combat.", death, gobelin3), baseFolder + "combat.wav"));

		        graph.addNode("Foret", new DecisionNode("Foret", "En entrant dans le Sanctuaire, l'atmosphère s'alourdit. Une étendue d'arbres à perte de vue semble chuchoter des secrets anciens à chaque souffle de vent."));
		        graph.addNode("ForetChemin", new ChanceNode("ForetChemin", "Est-ce que le chemin choisi sera le bon?"));
		        graph.addNode("ForetMauvaisChemin", new DecisionNode("ForetMauvaisChemin", "Vous vous perdez dans la forêt. Que voulez-vous faire?"));
		        graph.addNode("MortForet", new TerminalNode("MortForet", "Vous ne connaissez pas la forêt et continuez à avancer malgré tout. Des plantes carnivores vous attrapent et vous mangent."));
		        graph.addNode("ForetBonChemin", new DecisionNode("ForetBonChemin", "Vous trouvez votre chemin."));
		        graph.addNode("ForetMonstre", new ChanceNode("ForetMonstre", "Vous entendez du bruit."));
		        graph.addNode("CombatSlime1", new CombatNode("CombatSlime1", "Vous tombez nez à nez avec un slime qui vous attaque!", death, slime1));
		        graph.addNode("CombatGobelin2", new CombatNode("CombatGobelin2", "Vous tombez nez à nez avec un gobelin qui vous attaque!", death, gobelin2));
		        graph.addNode("CombatChimere", new CombatNode("CombatChimere", "Vous voyez la Pierre juste devant vos yeux. Une créature surgit alors, une chimère protégeant la pierre qui fonce sur vous!", death, chimere));
		        graph.addNode("CombatChimereGagne", new DecisionNode("CombatChimereGagne", "La Pierre scintille devant vous. Que voulez-vous en faire?"));
		        
		        graph.addNode("DetruirePierre", new ChanceNode("DetruirePierre", "La Pierre est brisée en mille morceaux à vos pieds. La forêt elle-même semble vous reprocher votre action, semblant plus sombre et menaçante qu'à votre arrivée. Vous quittez la forêt."));
		        
		        graph.addNode("PierrePourClanElement", new TerminalNode("PierrePourClanElement", "A écrire selon la classe du joueur."));
		        graph.addNode("PierrePourClanEnchanteur", new TerminalNode("PierrePourClanEnchanteur", "A écrire selon la classe du joueur."));
		        
		        graph.addNode("FinPaix", new TerminalNode("FinPaix", "Le Maître du Clan, extrêmement déçu par votre décision, vous informe que la Pierre était leur seule chance de gagner. Les deux clans devront envisager la paix. Vous perdez tout prestige et êtes renvoyé.e. Vous devrez poursuivre vos aventures seul.e. FIN"));
		        graph.addNode("FinConflit", new TerminalNode("FinConflit", "Le Maître du Clan s'affole en apprenant la nouvelle. Refusant toute paix ou alliance, il vous considère comme un traître et lance son sort le plus puissant : Immolation. Vous mourrez dans d'atroces souffrances. FIN"));
		        
		        graph.addNode("Guerisseuse", new DecisionNode("Guerisseuse", "Vous découvrez une petite maison où une guérisseuse s'occupe de son jardin. Elle vous remarque et vous salue : Bonjour, que faites-vous ici?"));
		        graph.addNode("GuerisseuseContreGuerre", new DecisionNode("GuerisseuseContreGuerre", "Vous demandez des informations sur la Pierre, mais la guérisseuse vous regarde avec suspicion : Et pourquoi voulez-vous des informations sur cette Pierre? Vous aussi vous voulez la récupérer? Pour pouvoir faire encore plus de mal autour de vous? Non merci."));
		        graph.addNode("GuerisseuseFinDiscussion", new DecisionNode("GuerisseuseFinDiscussion", "Elle coupe court à la conversation : Nous avons suffisamment discuté. Au revoir."));
		        graph.addNode("GuerisseusePlusInfo", new ChanceNode("GuerisseusePlusInfo", "Vous tentez de la convaincre : Je ne souhaite pas faire de mal, mais j'ai besoin de ces informations."));
		        graph.addNode("GuerisseusePotionInfo", new DecisionNode("GuerisseusePotionInfo", "Elle semble se radoucir : Je n'ai pas plus d'informations à donner. Mais je vous crois et je vous donne ces potions. J'espère qu'elles seront utiles dans votre quête."));
		        graph.addNode("GuerisseuseAider", new DecisionNode("GuerisseuseAider", "Vous lui demandez son aide concernant une étrange source d'eau : Est-ce que vous pourriez aider...? Notre source d'eau dégage d'étranges énergies depuis plusieurs jours. Allez à la rivière enquêter, et revenez vers moi."));
		        graph.addNode("GuerisseusePotionAider", new DecisionNode("GuerisseusePotionAider", "Après votre aide, elle exprime sa gratitude : Je sens que l'eau s'est purifiée. Merci pour votre aide. Je n'ai malheureusement pas plus d'informations sur la Pierre. Prenez ces potions et acceptez ma reconnaissance."));
		        
		        graph.addNode("Source", new DecisionNode("Source", "Vous arrivez à la rivière. Plus que d'étranges énergies, l'apparence de l'eau est tout à fait étrange, légèrement verte."));
		        graph.addNode("SourceEauMagie", new ChanceNode("SourceEauMagie", "Vous utilisez votre magie pour purifier l'eau."));
		        graph.addNode("SlimeGeant", new CombatNode("SlimeGeant", "Un slime gigantesque, qui polluait l'eau et la rendait gluante! Il n'apprécie pas votre agitation et attaque.", death, queenslime));
		        graph.addNode("SourceProblemeResolu", new DecisionNode("SourceProblemeResolu", "L'eau redevient d'un bleu azur digne des piscines les plus chlorées."));
 
		        
		        
		        // Enregistrement à l'auberge
		        graph.addArc("introduction", "explication", "Pourriez-vous m'expliquer en quoi consistent les clans?");
		        graph.addArc("introduction", "rejoindre", "Je suis ici pour rejoindre un clan.");

		        // Explication des clans
		        graph.addArc("explication", "clanElement", "Je suis un sorcier des éléments.");
		        graph.addArc("explication", "clanEnchanteur", "Je suis un sorcier enchanteur.");
		        graph.addArc("explication", "humain", "Je suis juste un humain...");

		        // Choix de compétence pour les humains
		        graph.addArc("rejoindre", "clanElement", "Je suis un sorcier des éléments.");
		        graph.addArc("rejoindre", "clanEnchanteur", "Je suis un sorcier enchanteur.");
		        graph.addArc("rejoindre", "humain", "Je suis un simple humain.");
		        graph.addArc("humain", "humainAvecCompetence", "Je suis ingénieur.");
		        graph.addArc("humain", "humainAvecCompetence", "Je suis combattant.");
		        graph.addArc("humain", "humainAvecCompetence", "Je suis persuasif.");

		        graph.addArc("humainAvecCompetence", "finHumain", "Je veux rejoindre le clan des éléments.");
		        graph.addArc("humainAvecCompetence", "finHumain", "Je veux rejoindre le clan des enchanteurs.");
		        graph.addArc("humainAvecCompetence", "finHumain", "Je ne veux pas rejoindre de clan.");

		        // Introduction du clan des éléments
		        graph.addArc("missionClanElement", "affiniteElement", "J'ai une affinité très forte avec le feu");
		        graph.addArc("missionClanElement", "affiniteElement", "J'ai une affinité très forte avec l' eau");
		        graph.addArc("missionClanElement", "affiniteElement", "J'ai une affinité très forte avec la terre");
		        graph.addArc("missionClanElement", "affiniteElement", "J'ai une affinité très forte avec l' air");
		        graph.addArc("affiniteElement", "maitreClanElementIntro3", "Écouter le maître du clan.");
		        graph.addArc("maitreClanElementIntro3", "AubergePostIntro", "Se rendre à l'auberge.");
		        graph.addArc("maitreClanElementIntro3", "BibliothequePostIntro", "Se rendre à la bibliothèque chercher l'historien.");
		        graph.addArc("maitreClanElementIntro3", "CarteCombat1", "Chercher un combat.");
		        graph.addArc("clanElement", "maitreClanElementIntro1", "Se rendre voir le maître du clan.");
		        graph.addArc("maitreClanElementIntro1", "maitreClanElementIntro2", "Continuer.");
		        graph.addArc("maitreClanElementIntro2", "missionClanElement", "Oui, je suis prêt à aider et à me préparer.");
		        graph.addArc("maitreClanElementIntro2", "demission", "Cette mission me semble trop conséquente.");

		        // Introduction du clan des enchanteurs
		        graph.addArc("clanEnchanteur", "maitreClanEnchanteurIntro1", "Se rendre dans le clan des enchanteurs.");
		        graph.addArc("maitreClanEnchanteurIntro1", "missionClanEnchanteur", "Oui, je suis prêt à aider et à me préparer.");
		        graph.addArc("maitreClanEnchanteurIntro1", "refusQuete", "La tâche me paraît trop complexe...");
		        graph.addArc("refusQuete", "missionClanEnchanteur", "Très bien, j'accepte de vous aider.");
		        graph.addArc("refusQuete", "demission", "Désolé.e mais trouvez une autre personne.");
		        graph.addArc("missionClanEnchanteur", "maitreClanEnchanteurIntro2", "Écouter le maître du clan.");
		        graph.addArc("maitreClanEnchanteurIntro2", "AubergePostIntro", "Se rendre à l'auberge.");
		        graph.addArc("maitreClanEnchanteurIntro2", "BibliothequePostIntro", "Se rendre à la bibliothèque chercher l'historien.");
		        graph.addArc("maitreClanEnchanteurIntro2", "CarteCombat1", "Chercher un combat.");

		        // Combats sur la carte
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

		        graph.addArc("PartiePerdue", "AubergisteParler", "Vous essayez d'oublier ce moment et allez voir l'aubergiste.");
		        graph.addArc("MeilleureArme", "AubergisteParler", "Vous vous dirigez vers l'aubergiste, fier.e de votre victoire.");

		        graph.addArc("AubergisteParler", "AubergePersonnesUtiles", "Y a-t-il des personnes en ville qui s'y connaissent sur les légendes?");
		        graph.addArc("AubergisteParler", "AubergePotions", "Où pourrais-je me préparer pour mon aventure?");

		        graph.addArc("AubergePersonnesUtiles", "AubergePierreElem", "Et vous, en savez-vous plus sur la Pierre élémentaire?");
		        graph.addArc("AubergePierreElem", "AubergeEmplacementBibliotheque", "Où puis-je trouver la bibliothèque?");
		        graph.addArc("AubergePierreElem", "BibliothequePostIntro", "Aller à la bibliothèque.");
		        graph.addArc("AubergePierreElem", "Guerisseuse", "Aller voir la guérisseuse.");
		        graph.addArc("AubergeEmplacementBibliotheque", "BibliothequePostIntro", "Aller à la bibliothèque.");
		        graph.addArc("AubergeEmplacementBibliotheque", "Guerisseuse", "Aller voir la guérisseuse.");

		        graph.addArc("AubergePotions", "AubergeEmplacementBibliotheque", "Sauriez-vous où se trouve l'historien?");
		        graph.addArc("AubergePotions", "Guerisseuse", "Aller voir la guérisseuse.");

		        // Bibliothèque quête
		        graph.addArc("BibliothequePostIntro", "BibliothequePierreElem", "Pourriez-vous me donner des informations sur la Pierre des éléments?");
		        graph.addArc("BibliothequePierreElem", "Foret", "Aller dans la forêt.");

		        // Parler à la guérisseuse
		        graph.addArc("AubergePersonnesUtiles", "Guerisseuse", "Aller voir la guérisseuse.");
		        graph.addArc("Guerisseuse", "GuerisseuseContreGuerre", "Bonjour, auriez-vous des informations sur la Pierre des Éléments?");
		        graph.addArc("Guerisseuse", "GuerisseuseAider", "Bonjour à vous, je viens d'arriver dans ce pays, pourrais-je vous aider d'une quelconque manière?");
		        graph.addArc("GuerisseuseContreGuerre", "GuerisseusePlusInfo", "Je vous promets que je ne veux aucun mal.");
		        graph.addArc("GuerisseusePlusInfo", "GuerisseuseFinDiscussion", "Les promesses ne suffisent pas.");
		        graph.addArc("GuerisseusePlusInfo", "GuerisseusePotionInfo", "Les promesses suffisent.");
		        graph.addArc("GuerisseuseContreGuerre", "GuerisseuseAider", "Que puis-je faire pour vous aider?");

		        graph.addArc("GuerisseuseFinDiscussion", "BibliothequePostIntro", "Aller à la bibliothèque.");

		        // Aider la guérisseuse
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

		        graph.addArc("CombatChimereGagne", "PierrePourClanElement", "Apporter la Pierre au maître du clan des Sorciers des éléments.");
		        graph.addArc("CombatChimereGagne", "DetruirePierre", "Briser la Pierre.");

		        graph.addArc("CombatChimereGagne", "PierrePourClanEnchanteur", "Apporter la Pierre au maître du clan des Enchanteurs.");
		        graph.addArc("CombatChimereGagne", "DetruirePierre", "Briser la Pierre.");
		        graph.addArc("DetruirePierre", "FinPaix", "Vous expliquez au maître du clan que vous avez détruit la Pierre.");
		        graph.addArc("DetruirePierre", "FinConflit", "Vous expliquez au maître du clan que vous avez détruit la Pierre.");


	      
	     return graph;
		 }
	}