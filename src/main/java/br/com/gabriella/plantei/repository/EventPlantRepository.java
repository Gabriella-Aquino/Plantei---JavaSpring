package br.com.gabriella.plantei.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabriella.plantei.model.EventPlant;

public interface EventPlantRepository extends JpaRepository<EventPlant, Long>{
  List<EventPlant> findByPlantUserId(long plant_user_id);
  List<EventPlant> findByUserId(long user_id);
  List<EventPlant> findByPlantUserGardenId(Long gardenId);
}
