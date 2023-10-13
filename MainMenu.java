//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.JFrame;
//import javax.swing.SwingUtilities;
//import javax.swing.colorchooser.*;
import javax.swing.*;


public class MainMenu {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Main menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
}
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> createAndShowGUI());
}
}

