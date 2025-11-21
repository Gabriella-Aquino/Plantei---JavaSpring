package br.com.gabriella.dtos.Plant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantReadDTO {
  private Long id;
  private String name;
  private String scientificName;
  private String type;
  private String description;
  private String watering;
  private String fertilizing;
  private String climate;
  private String pests;
  private boolean toxic;
}
