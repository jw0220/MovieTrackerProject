# My Personal Project

## Are you interested in:
- movies?
- tv shows?
- k-dramas?
- anime? 

## Have you ever been asked *What's your favourite movie?* and every single movie you've watched leaves your brain?

**Then this application is for you.**

This project was born out of an interest to find an easy way to track my favourite movies and TV shows
since I watch so many. I wanted a tracker that can also allow me to write my own reviews and ratings as well
as return statistics of what I have watched. I wanted to merge something like Letterboxd with SpotifyWrapped to bring 
forward a tracker that not only allows you to make a list of all the movies you have watched but also an application 
that can give you some insight into your favourite titles, genre, and other statistics.


## User Stories

- As a user, I want to be able to add a movie/tv show to a list of movies/tv shows.
- As a user, I want to be able to add a rating and review to a movie/tv show.
- As a user, I want to add information like the genre and minutes of the movie/tv show.
- As a user, I want to be able to view all the movies/tv shows I have watched.
- As a user, I want to be able to see statistics on my highest rated title, average rating, most watched genre,
and total minutes watched of all the movies and tv shows combined.
- As a user, I want to be able to save my movie list to file (if I so choose).
- As a user, I want to be able to load my movie list from file (if I so choose).

## Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by clicking the "Add Movies" button.
- You can generate the second required action related to adding Xs to a Y by clicking the "My Highest Rated Title",
"My Most Watched Genre", "My Total Minutes Watched", or the "My Average Rating" button.
- You can locate my visual component in the background of the home page of the GUI when you first run it. 
- You can save the state of my application by clicking the "Save Movies" button.
- You can reload the state of my application by clicking the "Load Saved Movies" button.

## Phase 4: Task 2

Thu Apr 13 12:58:22 PDT 2023
Added hunger games to list of movies
Thu Apr 13 12:58:30 PDT 2023
Got highest rated title so far
Thu Apr 13 12:58:32 PDT 2023
Got most watched genre so far 
Thu Apr 13 12:58:35 PDT 2023
Got total minutes watched so far
Thu Apr 13 12:58:37 PDT 2023
Got average rating so far

## Phase 4: Task 3

With more time I would reduce the duplication in my code for the MovieTrackerGUI class. There is a lot of duplication
for the titleTextField(), yearTextField(), ratingTextField(), reviewTextField(), minutesTextField(), and 
genreTextField() methods. I would make a separate method for the dimension of the text field methods since they all use
the same dimensions. This way if I want to change the dimension I would just need to change the dimension method rather 
going through and changing all the text field methods, which can easily result in bugs being added to the code. There 
is also lots of duplication that could be reduced in the addMovieButton(), viewMoviesButton(), 
highestRatedTitleButton(), mostWatchedGenreButton(), averageRatingButton(), totalMinutesButton(), saveButton(), 
and loadButton() methods. 
We can reduce the duplicity by making a separate method that takes all the code that is identical. Since we want all
the buttons to look the same on the GUI, making a separate method would allow us to change the button's size, colour, or
other attributes in that method rather than changing multiple methods, which can add bugs to the code. 
We can also increase cohesion in the MovieTrackerGUI class by making a separate class for the buttons and the text 
fields and have these classes as fields in the MoveTracker GUI class. This would increase cohesion as the button class 
will now handle all the button functions, the text field class will handle the textfield functions and MovieTrackerGUI 
class will handle putting the GUI together, that way each class now has one responsibility.


**References**
- ui package inspired by TellerApp 
- persistence package and tests inspired by JsonSerializationDemo
- GUI made with the help of https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html &
  https://www.youtube.com/watch?v=iE8tZ0hn2Ws & https://www.youtube.com/watch?v=Kmgo00avvEw




