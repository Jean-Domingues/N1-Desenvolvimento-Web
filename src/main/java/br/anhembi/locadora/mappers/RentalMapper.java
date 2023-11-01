package br.anhembi.locadora.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.anhembi.locadora.dtos.PostRentalDTO;
import br.anhembi.locadora.models.Rental;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RentalMapper {
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void patch(PostRentalDTO dto, @MappingTarget Rental entity);

	Rental rentalDtoToRental(PostRentalDTO dto);
}
