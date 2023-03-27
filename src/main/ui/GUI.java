package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame frame;
    private JLabel label;

    public GUI() {
        setFrame();
        ImageIcon background = new ImageIcon("images/moviebackground.jpg");

        label = new JLabel();
        label.setSize(1040, 800);
        label.setIcon(background);
        setTitle();

        frame.add(label);

        addMovieButton();
        viewMoviesButton();
        highestRatedTitleButton();
        totalMinutesButton();
        saveButton();
        loadButton();
        averageRatingButton();
        mostWatchedGenreButton();
    }

    //EFFECTS: makes the Add Movie button
    public void addMovieButton() {
        JButton button = new JButton();
        button.setText("Add Movie");
        button.setBounds(210, 50, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);
    }

    public void viewMoviesButton() {
        JButton button = new JButton();
        button.setText("View My Watched Movies");
        button.setBounds(600, 50, 200, 75);
        button.setBackground(Color.white);
        label.add(button);
    }

    public void highestRatedTitleButton() {
        JButton button = new JButton();
        button.setText("My Highest Rated Title");
        button.setBounds(210, 200, 200, 75);
        button.setBackground(Color.white);
        label.add(button);
    }

    public void mostWatchedGenreButton() {
        JButton button = new JButton();
        button.setText("My Most Watched Genre");
        button.setBounds(600, 200, 200, 75);
        button.setBackground(Color.white);
        label.add(button);
    }

    public void averageRatingButton() {
        JButton button = new JButton();
        button.setText("My Average Rating");
        button.setBounds(600, 350, 200, 75);
        button.setBackground(Color.white);
        label.add(button);
    }

    public void totalMinutesButton() {
        JButton button = new JButton();
        button.setText("My Total Minutes Watched");
        button.setBounds(210, 350, 200, 75);
        button.setBackground(Color.white);
        label.add(button);
    }

    public void saveButton() {
        JButton button = new JButton();
        button.setText("Save");
        button.setBounds(405, 500, 200, 75);
        button.setBackground(Color.white);
        label.add(button);
    }

    public void loadButton() {
        JButton button = new JButton();
        button.setText("Load Saved Tracker");
        button.setBounds(405, 599, 200, 75);
        button.setBackground(Color.white);
        label.add(button);
    }

    //EFFECTS: sets the title
    public void setTitle() {
        setFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel();

        label.setText("Movie Tracker App");
        panel.add(label);

        panel.setPreferredSize(new Dimension(500, 30));

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
    }

    //EFFECTS: sets the frame of GUI
    public void setFrame() {
        frame = new JFrame();
        frame.setSize(1040, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Movie Tracker App");
        //frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }
}
