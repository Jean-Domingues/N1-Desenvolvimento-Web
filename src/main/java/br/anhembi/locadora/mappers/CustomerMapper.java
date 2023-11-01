package br.anhembi.locadora.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.anhembi.locadora.dtos.PatchCustomerDTO;
import br.anhembi.locadora.dtos.PostCustomerDTO;
import br.anhembi.locadora.models.Customer;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void patch(PatchCustomerDTO dto, @MappingTarget Customer entity);

	Customer post(PostCustomerDTO dto);
}
