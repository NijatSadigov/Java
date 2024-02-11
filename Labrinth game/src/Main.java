package src;

import javax.swing.JFrame;

class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // change size to preferred size

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.StartGameThread();
        
    }
}