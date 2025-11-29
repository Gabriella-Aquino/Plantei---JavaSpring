package br.com.gabriella.plantei.test;

import br.com.gabriella.plantei.dtos.PlantUser.PlantUserCreateDTO;
import br.com.gabriella.plantei.dtos.PlantUser.PlantUserReadDTO;
import br.com.gabriella.plantei.dtos.PlantUser.PlantUserUpdateDTO;
import br.com.gabriella.plantei.mapper.PlantUserMapper;
import br.com.gabriella.plantei.model.PlantUser;
import br.com.gabriella.plantei.repository.PlantUserRepository;
import br.com.gabriella.plantei.service.PlantUserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlantUserServiceTest {

    @Mock
    PlantUserRepository plantUserRepository;

    @Mock
    PlantUserMapper plantUserMapper;

    @InjectMocks
    PlantUserService plantUserService;

    // ---------- CREATE ----------
    @Test
    void createPlantUser_Success() {
        PlantUserCreateDTO dto = new PlantUserCreateDTO(1L, "Minha Planta", 2L, 3L, LocalDate.now());
        PlantUser entity = new PlantUser();
        PlantUserReadDTO readDTO = new PlantUserReadDTO(10L, "Minha Planta", 1L, 2L, 3L, LocalDate.now());

        when(plantUserMapper.toEntity(dto)).thenReturn(entity);
        when(plantUserMapper.toReadDTO(entity)).thenReturn(readDTO);

        PlantUserReadDTO result = plantUserService.createPlantUser(dto);

        verify(plantUserRepository).save(entity);
        assertEquals(readDTO, result);
    }

    // ---------- GET BY ID ----------
    @Test
    void getPlantUserById_Success() {
        PlantUser entity = new PlantUser();
        entity.setId(5L);
        PlantUserReadDTO dto = new PlantUserReadDTO();

        when(plantUserRepository.findById(5L)).thenReturn(Optional.of(entity));
        when(plantUserMapper.toReadDTO(entity)).thenReturn(dto);

        PlantUserReadDTO result = plantUserService.getPlantUserById(5L);

        assertEquals(dto, result);
    }

    @Test
    void getPlantUserById_NotFound() {
        when(plantUserRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> plantUserService.getPlantUserById(1L));
    }

    // ---------- GET ALL ----------
    @Test
    void getAllPlantUser_Success() {
        PlantUser e1 = new PlantUser();
        PlantUser e2 = new PlantUser();

        PlantUserReadDTO d1 = new PlantUserReadDTO();
        PlantUserReadDTO d2 = new PlantUserReadDTO();

        when(plantUserRepository.findAll()).thenReturn(List.of(e1, e2));
        when(plantUserMapper.toReadDTO(e1)).thenReturn(d1);
        when(plantUserMapper.toReadDTO(e2)).thenReturn(d2);

        List<PlantUserReadDTO> result = plantUserService.getAllPlantUser();

        assertEquals(2, result.size());
        assertTrue(result.contains(d1));
        assertTrue(result.contains(d2));
    }

    // ---------- GET ALL BY USER ID ----------
    @Test
    void getAllPlantUserByUserId_Success() {
        PlantUser e = new PlantUser();
        PlantUserReadDTO dto = new PlantUserReadDTO();

        when(plantUserRepository.findByUserId(9L)).thenReturn(List.of(e));
        when(plantUserMapper.toReadDTO(e)).thenReturn(dto);

        List<PlantUserReadDTO> result = plantUserService.getAllPlantUserByUserId(9L);

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }

    @Test
    void getAllPlantUserByUserId_NotFound() {
        when(plantUserRepository.findByUserId(9L)).thenReturn(List.of());

        assertThrows(EntityNotFoundException.class, () ->
                plantUserService.getAllPlantUserByUserId(9L)
        );
    }

    // ---------- UPDATE ----------
    @Test
    void updatePlantUser_Success() {
        PlantUserUpdateDTO updateDTO = new PlantUserUpdateDTO("Novo Nome", 5L, LocalDate.now());

        PlantUser entity = new PlantUser();
        entity.setId(10L);

        PlantUserReadDTO readDTO = new PlantUserReadDTO();

        when(plantUserRepository.findById(10L)).thenReturn(Optional.of(entity));
        doNothing().when(plantUserMapper).updateEntityFromDTO(updateDTO, entity);
        when(plantUserMapper.toReadDTO(entity)).thenReturn(readDTO);

        PlantUserReadDTO result = plantUserService.updatePlantUser(10L, updateDTO);

        verify(plantUserRepository).save(entity);
        assertEquals(readDTO, result);
    }

    @Test
    void updatePlantUser_NotFound() {
        when(plantUserRepository.findById(123L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                plantUserService.updatePlantUser(123L, new PlantUserUpdateDTO())
        );
    }

    // ---------- DELETE ----------
    @Test
    void delete_Success() {
        when(plantUserRepository.existsById(7L)).thenReturn(true);

        plantUserService.delete(7L);

        verify(plantUserRepository).deleteById(7L);
    }

    @Test
    void delete_NotFound() {
        when(plantUserRepository.existsById(7L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () ->
                plantUserService.delete(7L)
        );
    }
}

