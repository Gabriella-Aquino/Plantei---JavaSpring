package br.com.gabriella.plantei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabriella.plantei.dtos.Plant.PlantCreateDTO;
import br.com.gabriella.plantei.dtos.Plant.PlantReadDTO;
import br.com.gabriella.plantei.mapper.PlantMapper;
import br.com.gabriella.plantei.model.Plant;
import br.com.gabriella.plantei.repository.PlantRepository;

@Service
public class PlantService {
  @Autowired

  private PlantRepository plantRepository;

  @Autowired
  private PlantMapper plantMapper;

  public PlantReadDTO create(PlantCreateDTO dto) {
    Plant plant = plantMapper.toEntity(dto);
    plantRepository.save(plant);
    return plantMapper.toReadDTO(plant);
  }
}
