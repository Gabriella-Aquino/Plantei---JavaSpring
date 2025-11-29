package br.com.gabriella.plantei.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Setter
@Table(name = "plant")
public class Plant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "plant_id")
  private Long id;

  private String name;

  @Column(name = "scientific_name")
  private String scientificName;

  private String type;

  @Column(columnDefinition = "text")
  private String description;

  private String watering;
  private String fertilizing;
  private String climate;
  private String pests;
  private boolean toxic;
}
