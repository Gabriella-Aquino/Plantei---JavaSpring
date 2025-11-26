package br.com.gabriella.plantei.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.gabriella.plantei.dtos.User.UserCreateDTO;
import br.com.gabriella.plantei.dtos.User.UserReadDTO;
import br.com.gabriella.plantei.dtos.User.UserUpdateDTO;
import br.com.gabriella.plantei.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
  // CREATE DTO → ENTITY
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  User toEntity(UserCreateDTO dto);

  // ENTITY → READ DTO
  UserReadDTO toReadDTO(User user);

  // UPDATE DTO → ENTITY (PATCH)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  void updateEntityFromDTO(UserUpdateDTO dto, @MappingTarget User user);
}
