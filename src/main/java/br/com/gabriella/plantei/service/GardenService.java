package br.com.gabriella.plantei.service;

import br.com.gabriella.plantei.dtos.Garden.GardenCreateDTO;
import br.com.gabriella.plantei.dtos.Garden.GardenReadDTO;
import br.com.gabriella.plantei.dtos.Garden.GardenUpdateDTO;
import br.com.gabriella.plantei.mapper.GardenMapper;
import br.com.gabriella.plantei.model.Garden;
import br.com.gabriella.plantei.repository.GardenRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenService {
    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private GardenMapper gardenMapper;

    public GardenReadDTO createGarden(GardenCreateDTO data) {
        Garden garden = gardenMapper.toEntity(data);
        gardenRepository.save(garden);
        return gardenMapper.toReadDTO(garden);
    }

    public GardenReadDTO getGardenById(Long id) {
        Garden garden = gardenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("jardim não encontrado"));
        return gardenMapper.toReadDTO(garden);
    }

    public List<GardenReadDTO> getAllGardens() {
        return gardenRepository.findAll().stream().map(gardenMapper::toReadDTO).toList();
    }

    public GardenReadDTO updateGarden(Long id, GardenUpdateDTO dto) {
        Garden garden = gardenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("jardim não encontrado"));

        gardenMapper.updateEntityFromDTO(dto, garden);

        gardenRepository.save(garden);
        return gardenMapper.toReadDTO(garden);
    }

    public void deleteGarden(Long id) {
        Garden garden = gardenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("jardim não encontrado"));

        gardenRepository.delete(garden);
    }
}
