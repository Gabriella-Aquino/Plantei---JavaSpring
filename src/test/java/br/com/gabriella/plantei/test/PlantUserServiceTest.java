package br.com.gabriella.plantei.test;

import br.com.gabriella.plantei.dtos.PlantUser.PlantUserCreateDTO;
import br.com.gabriella.plantei.dtos.PlantUser.PlantUserReadDTO;
import br.com.gabriella.plantei.dtos.PlantUser.PlantUserUpdateDTO;
import br.com.gabriella.plantei.mapper.PlantUserMapper;
import br.com.gabriella.plantei.model.PlantUser;
import br.com.gabriella.plantei.repository.PlantUserRepository;
import br.com.gabriella.plantei.service.PlantUserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlantUserServiceTest {

    @Mock
    private PlantUserRepository plantUserRepository;

    @Mock
    private PlantUserMapper plantUserMapper;

    @InjectMocks
    private PlantUserService plantUserService;

    private PlantUser plantUser;
    private PlantUserReadDTO readDTO;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        plantUser = new PlantUser();
        plantUser.setId(1L);

        readDTO = new PlantUserReadDTO();
        readDTO.setId(1L);
    }

    // -------------------------------------------------------------
    // CREATE
    // -------------------------------------------------------------
    @Test
    void testCreatePlantUser_Success() {
        PlantUserCreateDTO createDTO = new PlantUserCreateDTO(
                1L, "Minha planta", 2L, 3L, LocalDate.now()
        );

        when(plantUserMapper.toEntity(createDTO)).thenReturn(plantUser);
        when(plantUserRepository.save(plantUser)).thenReturn(plantUser);
        when(plantUserMapper.toReadDTO(plantUser)).thenReturn(readDTO);

        PlantUserReadDTO result = plantUserService.createPlantUser(createDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(plantUserMapper).toEntity(createDTO);
        verify(plantUserRepository).save(plantUser);
    }

    // -------------------------------------------------------------
    // GET BY ID
    // -------------------------------------------------------------
    @Test
    void testGetPlantUserById_Success() {
        when(plantUserRepository.findById(1L)).thenReturn(Optional.of(plantUser));
        when(plantUserMapper.toReadDTO(plantUser)).thenReturn(readDTO);

        PlantUserReadDTO result = plantUserService.getPlantUserById(1L);

        assertNotNull(result);
        verify(plantUserRepository).findById(1L);
    }

    @Test
    void testGetPlantUserById_NotFound() {
        when(plantUserRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> plantUserService.getPlantUserById(1L));
    }

    // -------------------------------------------------------------
    // GET ALL
    // -------------------------------------------------------------
    @Test
    void testGetAllPlantUser_Success() {
        when(plantUserRepository.findAll()).thenReturn(List.of(plantUser));
        when(plantUserMapper.toReadDTO(plantUser)).thenReturn(readDTO);

        List<PlantUserReadDTO> result = plantUserService.getAllPlantUser();

        assertEquals(1, result.size());
    }

    // -------------------------------------------------------------
    // GET ALL BY USER ID
    // -------------------------------------------------------------
    @Test
    void testGetAllPlantUserByUserId_Success() {
        when(plantUserRepository.findByUserId(10L)).thenReturn(List.of(plantUser));
        when(plantUserMapper.toReadDTO(plantUser)).thenReturn(readDTO);

        List<PlantUserReadDTO> result = plantUserService.getAllPlantUserByUserId(10L);

        assertEquals(1, result.size());
        verify(plantUserRepository).findByUserId(10L);
    }

    @Test
    void testGetAllPlantUserByUserId_NotFound() {
        when(plantUserRepository.findByUserId(10L)).thenReturn(List.of());

        assertThrows(EntityNotFoundException.class,
                () -> plantUserService.getAllPlantUserByUserId(10L));
    }

    // -------------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------------
    @Test
    void testUpdatePlantUser_Success() {
        PlantUserUpdateDTO updateDTO = new PlantUserUpdateDTO("Novo nome", 5L, LocalDate.now());

        when(plantUserRepository.findById(1L)).thenReturn(Optional.of(plantUser));
        doNothing().when(plantUserMapper).updateEntityFromDTO(updateDTO, plantUser);
        when(plantUserRepository.save(plantUser)).thenReturn(plantUser);
        when(plantUserMapper.toReadDTO(plantUser)).thenReturn(readDTO);

        PlantUserReadDTO result = plantUserService.updatePlantUser(1L, updateDTO);

        assertNotNull(result);
        verify(plantUserMapper).updateEntityFromDTO(updateDTO, plantUser);
        verify(plantUserRepository).save(plantUser);
    }

    @Test
    void testUpdatePlantUser_NotFound() {
        PlantUserUpdateDTO updateDTO = new PlantUserUpdateDTO();

        when(plantUserRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> plantUserService.updatePlantUser(1L, updateDTO));
    }

    // -------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------
    @Test
    void testDelete_Success() {
        when(plantUserRepository.existsById(1L)).thenReturn(true);
        doNothing().when(plantUserRepository).deleteById(1L);

        plantUserService.delete(1L);

        verify(plantUserRepository).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(plantUserRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class,
                () -> plantUserService.delete(1L));
    }
}
