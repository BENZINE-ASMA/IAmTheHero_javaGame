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
    private Event currentPlay;
    private boolean waitForPlayerMove = false;
    private Class<? extends Case> targetCaseClass = null;
    private String baseFolder = "C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\";

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
        //panel = new PanelComponent(new Terrain("C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\terrain2.txt", p), this);
        panel = new PanelComponent(new Terrain(baseFolder + "terrain2.txt", p), this);
        // Set up the new game interface
        getContentPane().add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();

        // Display the first choice
        displayCurrentNode();
    }

    
    public void displayCombatNode(CombatNode cn) {
        if (cn != null) {
            cn.handleCombat(this);
        }
    }
    
    
    public void displayCurrentNode() {
    	
    	if (currentPlay != null) {
    		
    		int cpt = 0;
            
            if (currentPlay instanceof NodeDecorator) {
            	cpt = ((NodeDecorator)currentPlay).playDecorator(panel, cpt);
            }
            if (cpt == 0) {
            	panel.setNodeImage(null);
            }
            
    		CombatNode combatNode = NodeUtil.findNodeOfType(currentPlay, CombatNode.class);
            if (combatNode != null) {
            	displayCombatNode((CombatNode) combatNode);
            	if (!(combatNode.getWinner())){
            		return;
            		
            	}
            }
    	//if (currentPlay.getTypes().contains(CombatNode.class)) {
    		//displayCombatNode((CombatNode) currentPlay);
    	//}
       	 
       	if (currentPlay.getTypes().contains(ChanceNode.class)) {
       		JOptionPane.showMessageDialog(this,	" c'est un Chance Node.... Patientez!");
       		
      		 System.out.println("say hello");
       		panel.repaint();
       		return;
        }

       
        
        panel.setNodeText(currentPlay.getDescription());
        Map<String, Event> nodesSuivant = currentPlay.getNodesSuivant();
        ArrayList<String> options = new ArrayList<>(nodesSuivant.keySet());
        panel.setNodeChoices(options);
        panel.revalidate();

    }
    }

	private NodesGraph createGraph() {
		
		NodesGraph graph = new NodesGraph();
		EntiteMobile gobelin1 = new EntiteMobile(50,50,10,14);
        gobelin1.setName("Geodfroy");
        EntiteMobile gobelin2 = new EntiteMobile(50,50,10,14);
        gobelin2.setName("Sundae");
        EntiteMobile gobelin3 = new EntiteMobile(50,50,10,14);
        gobelin3.setName("Zappy");
        EntiteMobile slime1 = new EntiteMobile(30,30,5,8);
        slime1.setName("Slime");
        EntiteMobile chimere = new EntiteMobile(110,110,25,20);
        chimere.setName("Chimère");
        EntiteMobile queenslime = new EntiteMobile(90,90,6,6);
        queenslime.setName("Reine des Slimes");
        
        
        TerminalNode death =  new TerminalNode ("mortCombat", "Vous êtes mort bravement au combat.");
        //Noeud terminal de mort lors d'un combat
        graph.addNode("mortCombat", death);
    

        
        graph.addNode("introduction", new ImageNode (new SoundNode(new DecisionNode("introduction", "Bonjour et bienvenue à l'auberge de la ville ! Vous pouvez tout faire ici, acheter armes, potions, et même rejoindre les clans de notre contrée ! Je ne vous ai jamais vu ici avant, que puis-je pour vous ?"), baseFolder + "musique1.wav" ), baseFolder + "taverne.jpg"));
        graph.addNode("explication", new ImageNode (new DecisionNode("explication", "Bien sûr! Dans notre région, il y a deux puissants clans qui se disputent depuis des années : les sorciers des éléments et les sorciers Enchanteurs. Les sorciers des éléments manipulent les forces naturelles telles que le feu, l'eau ou la terre, tandis que les sorciers Enchanteurs se concentrent sur la manipulation de l'énergie Enchanteurle et des âmes. Ces deux clans sont engagés dans une lutte de pouvoir perpétuelle, chacun cherchant à étendre son influence et affirmer sa supprématie. C'est une période de tension constante, et beaucoup craignent que cela ne conduise à un conflit ouvert un jour. Dis moi, quel sorcier es tu?"), baseFolder + "taverne.jpg"));
        graph.addNode("rejoindre", new ImageNode( new DecisionNode("rejoindre", "Bien sûr! Vous pouvez rejoindre le clan des sorciers Enchanteurs et celui des éléments. Quel type de sorcier êtes vous?"), baseFolder + "taverne.jpg"));

        graph.addNode("clanEnchanteur", new ImageNode(new DecisionNode("clanEnchanteur","[Vous signez des documents obscurs]. Très bien, vous êtes désormais dans le clan Enchanteur! Vous allez y être téléporté, au plaisir de vous revoir !"), baseFolder + "taverne.jpg"));

        graph.addNode("clanElement",  new ImageNode(new DecisionNode("clanElement", "[Vous signez des documents obscurs]. Très bien, vous êtes désormais dans le clan des éléments! Vous allez y être emmené directement. Merci d'être passé par chez nous et bonne chance!"), baseFolder + "taverne.jpg"));
        graph.addNode("humain", new ImageNode( new DecisionNode("humain", "quelle est votre compétence?"), baseFolder + "taverne.jpg"));
        graph.addNode("humainAvecCompetence", new ImageNode( new DecisionNode("humainAvecCompetence", "vous avez la compétence de '', que voulez-vous faire?"), baseFolder + "taverne.jpg"));
        graph.addNode("finHumain", new ImageNode (new TerminalNode("finHumain", "Vous ne pouvez pas rejoindre de clan, mais vous allez très bien vous entendre avec les membres du village ! L'aventure est réservée aux sorciers mais notre vie est elle aussi passionnante.\nVous vous installez dans cette nouvelle ville et vous faites de nouveaux amis. Vous vous mariez, avez des enfants, et vivez votre vie normale heureux.se jusqu'à votre mort paisible.\nFIN"), baseFolder + "taverne.jpg"));

        // Dialogue avec le maître du clan des éléments
        graph.addNode("maitreClanElementIntro1", new ImageNode (new DecisionNode("maitreClanElementIntro1", "Bienvenue, jeune sorcier. Je suis le Maître de ce clan, et je suis honoré de te rencontrer. Notre clan des Sorciers des Éléments lutte depuis des siècles pour maintenir notre position dominante sur ces terres. Nous devons nous assurer que nos pouvoirs ne tombent pas entre de mauvaises mains, notamment celles des Sorciers Enchanteurs, nos ennemis jurés."), baseFolder + "element.jpg"));
        graph.addNode("maitreClanElementIntro2", new ImageNode (new DecisionNode("maitreClanElementIntro2", "Nous sommes sur le point d'accomplir une quête cruciale qui renforcera notre position et nous donnera un avantage sur nos adversaires. Pour cela, nous avons besoin de sorciers talentueux comme toi. Mais avant de te confier cette responsabilité, il est essentiel que tu te prépares adéquatement."),  baseFolder + "element.jpg"));
        graph.addNode("maitreClanElementIntro3", new ImageNode (new DecisionNode("maitreClanElementIntro3", "Je te conseille de parler à l'Aubergiste et à notre Historien, ils ont des peut-être des informations qui pourraient nous être utiles. N'hésite pas à combattre les monstres que tu vois avant d'y aller, tu manques encore de puissance. Prends ces potions avec toi, elles pourraient d'être utiles."),  baseFolder + "element.jpg"));

        // Dialogue avec le maître du clan des enchanteurs
        graph.addNode("maitreClanEnchanteurIntro1", new ImageNode (new DecisionNode("maitreClanEnchanteurIntro1", "Bienvenue, jeune sorcier. Je suis le Maître de ce clan Enchanteur. Nous avons besoin de sorciers talentueux comme toi pour sécuriser nos reliques. Nos ennemis, les Sorciers des Elements, semblent déterminés à récupérer la Pierre des Elements pour leur accorder un pouvoir tout puissant. Nous ne pouvons pas les laisser faire, encore plus quand nous savons que notre clan la possédait il y a de cela quelques siècles. Vas tu nous rejoindre?"),  baseFolder + "enchanteur.jpg"));
        graph.addNode("refusQuete", new ImageNode (new DecisionNode("refusQuete", "En es-tu sûr.e ? C'est le destin du monde qui en dépend"), baseFolder + "enchanteur.jpg"));
        graph.addNode("maitreClanEnchanteurIntro2",new ImageNode ( new DecisionNode("maitreClanEnchanteurIntro2", "Je te conseille de parler à l'Aubergiste et à l'Historien dans le village. Avant cela, n'hésite pas à combattre pour t'améliorer. Les sorciers Enchanteurs ont une puissance décuplée pour chaque ennemi terrassé. Prends ces quelques potions avec toi, et bon voyage."), baseFolder + "enchanteur.jpg"));

        // Nœud de fin si refus de la quête principale
        graph.addNode("demission", new ImageNode(new TerminalNode("demission", "Je ne vois pas ce que tu aurais à faire au sein de notre clan si tu ne participes pas à notre objectif principal. Je me vois dans l'obligation de te congédier. Peut-être que nos chemins se croiseront un jour de nouveau."), baseFolder + "plaines.png"));

        // Ajout des missions pour chaque clan
        graph.addNode("missionClanElement",  new ImageNode (new DecisionNode("missionClanElement", "Excellent choix. Ta mission est de retrouver la Pierre des Éléments, une relique de grande puissance. Avant de parler davantage de la mission, parle moi un peu de toi. Quel est l'élement qui te fait vibrer, celui avec lequel tu as le plus d'affinité?"), baseFolder + "element.jpg"));
        graph.addNode("missionClanEnchanteur",  new ImageNode (new DecisionNode("missionClanEnchanteur", "Ta mission est de récupérer notre dû, la Pierre Elementaire, renfermant un grand pouvoir."), baseFolder + "enchanteur.jpg"));

     // Choix Affinité pour Sorcier Élement
        graph.addNode("affiniteElement", new ImageNode( new DecisionNode("affiniteElement", "Tu as donc une affinité avec '', très bien. Tes sorts utilisant cet élément auront donc davantage de puissance que les autres, n'hésite pas à les utiliser."), baseFolder + "element.jpg"));

        // Choix d'action post introduction
        // Choix dans l'auberge
        graph.addNode("AubergePostIntro", new ImageNode (new DecisionNode("AubergePostIntro", "L'auberge n'a pas changé depuis votre venue tout à l'heure. Alors que vous alliez voir l'aubergiste, un homme semblant assez alcoolisé vient vous parler. Il vous propose de vous offrir une récompense si vous le battez dans son domaine de prédilection, la pétanque"), baseFolder + "taverne.jpg"));
        
        //ASMA
        graph.addNode("PartiePetanque",new ImageNode (new ChanceNode("PartiePetanque", "Vous jouez avec l'homme."), baseFolder + "taverne.jpg"));
        
        
       //graph.addNode("PartiePetanque",new ChanceNode("PartiePetanque", "Vous jouez avec l'homme."));
        graph.addNode("MeilleureArme",new ImageNode ( new DecisionNode("MeilleureArme", "Vous êtes un as de la pétanque, tirez et pointez comme si vous faisiez ça depuis votre enfance. L'homme reconnait sa défaite, et vous offre un bâton magique qui semble en bien meilleur état que le votre."), baseFolder + "taverne.jpg"));
        graph.addNode("PartiePerdue", new ImageNode (new DecisionNode("PartiePerdue", "Vous faites tomber la boule sur vos pieds et criez de douleur. Vous ne gagnez pas ce match."), baseFolder + "taverne.jpg"));
        graph.addNode("AubergisteParler", new ImageNode (new DecisionNode("AubergisteParler", "L'aubergiste vous reconnait et vous demande ce que vous rechercher."), baseFolder + "taverne.jpg"));
        graph.addNode("AubergePierreElem", new ImageNode (new DecisionNode("AubergePierreElem", "C'est une relique légendaire, censée renfermer la puissance brute des éléments. On dit qu'elle a été perdue il y a des siècles... Vous devriez parler à l'historien de la Bibliothèque des Anciens, il en sait sûrement plus."), baseFolder + "taverne.jpg"));
        graph.addNode("AubergeEmplacementBibliotheque", new ImageNode (new DecisionNode("AubergeEmplacementBibliotheque", "La Bibliothèque des Anciens se trouve à l'Est de la ville, non loin d'ici. Vous devriez pouvoir voir l'emplacement de l'historien sur votre carte."), baseFolder + "taverne.jpg"));
        graph.addNode("AubergePersonnesUtiles", new ImageNode (new DecisionNode("AubergePersonnesUtiles", "L'historien est sans doute la personne qui s'y connait le mieux. Vous pourriez tout de même aller voir la guérisseuse au sud du village pour récupérer des potions spéciales pour vous protéger des dangers des donjons."), baseFolder + "taverne.jpg"));
        graph.addNode("AubergePotions", new ImageNode (new DecisionNode("AubergePotions", "Si vous voulez vous préparer pour votre aventure, allez voir la guérisseuse. Elle aura peut-être des potions pour vous, si elle vous trouve suffisamment sympathique."), baseFolder + "taverne.jpg"));

     // Choix dans la bibliothèque
        graph.addNode("BibliothequePostIntro", new ImageNode (new DecisionNode("BibliothequePostIntro", "Vous entrez dans une petite bibliothèque renfermant des livres poussiéreux. L'historien vous accueille et vous demande la raison de votre visite."), baseFolder + "bibliotheque.jpg"));
        graph.addNode("BibliothequePierreElem", new ImageNode (new DecisionNode("BibliothequePierreElem", "Historien: La Pierre Élémentaire est une relique ancienne. Elle amplifie les pouvoirs de ceux qui la possèdent. Après avoir effectué mes recherches, je pense qu'elle se trouve dans le Sanctuaire au nord est du village. Cependant il est rempli de créatures dangereuses, et je n'ai pas la force nécessaire pour m'y aventurer. Peut-être y parviendrez-vous?"), baseFolder + "bibliotheque.jpg"));

        graph.addNode("CarteCombat1", new ImageNode (new SoundNode( new CombatNode("CarteCombat1", "En vous promenant vous tombez sur un monstre dangereux, un gobelin ! Il vous attaque.", death, gobelin1), baseFolder + "combat.wav"), baseFolder + "gobelinmignon.jpg"));
        graph.addNode("CarteCombat2",  new ImageNode (new SoundNode(new CombatNode("CarteCombat2", "En voici un deuxième! Préparez-vous au combat.", death, gobelin3), baseFolder + "combat.wav"), baseFolder + "gobelinmignon.jpg"));

     // Aller dans la forêt
        graph.addNode("Foret", new ImageNode (new DecisionNode("Foret", "En entrant dans le Sanctuaire, vous sentez l'atmosphère s'alourdir. En inspectant autour de vous, vous voyez une étendue d'arbres à perte de vue, qui semblent chuchoter des secrets anciens à chaque souffle de vent."), baseFolder + "sanctuaire.jpg"));
        graph.addNode("ForetChemin", new ChanceNode("ForetChemin", "")); // Est ce que le chemin choisi sera le bon?
        graph.addNode("ForetMauvaisChemin", new ImageNode (new DecisionNode("ForetMauvaisChemin", "Vous vous perdez dans la forêt. Que voulez-vous faire?"), baseFolder + "sanctuaire.jpg"));
        graph.addNode("MortForet", new TerminalNode("MortForet", "Vous ne connaissez pas la forêt et continuez à avancer malgré tout. Des plantes carnivores vous attrapent et vous mangent."));
        graph.addNode("ForetBonChemin", new ImageNode (new DecisionNode("ForetBonChemin", "Vous trouvez votre chemin."), baseFolder + "sanctuaire.jpg"));
        graph.addNode("ForetMonstre", new ChanceNode("ForetMonstre", "Vous entendez du bruit.")); // Quel monstre va être choisi?
        graph.addNode("CombatSlime1", new ImageNode (new CombatNode("CombatSlime1", "Vous tombez nez à nez avec un slime qui vous attaque!", death, slime1), baseFolder + "slime.jpg"));
        graph.addNode("CombatGobelin2", new ImageNode (new CombatNode("CombatGobelin2", "Vous tombez nez à nez avec un gobelin qui vous attaque!", death, gobelin2), baseFolder + "gobelinmignon+jpg"));
        graph.addNode("CombatChimere", new ImageNode (new CombatNode("CombatChimere", "Vous voyez la Pierre juste devant vos yeux. Une créature surgit alors, une chimère protégeant la pierre qui fonce sur vous!", death, chimere), baseFolder + "chimere.jpg"));
        graph.addNode("CombatChimereGagne", new ImageNode (new DecisionNode("CombatChimereGagne", "La Pierre scintille devant vous. Que voulez-vous en faire?"), baseFolder + "pierre.jpeg"));
        graph.addNode("DetruirePierre", new ChanceNode("DetruirePierre", "La Pierre est brisée en mille morceaux à vos pieds. La forêt elle même semble vous reprocher votre action, semblant plus sombre et menaçante qu'à votre arrivée. Vous quittez la forêt."));

        // Ramener Pierre à son clan ou à l'autre : fin de l'histoire
        graph.addNode("PierrePourClanElement", new TerminalNode("PierrePourClanElement", "A écrire selon la classe du joueur."));
        graph.addNode("PierrePourClanEnchanteur", new TerminalNode("PierrePourClanEnchanteur", "A écrire selon la classe du joueur."));

        // Pierre détruite : fin de l'histoire
        graph.addNode("FinPaix", new TerminalNode("FinPaix", "A écrire selon la classe du joueur."));
        graph.addNode("FinConflit", new TerminalNode("FinConflit", "A écrire selon la classe du joueur."));

        // Choix avec guérisseuse
        graph.addNode("Guerisseuse", new ImageNode (new DecisionNode("Guerisseuse", "Vous voyez une maison et voyez la guérisseusse s'occuper de son jardin. Elle vous voit : Bonjour, que faites-vous ici?" ), baseFolder + "guerisseusemaison.jpeg"));
        graph.addNode("GuerisseuseContreGuerre", new ImageNode (new DecisionNode("GuerisseuseContreGuerre", "Et pourquoi voulez-vous des informations sur cette Pierre? Vous aussi vous voulez la récupérer ? Pour pouvoir faire encore plus de mal autour de vous? Non merci"), baseFolder + "guerisseusemaison.jpeg"));
        graph.addNode("GuerisseuseFinDiscussion", new ImageNode (new DecisionNode("GuerisseuseFinDiscussion", "Nous avons suffisamment discuté. Au revoir."), baseFolder + "guerisseusemaison.jpeg"));
        graph.addNode("GuerisseusePlusInfo", new ChanceNode("GuerisseusePlusInfo", "Je ne souhaite pas faire de mal, mais j'ai besoin de ces informations"));
        graph.addNode("GuerisseusePotionInfo", new ImageNode (new DecisionNode("GuerisseusePotionInfo", "Elle se radoucit : Je n'ai pas plus d'informations à donner. Mais je vous crois et je vous donne ces potions. J'espère qu'elles seront utiles dans votre quête."), baseFolder + "guerisseusemaison.jpeg"));
        graph.addNode("GuerisseuseAider", new ImageNode (new DecisionNode("GuerisseuseAider", "Est-ce que vous pourriez aider...? Oui je crois bien. Notre source d'eau dégage d'étranges énergies depuis plusieurs jours. Allez à la rivière enquêter, et revenez vers moi."), baseFolder + "guerisseusemaison.jpeg"));
        graph.addNode("GuerisseusePotionAider", new ImageNode (new DecisionNode("GuerisseusePotionAider", "Je sens que l'eau s'est purifiée. Je vous remercie pour votre aide. Je n'ai malheuresement pas plus d'informations sur la Pierre. Prenez donc ces potions et acceptez ma reconnaissance."), baseFolder + "guerisseusemaison.jpeg"));

        // Quête de la rivière
        graph.addNode("Source",  new ImageNode (new DecisionNode("Source", "Vous arrivez à la rivière. Plus que d'étranges énergies, l'apparence de l'eau est tout à fait étrange, légèrement verte."), baseFolder + "riviere.jpg"));
        graph.addNode("SourceEauMagie", new ChanceNode("SourceEauMagie", "Vous utilisez votre magie pour purifier l'eau."));
        graph.addNode("SlimeGeant",  new ImageNode (new CombatNode("SlimeGeant", "C'était un slime gigantesque qui polluait l'eau et la rendait gluante! Il n'apprécie pas votre agitation et attaque.", death, queenslime), baseFolder + "queenslime.jpeg"));
        graph.addNode("SourceProblemeResolu",  new ImageNode (new DecisionNode("SourceProblemeResolu", "L'eau redevient d'un bleu azur digne des piscines les plus chlorées."), baseFolder + "rivieresoignee.jpg"));
  
  

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
        
       
		return graph;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not used
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 
		
		if (currentPlay.getDescription().equals("Vous êtes mort bravement au combat.")) {
        	currentPlay.display();
        	return;
        }
		
		if (currentPlay instanceof ImageNode) {
		    ImageNode imageNode = (ImageNode) currentPlay;
		    if (imageNode.getNode() instanceof ChanceNode) {
		        
		        currentPlay = ((ChanceNode) imageNode.getNode()).chooseNext3();
		     
		        displayCurrentNode();
		        return;
		    }
		}
		
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
	        for (Event node : graph.getGraph().values()) {
	            node.setJoueur(p);
	        }
	        
	        
	    	
	        switch (e.getKeyCode()) {
	            case KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5 -> {
	                int choiceIndex = e.getKeyCode() - KeyEvent.VK_1;
	                if (choiceIndex >= 0 && choiceIndex < panel.nodeChoices.size()) {
	                    String choice = panel.nodeChoices.get(choiceIndex);
	                    //System.out.println(choice);
	                    panel.setNodeImage(null);
	                    panel.repaint();
	                    currentPlay = currentPlay.chooseNext2(choice);
	                    if (currentPlay != null && currentPlay.getDescription().equals("Vous êtes mort bravement au combat.")) {
	                    	currentPlay.display();
	                    	exit(0);
	                    }
	                  
	                    
	                    

	                    // Check the description and set waitForPlayerMove and targetCaseClass accordingly
	                    System.out.println("sooolve issue    " + this.currentPlay.getDescription());
	                    System.out.println(this.currentPlay.getClass());
	                    
	                    if (
	                    		(currentPlay.getDescription().startsWith("Bienvenue, jeune sorcier. Je suis le Maître de ce clan Enchanteur"))||
	                    		
	                    		(currentPlay.getDescription().startsWith("Vous ramenez la Pierre au clan des Enchanteurs."))
	                    		
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
	                        
	                        
	                        
	                    } else if(
	                		(currentPlay.getDescription().startsWith("En vous promenant vous tombez sur un monstre dangereux, un gobelin ")) || 
	                		(currentPlay.getDescription().startsWith("En voici un deuxième! Préparez-vous au combat "))
	                		){
                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers le Donjon");
                        waitForPlayerMove = true;
                        targetCaseClass = Donjon.class;  
	                       
	                    } else if (currentPlay.getDescription().startsWith("Vous arrivez à la rivière. Plus que d'étranges énergies,")) {
	                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers la rivière");
	                        waitForPlayerMove = true;
	                        targetCaseClass = Riviere.class;  
	                       
	                    } else if (currentPlay.getDescription().startsWith("En entrant dans le Sanctuaire,")) {
	                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers le Sanctuaire");
	                        waitForPlayerMove = true;
	                        targetCaseClass = Sanctuaire.class;  
	                       
	                    }
	                    
	                    else {
	                    	if (currentPlay instanceof CombatNode) {
		                    	System.out.println(" it issss a combatt");
		                    	displayCurrentNode();
		                    	displayCombatNode((CombatNode) currentPlay);
		                    	
		                    	//((CombatNode) currentPlay).display2(this);

		                            //displayCombatNode((CombatNode) currentPlay);
		                    	}
	                    	displayCurrentNode();
		                   
	                    }
	                    
	                  } 
	            }
	        }
	    }

	    panel.repaint();
	    //panel.setNodeImage(null);
		}
	}


	private void exit(int i) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void keyReleased(KeyEvent e) {
		// Not used
	}
	
	@Override
	public void saveGame(String playerName) {
	    //String fileName = "C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\" + playerName + ".dat";
		String fileName = baseFolder + playerName + ".dat";
	    
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
	    //String fileName = "C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\" + playerName + ".dat";
		String fileName = baseFolder + playerName + ".dat";
	   
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
	        GameSaver gamesaver = (GameSaver) in.readObject();
	        this.p = gamesaver.getPersonnage();
	        this.currentPlay = gamesaver.getCurrentPlay();
	        if (panel == null) {
	            //panel = new PanelComponent(new Terrain("C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\terrain2.txt", p), this);
	            panel = new PanelComponent(new Terrain(baseFolder + "terrain2.txt", p), this);
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
	public Event chooseNextNode(String choice) {
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
