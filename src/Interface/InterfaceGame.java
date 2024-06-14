package Interface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGame extends JFrame {

	private JTextField nameField;
	private JButton startButton;
	private JButton quitButton;

	public InterfaceGame() {
		
		setTitle("Jeu d'aventure");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);


		JLabel nameLabel = new JLabel("Enter Name:");
		nameField = new JTextField(20);

		startButton = new JButton("Commencer");
		quitButton = new JButton("Quitter");


		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleStart();
			}
		});

		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleQuit();
			}
		});

		 // Organiser les composants dans des panneaux
        JPanel inputPanel = new JPanel();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

       
        this.getContentPane().add(inputPanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
	
	private void showGameInterface() {
	   
	    getContentPane().removeAll();
	    repaint();
	    revalidate();
	    
	    JPanel gamePanel = new JPanel();
	    gamePanel.setLayout(new BorderLayout());
	    
	    JTextArea gameArea = new JTextArea(20, 40);
	    gameArea.setEditable(false);
	    gameArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
	    gameArea.setText("Bienvenue dans le jeu !\nIci commencera votre aventure.");
	    
	    gamePanel.add(new JScrollPane(gameArea), BorderLayout.CENTER);
	    
	    getContentPane().add(gamePanel);
	    revalidate();
	    repaint();
	}

    private void handleStart() {
        String name = nameField.getText();
        if (!name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bienvenue " + name + "! Le jeu va commencer.");
            showGameInterface();
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nom.");
        }
    }

    private void handleQuit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	InterfaceGame interfaceGraphique = new InterfaceGame();
                interfaceGraphique.setVisible(true);
            }
        });
    }
}