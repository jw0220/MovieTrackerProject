package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieListTest {
    private MovieList list1;
    private MovieList list2;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;
    private Movie movie4;
    private Movie movie5;

    @BeforeEach
    public void setup() {
        list1 = new MovieList();
        list2 = new MovieList();
        movie1 = new Movie("Harry Potter", "very good", "fantasy", 2003,
                    4.5, 120);
        movie2 = new Movie("The Hunger Games", "amazing!", "action", 2011,
                    5, 112);
        movie3 = new Movie("Black Panther", "awesome", "action", 2018, 5, 112);
        movie4 = new Movie("Little Women", "great", "coming of age", 2019, 5, 90);
        movie5 = new Movie("Catching Fire", "wow", "action", 2013, 4.8, 100);
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
        assertEquals("Black Panther", list1.getHighestRatedTitle());
    }

    @Test
    public void testGetHighestRatedTitleTied() {
        list1.addMovie(movie1);
        list1.addMovie(movie2);
        list1.addMovie(movie3);
        list1.addMovie(movie4);
        list1.addMovie(movie5);
        assertEquals("Little Women", list1.getHighestRatedTitle());
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
    public void testViewMoviesInMovieList() {
        list1.addMovie(movie1);
        list1.addMovie(movie2);
        List<String> viewMovies = new ArrayList<>();
        viewMovies.add(movie1.toString());
        viewMovies.add(movie2.toString());
        assertEquals(viewMovies, list1.viewMoviesInMovieList());
    }
}
