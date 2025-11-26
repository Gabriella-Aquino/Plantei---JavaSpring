package br.com.gabriella.plantei.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.gabriella.plantei.dtos.EventPlant.EventPlantCreateDTO;
import br.com.gabriella.plantei.dtos.EventPlant.EventPlantReadDTO;
import br.com.gabriella.plantei.dtos.EventPlant.EventPlantUpdateDTO;
import br.com.gabriella.plantei.model.EventPlant;
import br.com.gabriella.plantei.model.PlantUser;
import br.com.gabriella.plantei.model.User;

@Mapper(componentModel = "spring")
public interface EventPlantMapper {
  // CREATE DTO → ENTITY
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "plantUser", source = "plantUser") 
  @Mapping(target = "user", source = "user") 
  EventPlant toEntity(EventPlantCreateDTO dto, PlantUser plantUser, User user);

  // ENTITY → READ DTO
  @Mapping(target = "plantUserId", source = "plantUser.id")
  @Mapping(target = "userId", source = "user.id")
  EventPlantReadDTO toReadDTO(EventPlant event);

  // UPDATE DTO → ENTITY (PATCH)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateEntityFromDto(EventPlantUpdateDTO dto, @MappingTarget EventPlant event);
}
