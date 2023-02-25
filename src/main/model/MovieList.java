package model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    private ArrayList<Movie> movies;

    //EFFECTS: constructs a movie list as an empty list
    public MovieList() {
        this.movies = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add movie to the movie list
    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    //EFFECTS: get the highest rated title out of the list. If there is a tie for the highest rated title then
    //         get the most recent title of all the tied titles.
    public String getHighestRatedTitle() {
        int recent = movies.size() - 1;
        String highest = movies.get(recent).getTitle();
        for (int i = 0; i < movies.size(); i++) {
            for (int j = i + 1; j < movies.size(); j++) {
                if (movies.get(i).getRating() > movies.get(j).getRating()) {
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

    //EFFECTS: return the most watched genre of the movies in the list.
    //         - If there is a tie between multiple genres, then return the genre that is in the list first out of the
    //           ties genres
    // *reference: code drawn from https://youtu.be/uINMn4FeHCM*
    public String getMostWatchedGenre() {
        String most = movies.get(0).getGenre();
        int count = 0;
        for (int i = 0; i < movies.size(); i++) {
            int cnt = 0;
            for (int j = i + 1; j < movies.size(); j++) {
                if (movies.get(i).getGenre().equals(movies.get(j).getGenre())) {
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

    //EFFECTS: puts all the movies added to the movie list in string representation and return
    //         a list with all the movies in string representation.
    public List<String> viewMoviesInMovieList() {
        ArrayList<String> viewList = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            String movie = movies.get(i).toString();
            viewList.add(movie);
        }
        return viewList;
    }

    // EFFECTS: returns the numbers of movies currently in the list
    public int length() {
        return this.movies.size();
    }
}
