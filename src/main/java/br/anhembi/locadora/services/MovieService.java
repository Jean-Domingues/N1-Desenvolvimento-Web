package br.anhembi.locadora.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.anhembi.locadora.dtos.PostMovieDTO;
import br.anhembi.locadora.errors.custom.NotFoundError;
import br.anhembi.locadora.mappers.MovieMapper;
import br.anhembi.locadora.models.Movie;
import br.anhembi.locadora.repositories.CategoryRepo;
import br.anhembi.locadora.repositories.MovieRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
	private final MovieRepo movieRepo;
	private final MovieMapper movieMapper;

	private final CategoryRepo categoryRepo;

	public Movie save(PostMovieDTO movieDTO) {
		var category = categoryRepo.findById(movieDTO.getCategoryId()).orElseThrow(NotFoundError::new);
		var movie = movieMapper.movieDtoToMovie(movieDTO);
		movie.setCategory(category);
		return movieRepo.save(movie);
	}

	public List<Movie> findAll() {
		return movieRepo.findAll();
	}

	public Movie update(long id, PostMovieDTO movieDTO) {
		var movie = movieRepo.findById(id).orElseThrow(NotFoundError::new);

		movieMapper.patch(movieDTO, movie);
		if (movieDTO.getCategoryId() != null) {
			var category = categoryRepo.findById(movieDTO.getCategoryId()).orElseThrow(NotFoundError::new);
			movie.setCategory(category);
		}

		movieRepo.save(movie);

		return movie;
	}

	public Movie delete(long id) {
		var movie = movieRepo.findById(id).orElseThrow(NotFoundError::new);

		movieRepo.delete(movie);

		return movie;
	}
}
