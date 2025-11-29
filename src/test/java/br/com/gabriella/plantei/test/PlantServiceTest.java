package br.com.gabriella.plantei.test;

import br.com.gabriella.plantei.dtos.Plant.PlantCreateDTO;
import br.com.gabriella.plantei.dtos.Plant.PlantReadDTO;
import br.com.gabriella.plantei.dtos.Plant.PlantUpdateDTO;
import br.com.gabriella.plantei.mapper.PlantMapper;
import br.com.gabriella.plantei.model.Plant;
import br.com.gabriella.plantei.repository.PlantRepository;
import br.com.gabriella.plantei.service.PlantService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlantServiceTest {
    @Mock
    private PlantRepository plantRepository;

    @Mock
    private PlantMapper plantMapper;

    @InjectMocks
    private PlantService plantService;

    private Plant plant;
    private PlantReadDTO readDTO;

    @BeforeEach
    void setup() {
        plant = new Plant();
        plant.setId(1L);
        plant.setName("Rosa");
        plant.setScientificName("Rosa rubiginosa");

        readDTO = new PlantReadDTO(
                1L, "Rosa", "Rosa rubiginosa",
                null, null, null, null, null, null, false
        );
    }
    // CREATE ------------------------------
    @Test
    void testCreatePlant() {
        PlantCreateDTO createDTO = new PlantCreateDTO(
                "Rosa rubiginosa",
                "Rosa",
                "Flor",
                "Linda",
                "Diário",
                "Mensal",
                "Temperado",
                "Poucas",
                false
        );

        when(plantMapper.toEntity(createDTO)).thenReturn(plant);
        when(plantRepository.save(plant)).thenReturn(plant);
        when(plantMapper.toReadDTO(plant)).thenReturn(readDTO);

        PlantReadDTO result = plantService.createPlant(createDTO);

        assertNotNull(result);
        assertEquals("Rosa", result.getName());
        verify(plantRepository).save(plant);
    }

    // GET BY ID ------------------------------
    @Test
    void testGetPlantById_found() {
        when(plantRepository.findById(1L)).thenReturn(Optional.of(plant));
        when(plantMapper.toReadDTO(plant)).thenReturn(readDTO);

        PlantReadDTO result = plantService.getPlantById(1L);

        assertEquals("Rosa", result.getName());
    }

    @Test
    void testGetPlantById_notFound() {
        when(plantRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> plantService.getPlantById(99L)
        );
    }

    // GET ALL
    @Test
    void testGetAllPlants() {
        when(plantRepository.findAll()).thenReturn(List.of(plant));
        when(plantMapper.toReadDTO(plant)).thenReturn(readDTO);

        List<PlantReadDTO> result = plantService.getAllPlants();

        assertEquals(1, result.size());
        assertEquals("Rosa", result.get(0).getName());
    }

    // UPDATE ------------------------------
    @Test
    void testUpdatePlant() {
        PlantUpdateDTO updateDTO = new PlantUpdateDTO("Nova Rosa", null, null, null, null, null, null, null, null);

        when(plantRepository.findById(1L)).thenReturn(Optional.of(plant));
        doAnswer(invocation -> {
            // Simula o mapper actualizando o entity
            plant.setName(updateDTO.getName());
            return null;
        }).when(plantMapper).updateEntityFromDTO(updateDTO, plant);

        when(plantRepository.save(plant)).thenReturn(plant);
        when(plantMapper.toReadDTO(plant)).thenReturn(
                new PlantReadDTO(
                        1L,
                        "Nova Rosa",
                        "Rosa rubiginosa",
                        null, null, null, null, null, null, false
                )
        );

        PlantReadDTO result = plantService.updatePlant(1L, updateDTO);

        assertEquals("Nova Rosa", result.getName());
    }

    @Test
    void testUpdatePlant_notFound() {
        when(plantRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> plantService.updatePlant(99L, new PlantUpdateDTO())
        );
    }

    // DELETE ------------------------------
    @Test
    void testDelete_success() {
        when(plantRepository.existsById(1L)).thenReturn(true);

        plantService.delete(1L);

        verify(plantRepository).deleteById(1L);
    }

    @Test
    void testDelete_notFound() {
        when(plantRepository.existsById(99L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class,
                () -> plantService.delete(99L)
        );
    }


}
