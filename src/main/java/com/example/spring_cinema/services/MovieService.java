package com.example.spring_cinema.services;

import com.example.spring_cinema.models.Movie;
import com.example.spring_cinema.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    // dependency injection of MovieRepository into service
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    // retrieve list of all movies from the repo
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // retireve a specific movie by its id from the repo
    public Movie getMovieById(Integer id){
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        return optionalMovie.orElse(null);
    }

    // add a new movie to the repo and return saved movie

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }


    // update a movie
    public Movie updateMovie(Movie updatedMovie) {
        // check if the movie exists in the repository
        Optional<Movie> optionalMovie = movieRepository.findById(updatedMovie.getId());
        if (optionalMovie.isPresent()) {
            Movie existingMovie = optionalMovie.get();

            // update the attributes
            if (updatedMovie.getTitle() != null) {
                existingMovie.setTitle(updatedMovie.getTitle());
            }
            if (updatedMovie.getRating() != null) {
                existingMovie.setRating(updatedMovie.getRating());
            }
            if (updatedMovie.getDuration() > 0) {
                existingMovie.setDuration(updatedMovie.getDuration());
            }

            // save the updated movie
            return movieRepository.save(existingMovie);
        }

        // If the movie doesn't exist, return null
        return null;
    }

    //  delete movie
    public boolean deleteMovie(Integer id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (optionalMovie.isPresent()) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
