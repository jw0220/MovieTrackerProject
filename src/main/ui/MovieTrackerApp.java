package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.Scanner;

// Represents the Movie Tracker application
public class MovieTrackerApp {
    private static final String JSON_STORE = "./data/MovieList.json";
    private Scanner input;
    private MovieList myMovieList;
    private Movie movie;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the movie tracker application
    public MovieTrackerApp() throws FileNotFoundException {
        init();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTracker();
    }

    //MODIFIES: this
    //EFFECTS: process user input
    private void runTracker() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toUpperCase();

            if (command.equals("Q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("See ya!");
    }

    //MODIFIES: this
    //EFFECTS: user inputs movie information
    private void myMovieJournal() {
        input = new Scanner(System.in);

        System.out.println("Enter movie title: ");
        movie.setTitle(input.nextLine());
        String title = movie.getTitle();

        System.out.println("Enter movie year: ");
        movie.setYear(input.nextInt());
        int year = movie.getYear();

        System.out.println("Enter movie genre: ");
        input.nextLine();
        movie.setGenre(input.nextLine());
        String genre = movie.getGenre();

        System.out.println("Enter the length of the movie in minutes: ");
        movie.setMinutes(input.nextInt());
        int minutes = movie.getMinutes();

        System.out.println("My review of the movie: ");
        input.nextLine();
        movie.setReview(input.nextLine());
        String review = movie.getReview();

        System.out.println("My rating for the movie from 1 to 5: ");
        movie.setRating(input.nextDouble());
        double rating = movie.getRating();

        myMovieList.addMovie(new Movie(title, review, genre, year, rating, minutes));
    }

    //MODIFIES: this
    //EFFECTS: initializes console
    private void init() {
        input = new Scanner(System.in);
        myMovieList = new MovieList();
        movie = new Movie(null, null, null, 0, 0, 0);
        input.useDelimiter("\n");
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nEnter Y to add a movie");
        System.out.println("\nEnter A to see my average rating");
        System.out.println("\nPress G to see my most watched genre");
        System.out.println("\nEnter H to see my highest rated title");
        System.out.println("\nEnter M to see my total minutes watched");
        System.out.println("\nEnter V to view all the movies I have watched");
        System.out.println("\nEnter S to save movie list to file");
        System.out.println("\nEnter L to load movie list from file");
        System.out.println("\nEnter Q to leave application");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("A")) {
            seeAverageRating();
        } else if (command.equals("Y")) {
            myMovieJournal();
        } else if (command.equals("H")) {
            seeHighestRatedTitle();
        } else if (command.equals("G")) {
            seeMostWatchedGenre();
        } else if (command.equals("M")) {
            seeMinutesWatched();
        } else if (command.equals("V")) {
            viewMovies();
        } else if (command.equals("S")) {
            saveMovieList();
        } else if (command.equals("L")) {
            loadMovieList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: print the average rating of all movies in the list
    private void seeAverageRating() {
        double averageRating = myMovieList.getAverageRating();
        System.out.println(averageRating);
    }

    //MODIFIES: this
    //EFFECTS: print the most watched genre of all the movies in the list
    private void seeMostWatchedGenre() {
        String genre = myMovieList.getMostWatchedGenre();
        System.out.println(genre);
    }

    //EFFECTS: print the highest rated title in the movie list
    private void seeHighestRatedTitle() {
        String title = myMovieList.getHighestRatedTitle();
        System.out.println(title);
    }

    //EFFECTS: print the total minutes watched from all the movies in the list
    private void seeMinutesWatched() {
        int minutes = myMovieList.getTotalMinutesWatched();
        System.out.println(minutes);
    }

    //EFFECTS: prints out the list of movies entered by user in string representation
    private void viewMovies() {
        List<String> movies = myMovieList.viewMoviesInMovieList();
        System.out.println(movies);
    }

    //EFFECTS: saves MovieList to file
    private void saveMovieList() {
        try {
            jsonWriter.open();
            jsonWriter.write(myMovieList);
            jsonWriter.close();
            System.out.println("Saved MovieList to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads MovieList from file
    private void loadMovieList() {
        try {
            myMovieList = jsonReader.read();
            System.out.println("Loaded MovieList from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

