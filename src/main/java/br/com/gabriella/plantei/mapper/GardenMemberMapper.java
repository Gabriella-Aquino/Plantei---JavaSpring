package br.com.gabriella.plantei.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.gabriella.plantei.dtos.GardenMember.GardenMemberCreateDTO;
import br.com.gabriella.plantei.dtos.GardenMember.GardenMemberReadDTO;
import br.com.gabriella.plantei.model.GardenMember;

@Mapper(componentModel = "spring")
public interface GardenMemberMapper {
  // CREATE DTO → ENTITY
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "entryDate", ignore = true) // CreationTimestamp controla isso
  @Mapping(target = "garden", source = "gardenId")
  @Mapping(target = "user", source = "userId")
  GardenMember toEntity(GardenMemberCreateDTO dto);

  // ENTITY → READ DTO
  @Mapping(target = "gardenId", source = "garden.id")
  @Mapping(target = "userId", source = "user.id")
  GardenMemberReadDTO toReadDTO(GardenMember gardenMember);

}
