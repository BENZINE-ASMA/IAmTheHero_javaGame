package representation;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Interface.PanelComponent;

import java.util.HashMap;
import entities.Personnage;

public class ImageNode extends NodeDecorator {
	 private static final long serialVersionUID = 1L;
    private String imagePath;
    private JFrame frame;

    public ImageNode(Event decoratedNode, String imagePath) {
        super(decoratedNode);
        this.imagePath = imagePath;
        this.frame = new JFrame();
    }
    
    @Override
    public int playDecorator() {
    	displayImage();
        if (decoratedNode instanceof NodeDecorator) {
        	((NodeDecorator)decoratedNode).playDecorator();
        }
        return 0;
    }
    

    public int playDecorator(PanelComponent panel, int cpt) {
    	displayImage(panel);
    	cpt ++;
        if (decoratedNode instanceof NodeDecorator) {
        	cpt += ((NodeDecorator)decoratedNode).playDecorator(panel, cpt);
        }
        return cpt;
    }

    public void displayImage() {
        try {
            Image image = ImageIO.read(new File(this.imagePath));
            ImageIcon icon = new ImageIcon(image);
            JLabel label = new JLabel(icon);
            JPanel panel = new JPanel();
            panel.add(label);
            frame.getContentPane().add(panel);
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage de l'image : " + e.getMessage());
        }
    }
    
    public void displayImage(PanelComponent panel) {
    	if(getImagePath() != null) {
    		panel.setNodeImage(getImagePath());    		
    	}
    }

    public void hideImage() {
        if (frame != null) {
            frame.setVisible(false);
            frame.dispose();
        }
    }

    @Override
    public void display() {
        displayImage();
        super.display();
    }
    
    public void display2() {
    	displayImage();
    }

    @Override
    public Event chooseNext() {
        Event node = super.chooseNext();
        hideImage();
        return node;
    }

    @Override
    public Event chooseNext(String choice) {
        Event node = super.chooseNext(choice);
        hideImage();
        return node;
    }

    @Override
    public Event chooseNext2(String choice) {
        Event node = super.chooseNext2(choice);
        hideImage();
        return node;
    }

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
    
    
}
