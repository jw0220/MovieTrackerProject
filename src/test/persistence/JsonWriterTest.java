package persistence;

import model.Movie;
import model.MovieList;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            MovieList ml = new MovieList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMovieList() {
        try {
            MovieList ml = new MovieList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMovieList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMovieList.json");
            ml = reader.read();
            assertEquals(0, ml.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMovieList() {
        try {
            MovieList ml = new MovieList();
            ml.addMovie(new Movie("Hunger Games", "good", "action", 2012, 4, 120));
            ml.addMovie(new Movie("Spider Man", "great", "action", 2018, 5, 110));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMovieList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMovieList.json");
            ml = reader.read();
            List<Movie> movies = ml.getMovies();
            assertEquals(2, movies.size());
            checkMovie("Hunger Games", "good", "action", 2012, 4, 120, movies.get(0));
            checkMovie("Spider Man", "great", "action", 2018, 5, 110, movies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
