package ui;

import model.Movie;
import model.MovieList;

import java.util.Scanner;

public class MovieTrackerApp {
    private Scanner input;
    private MovieList myMovieList;
    private Movie movie;

    //EFFECTS: runs the movie tracker application
    public MovieTrackerApp() {
        init();
        runTracker();
    }

    //EFFECTS: process user input
    private void runTracker() {
        boolean keepGoing = true;
        String command = null;

        myMovieJournal();

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
        System.out.println("\nSee you next time!");
    }

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
        movie.setGenre(input.next());
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

        System.out.println(movie.toString());
    }

    //EFFECTS: initializes console
    private void init() {
        input = new Scanner(System.in);
        myMovieList = new MovieList();
        movie = new Movie(null, null, null, 0, 0, 0);
        input.useDelimiter("\n");
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nEnter Y to add more movies");
        System.out.println("\nEnter A to see my average rating");
        System.out.println("\nEnter G to see my most watched genre");
        System.out.println("\nEnter H to see my highest rates title");
        System.out.println("\nEnter M to see my total minutes watched");
        System.out.println("\nEnter Q to exit out of application");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("A")) {
            seeAverageRating();
        } else if (command.equals("Y")) {
            runTracker();
        } else if (command.equals("G")) {
            seeMostWatchedGenre();
        } else if (command.equals("H")) {
            seeHighestRatedTitle();
        } else if (command.equals("M")) {
            seeMinutesWatched();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this
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

    //MODIFIES: this
    //EFFECTS: print the highest rated title in the movie list
    private void seeHighestRatedTitle() {
        String title = myMovieList.getHighestRatedTitle();
        System.out.println(title);
    }

    //MODIFIES: this
    //EFFECTS: print the total minutes watched from all the movies in the list
    private void seeMinutesWatched() {
        int minutes = myMovieList.getTotalMinutesWatched();
        System.out.println(minutes);
    }
}
