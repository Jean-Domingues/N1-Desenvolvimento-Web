package br.anhembi.locadora.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.anhembi.locadora.dtos.PostRentalDTO;
import br.anhembi.locadora.models.Rental;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RentalMapper {
	Rental post(PostRentalDTO dto);
}
