package SnakeGame;
import javax.swing.JFrame;

public class SnakeFrame extends JFrame{

    SnakeFrame() {
        int frameWidth = 500; //Size of frame
        int frameHeight = 500;
        
        SnakePanel panel = new SnakePanel(frameWidth, frameHeight);

        this.setTitle("Snake Game!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(frameWidth, frameHeight);
        this.setLocationRelativeTo(null);
        
        this.add(panel); // Adding the panel before the frame becomes visible
        this.setVisible(true); // Making the frame visible
    }
}
