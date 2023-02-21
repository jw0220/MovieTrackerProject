package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

    //@Test
    //public void testToString() {

