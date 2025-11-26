package br.com.gabriella.plantei.mapper;

import br.com.gabriella.plantei.dtos.Plant.PlantCreateDTO;
import br.com.gabriella.plantei.dtos.Plant.PlantReadDTO;
import br.com.gabriella.plantei.dtos.Plant.PlantUpdateDTO;
import br.com.gabriella.plantei.model.Plant;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PlantMapper {
  // CREATE DTO → ENTITY
  Plant toEntity(PlantCreateDTO dto);

   // ENTITY → READ DTO
  PlantReadDTO toReadDTO(Plant plant);

  // UPDATE DTO → ENTITY (PATCH)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateEntityFromDTO(PlantUpdateDTO dto, @MappingTarget Plant plant);
}
