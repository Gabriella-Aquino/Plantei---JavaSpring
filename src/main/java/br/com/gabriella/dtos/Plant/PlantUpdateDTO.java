package br.com.gabriella.dtos.Plant;

import br.com.gabriella.validation.OptionalNotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantUpdateDTO {
  @OptionalNotBlank
  private String name;
  @OptionalNotBlank
  private String scientificName;
  @OptionalNotBlank
  private String type;
  @OptionalNotBlank
  private String description;
  @OptionalNotBlank
  private String watering;
  @OptionalNotBlank
  private String fertilizing;
  @OptionalNotBlank
  private String climate;
  @OptionalNotBlank
  private String pests;
  private Boolean toxic;
}
