package Interface;

import javax.imageio.ImageIO;
import javax.swing.*;
import Components.*;
import entities.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe représente un panneau graphique pour afficher le terrain de jeu, le texte
 * associé à un nœud et les choix disponibles pour le joueur.
 */
public class PanelComponent extends JPanel implements Serializable, KeyListener {
    private static final long serialVersionUID = 1L;

    protected Terrain terrain;
    private final int tailleCase = 24;
    private BufferedImage wallImage;
    private BufferedImage riverImage;
    private BufferedImage clanAImage;
    private BufferedImage clanBImage;
    private BufferedImage personnageImage;
    private BufferedImage guerisseuseImage;
    private BufferedImage historienImage;
    private BufferedImage aubergisteImage;
    private String nodeText;
    private BufferedImage nodeImage; // Nouvelle propriété pour l'image du nœud
    ArrayList<String> nodeChoices;
    private JButton saveButton;
    private String baseFolder = "C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\";

    /**
     * Constructeur de la classe PanelComponent.
     *
     * @param terrain le terrain de jeu à afficher
     * @param fc le composant de fenêtre parent associé pour la gestion des événements
     */
    public PanelComponent(Terrain terrain, FrameComponent fc) {
        this.terrain = terrain;
        setBackground(Color.white);
        setPreferredSize(new Dimension(terrain.getHauteur() * tailleCase, terrain.getLargeur() * tailleCase));
        loadImages();
        this.nodeText = "";
        this.nodeChoices = new ArrayList<>();
        setFocusable(true); 
        addKeyListener(fc);

        // Créer et ajouter le bouton de sauvegarde
        saveButton = new JButton("Save Game");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = fc.nameField.getText();
                if (!playerName.isEmpty()) {
                    fc.saveGame(playerName);
                } else {
                    JOptionPane.showMessageDialog(fc, "Please enter your name to save the game.");
                }
            }
        });

        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Charge les images nécessaires pour le jeu depuis le dossier spécifié.
     */

    private void loadImages() {
        try {
            wallImage = ImageIO.read(new File(baseFolder + "wall.png"));
            riverImage = ImageIO.read(new File(baseFolder + "river.png"));
            personnageImage = ImageIO.read(new File(baseFolder + "personnage.png"));
            guerisseuseImage = ImageIO.read(new File(baseFolder + "guerisseuse.png"));
            historienImage = ImageIO.read(new File(baseFolder + "historien.png"));
            aubergisteImage = ImageIO.read(new File(baseFolder + "aubergiste.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Définit le texte à afficher pour le nœud actuel.
     *
     * @param text le texte à afficher
     */
    public void setNodeText(String text) {
        this.nodeText = insertLineBreaks(text,140);
        repaint();
    }
    
    /**
     * Insère des sauts de ligne dans le texte pour limiter la largeur.
     *
     * @param text le texte à formater
     * @param maxLength la longueur maximale d'une ligne
     * @return le texte formaté avec des sauts de ligne
     */
    public String insertLineBreaks(String text, int maxLength) {
        StringBuilder formattedText = new StringBuilder();
        int length = text.length();
        int start = 0;
        

        while (start < length) {
            int end = Math.min(start + maxLength, length);
            if (end < length) {
                // Si nous ne sommes pas à la fin du texte, trouvons le dernier espace avant la limite
                int lastSpace = text.lastIndexOf(' ', end);
                if (lastSpace > start) {
                    end = lastSpace;
                }
            }
            // Ajouter le segment de texte au résultat
            formattedText.append(text, start, end);
            // Ajouter un saut de ligne s'il ne s'agit pas de la fin du texte
            if (end < length) {
                formattedText.append("\n");
            }
            start = end + 1; // Recommencer après l'espace (ou après la limite si aucun espace trouvé)
        }

        return formattedText.toString();
    }

    /**
     * Définit l'image à afficher pour le nœud actuel.
     *
     * @param imagePath le chemin de l'image à afficher
     */
    public void setNodeImage(String imagePath) {
        try {
            if (imagePath != null) {
                this.nodeImage = ImageIO.read(new File(imagePath));
            } else {
                this.nodeImage = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    /**
     * Définit les choix disponibles pour le nœud actuel.
     *
     * @param choices la liste des choix à afficher
     */
    public void setNodeChoices(ArrayList<String> choices) {
        this.nodeChoices = choices;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int textAreaHeight = 170; // Augmenter cette valeur pour agrandir la section de texte
        int imageHeight = getHeight() - textAreaHeight;

        // Dessiner l'image du nœud si elle est définie
        if (nodeImage != null) {
            g.drawImage(nodeImage, 0, 0, getWidth(), imageHeight, this);
        } else {
            // Dessiner le terrain si l'image du nœud n'est pas définie
            for (Case[] cases : this.terrain.getCarte()) {
                for (Case c : cases) {
                    if (c instanceof CaseIntraversable) {
                        g.drawImage(wallImage, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
                    }
                    if (c instanceof CaseCLanA) {
                        g.setColor(Color.pink);
                        g.fillRect(c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase);
                    }
                    if (c instanceof CaseClanB) {
                        g.setColor(Color.magenta);
                        g.fillRect(c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase);
                    }
                    if (c instanceof Riviere) {
                        g.drawImage(riverImage, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
                    }
                    if (c instanceof Donjon) {
                        g.setColor(Color.gray);
                        g.fillRect(c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase);
                    }
                    if (c instanceof Sanctuaire) {
                        g.setColor(Color.green);
                        g.fillRect(c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase);
                    }
                    if (c instanceof CaseTraversable) {
                        if (((CaseTraversable) c).getContenu() instanceof Personnage) {
                            g.drawImage(personnageImage, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
                        }
                        if (((CaseTraversable) c).getContenu() instanceof Humain) {
                            if (((Humain) (((CaseTraversable) c).getContenu())).getCompetence().equals(Competence.guerisseuse)) {
                                g.drawImage(guerisseuseImage, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
                            }
                            if (((Humain) (((CaseTraversable) c).getContenu())).getCompetence().equals(Competence.aubergiste)) {
                                g.drawImage(aubergisteImage, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
                            }
                            if (((Humain) (((CaseTraversable) c).getContenu())).getCompetence().equals(Competence.historien)) {
                                g.drawImage(historienImage, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
                            }
                        }
                    }
                }
            }
        }

        g.setColor(Color.WHITE);
        g.fillRect(0, imageHeight, getWidth(), textAreaHeight);

        int y = imageHeight + 20;
        if (nodeText != null && !nodeText.isEmpty()) {
            g.setColor(Color.BLACK);
            y = drawMultilineString(g, nodeText, 10, y);
        }

        // Ajout de l'espace supplémentaire avant les choix du nœud
        y += 20;

        // Dessiner les choix du nœud
        if (nodeChoices != null && !nodeChoices.isEmpty()) {
            for (int i = 0; i < nodeChoices.size(); i++) {
                g.drawString((i + 1) + ". " + nodeChoices.get(i), 10, y + (i * 20));
            }
        }
    }

    /**
     * Dessine une chaîne de texte sur plusieurs lignes dans le composant graphique.
     *
     * @param g l'objet Graphics pour dessiner
     * @param text le texte à dessiner
     * @param x la position horizontale de départ
     * @param y la position verticale de départ
     * @return la position verticale après le dessin du texte
     */
    private int drawMultilineString(Graphics g, String text, int x, int y) {
        FontMetrics fm = g.getFontMetrics();
        int lineHeight = fm.getHeight();

        for (String line : text.split("\n")) {
            g.drawString(line, x, y);
            y += lineHeight;
        }
        return y;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilisé
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Non utilisé
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilisé
    }
}
