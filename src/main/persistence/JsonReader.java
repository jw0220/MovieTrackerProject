package persistence;

import model.MovieList;
import model.Movie;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads MovieList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MovieList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses MovieList from JSON object and returns it
    private MovieList parseMovieList(JSONObject jsonObject) {
        //String movies = jsonObject.getString("movies");
        MovieList ml = new MovieList();
        addMovies(ml, jsonObject);
        return ml;
    }

    // MODIFIES: ml
    // EFFECTS: parses movies from JSON object and adds them to MovieList
    private void addMovies(MovieList ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(ml, nextMovie);
        }
    }

    // MODIFIES: ml
    // EFFECTS: parses movie from JSON object and adds it to MovieList
    private void addMovie(MovieList ml, JSONObject jsonObject) {
        String title = jsonObject.getString("Title");
        String review = jsonObject.getString("Review");
        String genre = jsonObject.getString("Genre");
        int year = jsonObject.getInt("Year");
        double rating = jsonObject.getDouble("Rating");
        int minutes = jsonObject.getInt("Total Minutes");
        Movie movie = new Movie(title, review, genre, year, rating, minutes);
        ml.addMovie(movie);
    }
}
