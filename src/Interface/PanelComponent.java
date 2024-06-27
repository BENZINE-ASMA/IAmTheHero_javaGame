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
    private String baseFolder = "C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\";

    public PanelComponent(Terrain terrain, FrameComponent fc) {
        this.terrain = terrain;
        setBackground(Color.white);
        setPreferredSize(new Dimension(terrain.getHauteur() * tailleCase, terrain.getLargeur() * tailleCase));
        loadImages();
        this.nodeText = "";
        this.nodeChoices = new ArrayList<>();
        setFocusable(true); 
        addKeyListener(fc);

        // Create and add the save button
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

    public void setNodeText(String text) {
        this.nodeText = insertLineBreaks(text,150);
        repaint();
    }
    
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

    public void setNodeChoices(ArrayList<String> choices) {
        this.nodeChoices = choices;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int textAreaHeight = 150; // Augmenter cette valeur pour agrandir la section de texte
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
                        g.setColor(Color.blue);
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

        if (nodeText != null && !nodeText.isEmpty()) {
            g.setColor(Color.BLACK);
            g.drawString(nodeText, 10, imageHeight + 20);
        }

        // Dessiner les choix du nœud
        if (nodeChoices != null && !nodeChoices.isEmpty()) {
            for (int i = 0; i < nodeChoices.size(); i++) {
                g.drawString((i + 1) + ". " + nodeChoices.get(i), 10, imageHeight + 40 + (i * 20));
            }
        }
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
