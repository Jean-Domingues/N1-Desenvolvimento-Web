package br.anhembi.locadora.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.anhembi.locadora.dtos.PatchInventoryDTO;
import br.anhembi.locadora.dtos.PostInventoryDTO;
import br.anhembi.locadora.models.Inventory;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface InventoryMapper {
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void patch(PatchInventoryDTO dto, @MappingTarget Inventory entity);

	Inventory post(PostInventoryDTO dto);
}
