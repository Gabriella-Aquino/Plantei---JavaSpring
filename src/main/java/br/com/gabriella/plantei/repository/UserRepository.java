package br.com.gabriella.plantei.repository;

import br.com.gabriella.plantei.model.User;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String name);

  List<User> findByUsernameContaining(String name);

}
