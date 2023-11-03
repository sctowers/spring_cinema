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

    // retrieve list of all movies from the repo
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // retrieve a specific movie by its id from the repo
    public Movie getMovieById(Integer id){
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        return optionalMovie.orElse(null);
    }

    // add a new movie to the repo and return saved movie

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }


    // update a movie
    public Movie updateMovie(Integer id, Movie updatedMovie) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie existingMovie = optionalMovie.get();

            if (updatedMovie.getTitle() != null) {
                existingMovie.setTitle(updatedMovie.getTitle());
            }
            if (updatedMovie.getRating() != null) {
                existingMovie.setRating(updatedMovie.getRating());
            }
            if (updatedMovie.getDuration() > 0) {
                existingMovie.setDuration(updatedMovie.getDuration());
            }

            return movieRepository.save(existingMovie);
        }

        return null;
    }

    //  delete movie
    public boolean deleteMovie(Integer id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
