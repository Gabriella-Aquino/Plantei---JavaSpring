package br.com.gabriella.plantei.dtos.PlantUser;

import java.time.LocalDate;

import br.com.gabriella.plantei.model.Plant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantUserReadDTO {
  private Long id;
  private String nickname;
  private Plant plant;
  private Long userId;
  private Long gardenId;
  private LocalDate acquisitionDate;
}
