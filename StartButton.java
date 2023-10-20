package SnakeGame;
import javax.swing.JButton;
import java.awt.*;


public class StartButton {
    private JButton button; // The actual button

    public StartButton() {
        button = new JButton("Press anywhere to start the game!");
        button.setFocusable(false); 
        button.setOpaque(true);
        button.setBorderPainted(false);

        button.setBackground(new Color(0, 153, 153));
        button.setFont(new Font("Impact", Font.ROMAN_BASELINE, 20));
        button.setForeground(new Color(0,51,51));

    }

    public JButton getButton() {
        return button;
    }

}
