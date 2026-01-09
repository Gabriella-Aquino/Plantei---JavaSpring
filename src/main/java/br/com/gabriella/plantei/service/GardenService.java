package br.com.gabriella.plantei.service;

import br.com.gabriella.plantei.dtos.Garden.GardenCreateDTO;
import br.com.gabriella.plantei.dtos.Garden.GardenReadDTO;
import br.com.gabriella.plantei.dtos.Garden.GardenUpdateDTO;
import br.com.gabriella.plantei.dtos.GardenMember.GardenMemberReadDTO;
import br.com.gabriella.plantei.dtos.PlantUser.PlantUserReadDTO;
import br.com.gabriella.plantei.exception.ResourceNotFoundException;
import br.com.gabriella.plantei.mapper.GardenMapper;
import br.com.gabriella.plantei.mapper.GardenMemberMapper;
import br.com.gabriella.plantei.mapper.PlantUserMapper;
import br.com.gabriella.plantei.model.Garden;
import br.com.gabriella.plantei.model.PlantUser;
import br.com.gabriella.plantei.repository.GardenRepository;
import br.com.gabriella.plantei.repository.PlantUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenService {
    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private PlantUserRepository plantUserRepository;

    @Autowired
    private GardenMapper gardenMapper;
    @Autowired
    private GardenMemberMapper gardenMemberMapper;

    @Autowired
    private PlantUserMapper plantUserMapper;

    public GardenReadDTO createGarden(GardenCreateDTO data) {
        Garden garden = gardenMapper.toEntity(data);
        gardenRepository.save(garden);
        return gardenMapper.toReadDTO(garden);
    }

    public GardenReadDTO getGardenById(Long id) {
        Garden garden = gardenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("jardim não encontrado"));
        GardenReadDTO dto = gardenMapper.toReadDTO(garden);

        List<GardenMemberReadDTO> members = garden.getMembers()
                .stream()
                .map(gardenMemberMapper::toReadDTO)
                .toList();

        dto.setMembers(members);

        return dto;
    }

    public List<PlantUserReadDTO> getPlantUsersByGarden(Long gardenId) {
        List<PlantUser> entities = plantUserRepository.findByGardenId(gardenId);
        return entities.stream()
                .map(plantUserMapper::toReadDTO)
                .toList();
    }

    public List<GardenReadDTO> getAllGardens() {
        return gardenRepository.findAll().stream().map(gardenMapper::toReadDTO).toList();
    }

    public GardenReadDTO updateGarden(Long id, GardenUpdateDTO dto) {
        Garden garden = gardenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("jardim não encontrado"));

        gardenMapper.updateEntityFromDTO(dto, garden);

        gardenRepository.save(garden);
        return gardenMapper.toReadDTO(garden);
    }

    public void deleteGarden(Long id) {
        Garden garden = gardenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("jardim não encontrado"));

        gardenRepository.delete(garden);
    }
}
