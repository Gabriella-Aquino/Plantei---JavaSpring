package br.com.gabriella.plantei.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabriella.plantei.model.GardenMember;

import java.util.List;
import java.util.Optional;

public interface GardenMemberRepository extends JpaRepository<GardenMember, Long>{
    Optional<GardenMember> findByGardenIdAndUserId(Long gardenId, Long userId);
    List<GardenMember> findByGardenId(Long gardenId);

}
