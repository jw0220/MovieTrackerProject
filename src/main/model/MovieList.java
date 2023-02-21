package model;

import java.util.ArrayList;

public class MovieList {
    private ArrayList<Movie> movies;

    //EFFECTS: constructs a movie list as an empty list
    public MovieList() {
        this.movies = new ArrayList<>();
    }

    //EFFECTS: add movie to the movie list
    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    //EFFECTS: get the highest rated title out of the list if tie get the most recent or
    //        if the tied with another movie, get the most recent one out of the tied movies
    public String getHighestRatedTitle() {
        int recent = movies.size() - 1;
        String highest = movies.get(recent).getTitle();
        for (int i = 0; i < movies.size(); i++) {
            for (int j = i + 1; j < movies.size(); j++) {
                if (movies.get(i).getRating() >= movies.get(j).getRating()) {
                    highest = movies.get(i).getTitle();
                } else {
                    highest = movies.get(j).getTitle();
                }
            }
        }
        return highest;
    }

    //EFFECTS: get the total minutes watched of all the movies in the list
    public int getTotalMinutesWatched() {
        int totalMinutes = 0;
        for (Movie m: this.movies) {
            totalMinutes = totalMinutes + m.getMinutes();
        }
        return totalMinutes;
    }

    //EFFECTS: get the average rating of all the movies in the list
    public double getAverageRating() {
        double totalRating = 0;
        for (Movie m: this.movies) {
            totalRating = totalRating + m.getRating();
        }
        return totalRating / this.movies.size();
    }

    //EFFECTS: get the most watched genre of the movies in the list or
    //         if tie get the most recent movie genre
    public String getMostWatchedGenre() {
        int recent = movies.size() - 1;
        String most = movies.get(recent).getGenre();
        int count = 0;
        for (int i = 0; i < movies.size(); i++) {
            int cnt = 0;
            for (int j = i + 1; j < movies.size(); j++) {
                if (movies.get(i).getGenre() == movies.get(j).getGenre()) {
                    cnt++;
                }
                if (count < cnt) {
                    most = movies.get(i).getGenre();
                    count = cnt;
                }
            }
        }
        return most;
    }

    // EFFECTS: returns the numbers of movies currently in the list
    public int length() {
        return this.movies.size();
    }
}
