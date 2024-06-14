package Interface;

import javax.imageio.ImageIO;
import javax.swing.*;
import Components.*;
import entities.Personnage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelComponent extends JPanel {

    protected final Terrain terrain;
    private final int tailleCase = 24;
    private BufferedImage wallImage;
    private BufferedImage riverImage;
    private BufferedImage clanAImage;
    private BufferedImage clanBImage;
    private BufferedImage personnageImage;

    public PanelComponent(Terrain terrain) {
        this.terrain = terrain;
        setBackground(Color.white);
        setPreferredSize(new Dimension(terrain.getHauteur() * tailleCase, terrain.getLargeur() * tailleCase));
        loadImages();
        setFocusable(true); // Make sure the panel can receive focus
    }

    private void loadImages() {
        try {
            wallImage = ImageIO.read(new File("C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\wall.png"));
            riverImage = ImageIO.read(new File("C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\river.png"));
          //  clanAImage = ImageIO.read(new File("C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\clanA.png"));
           // clanBImage = ImageIO.read(new File("C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\clanB.png"));
            personnageImage = ImageIO.read(new File("C:\\Users\\lenovo\\eclipse-workspaces\\IAmTheHero_javaGame\\src\\personnage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                   // g.drawImage(clanBImage, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
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
                if (c instanceof CaseTraversable) {
                    if (((CaseTraversable) c).getContenu() instanceof Personnage) {
                        g.drawImage(personnageImage, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
                    }
                }
            }
        }
    }
}
