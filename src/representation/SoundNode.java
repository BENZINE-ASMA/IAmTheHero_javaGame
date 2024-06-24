package representation;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.HashMap;

public class SoundNode extends NodeDecorator {
    private String musicPath;
    private Clip clip; // Référence au Clip

    public SoundNode(Event musicNode, String musicPath) {
        super(musicNode);
        this.musicPath = musicPath;
    }

    public void playAudio() {
        try {
            File musicFile = new File(this.musicPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture du fichier audio : " + e.getMessage());
        }
    }

    public void stopAudio() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close(); // Libérer les ressources
        }
    }
    
    @Override
    public void playDecorator() {
    	playAudio();
    	if (decoratedNode instanceof NodeDecorator) {
        	((NodeDecorator)decoratedNode).playDecorator();
        }
    }

    @Override
    public void display() {
        playAudio();
        super.display();
    }
    
    public void display2() {
    	playAudio();
    }

    @Override
    public Event chooseNext() {
        Event node = super.chooseNext();
        // Arrêter la musique
        stopAudio();
        return node;
    }

    @Override
    public Event chooseNext(String choice) {
        Event node = super.chooseNext(choice);
        // Arrêter la musique
        stopAudio();
        return node;
    }

    @Override
    public Event chooseNext2(String choice) {
        Event node = super.chooseNext2(choice);
        // Arrêter la musique
        stopAudio();
        return node;
    }
}
