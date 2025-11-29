package br.com.gabriella.plantei.test;

import br.com.gabriella.plantei.dtos.Garden.GardenCreateDTO;
import br.com.gabriella.plantei.dtos.Garden.GardenReadDTO;
import br.com.gabriella.plantei.dtos.Garden.GardenUpdateDTO;
import br.com.gabriella.plantei.dtos.PlantUser.PlantUserReadDTO;
import br.com.gabriella.plantei.mapper.GardenMapper;
import br.com.gabriella.plantei.mapper.PlantUserMapper;
import br.com.gabriella.plantei.model.Garden;
import br.com.gabriella.plantei.model.PlantUser;
import br.com.gabriella.plantei.repository.GardenRepository;
import br.com.gabriella.plantei.repository.PlantUserRepository;
import br.com.gabriella.plantei.service.GardenService;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GardenServiceTest {

    @Mock
    private GardenRepository gardenRepository;

    @Mock
    private PlantUserRepository plantUserRepository;

    @Mock
    private GardenMapper gardenMapper;

    @Mock
    private PlantUserMapper plantUserMapper;

    @InjectMocks
    private GardenService gardenService;

    private Garden garden;
    private GardenReadDTO readDTO;

    @BeforeEach
    void setup() {
        garden = new Garden();
        garden.setId(1L);
        garden.setName("Meu Jardim");
        garden.setCreatedAt(LocalDateTime.now());

        readDTO = new GardenReadDTO(
                garden.getId(),
                garden.getName(),
                10L,
                garden.getCreatedAt()
        );
    }

    // ----------------------------------------------------------------------

    @Test
    void testCreateGarden() {
        GardenCreateDTO dto = new GardenCreateDTO(10L, "Novo Jardim");

        when(gardenMapper.toEntity(dto)).thenReturn(garden);
        when(gardenRepository.save(garden)).thenReturn(garden);
        when(gardenMapper.toReadDTO(garden)).thenReturn(readDTO);

        GardenReadDTO result = gardenService.createGarden(dto);

        assertEquals("Meu Jardim", result.getName());
        verify(gardenRepository).save(garden);
    }

    // ----------------------------------------------------------------------

    @Test
    void testGetGardenById() {
        when(gardenRepository.findById(1L)).thenReturn(Optional.of(garden));
        when(gardenMapper.toReadDTO(garden)).thenReturn(readDTO);

        GardenReadDTO result = gardenService.getGardenById(1L);

        assertEquals(1L, result.getId());
        verify(gardenRepository).findById(1L);
    }

    @Test
    void testGetGardenById_NotFound() {
        when(gardenRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> gardenService.getGardenById(99L));
    }

    // ----------------------------------------------------------------------

    @Test
    void testGetPlantUsersByGarden() {
        PlantUser plantUser = new PlantUser();
        plantUser.setId(1L);

        PlantUserReadDTO plantUserDTO = new PlantUserReadDTO(
                1L, "nick", 2L, 3L, 1L, null
        );

        when(plantUserRepository.findByGardenId(1L))
                .thenReturn(List.of(plantUser));

        when(plantUserMapper.toReadDTO(plantUser))
                .thenReturn(plantUserDTO);

        List<PlantUserReadDTO> result = gardenService.getPlantUsersByGarden(1L);

        assertEquals(1, result.size());
        verify(plantUserRepository).findByGardenId(1L);
    }

    // ----------------------------------------------------------------------

    @Test
    void testGetAllGardens() {
        when(gardenRepository.findAll()).thenReturn(List.of(garden));
        when(gardenMapper.toReadDTO(garden)).thenReturn(readDTO);

        List<GardenReadDTO> list = gardenService.getAllGardens();

        assertEquals(1, list.size());
    }

    // ----------------------------------------------------------------------

    @Test
    void testUpdateGarden() {
        GardenUpdateDTO updateDTO = new GardenUpdateDTO("Nome Atualizado");

        when(gardenRepository.findById(1L)).thenReturn(Optional.of(garden));

        // updateEntityFromDTO é void → só verifica se foi chamado
        doAnswer(invocation -> {
            garden.setName("Nome Atualizado");
            return null;
        }).when(gardenMapper).updateEntityFromDTO(eq(updateDTO), eq(garden));

        when(gardenRepository.save(garden)).thenReturn(garden);
        when(gardenMapper.toReadDTO(garden)).thenReturn(
                new GardenReadDTO(1L, "Nome Atualizado", 10L, garden.getCreatedAt())
        );

        GardenReadDTO result = gardenService.updateGarden(1L, updateDTO);

        assertEquals("Nome Atualizado", result.getName());
        verify(gardenMapper).updateEntityFromDTO(updateDTO, garden);
    }

    @Test
    void testUpdateGarden_NotFound() {
        when(gardenRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> gardenService.updateGarden(999L, new GardenUpdateDTO("x")));
    }

    // ----------------------------------------------------------------------

    @Test
    void testDeleteGarden() {
        when(gardenRepository.findById(1L)).thenReturn(Optional.of(garden));

        gardenService.deleteGarden(1L);

        verify(gardenRepository).delete(garden);
    }

    @Test
    void testDeleteGarden_NotFound() {
        when(gardenRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> gardenService.deleteGarden(999L));
    }
}
