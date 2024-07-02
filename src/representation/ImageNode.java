package representation;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Interface.PanelComponent;

/**
 * Classe ImageNode qui étend NodeDecorator pour ajouter la fonctionnalité d'affichage d'image à un nœud.
 */
public class ImageNode extends NodeDecorator {
    private static final long serialVersionUID = 1L;
    private String imagePath;
    private JFrame frame;

    /**
     * Constructeur pour créer un ImageNode.
     *
     * @param decoratedNode le nœud à décorer avec la fonctionnalité d'affichage d'image
     * @param imagePath le chemin de l'image à afficher
     */
    public ImageNode(Event decoratedNode, String imagePath) {
        super(decoratedNode);
        this.imagePath = imagePath;
        this.frame = new JFrame();
    }

    /**
     * Joue l'image associée et appelle la méthode playDecorator() du nœud décoré.
     *
     * @return un entier permettant de savoir si la méthode affiche une image (version interface seulement)
     */
    @Override
    public int playDecorator() {
        displayImage();
        if (decoratedNode instanceof NodeDecorator) {
            ((NodeDecorator) decoratedNode).playDecorator();
        }
        return 0;
    }

    /**
     * Joue l'image associée et appelle la méthode playDecorator() du nœud décoré avec interaction graphique.
     *
     * @param panel le composant de panneau à utiliser pour l'interaction
     * @param cpt un compteur ou indice nécessaire pour l'action
     * @return un entier permettant de savoir si la méthode affiche une image
     */
    @Override
    public int playDecorator(PanelComponent panel, int cpt) {
        displayImage(panel);
        cpt++;
        if (decoratedNode instanceof NodeDecorator) {
            cpt += ((NodeDecorator) decoratedNode).playDecorator(panel, cpt);
        }
        return cpt;
    }

    /**
     * Affiche l'image spécifiée.
     */
    public void displayImage() {
        try {
            Image image = loadImage(this.imagePath);
            if (image != null) {
                ImageIcon icon = new ImageIcon(image);
                JLabel label = new JLabel(icon);
                JPanel panel = new JPanel();
                panel.add(label);
                frame.getContentPane().add(panel);
                frame.pack();
                frame.setVisible(true);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de l'affichage de l'image : " + e.getMessage());
        }
    }

    /**
     * Affiche l'image spécifiée dans un PanelComponent.
     *
     * @param panel le PanelComponent où afficher l'image
     */
    public void displayImage(PanelComponent panel) {
        if (getImagePath() != null) {
            panel.setNodeImage(getImagePath());
        }
    }

    /**
     * Cache l'image affichée et libère les ressources associées.
     */
    public void hideImage() {
        if (frame != null) {
            frame.setVisible(false);
            frame.dispose();
        }
    }

    /**
     * Affiche le nœud décoré et l'image associée.
     */
    @Override
    public void display() {
        displayImage();
        super.display();
    }

    /**
     * Affiche des informations supplémentaires spécifiques au décorateur et l'image associée.
     */
    public void display2() {
        displayImage();
    }

    /**
     * Choisit le prochain événement à exécuter à partir du nœud décoré et cache l'image.
     *
     * @return l'événement suivant à exécuter
     */
    @Override
    public Event chooseNext() {
        Event node = super.chooseNext();
        hideImage();
        return node;
    }

    /**
     * Choisit le prochain événement à exécuter à partir du nœud décoré en fonction d'un choix spécifique et cache l'image.
     *
     * @param choice le choix spécifique pour déterminer l'événement suivant
     * @return l'événement suivant à exécuter en fonction du choix donné
     */
    @Override
    public Event chooseNext(String choice) {
        Event node = super.chooseNext(choice);
        hideImage();
        return node;
    }

    /**
     * Choisit le prochain événement à exécuter à partir du nœud décoré en fonction d'un choix spécifique et cache l'image.
     *
     * @param choice le choix spécifique pour déterminer l'événement suivant
     * @return l'événement suivant à exécuter en fonction du choix donné
     */
    @Override
    public Event chooseNext2(String choice) {
        Event node = super.chooseNext2(choice);
        hideImage();
        return node;
    }

    /**
     * Obtient le chemin de l'image associée.
     *
     * @return le chemin de l'image
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Définit le chemin de l'image associée.
     *
     * @param imagePath le chemin de l'image
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Charge une image à partir du chemin spécifié en utilisant le ClassLoader.
     *
     * @param path le chemin relatif de l'image à charger
     * @return l'image chargée
     * @throws IOException si l'image ne peut pas être chargée
     */
    private Image loadImage(String path) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + path);
        }
        return ImageIO.read(inputStream);
    }
}
