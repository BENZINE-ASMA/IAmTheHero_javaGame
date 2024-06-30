package Interface;

import javax.swing.*;

import Components.Case;
import Components.CaseCLanA;
import Components.CaseClanB;

import Components.Donjon;
import Components.Riviere;
import Components.Sanctuaire;
import Components.Village;

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

/**
 * La classe principale pour l'interface du jeu d'aventure.
 * Cette classe étend JFrame et implémente GameInterface, Serializable, KeyListener.
 * Elle gère la configuration du jeu, les entrées utilisateur et la navigation entre les nœuds du jeu.
 */
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
    private String baseFolder = "C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\";

    
    /**
     * Constructeur de la fenêtre principale pour l'interface de jeu.
     * Initialise les composants et configure la disposition de la fenêtre principale.
     */
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

    
    /**
     * Gère l'action du bouton "Commencer".
     * Vérifie si un nom est entré et démarre l'interface du jeu.
     */
    private void handleStart() {
        String name = nameField.getText();
        if (!name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bienvenue " + name + "! Le jeu va commencer.");
            showGameInterface();
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nom.");
        }
    }

    /**
     * Gère l'action du bouton "Quitter".
     * Quitte le jeu lorsque le bouton "Quitter" est pressé.
     */
    private void handleQuit() {
        System.exit(0);
    }

    /**
     * Affiche l'interface de jeu après avoir effacé le contenu de la fenêtre principale.
     * Crée un nouveau PanelComponent avec un Terrain et le définit comme contenu principal.
     */
    private void showGameInterface() {
        // Clear the current content
        getContentPane().removeAll();
        repaint();
        revalidate();

        // Create the terrain and panel
        panel = new PanelComponent(new Terrain(baseFolder + "terrain2.txt", p), this);
        // Set up the new game interface
        getContentPane().add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();

        // Display the first choice
        displayCurrentNode();
    }

    
    /**
     * Affiche le nœud de combat en appelant handleCombat sur le CombatNode.
     * @param cn Le CombatNode à afficher.
     */
    public void displayCombatNode(CombatNode cn) {
        if (cn != null) {
            cn.handleCombat(this);
        }
    }
    
    /**
     * Affiche le nœud actuel en fonction de son type.
     * Gère les nœuds décorateurs, les nœuds de combat, les nœuds de chance entre autre.
     */
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

       	if (currentPlay.getTypes().contains(ChanceNode.class)) {
       		JOptionPane.showMessageDialog(this,	"Vous vous en remettez à la chance.");
       		
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

    /**
     * Crée et initialise le graphique de nœuds pour le jeu.
     * Ajoute des nœuds initiaux et des entités au graphique.
     * @return L'objet NodesGraph créé.
     */
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
        
        
        TerminalNode death =  new TerminalNode ("mortCombat", "Vous êtes mort bravement au combat. Votre aventure se termine ici.");
        //Noeud terminal de mort lors d'un combat
        graph.addNode("mortCombat", death);
    
        graph.addNode("introduction", new ImageNode(new SoundNode(new DecisionNode("introduction", "Vous arrivez dans l'auberge du village. L'aubergiste vous accueille et vous propose de rejoindre un des clans présentes sur le territoire."), baseFolder + "medieval1.wav"), baseFolder + "taverne.jpeg"));
        graph.addNode("explication", new ImageNode(new DecisionNode("explication", "Dans la région, deux puissants clans se disputent depuis des années : les sorciers des éléments manipulent feu, eau, terre, tandis que les sorciers Enchanteurs maîtrisent l'énergie et les âmes. Une lutte de pouvoir constante menace d'éclater en conflit ouvert."), baseFolder + "taverne.jpeg"));
        graph.addNode("rejoindre", new ImageNode(new DecisionNode("rejoindre", "Vous pouvez rejoindre le clan des sorciers Enchanteurs ou celui des éléments. Quel type de sorcier êtes-vous ?"), baseFolder + "taverne.jpeg"));

        graph.addNode("clanEnchanteur", new ImageNode(new DecisionNode("clanEnchanteur", "Très bien, vous êtes désormais dans le clan Enchanteur ! L'aubergiste vous indique que le clan des Enchanteurs se trouve au Sud-Est du village."), baseFolder + "taverne.jpeg"));

        graph.addNode("clanElement", new ImageNode(new DecisionNode("clanElement", "Très bien, vous êtes désormais dans le clan des éléments ! L'aubergiste vous indique que le clan des Eléments se trouve au Nord-Ouest du village."), baseFolder + "taverne.jpeg"));

        graph.addNode("humain", new ImageNode(new DecisionNode("humain", "Quelle est votre compétence ?"), baseFolder + "taverne.jpeg"));
        graph.addNode("humainAvecCompetence", new ImageNode(new DecisionNode("humainAvecCompetence", "Vous avez la compétence de '', que voulez-vous faire ?"), baseFolder + "taverne.jpeg"));
        graph.addNode("finHumain", new ImageNode(new SoundNode (new TerminalNode("finHumain", "Vous ne pouvez pas rejoindre de clan, mais vous vous entendez bien avec les villageois ! Vous vous installez, faites des amis, fondez une famille, et menez une vie heureuse jusqu'à une mort paisible. FIN"), baseFolder + "musique1.wav"), baseFolder + "taverne.jpeg"));

     // Dialogue avec le maître du clan des éléments
        graph.addNode("maitreClanElementIntro1", new ImageNode(new SoundNode (new DecisionNode("maitreClanElementIntro1", "Vous êtes accueilli par le Maître du Clan des Éléments. Ce dernier se présente comme le gardien de leur pouvoir ancestral, luttant depuis des siècles pour maintenir leur suprématie face aux Sorciers Enchanteurs, leurs rivaux jurés."), baseFolder + "medieval2.wav"), baseFolder + "element.jpeg"));
        graph.addNode("maitreClanElementIntro2", new ImageNode(new DecisionNode("maitreClanElementIntro2", "Il vous explique que leur clan est sur le point d'accomplir une quête cruciale pour renforcer leur position et prendre l'avantage sur leurs adversaires. Il insiste sur le danger et la nécessité d'une bonne préparation. "), baseFolder + "element.jpeg"));
        graph.addNode("maitreClanElementIntro3", new ImageNode(new DecisionNode("maitreClanElementIntro3", "Le Maître vous conseille de vous informer auprès de l'Aubergiste et de l'Historien du village, soulignant qu'ils pourraient détenir des informations vitales. Il encourage également à combattre des monstres pour gagner en puissance, et vous offre des potions."), baseFolder + "element.jpeg"));

     // Dialogue avec le maître du clan des enchanteurs
        graph.addNode("maitreClanEnchanteurIntro1", new ImageNode(new SoundNode(new DecisionNode("maitreClanEnchanteurIntro1", "Le Maître du Clan Enchanteur vous accueille, exprimant le besoin urgent de protéger les reliques des Sorciers des Éléments. Il évoque la menace que représente la Pierre des Éléments, une source de pouvoir convoitée par leurs ennemis depuis des siècles."), baseFolder + "medieval2.wav"), baseFolder + "enchanteur.jpeg"));
        graph.addNode("refusQuete", new ImageNode(new DecisionNode("refusQuete", "Il vous informe que ce choix pourrait avoir des conséquences désastreuses sur le monde."), baseFolder + "enchanteur.jpeg"));
        graph.addNode("maitreClanEnchanteurIntro2", new ImageNode(new DecisionNode("maitreClanEnchanteurIntro2", "Il vous recommande de vous entretenir avec l'Aubergiste et l'Historien du village pour recueillir des informations. Il vous informe que le sort Explosion d'Ames gagne en puissance avec chaque ennemi vaincu, mais qu'à chaque utilisation, le nombre d'âme revient à zéro. Il vous offre des potions pour renforcer vos pouvoirs lors des combats."), baseFolder + "enchanteur.jpeg"));

     // Nœud de fin si refus de la quête principale
        graph.addNode("demission", new ImageNode(new TerminalNode("demission", "Le Maître du Clan regrette votre refus de participer à leur mission cruciale. Il exprime sa déception et vous exclu du clan. Vous continuerez désormais vos aventures seul.e. FIN"), baseFolder + "plaines.png"));

     // Ajout des missions pour chaque clan
        graph.addNode("missionClanElement", new ImageNode(new DecisionNode("missionClanElement", "Votre mission est de retrouver la Pierre des Éléments, une relique de grande puissance. Avant de parler davantage de la mission, parles un peu de toi. Avec quel élément as-tu le plus d'affinité ?"), baseFolder + "element.jpeg"));
        graph.addNode("missionClanEnchanteur", new ImageNode(new DecisionNode("missionClanEnchanteur", "Ta mission est de récupérer la Pierre Élémentaire, renfermant un grand pouvoir."), baseFolder + "enchanteur.jpeg"));

     // Choix Affinité pour Sorcier Élement
        graph.addNode("affiniteElement", new ImageNode(new DecisionNode("affiniteElement", "Tu as donc une affinité avec '', très bien. Tes sorts utilisant cet élément auront donc davantage de puissance que les autres. N'hésite pas à les utiliser."), baseFolder + "element.jpeg"));

        
     // Choix dans l'auberge
        graph.addNode("AubergePostIntro", new ImageNode(new SoundNode (new DecisionNode("AubergePostIntro", "L'auberge n'a pas changé depuis votre dernière visite. Alors que vous vous apprêtiez à parler à l'aubergiste, un homme armé d'une bière s'approche de vous. Il vous propose une récompense si vous le battez à son jeu favori, la pétanque."), baseFolder + "medieval1.wav"), baseFolder + "taverne.jpeg"));

        // Partie de pétanque
        graph.addNode("PartiePetanque", new ImageNode (new ChanceNode("PartiePetanque", "Une partie de pétanque est engagée avec l'homme."), baseFolder + "taverne.jpeg"));

        // Récompense de la partie gagnée
        graph.addNode("MeilleureArme", new ImageNode(new DecisionNode("MeilleureArme", "Votre maîtrise surprend l'homme, qui admet sa défaite et vous offre un bâton magique en bien meilleur état que le vôtre."), baseFolder + "taverne.jpeg"));

        // Partie perdue
        graph.addNode("PartiePerdue", new ImageNode(new DecisionNode("PartiePerdue", "Malheureusement, vous faites tomber la boule sur vos pieds, criant de douleur. Vous ne gagnez pas ce match."), baseFolder + "taverne.jpeg"));

        // Interaction avec l'aubergiste
        graph.addNode("AubergisteParler", new ImageNode(new DecisionNode("AubergisteParler", "L'aubergiste vous reconnaît et vous demande ce que vous recherchez."), baseFolder + "taverne.jpeg"));

        // Informations sur la Pierre des Éléments
        graph.addNode("AubergePierreElem", new ImageNode(new DecisionNode("AubergePierreElem", "La Pierre des Éléments est une relique légendaire, réputée pour renfermer la puissance brute des éléments. On dit qu'elle a été perdue il y a des siècles... L'historien de la Bibliothèque des Anciens en sait sûrement plus."), baseFolder + "taverne.jpeg"));

        // Emplacement de la Bibliothèque des Anciens
        graph.addNode("AubergeEmplacementBibliotheque", new ImageNode(new DecisionNode("AubergeEmplacementBibliotheque", "La Bibliothèque des Anciens se trouve à l'Est de la ville, à proximité d'ici. Vous devriez voir l'emplacement de l'historien sur votre carte."), baseFolder + "taverne.jpeg"));

        // Personnes utiles à rencontrer
        graph.addNode("AubergePersonnesUtiles", new ImageNode(new DecisionNode("AubergePersonnesUtiles", "L'historien est l'expert incontesté en la matière. Vous pourriez également visiter la guérisseuse au sud du village pour des potions spéciales avant de vous aventurer dans les donjons."), baseFolder + "taverne.jpeg"));

        // Conseil pour les potions
        graph.addNode("AubergePotions", new ImageNode(new DecisionNode("AubergePotions", "Pour vous préparer, consultez la guérisseuse. Elle pourrait vous proposer des potions si elle vous apprécie."), baseFolder + "taverne.jpeg"));

        
     // Choix dans la bibliothèque
        graph.addNode("BibliothequePostIntro", new ImageNode(new DecisionNode("BibliothequePostIntro", "Vous entrez dans une petite bibliothèque renfermant des livres poussiéreux. L'historien vous accueille et vous demande la raison de votre visite."), baseFolder + "bibliotheque.jpeg"));
        graph.addNode("BibliothequePierreElem", new ImageNode(new DecisionNode("BibliothequePierreElem", "Historien : La Pierre Élémentaire est une relique ancienne. Elle amplifie les pouvoirs de ceux qui la possèdent. Après avoir effectué mes recherches, je pense qu'elle se trouve dans le Sanctuaire au nord-est du village. Cependant, il est rempli de créatures dangereuses, et je n'ai pas la force nécessaire pour m'y aventurer. Peut-être y parviendrez-vous?"), baseFolder + "bibliotheque.jpeg"));

        // Combat sur la carte
        graph.addNode("CarteCombat1", new ImageNode(new SoundNode(new CombatNode("CarteCombat1", "En vous promenant, vous tombez sur un monstre dangereux, un gobelin ! Il vous attaque.", death, gobelin1), baseFolder + "tetris.wav"), baseFolder + "goblinmignon.jpeg"));
        graph.addNode("CarteCombat2", new ImageNode(new SoundNode(new CombatNode("CarteCombat2", "En voici un deuxième ! Préparez-vous au combat.", death, gobelin3), baseFolder + "tetris.wav"), baseFolder + "goblinmignon.jpeg"));


     // Choix dans la forêt
        graph.addNode("Foret", new ImageNode(new DecisionNode("Foret", "En entrant dans le Sanctuaire, l'atmosphère s'alourdit. Une étendue d'arbres à perte de vue semble chuchoter des secrets anciens à chaque souffle de vent."), baseFolder + "sanctuaire.jpeg"));

        // Chance de choisir le bon chemin dans la forêt
        graph.addNode("ForetChemin", new ImageNode(new ChanceNode("ForetChemin", "Est-ce que le chemin choisi sera le bon?"), baseFolder + "sanctuaire.jpeg"));

        // Se perdre dans la forêt
        graph.addNode("ForetMauvaisChemin", new ImageNode(new DecisionNode("ForetMauvaisChemin", "Vous vous perdez dans la forêt. Que voulez-vous faire?"), baseFolder + "sanctuaire.jpeg"));

        // Mort dans la forêt
        graph.addNode("MortForet", new ImageNode( new TerminalNode("MortForet", "Vous ne connaissez pas la forêt et continuez à avancer malgré tout. Des plantes carnivores vous attrapent et vous mangent."), baseFolder + "plant.jpeg"));

        // Trouver le bon chemin dans la forêt
        graph.addNode("ForetBonChemin", new ImageNode(new DecisionNode("ForetBonChemin", "Vous trouvez votre chemin."), baseFolder + "sanctuaire.jpeg"));

        // Rencontrer un monstre dans la forêt
        graph.addNode("ForetMonstre", new ImageNode (new ChanceNode("ForetMonstre", "Vous entendez du bruit."), baseFolder + "sanctuaire.jpeg"));

        // Combat 
        graph.addNode("CombatSlime1", new ImageNode(new SoundNode (new CombatNode("CombatSlime1", "Vous tombez nez à nez avec un slime qui vous attaque!", death, slime1), baseFolder + "tetris.wav"), baseFolder + "slime.jpg"));
        graph.addNode("CombatGobelin2", new ImageNode(new SoundNode (new CombatNode("CombatGobelin2", "Vous tombez nez à nez avec un gobelin qui vous attaque!", death, gobelin2), baseFolder + "tetris.wav"), baseFolder + "goblinmignon.jpeg"));
        graph.addNode("CombatChimere", new ImageNode(new SoundNode (new CombatNode("CombatChimere", "Vous voyez la Pierre juste devant vos yeux. Une créature surgit alors, une chimère protégeant la pierre qui fonce sur vous!", death, chimere), baseFolder + "tetris.wav"), baseFolder + "chimere.jpeg"));

        // Gagner le combat contre la chimère et décider du sort de la Pierre
        graph.addNode("CombatChimereGagne", new ImageNode(new DecisionNode("CombatChimereGagne", "La Pierre scintille devant vous. Que voulez-vous en faire?"), baseFolder + "pierre.jpeg"));

        // Décision de détruire la Pierre	
        graph.addNode("DetruirePierre", new ImageNode (new ChanceNode("DetruirePierre", "La Pierre est brisée en mille morceaux à vos pieds. La forêt elle-même semble vous reprocher votre action, semblant plus sombre et menaçante qu'à votre arrivée. Vous quittez la forêt."), baseFolder + "pierre.jpeg"));

        // Ramener Pierre à son clan ou à l'autre : fin de l'histoire
        graph.addNode("PierrePourClanElement", new ImageNode (new TerminalNode("PierrePourClanElement", "A écrire selon la classe du joueur."), baseFolder + "element.jpeg"));
        graph.addNode("PierrePourClanEnchanteur", new ImageNode (new TerminalNode("PierrePourClanEnchanteur", "A écrire selon la classe du joueur."), baseFolder + "enchanteur.jpeg"));

        // Pierre détruite : fin de l'histoire
        graph.addNode("FinPaix", new ImageNode( new TerminalNode("FinPaix", "Le Maître du Clan, extrêmement déçu par votre décision, vous informe que la Pierre était leur seule chance de gagner. Les deux clans devront envisager la paix. Vous perdez tout prestige et êtes renvoyé.e. Vous devrez poursuivre vos aventures seul.e. FIN"), baseFolder + "interieurclan.jpeg"));
        graph.addNode("FinConflit", new ImageNode (new TerminalNode("FinConflit", "Le Maître du Clan s'affole en apprenant la nouvelle. Refusant toute paix ou alliance, il vous considère comme un traître et lance son sort le plus puissant : Immolation. Vous mourrez dans d'atroces souffrances. FIN"), baseFolder + "interieurclan.jpeg"));

     // Choix avec la guérisseuse
        graph.addNode("Guerisseuse", new ImageNode(new DecisionNode("Guerisseuse", "Vous découvrez une petite maison où une guérisseuse s'occupe de son jardin. Elle vous remarque et vous salue : Bonjour, que faites-vous ici?"), baseFolder + "guerisseusemaison.jpeg"));
        graph.addNode("GuerisseuseContreGuerre", new ImageNode(new DecisionNode("GuerisseuseContreGuerre", "Vous demandez des informations sur la Pierre, mais la guérisseuse vous regarde avec suspicion : Et pourquoi voulez-vous des informations sur cette Pierre? Vous aussi vous voulez la récupérer? Pour pouvoir faire encore plus de mal autour de vous? Non merci."), baseFolder + "guerisseusemaison.jpeg"));
        graph.addNode("GuerisseuseFinDiscussion", new ImageNode(new DecisionNode("GuerisseuseFinDiscussion", "Elle coupe court à la conversation : Nous avons suffisamment discuté. Au revoir."), baseFolder + "guerisseusemaison.jpeg"));
        graph.addNode("GuerisseusePlusInfo", new ImageNode (new ChanceNode("GuerisseusePlusInfo", "Vous tentez de la convaincre : Je ne souhaite pas faire de mal, mais j'ai besoin de ces informations."), baseFolder + "guerisseusemaison.jpeg"));
        graph.addNode("GuerisseusePotionInfo", new ImageNode(new DecisionNode("GuerisseusePotionInfo", "Elle semble se radoucir : Je n'ai pas plus d'informations à donner. Mais je vous crois et je vous donne ces potions. J'espère qu'elles seront utiles dans votre quête."), baseFolder + "guerisseusemaison.jpeg"));
        graph.addNode("GuerisseuseAider", new ImageNode(new DecisionNode("GuerisseuseAider", "Vous lui demandez son aide concernant une étrange source d'eau : Est-ce que vous pourriez aider...? Notre source d'eau dégage d'étranges énergies depuis plusieurs jours. Allez à la rivière enquêter, et revenez vers moi."), baseFolder + "guerisseusemaison.jpeg"));
        graph.addNode("GuerisseusePotionAider", new ImageNode(new DecisionNode("GuerisseusePotionAider", "Après votre aide, elle exprime sa gratitude : Je sens que l'eau s'est purifiée. Merci pour votre aide. Je n'ai malheureusement pas plus d'informations sur la Pierre. Prenez ces potions et acceptez ma reconnaissance."), baseFolder + "guerisseusemaison.jpeg"));

     // Quête de la rivière
        graph.addNode("Source", new ImageNode(new DecisionNode("Source", "Vous arrivez à la rivière. Plus que d'étranges énergies, l'apparence de l'eau est tout à fait étrange, légèrement verte."), baseFolder + "riviere.jpeg"));
        graph.addNode("SourceEauMagie", new ImageNode (new ChanceNode("SourceEauMagie", "Vous utilisez votre magie pour purifier l'eau."), baseFolder + "riviere.jpeg"));
        graph.addNode("SlimeGeant", new ImageNode(new SoundNode (new CombatNode("SlimeGeant", "Un slime gigantesque, qui polluait l'eau et la rendait gluante! Il n'apprécie pas votre agitation et attaque.", death, queenslime), baseFolder + "tetris.wav"), baseFolder + "queenslime.jpeg"));
        graph.addNode("SourceProblemeResolu", new ImageNode(new DecisionNode("SourceProblemeResolu", "L'eau redevient d'un bleu azur digne des piscines les plus chlorées."), baseFolder + "rivieresoignee.jpeg"));

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
        graph.addArc("GuerisseusePotionInfo", "BibliothequePostIntro", "Aller à la bibliothèque.");

        
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

	@Override
	public void keyTyped(KeyEvent e) {
		// Not used
	}

	/**
	 * Écouteur pour la touche enfoncée. Gère les mouvements du joueur et les interactions clés du jeu.
	 * @param e L'événement KeyEvent généré lorsqu'une touche est enfoncée.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		 
		// Vérifie si le joueur est mort au combat
		if (currentPlay.getDescription().equals("Vous êtes mort bravement au combat.")) {
        	currentPlay.display();
        	return;
        }
	
		
		if (currentPlay instanceof ChanceNode) {
        	currentPlay = ((ChanceNode)currentPlay).chooseNext3();
        	displayCurrentNode();
        	
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
	                    panel.setNodeImage(null);
	                    panel.repaint();
	                    currentPlay = currentPlay.chooseNext2(choice);
	                    if (currentPlay != null && currentPlay.getDescription().equals("Vous êtes mort bravement au combat.")) {
	                    	currentPlay.display();
	                    	exit(0);
	                    }
	                  
	                    
	                    

	                    // Check the description and set waitForPlayerMove and targetCaseClass accordingly
	                    if (
	                    		("maitreClanEnchanteurIntro1".equals(currentPlay.getNom()))||
	                    		("PierrePourClanEnchanteur".equals(currentPlay.getNom()))
	                    		
	                    		){
	                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers le clan Enchanteur");
	                        waitForPlayerMove = true;
	                        targetCaseClass = CaseCLanA.class;  
	                       
	                    } else if(
	                    		("maitreClanElementIntro1".equals(currentPlay.getNom()))||
	                    		("PierrePourClanElement".equals(currentPlay.getNom()))
	                    		){
	                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers le clan des éléments");
	                        waitForPlayerMove = true;
	                        targetCaseClass = CaseClanB.class;  
	                       
	                    } else if (
	                    		("AubergePostIntro".equals(currentPlay.getNom()))||
	                    		("BibliothequePostIntro".equals(currentPlay.getNom()))||
	                    		("Guerisseuse".equals(currentPlay.getNom()))||
	                    		("GuerisseusePotionAider".equals(currentPlay.getNom()))
	                    		
	                    		){
	                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers Le village (au centre de la carte)");
	                        waitForPlayerMove = true;
	                        targetCaseClass = Village.class;
	                        
	                        
	                        
	                    } else if(
                    		("CarteCombat1".equals(currentPlay.getNom()))||
                    		("CarteCombat2".equals(currentPlay.getNom()))
	                		){
                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers le Donjon (en gris sur la carte)");
                        waitForPlayerMove = true;
                        targetCaseClass = Donjon.class;  
	                       
	                    } else if (("Source".equals(currentPlay.getNom()))) {
	                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers la rivière");
	                        waitForPlayerMove = true;
	                        targetCaseClass = Riviere.class;  
	                       
	                    } else if ("Foret".equals(currentPlay.getNom())) {
	                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers le Sanctuaire (en vert sur la carte)");
	                        waitForPlayerMove = true;
	                        targetCaseClass = Sanctuaire.class;  
	                       
	                    
	                } else if(
	                		(currentPlay.getDescription().startsWith("En vous promenant vous tombez sur un monstre dangereux, un gobelin ")) || 
	                		(currentPlay.getDescription().startsWith("En voici un deuxième! Préparez-vous au combat "))
	                		){
                        JOptionPane.showMessageDialog(this, "Vous êtes censé vous déplacer vers le Donjon");
                        waitForPlayerMove = true;
                        targetCaseClass = Donjon.class;  
                       
                    }
	                    
	                    else {
	                    	if (currentPlay instanceof CombatNode) {
		                    	System.out.println(" it issss a combatt");
		                    	displayCurrentNode();
		                    	displayCombatNode((CombatNode) currentPlay);
		                    	
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


	private void exit(int i) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void keyReleased(KeyEvent e) {
		// Not used
	}
	
	
	/**
     * Méthode pour sauvegarder une partie.
     * @param playerName Le nom du joueur pour sauvegarder la partie.
     */
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
	
	/**
     * Méthode pour charger une partie sauvegardée du jeu.
     * @param playerName Le nom du joueur pour charger la partie.
     */
	@Override
	public  void loadGame(String playerName) {
	    String fileName = baseFolder + playerName + ".dat";
	   
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
	        GameSaver gamesaver = (GameSaver) in.readObject();
	        this.p = gamesaver.getPersonnage();
	        this.currentPlay = gamesaver.getCurrentPlay();
	        if (panel == null) {
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
