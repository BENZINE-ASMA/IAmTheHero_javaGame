package Interface;

import javax.swing.*;

import Components.Case;
import Components.CaseCLanA;
import Components.CaseClanB;

import Components.Donjon;
import Components.Riviere;
import Components.Sanctuaire;
import Components.Village;
import Interface.PanelComponent;
import entities.Direction;
import entities.EntiteMobile;
import entities.Humain;
import entities.Personnage;
import entities.SorcierElement;
import entities.SorcierSpirituel;
import representation.*;
import representation.Event;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class FrameComponent extends JFrame implements GameInterface,Serializable, KeyListener {
    private static final long serialVersionUID = 1L;
    private Personnage p = new Personnage();
    PanelComponent panel;
    JTextField nameField;
    private JButton startButton;
    private JButton quitButton;
    private JButton loadButton;
    private NodesGraph graph;
    private Node currentPlay;
    private boolean waitForPlayerMove = false;
    private Class<? extends Case> targetCaseClass = null;

    public FrameComponent() {
        // Initialize graph and currentPlay
        graph = createGraph();
        currentPlay = graph.getGraph().get("introduction");

        // Configure the main window
        setTitle("Jeu d'aventure");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel nameLabel = new JLabel("Enter Name:");
        nameField = new JTextField(20);

        startButton = new JButton("Commencer");
        quitButton = new JButton("Quitter");

        loadButton = new JButton("Load Game");

        // Add listeners for the buttons
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleStart();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleQuit();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = nameField.getText();
                if (!playerName.isEmpty()) {
                    loadGame(playerName);
                } else {
                    JOptionPane.showMessageDialog(FrameComponent.this, "Please enter your name to load the game.");
                }
            }
        });

        // Organize components into panels
        JPanel inputPanel = new JPanel();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

        JPanel loadPanel = new JPanel();
        loadPanel.add(loadButton);

        // Add panels to the main window
        getContentPane().add(inputPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(loadPanel, BorderLayout.NORTH);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    

    private void handleStart() {
        String name = nameField.getText();
        if (!name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bienvenue " + name + "! Le jeu va commencer.");
            showGameInterface();
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nom.");
        }
    }

    private void handleQuit() {
        System.exit(0);
    }

    private void showGameInterface() {
        // Clear the current content
        getContentPane().removeAll();
        repaint();
        revalidate();

        // Create the terrain and panel
        panel = new PanelComponent(new Terrain("C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\terrain2.txt", p), this);

        // Set up the new game interface
        getContentPane().add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();

        // Display the first choice
        displayCurrentNode();
    }

    public static void majCombatNode(Personnage p, String nodeName, NodesGraph graph) {
        CombatNode cn = (CombatNode) ((graph.getGraph()).get(nodeName));
        cn.setJoueur(p);
    }
    

    public void displayCurrentNode() {
        if (currentPlay != null) {
            // Mettre à jour le panel avec le texte du nœud courant et les choix
            panel.setNodeText(currentPlay.getDescription());

            if (currentPlay instanceof InnerNode) {
                Map<String, Node> nodesSuivant = ((InnerNode) currentPlay).getNodesSuivant();
                ArrayList<String> options = new ArrayList<>(nodesSuivant.keySet());
                panel.setNodeChoices(options);
            } else {
                panel.setNodeChoices(new ArrayList<>());
            }

            panel.repaint();
        }
    }

	private NodesGraph createGraph() {
		
		NodesGraph graph = new NodesGraph();
		EntiteMobile gobelin1 = new EntiteMobile(50,50,10,14);
        gobelin1.setName("Geodfroy");
        EntiteMobile gobelin2 = new EntiteMobile(50,50,10,14);
        gobelin2.setName("Gontrant");
        EntiteMobile gobelin3 = new EntiteMobile(50,50,10,14);
        gobelin3.setName("Zappy");
        EntiteMobile slime1 = new EntiteMobile(30,30,5,8);
        slime1.setName("Slime");
        EntiteMobile chimere = new EntiteMobile(110,110,25,20);
        chimere.setName("Chimère");
        EntiteMobile queenslime = new EntiteMobile(90,90,6,6);
        queenslime.setName("Reine des Slimes");
        
        
        //graph.addSoundNode(new DecisionNode("introduction", "Bonjour et bienvenue à l'auberge de la ville ! Vous pouvez tout faire ici, acheter armes, potions, et même rejoindre les clans de notre contrée ! Je ne vous ai jamais vu ici avant, que puis-je pour vous ?"), "C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\musique1.mp3"));
        
        graph.addTerminalNode("mortCombat", "Vous êtes mort bravement au combat.");
        
		graph.addDecisionNode("introduction", "Bonjour et bienvenue à l'auberge de la ville ! Vous pouvez tout faire ici, acheter armes, potions, et même rejoindre les clans de notre contrée ! Je ne vous ai jamais vu ici avant, que puis-je pour vous ?");
        graph.addDecisionNode("explication", "Bien sûr! Dans notre région, il y a deux puissants clans qui se disputent depuis des années : les sorciers des éléments et les sorciers Enchanteurs. Les sorciers des éléments manipulent les forces naturelles telles que le feu, l'eau ou la terre, tandis que les sorciers Enchanteurs se concentrent sur la manipulation de l'énergie Enchanteurle et des âmes. Ces deux clans sont engagés dans une lutte de pouvoir perpétuelle, chacun cherchant à étendre son influence et affirmer sa supprématie. C'est une période de tension constante, et beaucoup craignent que cela ne conduise à un conflit ouvert un jour. Dis moi, quel sorcier es tu?");
        graph.addDecisionNode("rejoindre", "Bien sûr! Vous pouvez rejoindre le clan des sorciers Enchanteurs et celui des éléments. Quel type de sorcier êtes vous?");
        graph.addDecisionNode("clanEnchanteur", "[Vous signez des documents obscurs]. Très bien, vous êtes désormais dans le clan Enchanteur! Vous allez y être téléporté, au plaisir de vous revoir !");
        graph.addDecisionNode("clanElement", "[Vous signez des documents obscurs]. Très bien, vous êtes désormais dans le clan des éléments! Vous allez y être emmené directement. Merci d'être passé par chez nous et bonne chance!");
        graph.addDecisionNode("humain", "quelle est votre compétence?");
        graph.addDecisionNode("humainAvecCompetence", "vous avez la compétence de '', que voulez-vous faire?");
        graph.addTerminalNode("finHumain", "Vous ne pouvez pas rejoindre de clan, mais vous allez très bien vous entendre avec les membres du village ! L'aventure est réservée aux sorciers mais notre vie est elle aussi passionnante.\\nVous vous installez dans cette nouvelle ville et vous faites de nouveaux amis. Vous vous mariez, avez des enfants, et vivez votre vie normale heureux.se jusqu'à votre mort paisible.\nFIN");

        
        // Dialogue avec le maitre du clan des éléments
        graph.addDecisionNode("maitreClanElementIntro1", "Bienvenue, jeune sorcier. Je suis le Maître de ce clan, et je suis honoré de te rencontrer. Notre clan des Sorciers des Éléments lutte depuis des siècles pour maintenir notre position dominante sur ces terres. Nous devons nous assurer que nos pouvoirs ne tombent pas entre de mauvaises mains, notamment celles des Sorciers Enchanteurs, nos ennemis jurés.");
        graph.addDecisionNode("maitreClanElementIntro2", "Nous sommes sur le point d'accomplir une quête cruciale qui renforcera notre position et nous donnera un avantage sur nos adversaires. Pour cela, nous avons besoin de sorciers talentueux comme toi. Mais avant de te confier cette responsabilité, il est essentiel que tu te prépares adéquatement.");
        // CE NODE
        graph.addDecisionNode("maitreClanElementIntro3", "Je te conseille de parler à l'Aubergiste et à notre Historien, ils ont des peut-être des informations qui pourraient nous être utiles. N'hésite pas à combattre les monstres que tu vois avant d'y aller, tu manques encore de puissance. Prends ces potions avec toi, elles pourraient d'être utiles.");
        
        // Dialogue avec le maitre du clan des enchanteurs
        graph.addDecisionNode("maitreClanEnchanteurIntro1", "Bienvenue, jeune sorcier. Je suis le Maître de ce clan Enchanteur. Nous avons besoin de sorciers talentueux comme toi pour sécuriser nos reliques. Nos ennemis, les Sorciers des Elements, semblent déterminés à récupérer la Pierre des Elements pour leur accorder un pouvoir tout puissant. Nous ne pouvons pas les laisser faire, encore plus quand nous savons que notre clan la possédait il y a de cela quelques siècles. Vas tu nous rejoindre?");
        graph.addDecisionNode("refusQuete", "En es-tu sûr.e ? C'est le destin du monde qui en dépend");
        graph.addDecisionNode("maitreClanEnchanteurIntro2", "Je te conseille de parler à l'Aubergiste et à l'Historien dans le village. Avant cela, n'hésite pas à combattre pour t'améliorer. Les sorciers Enchanteurs ont une puissance décuplée pour chaque ennemi terrassé. Prends ces quelques potions avec toi, et bon voyage.");
        
        //Noeud de fin si refus de la quête principale
        graph.addTerminalNode("demission", "Je ne vois pas ce que tu aurais à faire au sein de notre clan si tu ne participes pas à notre objectif principal. Je me vois dans l'obligation de te congédier. Peut-être que nos chemins se croiseront un jour de nouveau.");
        
        // Ajout des missions pour chaque clan
        graph.addDecisionNode("missionClanElement", "Excellent choix. Ta mission est de retrouver la Pierre des Éléments, une relique de grande puissance. Avant de parler davantage de la mission, parle moi un peu de toi. Quel est l'élement qui te fait vibrer, celui avec lequel tu as le plus d'affinité?");
        graph.addDecisionNode("missionClanEnchanteur", "Ta mission est de récupérer notre dû, la Pierre Elementaire, renfermant un grand pouvoir.");

        // Choix Affinite pour Sorcier Element
        graph.addDecisionNode("affiniteElement", "Tu as donc une affinité avec '', très bien. Tes sorts utilisant cet élément auront donc davantage de puissance que les autres, n'hésite pas à les utiliser.");
        
  
        // Choix d'action post introduction
        // Choix dans l'auberge
        graph.addDecisionNode("AubergePostIntro", "L'auberge n'a pas changé depuis votre venue tout à l'heure. Alors que vous alliez voir l'aubergiste, un homme semblant assez alcoolisé vient vous parler. Il vous propose de vous offrir une récompense si vous le battez dans son domaine de prédilection, la pétanque");
        graph.addChanceNode("PartiePetanque", "Vous jouez avec l'homme.");
        graph.addDecisionNode("MeilleureArme", "Vous êtes un as de la pétanque, tirez et pointez comme si vous faisiez ça depuis votre enfance. L'homme reconnait sa défaite, et vous offre un bâton magique qui semble en bien meilleur état que le votre.");
        graph.addDecisionNode("PartiePerdue", "Vous faites tomber la boule sur vos pieds et criez de douleur. Vous ne gagnez pas ce match.");
        graph.addDecisionNode("AubergisteParler", "L'aubergiste vous reconnait et vous demande ce que vous rechercher.");
        graph.addDecisionNode("AubergePierreElem", "C'est une relique légendaire, censée renfermer la puissance brute des éléments. On dit qu'elle a été perdue il y a des siècles... Vous devriez parler à l'historien de la Bibliothèque des Anciens, il en sait sûrement plus.");
        graph.addDecisionNode("AubergeEmplacementBibliotheque", "La Bibliothèque des Anciens se trouve à l'Est de la ville, non loin d'ici. Vous devriez pouvoir voir l'emplacement de l'historien sur votre carte.");
        graph.addDecisionNode("AubergePersonnesUtiles", "L'historien est sans doute la personne qui s'y connait le mieux. Vous pourriez tout de même aller voir la guérisseuse au sud du village pour récupérer des potions spéciales pour vous protéger des dangers des donjons.");
        graph.addDecisionNode("AubergePotions", "Si vous voulez vous préparer pour votre aventure, allez voir la guérisseuse. Elle aura peut-être des potions pour vous, si elle vous trouve suffisamment sympathique.");
        
        
        // Choix dans la bibliotheque
        graph.addDecisionNode("BibliothequePostIntro", "Vous entrez dans une petite bibliothèque renfermant des livres poussiéreux. L'historien vous accueille et vous demande la raison de votre visite.");
        graph.addDecisionNode("BibliothequePierreElem", "Historien: La Pierre Élémentaire est une relique ancienne. Elle amplifie les pouvoirs de ceux qui la possèdent. Elle pourrait être cachée dans les Grottes au Sud ou dans la Forêt à l'Est. ");
        
        graph.addCombatNode("CarteCombat1", "Vous vous baladez pour trouver un monstre à combattre.", "mortCombat", gobelin1);
        graph.addCombatNode("CarteCombat2", "Vous cherchez un deuxième monstre.", "mortCombat", gobelin3);
        
        
        //Aller dans la forêt
        graph.addDecisionNode("Foret", "En entrant dans la Forêt des Murmures, vous êtes enveloppé par une canopée dense et lumineuse. Les arbres chuchotent des secrets anciens à chaque souffle de vent. Par où aller?");
        graph.addChanceNode("ForetChemin", ""); //Est ce que le chemin choisi sera le bon?
        graph.addDecisionNode("ForetMauvaisChemin", "Vous vous perdez dans la forêt. Que voulez-vous faire?");
        graph.addTerminalNode("MortForet", "Vous ne connaissez pas la forêt et continuez à avancer malgré tout. Des plantes carnivores vous attrapent et vous mangent.");
        graph.addDecisionNode("ForetBonChemin", "Vous trouvez votre chemin.");
        graph.addChanceNode("ForetMonstre", "Vous entendez du bruit."); //Quel monstre va être choisi?
        graph.addCombatNode("CombatSlime1", "Vous tombez nez à nez avec un slime qui vous attaque!", "mortCombat", slime1);
        graph.addCombatNode("CombatGobelin2", "Vous tombez nez à nez avec un gobelin qui vous attaque!", "mortCombat", gobelin2);
        graph.addCombatNode("CombatChimere", "Vous voyez la Pierre juste devant vos yeux. Une créature surgit alors, une chimère protégeant la pierre qui fonce sur vous!", "mortCombat", chimere);
        graph.addDecisionNode("CombatChimereGagne", "La Pierre scintille devant vous. Que voulez-vous en faire?");
        graph.addChanceNode("DetruirePierre", "La Pierre est brisée en mille morceaux à vos pieds. Qu'avez-vous fait...?\nVous quittez la forêt, qui semble plus sombre que lorsque vous êtes arrivé.e");
        
        
        
        //Ramener Pierre à son clan : fin de l'histoire
        graph.addTerminalNode("PierrePourClanElement", "Vous ramenez la Pierre au clan des éléments. Le maitre du clan vous remercie. Il a un regard triomphant, et quelque peu sournois. Votre clan survivra, le destin de l'autre semble arriver à son terme.");
        graph.addTerminalNode("PierrePourClanEnchanteur", "Vous ramenez la Pierre au clan des enchanteurs. Le maitre du clan vous remercie. Il a un regard triomphant, et quelque peu sournois. Le clan des enchanteurs survivra, et s'il est clément le clan des éléments survivra.");
        
        //Pierre Detruite : fin de l'histoire
        graph.addTerminalNode("FinPaixElement", "Maitre du clan : Comment avez vous pu détruire la Pierre! On la recherche depuis tout ce temps ! Nous ne pourrons jamais battre les Enchanteurs sans... J'imagine que nous n'avons pas le choix et devons essayer de trouver la paix avec eux. Je ne te remercie pas, mais ce qui est fait est fait. Bon vent, pars, et que je ne te revois jamais.\nVous partez en exil, mais peut être que votre action aura permis la paix");
        graph.addTerminalNode("FinConflitElement", "Maitre du clan : Comment avez vous pu détruire la Pierre! On la recherche depuis tout ce temps ! Nous ne pourrons jamais battre les Enchanteurs sans! Vous n'êtes qu'un traitre et méritez de mourir en traitre. Sachez que nous ne ferons jamais la paix avec les enchanteurs. Meurs, traitre.\nLe maitre du clan lance le sort Immolation. Vous mourrez dans d'atroces souffrances.");

        graph.addTerminalNode("FinPaixEnchanteur", "Maitre du clan : Comment avez vous pu détruire la Pierre, une relique aussi précieuse! Au moins le clan des Elements ne l'aura pas, eux qui étaient obsédés par l'idée de la trouver. Je n'aime pas ton choix mais ne te tuerai pas pour autant ne t'en fait pas. Tu nettoiras nos lattrines durant les deux prochaines années. Nous essayerons de discuter avec le clan des éléments pour parvenir à un semblant de paix.\nVotre vie ne va pas être glorieuse, mais le destin de ces terres semble s'illuminer.");
        graph.addTerminalNode("FinConflitEnchanteur", "Maitre du clan : Comment avez vous pu détruire la Pierre! On la recherche depuis tout ce temps ! Tant de pouvoir contenu dedans que nous aurions pu récupérer... Sachez que nous ne ferons jamais la paix avec les enchanteurs. Meurs.\nLe maitre du clan lance le sort j'ai oublié le nom. Vous avez la sensation horrible de vous faire transpercer par un coup d'épée. Le monde autour de vous s'assombrit, et vous tombez au sol.");


        // Choix avec guerisseuse
        graph.addDecisionNode("Guerisseuse", "Vous voyez une maison et voyez la guerisseusse s'occuper de son jardin. Elle vous voit : Bonjour, que faites-vous ici?" );
        graph.addDecisionNode("GuerisseuseContreGuerre", "Et pourquoi voulez-vous des informations sur cette Pierre? Vous aussi vous voulez la récupérer ? Pour pouvoir faire encore plus de mal autour de vous? Non merci");
        graph.addDecisionNode("GuerisseuseFinDiscussion", "Nous avons suffisamment discuté. Au revoir.");
        graph.addChanceNode("GuerisseusePlusInfo", "Je ne souhaite pas faire de mal, mais j'ai besoin de ces informations");
        graph.addDecisionNode("GuerisseusePotionInfo", "Elle se radoucit : Je n'ai pas plus d'informations à donner. Mais je vous crois et je vous donne ces potions. J'espère qu'elles seront utiles dans votre quête.");
        graph.addDecisionNode("GuerisseuseAider", "Est-ce que vous pourriez aider...? Oui je crois bien. Notre source d'eau dégage d'étranges énergies depuis plusieurs jours. Allez à la rivière enquêter, et revenez vers moi.");
        graph.addDecisionNode("GuerisseusePotionAider", "Je sens que l'eau s'est purifiée. Je vous remercie pour votre aide. Je n'ai malheuresement pas plus d'informations sur la Pierre. Prenez donc ces potions et acceptez ma reconnaissance.");

        // Quête de la rivière
        graph.addDecisionNode("Source", "Vous arrivez à la rivière. Plus que d'étranges énergies, l'apparence de l'eau est tout à fait étrange, légèrement verte.");
        graph.addChanceNode("SourceEauMagieElement", "Vous utilisez votre magie de l'eau pour la renouveller");
        graph.addChanceNode("SourceEauMagieEnchanteur", "Vous utilisez Lumière Curative pour purifier l'eau");
        graph.addCombatNode("SlimeGeant", "C'était un slime gigantesque qui polluait l'eau et la rendait gluante! Il n'apprécie pas votre agitation et attaque.", "mortCombat", queenslime);;
        graph.addDecisionNode("SourceProblemeResolu", "L'eau redevient d'un bleu azur digne des piscines les plus chlorées.");

  

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
        graph.addArc("Source", "SourceEauMagieElement", "Lancer sort magie element.");
        graph.addArc("Source", "SourceEauMagieEnchanteur", "Lancer sort magie enchanteur.");
        graph.addArc("Source", "SlimeGeant", "Donner un coup dans l'eau.");
        graph.addArc("SourceEauMagieElement", "SlimeGeant", "Gros Slime.");
        graph.addArc("SourceEauMagieEnchanteur", "SlimeGeant", "Gros Slime.");
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
        
        graph.addArc("CombatChimereGagne", "PierrePourClanElement", "Apporter la Pierre au maitre du clan des éléments.");
        graph.addArc("CombatChimereGagne", "DetruirePierre", "Briser la Pierre.");
        graph.addArc("DetruirePierre", "FinPaixElement", "Vous expliquez au maitre du clan que vous avez détruit la Pierre.");
        graph.addArc("DetruirePierre", "FinConflitElement", "Vous expliquez au maitre du clan que vous avez détruit la Pierre.");
        
        graph.addArc("CombatChimereGagne", "PierrePourClanEnchanteur", "Apporter la Pierre au maitre du clan des enchanteurs.");
        graph.addArc("CombatChimereGagne", "DetruirePierre", "Briser la Pierre.");
        graph.addArc("DetruirePierre", "FinPaixEnchanteur", "Vous expliquez au maitre du clan que vous avez détruit la Pierre.");
        graph.addArc("DetruirePierre", "FinConflitEnchanteur", "Vous expliquez au maitre du clan que vous avez détruit la Pierre.");
        
       
		return graph;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not used
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (panel != null) {
			
		
	    // Handle movement keys
	    switch (e.getKeyCode()) {
	        case KeyEvent.VK_UP -> panel.terrain.movePlayer(Direction.nord);
	        case KeyEvent.VK_DOWN -> panel.terrain.movePlayer(Direction.sud);
	        case KeyEvent.VK_LEFT -> panel.terrain.movePlayer(Direction.ouest);
	        case KeyEvent.VK_RIGHT -> panel.terrain.movePlayer(Direction.est);
	    }

	    // Check if the player is on the required case to reset waitForPlayerMove
	    if (waitForPlayerMove && targetCaseClass != null && panel.terrain.isPlayerOnCase(targetCaseClass)) {
	        waitForPlayerMove = false;
	       
	        targetCaseClass = null;  // Reset the target case
	        
	        if (currentPlay instanceof CombatNode) {
            	System.out.println(" it issss a combatt");
            
            	((CombatNode) currentPlay).display2(this);
            }
	        displayCurrentNode();
	    }

	    // Handle choice keys only if we are not waiting for player move
	    if (!waitForPlayerMove) {
	    	switch (currentPlay.getNom()) {
            case "clanElement": p = new SorcierElement(); break;
            case "clanEnchanteur": p = new SorcierSpirituel(); break;
            case "humain": p = new Humain(); break;
            	
	    	}

	    	// Mise à jour des nœuds avec le joueur choisi
	        for (Node node : graph.getGraph().values()) {
	            node.setJoueur(p);
	        }
    
	    	
	        switch (e.getKeyCode()) {
	            case KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5 -> {
	                int choiceIndex = e.getKeyCode() - KeyEvent.VK_1;
	                if (choiceIndex >= 0 && choiceIndex < panel.nodeChoices.size()) {
	                    String choice = panel.nodeChoices.get(choiceIndex);
	                    //System.out.println(choice);
	                    currentPlay = currentPlay.chooseNext2(choice);
	                    
	                    
	                    

	                    // Check the description and set waitForPlayerMove and targetCaseClass accordingly
	                    if (
	                    		(currentPlay.getDescription().startsWith("Bienvenue, jeune sorcier. Je suis le Maître de ce clan Enchanteur"))||
	                    		
	                    		(currentPlay.getDescription().startsWith("Vous ramenez la Pierre au clan des enchanteurs."))
	                    		
	                    		){
	                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers le clan Enchanteur");
	                        waitForPlayerMove = true;
	                        targetCaseClass = CaseCLanA.class;  
	                       
	                    } else if(
	                    		(currentPlay.getDescription().startsWith("Bienvenue, jeune sorcier. Je suis le Maître de ce clan, et je suis honoré de te rencontrer. Notre clan des Sorciers des Éléments"))
	                    		|| (currentPlay.getDescription().startsWith("Vous ramenez la Pierre au clan des éléments."))
	                    		){
	                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers le clan des éléments");
	                        waitForPlayerMove = true;
	                        targetCaseClass = CaseClanB.class;  
	                       
	                    } else if (
	                    		(currentPlay.getDescription().startsWith("L'auberge n'a pas changé depuis votre venue tout à l'heure")) ||
	                    		(currentPlay.getDescription().startsWith("Vous entrez dans une petite bibliothèque renfermant des livres poussiéreux.")) ||
	                    		(currentPlay.getDescription().startsWith("Vous voyez une maison et voyez la guerisseusse s'occuper de son jardin")) ||
	                    		(currentPlay.getDescription().startsWith("Je sens que l'eau s'est purifiée."))
	                    		
	                    		){
	                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers Le village");
	                        waitForPlayerMove = true;
	                        targetCaseClass = Village.class;
	                        
	                        
	                        
	                    } else if (
	                    		(currentPlay.getDescription().startsWith("Vous vous baladez pour trouver un monstre à combattre."))
	                    		|| (currentPlay.getDescription().startsWith("Vous cherchez un deuxième monstre."))
	                    		
	                    		)
	                    {
	                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers le monstre présent sur une case grise.");
	                        waitForPlayerMove = true;
	                        targetCaseClass = Donjon.class; 
	                       
	                    } else if (currentPlay.getDescription().startsWith("Vous arrivez à la rivière. Plus que d'étranges énergies,")) {
	                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers la rivière");
	                        waitForPlayerMove = true;
	                        targetCaseClass = Riviere.class;  
	                       
	                    } else if (currentPlay.getDescription().startsWith("En entrant dans la Forêt des Murmure")) {
	                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers le Sanctuaire");
	                        waitForPlayerMove = true;
	                        targetCaseClass = Sanctuaire.class;  
	                       
	                    }
	                    
	                    else {
	                    	if (currentPlay instanceof CombatNode) {
		                    	System.out.println(" it issss a combatt");
		                    
		                    	((CombatNode) currentPlay).display2(this);
		                    }
	                    	displayCurrentNode();
		                   
	                    }
	                    
	                  } 
	            }
	        }
	    }

	    panel.repaint();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// Not used
	}
	
	@Override
	public void saveGame(String playerName) {
	    String fileName = "C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\" + playerName + ".dat";
	    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
	        GameSaver gamesaver = new GameSaver(this.p, this.currentPlay, this.panel.terrain);
	        out.writeObject(gamesaver);
	        JOptionPane.showMessageDialog(this, "Game saved successfully as " + playerName + ".dat");
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(this, "Error saving game: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	@Override
	public  void loadGame(String playerName) {
	    String fileName = "C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\" + playerName + ".dat";
	    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
	        GameSaver gamesaver = (GameSaver) in.readObject();
	        this.p = gamesaver.getPersonnage();
	        this.currentPlay = gamesaver.getCurrentPlay();
	        if (panel == null) {
	            panel = new PanelComponent(new Terrain("C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\terrain2.txt", p), this);
	        }
	        this.panel.terrain = gamesaver.getTerrain();
	        JOptionPane.showMessageDialog(this, "Game loaded successfully from " + playerName + ".dat");
	        showGameInterface();
	    } catch (IOException | ClassNotFoundException e) {
	        JOptionPane.showMessageDialog(this, "Error loading game: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	@Override
	public Node chooseNextNode(String choice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personnage getPlayer() {
		return this.p;
	}

	@Override
	public void setPlayer(Personnage player) {
		this.p = player;
		
	}
	
}
