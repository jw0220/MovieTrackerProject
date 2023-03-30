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
    private GridBagConstraints constraints;
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

    //MODIFIES: this
    //EFFECTS: runs the movie tracker GUI and initializes the home page.
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

    //MODIFIES: this
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

    //MODIFIES: this
    //EFFECTS: makes the GUI for when the Add Movie button is clicked
    public void addMovieGUI() {
        setAddMovieFrame();
        constraints = new GridBagConstraints();

        titleTextField();
        yearTextField();
        ratingTextField();
        reviewTextField();
        minutesTextField();
        genreTextField();
        doneButton();
    }

    //MODIFIES: this
    //EFFECTS: initializes a new frame for the GUI when the Add Movie button is clicked
    public void setAddMovieFrame() {
        addMovieFrame = new JFrame();
        addMovieFrame.setPreferredSize(new Dimension(500, 500));
        addMovieFrame.setLocationRelativeTo(null);
        addMovieFrame.setVisible(true);
        addMovieFrame.getContentPane().setLayout(new GridBagLayout());
    }

    //MODIFIES: this
    //EFFECTS: makes the text box so the user can input the title of the movie
    public void titleTextField() {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel title = new JLabel("Title:  ");
        addMovieFrame.getContentPane().add(title, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        textTitle = new JTextField();
        textTitle.setPreferredSize(new Dimension(200, 20));
        addMovieFrame.getContentPane().add(textTitle, constraints);
    }

    //MODIFIES: this
    //EFFECTS: makes the text box so the user can input the year of the movie
    public void yearTextField() {
        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel year = new JLabel("Year:  ");
        addMovieFrame.getContentPane().add(year, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        textYear = new JTextField();
        textYear.setPreferredSize(new Dimension(200, 20));
        addMovieFrame.getContentPane().add(textYear, constraints);
    }

    //MODIFIES: this
    //EFFECTS: makes the text box so the user can input the rating of the movie
    public void ratingTextField() {
        constraints.gridx = 0;
        constraints.gridy = 2;
        JLabel rating = new JLabel("Rating(0-5):  ");
        addMovieFrame.getContentPane().add(rating, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        textRating = new JTextField();
        textRating.setPreferredSize(new Dimension(200, 20));
        addMovieFrame.getContentPane().add(textRating, constraints);
    }

    //MODIFIES: this
    //EFFECTS: makes the text box so the user can input their review of the movie
    public void reviewTextField() {
        constraints.gridx = 0;
        constraints.gridy = 3;
        JLabel review = new JLabel("Review:  ");
        addMovieFrame.getContentPane().add(review, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        textReview = new JTextField();
        textReview.setPreferredSize(new Dimension(200, 20));
        addMovieFrame.getContentPane().add(textReview, constraints);
    }

    //MODIFIES: this
    //EFFECTS: makes the text box so the user can input the minutes of the movie
    public void minutesTextField() {
        constraints.gridx = 0;
        constraints.gridy = 4;
        JLabel minutes = new JLabel("Total Minutes:  ");
        addMovieFrame.getContentPane().add(minutes, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        textMinutes = new JTextField();
        addMovieFrame.getContentPane().add(textMinutes, constraints);
    }

    //MODIFIES: this
    //EFFECTS: makes the text box so the user can input the genre of the movie
    public void genreTextField() {
        constraints.gridx = 0;
        constraints.gridy = 5;
        JLabel genre = new JLabel("Genre:  ");
        addMovieFrame.getContentPane().add(genre, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        textGenre = new JTextField();
        textGenre.setPreferredSize(new Dimension(200, 20));
        addMovieFrame.getContentPane().add(textGenre, constraints);
    }

    //MODIFIES: this
    //EFFECTS: makes the done! button when user is done inputting their user info
    public void doneButton() {
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        JButton done = new JButton("Done!");
        addMovieFrame.getContentPane().add(done, constraints);
        done.addActionListener(e -> done());
        addMovieFrame.pack();
    }

    //MODIFIES: this
    //EFFECTS: makes the GUI for when done! button is clicked
    public void done() {
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
    }

    //MODIFIES: this
    //EFFECTS: makes the View Movies button
    public void viewMoviesButton() {
        JButton button = new JButton();
        button.setText("View My Watched Movies");
        button.setBounds(600, 50, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);
        button.addActionListener(e -> viewMovies());
    }

    //MODIFIES: this
    //EFFECTS: makes the GUI for when the view movies button is clicked
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

    //EFFECTS: makes the panel that prints out the list of movies that the user entered
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

    //MODIFIES: this
    //EFFECTS: makes the highest rated title button
    public void highestRatedTitleButton() {
        JButton button = new JButton();
        button.setText("My Highest Rated Title");
        button.setBounds(210, 200, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);

        button.addActionListener(e -> highestRatedTitle());
    }

    //EFFECTS: makes a dialogue box to show the highest rated movie title when the highest movie title button
    //         is clicked
    public void highestRatedTitle() {
        JOptionPane.showMessageDialog(null, myMovieList.getHighestRatedTitle(),
                "My Highest Rated Title", JOptionPane.PLAIN_MESSAGE);
    }

    //MODIFIES: this
    //EFFECTS: makes the most watched genre button
    public void mostWatchedGenreButton() {
        JButton button = new JButton();
        button.setText("My Most Watched Genre");
        button.setBounds(600, 200, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);

        button.addActionListener(e -> mostWatchedGenre());
    }


    //EFFECTS: makes a dialogue box to show the most watched genre when the most watched genre button
    //         is clicked
    public void mostWatchedGenre() {
        JOptionPane.showMessageDialog(null, myMovieList.getMostWatchedGenre(),
                "My Most Watched Genre", JOptionPane.PLAIN_MESSAGE);
    }

    //MODIFIES: this
    //EFFECTS: makes the average rating button
    public void averageRatingButton() {
        JButton button = new JButton();
        button.setText("My Average Rating");
        button.setBounds(600, 350, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);

        button.addActionListener(e -> averageRating());
    }

    //EFFECTS: makes a dialogue box to show the most watched genre when the most watched genre button
    //         is clicked
    public void averageRating() {
        JOptionPane.showMessageDialog(null, myMovieList.getAverageRating(), "My Average Rating",
                JOptionPane.PLAIN_MESSAGE);
    }

    //MODIFIES: this
    //EFFECTS: makes the total minutes button
    public void totalMinutesButton() {
        JButton button = new JButton();
        button.setText("My Total Minutes Watched");
        button.setBounds(210, 350, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);

        button.addActionListener(e -> totalMinutedWatched());
    }

    //EFFECTS: makes a dialogue box to show the total minutes watched when the total minuted watched button
    //         is clicked
    public void totalMinutedWatched() {
        JOptionPane.showMessageDialog(null, myMovieList.getTotalMinutesWatched(),
                "Total Minutes Watched", JOptionPane.PLAIN_MESSAGE);
    }

    //MODIFIES: this
    //EFFECTS: makes the save button
    public void saveButton() {
        JButton button = new JButton();
        button.setText("Save Movies");
        button.setBounds(405, 500, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);
        button.addActionListener(e -> saveMovies());
    }

    //EFFECTS: saves MovieList to file
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

    //MODIFIES: this
    //EFFECTS: makes the load button
    public void loadButton() {
        JButton button = new JButton();
        button.setText("Load Saved Movies");
        button.setBounds(405, 599, 200, 75);
        button.setBackground(Color.white);
        button.setFocusable(false);
        label.add(button);
        button.addActionListener(e -> loadMovies());
    }

    // MODIFIES: this
    // EFFECTS: loads MovieList from file
    public void loadMovies() {
        JFrame frame = new JFrame("Loaded Movies");

        try {
            myMovieList = jsonReader.read();
            JOptionPane.showMessageDialog(frame, "Loaded movies from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: sets the title for the main/first frame
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

    //MODIFIES: this
    //EFFECTS: sets the main/first frame of GUI
    public void setFrame() {
        frame = new JFrame();
        frame.setSize(1040, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Movie Tracker App");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }
}
