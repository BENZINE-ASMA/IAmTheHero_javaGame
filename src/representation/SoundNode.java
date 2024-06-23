package representation;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.HashMap; 

public class SoundNode extends NodeDecorator {
	private String musicPath;
	
	public SoundNode(Event musicNode, String musicPath) {
		super(musicNode);
		this.musicPath = musicPath;
	}
	
	
	public void playAudio() {
        try {
            File musicFile = new File(this.musicPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();

        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture du fichier audio : " + e.getMessage());
        }
    }

	@Override
	public void display() {
		playAudio();
		super.display();
		
	}

	@Override
	public Event chooseNext() {
		return super.chooseNext();
	}

	@Override
	public Event chooseNext(String choice) {
		return super.chooseNext(choice);
	}

	@Override
	public Event chooseNext2(String choice) {
		return super.chooseNext2(choice);
	}

}
