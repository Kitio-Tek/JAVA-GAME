package SnakeGame;

import javax.swing.JButton;
import java.awt.*;


public class RestartButton {
    private JButton button; // The actual button

    public RestartButton() {
        button = new JButton("Play Again!");
        button.setVisible(false); // Initially, the button is not visible
        button.setFocusable(false); 
        button.setOpaque(true);
        button.setBorderPainted(false);

        button.setBackground(new Color(0, 173, 173));
        button.setFont(new Font("Futura", Font.ROMAN_BASELINE, 16));
        button.setForeground(Color.WHITE);
        button.setBounds(170, 120, 160, 40); // Adds the button to the panel
    }

    public JButton getButton() {
        return button;
    }

}
