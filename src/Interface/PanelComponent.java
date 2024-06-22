package Interface;
import javax.imageio.ImageIO;
import javax.swing.*;
import Components.*;
import entities.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class PanelComponent extends JPanel implements Serializable,KeyListener {
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
    ArrayList<String> nodeChoices;
    private JButton saveButton;

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

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        this.add(saveButton, BorderLayout.SOUTH);
    }

    private void loadImages() {
    	
        try {
            wallImage = ImageIO.read(new File("C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\wall.png"));
            riverImage = ImageIO.read(new File("C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\river.png"));
            personnageImage = ImageIO.read(new File("C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\personnage.png"));
            guerisseuseImage = ImageIO.read(new File("C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\guerisseuse.png"));
            historienImage = ImageIO.read(new File("C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\historien.png"));
            aubergisteImage = ImageIO.read(new File("C:\\Users\\marie\\eclipse-workspace\\IAmTheHero_javaGame\\src\\aubergiste.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNodeText(String text) {
        this.nodeText = text;
        repaint();
    }

    public void setNodeChoices(ArrayList<String> choices) {
        this.nodeChoices = choices;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
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

        if (nodeText != null && !nodeText.isEmpty()) {
            g.setColor(Color.BLACK);
            g.drawString(nodeText, 10, terrain.getHauteur() * tailleCase + 20);
        }

        // Dessiner les choix du nœud
        if (nodeChoices != null && !nodeChoices.isEmpty()) {
            for (int i = 0; i < nodeChoices.size(); i++) {
                g.drawString((i + 1) + ". " + nodeChoices.get(i), 10, terrain.getHauteur() * tailleCase + 40 + (i * 20));
            }
        }
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

