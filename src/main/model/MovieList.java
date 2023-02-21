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

    //EFFECTS: get the highest rated title out of the list
    public String getHighestRatedTitle() {
        for (int i = 0; i < movies.size(); i++) {
            for (int j = i + 1; j < movies.size(); j++) {
                if (movies.get(i).getRating() > movies.get(j).getRating()) {
                    return movies.get(i).getTitle();
                } else {
                    return movies.get(j).getTitle();
                }
            }
        }
        return "";
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

    //EFFECTS: get the most watched genre of the movies in the list.
    public String getMostWatchedGenre() {
        String most = movies.get(0).getGenre();
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
