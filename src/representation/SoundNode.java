package representation;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.io.InputStream;
import Interface.PanelComponent;

/**
 * Classe SoundNode qui étend NodeDecorator pour ajouter la fonctionnalité de lecture audio à un nœud.
 */
public class SoundNode extends NodeDecorator {
    private static final long serialVersionUID = 1L;
    private String musicPath;
    private transient Clip clip; // Référence au Clip

    /**
     * Constructeur pour créer un SoundNode.
     *
     * @param musicNode le nœud à décorer avec la fonctionnalité audio
     * @param musicPath le chemin du fichier audio à jouer
     */
    public SoundNode(Event musicNode, String musicPath) {
        super(musicNode);
        this.musicPath = musicPath;
    }

    /**
     * Joue le fichier audio spécifié.
     */
    public void playAudio() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.musicPath);
            if (inputStream == null) {
                throw new IOException("Resource not found: " + this.musicPath);
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture du fichier audio : " + e.getMessage());
        }
    }

    /**
     * Arrête la lecture audio et libère les ressources associées.
     */
    public void stopAudio() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close(); // Libérer les ressources
        }
    }

    /**
     * Joue l'audio associé et appelle la méthode playDecorator() du nœud décoré.
     *
     * @return un entier représentant si le noeud passera par une image (dans l'interface)
     */
    @Override
    public int playDecorator() {
        playAudio();
        if (decoratedNode instanceof NodeDecorator) {
            ((NodeDecorator) decoratedNode).playDecorator();
        }
        return 0;
    }

    /**
     * Joue l'audio associé et appelle la méthode playDecorator() du nœud décoré avec interaction graphique.
     *
     * @param panel le composant de panneau à utiliser pour l'interaction
     * @param cpt un compteur ou indice nécessaire pour l'action
     * @return un entier représentant si le noeud passera par le type image dans l'interface
     */
    @Override
    public int playDecorator(PanelComponent panel, int cpt) {
        playAudio();
        if (decoratedNode instanceof NodeDecorator) {
            cpt += ((NodeDecorator) decoratedNode).playDecorator(panel, cpt);
        }
        return cpt;
    }

    /**
     * Affiche le nœud décoré et joue l'audio associé.
     */
    @Override
    public void display() {
        playAudio();
        super.display();
    }

    /**
     * Affiche des informations supplémentaires spécifiques au décorateur et joue l'audio associé.
     */
    public void display2() {
        playAudio();
    }

    /**
     * Choisit le prochain événement à exécuter à partir du nœud décoré et arrête l'audio.
     *
     * @return l'événement suivant à exécuter
     */
    @Override
    public Event chooseNext() {
        Event node = super.chooseNext();
        stopAudio();
        return node;
    }

    /**
     * Choisit le prochain événement à exécuter à partir du nœud décoré en fonction d'un choix spécifique et arrête l'audio.
     *
     * @param choice le choix spécifique pour déterminer l'événement suivant
     * @return l'événement suivant à exécuter en fonction du choix donné
     */
    @Override
    public Event chooseNext(String choice) {
        Event node = super.chooseNext(choice);
        stopAudio();
        return node;
    }

    /**
     * Choisit le prochain événement à exécuter à partir du nœud décoré en fonction d'un choix spécifique et arrête l'audio.
     *
     * @param choice le choix spécifique pour déterminer l'événement suivant
     * @return l'événement suivant à exécuter en fonction du choix donné
     */
    @Override
    public Event chooseNext2(String choice) {
        Event node = super.chooseNext2(choice);
        stopAudio();
        return node;
    }
}
