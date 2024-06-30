package Interface;

import javax.swing.SwingUtilities;

/**
 * Classe principale qui démarre l'interface graphique Swing.
 */
public class MainUI {
    
    /**
     * Méthode principale qui démarre l'application Swing dans l'Event Dispatch Thread (EDT).
     * @param args les arguments de la ligne de commande (non utilisés dans cette application)
     */
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
