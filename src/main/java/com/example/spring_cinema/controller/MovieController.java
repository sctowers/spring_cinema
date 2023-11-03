package com.example.spring_cinema.controller;

import com.example.spring_cinema.models.Movie;
import com.example.spring_cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    // get request for the movies endpoint (returns list of all movies)
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    // get request for the movies endpoint (returns a specific movie by its id)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // post request for the movies endpoint (adds a new movie to the system)
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    // patch request for the movies endpoint (updates the movie)
    @PatchMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody Movie updatedMovie) {
        Movie updated = movieService.updateMovie(id, updatedMovie);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // to delete a movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {
        boolean deleted = movieService.deleteMovie(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


