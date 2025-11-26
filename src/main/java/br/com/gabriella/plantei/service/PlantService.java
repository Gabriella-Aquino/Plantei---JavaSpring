package br.com.gabriella.plantei.service;

import br.com.gabriella.plantei.dtos.Plant.PlantUpdateDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabriella.plantei.dtos.Plant.PlantCreateDTO;
import br.com.gabriella.plantei.dtos.Plant.PlantReadDTO;
import br.com.gabriella.plantei.mapper.PlantMapper;
import br.com.gabriella.plantei.model.Plant;
import br.com.gabriella.plantei.repository.PlantRepository;

import java.util.List;

@Service
public class PlantService {
  @Autowired

  private PlantRepository plantRepository;

  @Autowired
  private PlantMapper plantMapper;

  public PlantReadDTO createPlant(PlantCreateDTO data) {
    Plant plant = plantMapper.toEntity(data);
    plantRepository.save(plant);
    return plantMapper.toReadDTO(plant);
  }

  public  PlantReadDTO getPlantById(long id){
      Plant plant = plantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Planta não encontrada"));
      return plantMapper.toReadDTO(plant);
  }

  public List<PlantReadDTO> listAllPlants(){
      return plantRepository.findAll().stream().map(plantMapper::toReadDTO).toList();
  }

  public PlantReadDTO updatePlant(long id, PlantUpdateDTO data){
      Plant plant = plantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Planta não encontrada"));
      plantMapper.updateEntityFromDTO(data, plant);
      Plant update =plantRepository.save(plant);
      return plantMapper.toReadDTO(update);
  }
}
