package model;

// Represents a movie with a title, review, genre, rating, year, and length in minutes
public class Movie {
    private String title;
    private String review;
    private String genre;
    private double rating;
    private int year;
    private int minutes;

    //EFFECTS: construct a movie with given title, review, genre, year, rating, and total minutes
    public Movie(String title, String review, String genre, int year, double rating, int minutes) {
        this.title = title;
        this.review = review;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.minutes = minutes;
    }

    //EFFECTS: return title of movie
    public String getTitle() {
        return title;
    }

    //MODIFIES: this
    //EFFECTS: set the title for the movie
    public void setTitle(String t) {
        title = t;
    }

    //EFFECTS: return the review of the movie
    public String getReview() {
        return review;
    }

    //MODIFIES: this
    //EFFECTS: set the review for the movie
    public void setReview(String r) {
        review = r;
    }

    //EFFECTS: return the genre of the movie
    public String getGenre() {
        return genre;
    }

    //MODIFIES: this
    //EFFECTS: set the genre of the movie
    public void setGenre(String g) {
        genre = g;
    }

    //EFFECTS: return the rating of the movie
    public double getRating() {
        return rating;
    }

    //MODIFIES: this
    //EFFECTS: set the rating of the movie from 1 to 5
    //         - 1 being the lowest rating
    //         - 5 being the highest rating
    public void setRating(double r) {
        rating = r;
    }

    //EFFECTS: get the year of the movie
    public int getYear() {
        return year;
    }

    //MODIFIES: this
    //EFFECTS: set the year of the movie
    public void setYear(int y) {
        year = y;
    }

    //EFFECTS: get the total minutes of the movie
    public int getMinutes() {
        return minutes;
    }

    //MODIFIES: this
    //EFFECTS: set the total minutes of the movie
    public void setMinutes(int m) {
        minutes = m;
    }

    //EFFECTS: return a string representation of a movie
    public String toString() {
        return "\n"
                + "\nMovie title: " + title
               + "\n Year: " + year
               + "\n Genre: " + genre
               + "\n Total Minutes: " + minutes
               + "\n My Rating: " + rating
               + "\n My Review: " + review;
    }
}
