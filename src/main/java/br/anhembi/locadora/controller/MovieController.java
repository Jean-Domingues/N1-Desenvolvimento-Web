package br.anhembi.locadora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.anhembi.locadora.dtos.PostMovieDTO;
import br.anhembi.locadora.models.Movie;
import br.anhembi.locadora.services.MovieService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	private MovieService movieService;

	@GetMapping
	public ResponseEntity<List<Movie>> get() {
		var movies = movieService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(movies);
	}

	@PostMapping
	public ResponseEntity<Movie> create(@Valid @RequestBody PostMovieDTO movieDto) {
		var movie = movieService.save(movieDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(movie);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Movie> update(@PathVariable long id, @Valid @RequestBody PostMovieDTO movieDTO) {
		var movie = movieService.update(id, movieDTO);
		return ResponseEntity.ok(movie);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		movieService.delete(id);
		return ResponseEntity.ok().build();
	}
}
