package ui;

import javax.swing.*;

public class NewWindow {

    JFrame frame = new JFrame();
    JLabel label = new JLabel();

    public NewWindow() {
        frame.setSize(1040, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
