package br.com.gabriella.plantei.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.gabriella.plantei.dtos.PlantUser.PlantUserCreateDTO;
import br.com.gabriella.plantei.dtos.PlantUser.PlantUserReadDTO;
import br.com.gabriella.plantei.dtos.PlantUser.PlantUserUpdateDTO;
import br.com.gabriella.plantei.model.PlantUser;

@Mapper(componentModel = "spring")
public interface PlantUserMapper {
  // CREATE DTO → ENTITY
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "plant", source = "plantId")
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "garden", source = "gardenId")
    PlantUser toEntity(PlantUserCreateDTO dto);

    // ENTITY → READ DTO
    @Mapping(target = "plantId", source = "plant.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "gardenId", source = "garden.id")
    PlantUserReadDTO toReadDTO(PlantUser entity);

    // UPDATE DTO → ENTITY (PATCH)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "plant", ignore = true)   // não pode alterar planta
    @Mapping(target = "user", ignore = true)    // não pode alterar dono
    @Mapping(target = "garden", source = "gardenId")
    void updateEntityFromDTO(PlantUserUpdateDTO dto, @MappingTarget PlantUser entity);

}
