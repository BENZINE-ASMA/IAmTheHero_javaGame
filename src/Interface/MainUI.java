package Interface;

import javax.swing.SwingUtilities;

public class MainUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrameComponent interfaceGraphique = new FrameComponent();
                interfaceGraphique.setVisible(true);
            }
        });
    }
}
