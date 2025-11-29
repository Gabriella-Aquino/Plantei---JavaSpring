package br.com.gabriella.plantei.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabriella.plantei.model.Plant;
import br.com.gabriella.plantei.model.PlantUser;

public interface PlantUserRepository extends JpaRepository<PlantUser, Long>{
    List<PlantUser> findByUserId(Long userId);
    List<Plant> findByNicknameContaining(String nickname);
}


