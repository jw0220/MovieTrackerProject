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
    private JLabel label;
    private MovieList myMovieList;
    private Movie movie;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private String newTitle;
//    private String review;
    private double newRating;
//    private int year;
//    private int minutes;
//    private String genre;

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
        newTitle = "";
        newRating = 0;

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        myMovieList = new MovieList();
        movie = new Movie(newTitle, "", "", 0, 0, 0);
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

    //fix
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
        textTitle.addActionListener(e -> newTitle = textTitle.getText());
        frame.getContentPane().add(textTitle, c);

        c.gridx = 0;
        c.gridy = 1;
        JLabel year = new JLabel("Year:  ");
        frame.getContentPane().add(year, c);

        c.gridx = 1;
        c.gridy = 1;
        JTextField textYear = new JTextField();
        textYear.setPreferredSize(new Dimension(200, 20));
        textYear.addActionListener(e -> movie.setYear(Integer.parseInt(textYear.getText())));
        frame.getContentPane().add(textYear, c);

        c.gridx = 0;
        c.gridy = 2;
        JLabel rating = new JLabel("Rating:  ");
        frame.getContentPane().add(rating, c);

        c.gridx = 1;
        c.gridy = 2;
        JTextField textRating = new JTextField();
        textRating.setPreferredSize(new Dimension(200, 20));
        textRating.addActionListener(e -> newRating = Double.parseDouble(textRating.getText()));
        frame.getContentPane().add(textRating, c);

        c.gridx = 0;
        c.gridy = 3;
        JLabel review = new JLabel("Review:  ");
        frame.getContentPane().add(review, c);

        c.gridx = 1;
        c.gridy = 3;
        JTextField textReview = new JTextField();
        textReview.setPreferredSize(new Dimension(200, 20));
        textReview.addActionListener(e -> movie.setReview(textReview.getText()));
        frame.getContentPane().add(textReview, c);

        c.gridx = 0;
        c.gridy = 4;
        JLabel minutes = new JLabel("Total Minutes:  ");
        frame.getContentPane().add(minutes, c);

        c.gridx = 1;
        c.gridy = 4;
        JTextField textMinutes = new JTextField();
        textMinutes.addActionListener(e -> movie.setMinutes(Integer.parseInt(textMinutes.getText())));
        frame.getContentPane().add(textMinutes, c);

        c.gridx = 0;
        c.gridy = 5;
        JLabel genre = new JLabel("Genre:  ");
        frame.getContentPane().add(genre, c);

        c.gridx = 1;
        c.gridy = 5;
        JTextField textGenre = new JTextField();
        textGenre.setPreferredSize(new Dimension(200, 20));
        textGenre.addActionListener(e -> movie.setGenre(textGenre.getText()));
        frame.getContentPane().add(textGenre, c);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        JButton done = new JButton("Add Movie to MovieList");
        frame.getContentPane().add(done, c);
        done.addActionListener(e -> {
            myMovieList.addMovie(new Movie(newTitle, movie.getReview(), movie.getGenre(), movie.getYear(),
                    newRating, movie.getMinutes()));
            viewMovies();
        });

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

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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
