package br.com.gabriella.plantei.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.gabriella.plantei.dtos.Garden.GardenCreateDTO;
import br.com.gabriella.plantei.dtos.Garden.GardenReadDTO;
import br.com.gabriella.plantei.dtos.Garden.GardenUpdateDTO;
import br.com.gabriella.plantei.model.Garden;

@Mapper(componentModel = "spring")
public interface GardenMapper {
  // CREATE DTO → ENTITY
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "admin.id", source = "adminId")
  Garden toEntity(GardenCreateDTO dto);

  // ENTITY → READ DTO
  @Mapping(target = "adminId", source = "admin.id")
  GardenReadDTO toReadDTO(Garden garden);

  // UPDATE DTO → ENTITY (PATCH)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "admin", ignore = true) // Admin não é atualizado no PATCH
  @Mapping(target = "members", ignore = true)
  @Mapping(target = "createdAt", ignore = true) // Nunca atualizado
  void updateEntityFromDTO(GardenUpdateDTO dto, @MappingTarget Garden garden);
}
