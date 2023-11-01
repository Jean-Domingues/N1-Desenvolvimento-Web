package br.anhembi.locadora.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.anhembi.locadora.dtos.PostMovieDTO;
import br.anhembi.locadora.models.Movie;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MovieMapper {
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void patch(PostMovieDTO dto, @MappingTarget Movie entity);

	Movie movieDtoToMovie(PostMovieDTO dto);
}
