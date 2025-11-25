package br.com.gabriella.plantei.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabriella.plantei.model.Garden;
import br.com.gabriella.plantei.model.Plant;

public interface GardenRepository extends JpaRepository<Garden, Long>{
  List<Plant> findByNameContaining(String name);
}
