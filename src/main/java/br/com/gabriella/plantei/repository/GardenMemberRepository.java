package br.com.gabriella.plantei.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabriella.plantei.model.GardenMember;

public interface GardenMemberRepository extends JpaRepository<GardenMember, Long>{
  
}
