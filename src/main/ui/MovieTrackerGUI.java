package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a Movie Tracker GUI application
public class MovieTrackerGUI {
    private JFrame frame;
    private JFrame addMovieFrame;
    private GridBagConstraints c;
    private JLabel label;
    private MovieList myMovieList;
    private Movie movie;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTextField textTitle;
    private JTextField textYear;
    private JTextField textRating;
    private JTextField textReview;
    private JTextField textMinutes;
    private JTextField textGenre;

    private static final String JSON_STORE = "./data/MovieList.json";

    //EFFECTS: runs the movie tracker GUI
    public MovieTrackerGUI() throws FileNotFoundException {
        init();
        setTitle();
        ImageIcon background = new ImageIcon("images/moviebackground.jpg");

        label = new JLabel();
        label.setSize(1040, 800);
        label.setIcon(background);
        frame.add(label);
        addButtons();
        frame.pack();
    }

    //MODIFIES: this
    //EFFECTS: initializes fields
    public void init() {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        myMovieList = new MovieList();
        movie = new Movie(null, null, null, 0, 0, 0);
    }

    //EFFECTS: adds all the buttons to the homepage
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

    public void addMovieGUI() {
        setAddMovieFrame();
        c = new GridBagConstraints();

        titleTextField();
        yearTextField();
        ratingTextField();
        reviewTextField();
        minutesTextField();
        genreTextField();
        doneButton();
        addMovieFrame.pack();
    }

    public void setAddMovieFrame() {
        addMovieFrame = new JFrame();
        addMovieFrame.setPreferredSize(new Dimension(500, 500));
        addMovieFrame.setLocationRelativeTo(null);
        addMovieFrame.setVisible(true);
        addMovieFrame.getContentPane().setLayout(new GridBagLayout());
    }

    public void titleTextField() {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JLabel title = new JLabel("Title:  ");
        addMovieFrame.getContentPane().add(title, c);

        c.gridx = 1;
        c.gridy = 0;
        textTitle = new JTextField();
        textTitle.setPreferredSize(new Dimension(200, 20));
        addMovieFrame.getContentPane().add(textTitle, c);
    }

    public void yearTextField() {
        c.gridx = 0;
        c.gridy = 1;
        JLabel year = new JLabel("Year:  ");
        addMovieFrame.getContentPane().add(year, c);

        c.gridx = 1;
        c.gridy = 1;
        textYear = new JTextField();
        textYear.setPreferredSize(new Dimension(200, 20));
        addMovieFrame.getContentPane().add(textYear, c);
    }

    public void ratingTextField() {
        c.gridx = 0;
        c.gridy = 2;
        JLabel rating = new JLabel("Rating(0-5):  ");
        addMovieFrame.getContentPane().add(rating, c);

        c.gridx = 1;
        c.gridy = 2;
        textRating = new JTextField();
        textRating.setPreferredSize(new Dimension(200, 20));
        addMovieFrame.getContentPane().add(textRating, c);
    }

    public void reviewTextField() {
        c.gridx = 0;
        c.gridy = 3;
        JLabel review = new JLabel("Review:  ");
        addMovieFrame.getContentPane().add(review, c);

        c.gridx = 1;
        c.gridy = 3;
        textReview = new JTextField();
        textReview.setPreferredSize(new Dimension(200, 20));
        addMovieFrame.getContentPane().add(textReview, c);
    }

    public void minutesTextField() {
        c.gridx = 0;
        c.gridy = 4;
        JLabel minutes = new JLabel("Total Minutes:  ");
        addMovieFrame.getContentPane().add(minutes, c);

        c.gridx = 1;
        c.gridy = 4;
        textMinutes = new JTextField();
        addMovieFrame.getContentPane().add(textMinutes, c);
    }

    public void genreTextField() {
        c.gridx = 0;
        c.gridy = 5;
        JLabel genre = new JLabel("Genre:  ");
        addMovieFrame.getContentPane().add(genre, c);

        c.gridx = 1;
        c.gridy = 5;
        textGenre = new JTextField();
        textGenre.setPreferredSize(new Dimension(200, 20));
        addMovieFrame.getContentPane().add(textGenre, c);
    }

    public void doneButton() {
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        JButton done = new JButton("Done!");
        addMovieFrame.getContentPane().add(done, c);
        done.addActionListener(e -> {
            movie.setTitle(textTitle.getText());
            movie.setYear(Integer.parseInt(textYear.getText()));
            movie.setRating(Double.parseDouble(textRating.getText()));
            movie.setGenre(textGenre.getText());
            movie.setMinutes(Integer.parseInt(textMinutes.getText()));
            movie.setReview(textReview.getText());
            myMovieList.addMovie(new Movie(movie.getTitle(), movie.getReview(), movie.getGenre(), movie.getYear(),
                    movie.getRating(), movie.getMinutes()));
            addMovieFrame.dispose();
            viewMovies();
        });
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

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(scrollPane);

        frame.setVisible(true);
        frame.pack();
        frame.setSize(800, 800);

        frame.setLocationRelativeTo(null);
    }

    public JPanel viewMoviesToString() {
        JPanel panel = new JPanel();

        for (int i = 0; i < myMovieList.length(); i++) {
            Movie movie = myMovieList.getMovies().get(i);

            JLabel titleLabel = new JLabel("\nTitle: " + movie.getTitle());
            JLabel yearLabel = new JLabel("\nYear: " + movie.getYear());
            JLabel reviewLabel = new JLabel("\nReview: " + movie.getReview());
            JLabel genreLabel = new JLabel("\nGenre: " + movie.getGenre());
            JLabel ratingLabel = new JLabel("\nRating: " + movie.getRating());
            JLabel minutesLabel = new JLabel("\nTotal Minutes: " + movie.getMinutes());
            JLabel spaceLabel = new JLabel("\n ");

            panel.add(titleLabel);
            panel.add(reviewLabel);
            panel.add(genreLabel);
            panel.add(yearLabel);
            panel.add(ratingLabel);
            panel.add(minutesLabel);
            panel.add(spaceLabel);
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

        button.addActionListener(e -> highestRatedTitle());
    }

    public void highestRatedTitle() {
        JOptionPane.showMessageDialog(null, myMovieList.getHighestRatedTitle(),
                "My Highest Rated Title", JOptionPane.PLAIN_MESSAGE);
    }

    public void mostWatchedGenreButton() {
        JButton button = new JButton();
        button.setText("My Most Watched Genre");
        button.setBounds(600, 200, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);

        button.addActionListener(e -> mostWatchedGenre());
    }

    public void mostWatchedGenre() {
        JOptionPane.showMessageDialog(null, myMovieList.getMostWatchedGenre(),
                "My Most Watched Genre", JOptionPane.PLAIN_MESSAGE);
    }

    public void averageRatingButton() {
        JButton button = new JButton();
        button.setText("My Average Rating");
        button.setBounds(600, 350, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);

        button.addActionListener(e -> averageRating());
    }

    public void averageRating() {
        JOptionPane.showMessageDialog(null, myMovieList.getAverageRating(), "My Average Rating",
                JOptionPane.PLAIN_MESSAGE);
    }

    public void totalMinutesButton() {
        JButton button = new JButton();
        button.setText("My Total Minutes Watched");
        button.setBounds(210, 350, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);

        button.addActionListener(e -> totalMinutedWatched());
    }

    public void totalMinutedWatched() {
        JOptionPane.showMessageDialog(null, myMovieList.getTotalMinutesWatched(),
                "Total Minutes Watched", JOptionPane.PLAIN_MESSAGE);
    }

    public void saveButton() {
        JButton button = new JButton();
        button.setText("Save Movies");
        button.setBounds(405, 500, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);
        button.addActionListener(e -> saveMovies());
    }

    public void saveMovies() {
        JFrame frame = new JFrame("Saving Movies");

        try {
            jsonWriter.open();
            jsonWriter.write(myMovieList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame, "Movies Saved.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Unable to write to file: " + JSON_STORE);
        }
    }

    public void loadButton() {
        JButton button = new JButton();
        button.setText("Load Saved Movies");
        button.setBounds(405, 599, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);
        button.addActionListener(e -> loadMovies());
    }

    public void loadMovies() {
        JFrame frame = new JFrame("Loaded Movies");

        try {
            myMovieList = jsonReader.read();
            JOptionPane.showMessageDialog(frame, "Loaded movies from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: sets the title of the frame
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
