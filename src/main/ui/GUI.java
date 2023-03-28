package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame frame;
    private JLabel label;
    private double rating;
    private int minutes;
    private String title;
    private String genre;
    private int year;
    private String review;
    private static final String JSON_STORE = "./data/MovieList.json";

    public GUI() {
        this.title = null;
        this.minutes = 0;
        this.rating = 0;
        this.genre = null;
        this.year = 0;
        this.review = null;
        setFrame();
        setTitle();
        ImageIcon background = new ImageIcon("images/moviebackground.jpg");

        label = new JLabel();
        label.setSize(1040, 800);
        label.setIcon(background);
        frame.add(label);
        addButtons();
        //frame.getContentPane().add(label);
    }

    public void addButtons() {
        addMovieButton();
        viewMoviesButton();
        highestRatedTitleButton();
        mostWatchedGenreButton();
        averageRatingButton();
        totalMinutesButton();
        saveButton();
        loadButton();
    }

    //EFFECTS: makes the Add Movie button
    public void addMovieButton() {
        JButton button = new JButton();
        button.setText("Add Movie");
        button.setBounds(210, 50, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);

        button.addActionListener(e -> addMovieGUI());
        label.add(button);
    }

    @SuppressWarnings("methodlength")
    public void addMovieGUI() {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JLabel title = new JLabel("Title:  ");
        frame.getContentPane().add(title, c);

        c.gridx = 1;
        c.gridy = 0;
        JTextField textTitle = new JTextField();
        textTitle.setPreferredSize(new Dimension(200, 20));
        textTitle.addActionListener(e -> textTitle.getText());
        frame.getContentPane().add(textTitle, c);

        c.gridx = 0;
        c.gridy = 1;
        JLabel year = new JLabel("Year:  ");
        frame.getContentPane().add(year, c);

        c.gridx = 1;
        c.gridy = 1;
        JTextField textYear = new JTextField();
        textYear.setPreferredSize(new Dimension(200, 20));
        textYear.addActionListener(e -> textYear.getText());
        frame.getContentPane().add(textYear, c);

        c.gridx = 0;
        c.gridy = 2;
        JLabel rating = new JLabel("Rating:  ");
        frame.getContentPane().add(rating, c);

        c.gridx = 1;
        c.gridy = 2;
        JTextField textRating = new JTextField();
        textRating.setPreferredSize(new Dimension(200, 20));
        textRating.addActionListener(e -> textRating.getText());
        frame.getContentPane().add(textRating, c);

        c.gridx = 0;
        c.gridy = 3;
        JLabel review = new JLabel("Review:  ");
        frame.getContentPane().add(review, c);

        c.gridx = 1;
        c.gridy = 3;
        JTextField textReview = new JTextField();
        textReview.setPreferredSize(new Dimension(200, 20));
        textReview.addActionListener(e -> textReview.getText());
        frame.getContentPane().add(textReview, c);

        c.gridx = 0;
        c.gridy = 4;
        JLabel minutes = new JLabel("Total Minutes:  ");
        frame.getContentPane().add(minutes, c);

        c.gridx = 1;
        c.gridy = 4;
        JTextField textMinutes = new JTextField();
        textMinutes.addActionListener(e -> minutes.equals(textMinutes.getText()));
        frame.getContentPane().add(textMinutes, c);

        c.gridx = 0;
        c.gridy = 5;
        JLabel genre = new JLabel("Genre:  ");
        frame.getContentPane().add(genre, c);

        c.gridx = 1;
        c.gridy = 5;
        JTextField textGenre = new JTextField();
        textGenre.setPreferredSize(new Dimension(200, 20));
        textGenre.addActionListener(e -> textGenre.getText());
        genre.equals(textGenre.getText());
        frame.getContentPane().add(textGenre, c);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        JButton done = new JButton("Add Movie to MovieList");
        frame.getContentPane().add(done, c);
        done.addActionListener(e -> viewMovies());

        frame.pack();
    }

    public void viewMoviesButton() {
        JButton button = new JButton();
        button.setText("View My Watched Movies");
        button.setBounds(600, 50, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);
        button.addActionListener(e -> viewMovies());
    }

    public void viewMovies() {
        JFrame frame = new JFrame("Showing All Movies");

        JPanel panel = viewMoviesToString();

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(scrollPane);

        frame.setVisible(true);
        frame.pack();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        frame.setLocationRelativeTo(null);
    }

    public JPanel viewMoviesToString() {
        JPanel panel = new JPanel();
        MovieList myMovies = new MovieList();
        myMovies.addMovie(new Movie(title, review, genre, year, rating, minutes));
        for (int i = 0; i < myMovies.length(); i++) {
            Movie movie = myMovies.getMovies().get(i);

            JLabel titleLabel = new JLabel("\tTitle: " + movie.getTitle());
            JLabel yearLabel = new JLabel("\tYear: " + movie.getYear());
            JLabel reviewLabel = new JLabel("\tReview: " + movie.getReview());
            JLabel genreLabel = new JLabel("\tGenre: " + movie.getGenre());
            JLabel ratingLabel = new JLabel("\tRating: " + movie.getRating());
            JLabel minutesLabel = new JLabel("\tTotal Minutes: " + movie.getMinutes());

            panel.add(titleLabel);
            panel.add(reviewLabel);
            panel.add(genreLabel);
            panel.add(yearLabel);
            panel.add(ratingLabel);
            panel.add(minutesLabel);
        }
        return panel;
    }

    public void highestRatedTitleButton() {
        JButton button = new JButton();
        button.setText("My Highest Rated Title");
        button.setBounds(210, 200, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);
    }

    public void mostWatchedGenreButton() {
        JButton button = new JButton();
        button.setText("My Most Watched Genre");
        button.setBounds(600, 200, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);
    }

    public void averageRatingButton() {
        JButton button = new JButton();
        button.setText("My Average Rating");
        button.setBounds(600, 350, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);
    }

    public void totalMinutesButton() {
        JButton button = new JButton();
        button.setText("My Total Minutes Watched");
        button.setBounds(210, 350, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);
    }

    public void saveButton() {
        JButton button = new JButton();
        button.setText("Save");
        button.setBounds(405, 500, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);
    }

    public void loadButton() {
        JButton button = new JButton();
        button.setText("Load Saved Tracker");
        button.setBounds(405, 599, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);
    }

    //EFFECTS: sets the title
    public void setTitle() {
        setFrame();
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel();

        label1.setText("Movie Tracker App");
        panel.add(label1);

        panel.setPreferredSize(new Dimension(500, 30));

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
    }

    //EFFECTS: sets the frame of GUI
    public void setFrame() {
        frame = new JFrame();
        frame.setSize(1040, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Movie Tracker App");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }
}
