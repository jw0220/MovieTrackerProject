package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    public void runBefore() {
        movie1 = new Movie("Harry Potter", "very good", "fantasy", 2003,
                4.5, 120);
        movie2 = new Movie("The Hunger Games", "amazing!", "action", 2011,
                5, 112);
    }

    @Test
    public void testConstructor() {
        assertEquals("Harry Potter", movie1.getTitle());
        assertEquals("very good", movie1.getReview());
        assertEquals("fantasy", movie1.getGenre());
        assertEquals(2003, movie1.getYear());
        assertEquals(4.5, movie1.getRating());
        assertEquals(120, movie1.getMinutes());

        assertEquals("The Hunger Games", movie2.getTitle());
        assertEquals("amazing!", movie2.getReview());
        assertEquals("action", movie2.getGenre());
        assertEquals(2011, movie2.getYear());
        assertEquals(5, movie2.getRating());
        assertEquals(112, movie2.getMinutes());
    }

    @Test
    public void testToString() {
        assertEquals( "\n" +
                "\nMovie title: Harry Potter" +
                "\n Year: 2003" +
                "\n Genre: fantasy" +
                "\n Total Minutes: 120" +
                "\n My Rating: 4.5" +
                "\n My Review: very good",
                movie1.toString());
    }

    @Test
    public void testSetTitle() {
        movie1.setTitle("Lady Bird");
        assertEquals("Lady Bird", movie1.getTitle());
    }

    @Test
    public void testSetReview() {
        movie1.setReview("terrible");
        assertEquals("terrible", movie1.getReview());
    }

    @Test
    public void testSetGenre() {
        movie1.setGenre("action");
        assertEquals("action", movie1.getGenre());
    }

    @Test
    public void testSetRating() {
        movie1.setRating(2);
        assertEquals(2, movie1.getRating());
    }

    @Test
    public void testSetYear() {
        movie1.setYear(2011);
        assertEquals(2011, movie1.getYear());
    }

    @Test
    public void testSetMinutes() {
        movie1.setMinutes(90);
        assertEquals(90, movie1.getMinutes());
    }
}