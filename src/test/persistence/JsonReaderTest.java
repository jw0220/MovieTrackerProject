package persistence;

import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MovieList ml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMovieList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMovieList.json");
        try {
            MovieList ml = reader.read();
            //assertEquals("My work room", ml.getName());
            assertEquals(0, ml.length());
        } catch (IOException e) {
            //fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMovieList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMovieList.json");
        try {
            MovieList ml = reader.read();
            //assertEquals("My work room", wr.getName());
            List<Movie> movies= ml.getMovies();
            assertEquals(2, movies.size());
            checkMovie("Hunger Games", "good", "action", 2012, 4, 120,
                    movies.get(0));
            checkMovie("Spider Man", "great", "action", 2018, 5, 110,
                    movies.get(1));
        } catch (IOException e) {
            //fail("Couldn't read from file");
        }
    }
}
