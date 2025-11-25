package br.com.gabriella.plantei.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabriella.plantei.model.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long>{
  List<Plant> findByNameContaining(String name);
}
