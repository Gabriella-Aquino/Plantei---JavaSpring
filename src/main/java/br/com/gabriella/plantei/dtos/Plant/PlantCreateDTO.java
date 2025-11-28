package br.com.gabriella.plantei.dtos.Plant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantCreateDTO {
  @NotBlank private String scientificName;
  @NotBlank private String name;
  @NotBlank private String type;
  @NotBlank private String description;
  @NotBlank private String watering;
  @NotBlank private String fertilizing;
  @NotBlank private String climate;
  @NotBlank private String pests;
  @NotNull private boolean toxic;
}
