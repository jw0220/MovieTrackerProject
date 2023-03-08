package persistence;

import model.Movie;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMovie(String title, String review, String genre, int year, double rating,
                              int minutes, Movie movie) {
        assertEquals(title, movie.getTitle());
        assertEquals(review, movie.getReview());
        assertEquals(genre, movie.getGenre());
        assertEquals(year, movie.getYear());
        assertEquals(rating, movie.getRating());
        assertEquals(minutes, movie.getMinutes());
    }
}
