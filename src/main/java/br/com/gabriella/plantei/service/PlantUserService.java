package br.com.gabriella.plantei.service;

import br.com.gabriella.plantei.dtos.PlantUser.PlantUserCreateDTO;
import br.com.gabriella.plantei.dtos.PlantUser.PlantUserReadDTO;
import br.com.gabriella.plantei.dtos.PlantUser.PlantUserUpdateDTO;
import br.com.gabriella.plantei.mapper.PlantUserMapper;
import br.com.gabriella.plantei.model.PlantUser;
import br.com.gabriella.plantei.repository.PlantUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantUserService {
    @Autowired
    private PlantUserRepository plantUserRepository;

    @Autowired
    private PlantUserMapper plantUserMapper;

    public PlantUserReadDTO createPlantUser(PlantUserCreateDTO data){
        PlantUser plantUser = plantUserMapper.toEntity(data);
        plantUserRepository.save(plantUser);
        return plantUserMapper.toReadDTO(plantUser);
    }

    public PlantUserReadDTO getPlantUserById(long id){
        PlantUser plantUser = plantUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("planta do usuario não encontrada")));
        return plantUserMapper.toReadDTO(plantUser);
    }

    public List<PlantUserReadDTO> getAllPlantUser(){
        return plantUserRepository.findAll().stream().map(plantUserMapper::toReadDTO).toList();
    }

    public List<PlantUserReadDTO> getAllPlantUserByUserId(Long userId) {
        List<PlantUser> list = plantUserRepository.findByUserId(userId);

        if (list.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma planta encontrada para este usuário");
        }

        return list.stream()
                .map(plantUserMapper::toReadDTO)
                .toList();
    }

    public PlantUserReadDTO updatePlantUser(long id, PlantUserUpdateDTO data){
        PlantUser plantUser = plantUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("planta do usuário não encontrada"));

        plantUserMapper.updateEntityFromDTO(data, plantUser);

        plantUserRepository.save(plantUser);
        return plantUserMapper.toReadDTO(plantUser);
    }

    public void delete(long id){
        if(!plantUserRepository.existsById(id)){
            throw new EntityNotFoundException("Planta do usuario não encontrada");
        }
        plantUserRepository.deleteById(id);
    }

}
