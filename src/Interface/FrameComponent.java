package Interface;

import javax.swing.*;
import entities.Direction;
import entities.Personnage;
import representation.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class FrameComponent extends JFrame implements KeyListener {
    private Personnage p = new Personnage();
    PanelComponent panel;
    private JTextField nameField;
    private JButton startButton;
    private JButton quitButton;
    private NodesGraph graph;
    private Node currentPlay;

    public FrameComponent() {
        // Initialize graph and currentPlay
        graph = createGraph();
        currentPlay = graph.getGraph().get("introduction");

        // Configure the main window
        setTitle("Jeu d'aventure");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel nameLabel = new JLabel("Enter Name:");
        nameField = new JTextField(20);

        startButton = new JButton("Commencer");
        quitButton = new JButton("Quitter");

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

        // Organize components into panels
        JPanel inputPanel = new JPanel();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

        // Add panels to the main window
        getContentPane().add(inputPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

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
        panel = new PanelComponent(new Terrain("C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\terrain2.txt", p));
        
        // Set up the new game interface
        getContentPane().add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();

        // Display the first choice
        displayCurrentNode();
    }

    private void displayCurrentNode() {
        if (currentPlay != null) {
            // Display current node text
            JOptionPane.showMessageDialog(this, currentPlay.getDescription());

            // Ensure currentPlay is an instance of InnerNode to access getNodesSuivant method
            if (currentPlay instanceof InnerNode) {
                // Retrieve choices and convert to ArrayList
                Map<String, Node> nodesSuivant = ((InnerNode) currentPlay).getNodesSuivant();
                ArrayList<String> options = new ArrayList<>(nodesSuivant.keySet());

                // Display choices
                String choice = (String) JOptionPane.showInputDialog(
                    this,
                    "Choisissez une action:",
                    "Choix",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options.toArray(new String[0]),
                    options.get(0)
                );

                if (choice != null) {
                    currentPlay = currentPlay.chooseNext(choice);
                    displayCurrentNode();
                }
            }
        }
    }
               

    private NodesGraph createGraph() {
        NodesGraph graph = new NodesGraph();
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

        return graph;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrameComponent interfaceGraphique = new FrameComponent();
                interfaceGraphique.setVisible(true);
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> panel.terrain.movePlayer(Direction.nord);
            case KeyEvent.VK_DOWN -> panel.terrain.movePlayer(Direction.sud);
            case KeyEvent.VK_LEFT -> panel.terrain.movePlayer(Direction.ouest);
            case KeyEvent.VK_RIGHT -> panel.terrain.movePlayer(Direction.est);
        }
        panel.repaint(); // Repaint the panel to reflect changes
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }
}
