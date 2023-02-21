package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieListTest {
    private MovieList list1;
    private MovieList list2;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    @BeforeEach
    public void setup() {
        list1 = new MovieList();
        list2 = new MovieList();
        movie1 = new Movie("Harry Potter", "very good", "fantasy", 2003,
                    4.5, 120);
        movie2 = new Movie("The Hunger Games", "amazing!", "action", 2011,
                    5, 112);
        movie3 = new Movie("Black Panther", "awesome", "action", 2018, 4.9, 112);
    }

    @Test
    public void  testConstructor() {
        assertEquals(0, list1.length());
        assertEquals(0, list2.length());
    }

    @Test
    public void testAddMovie() {
        list1.addMovie(movie1);
        assertEquals(1, list1.length());
        list1.addMovie(movie2);
        assertEquals(2, list1.length());
    }

    @Test
    public void testGetHighestRatedTitle() {
        list1.addMovie(movie1);
        list1.addMovie(movie2);
        list1.addMovie(movie3);
        assertEquals("The Hunger Games", list1.getHighestRatedTitle());
    }

    @Test
    public void testGetTotalMinutesWatched() {
        list1.addMovie(movie1);
        list1.addMovie(movie2);
        assertEquals(232, list1.getTotalMinutesWatched());
    }

    @Test
    public void testGetTotalMinutesWatchedEmptyList() {
        assertEquals(0, list1.length());
        assertEquals(0, list1.getTotalMinutesWatched());
    }

    @Test
    public void testGetAverageRating() {
        list1.addMovie(movie1);
        list1.addMovie(movie2);
        assertEquals(4.75, list1.getAverageRating());
    }

    @Test
    public void testGetMostWatchedGenre() {
        list1.addMovie(movie1);
        list1.addMovie(movie2);
        list1.addMovie(movie3);
        assertEquals("action", list1.getMostWatchedGenre());
    }
}
