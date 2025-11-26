package br.com.gabriella.plantei.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "plant_user")
public class PlantUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "plant_user_id")
  private Long id;

  private String nickname;

  @ManyToOne
  @JoinColumn(name = "plant_id")
  private Plant plant; 

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "garden_id")
  private Garden garden;

  @Column(name = "acquisition_date")
  private LocalDate acquisitionDate;
}
