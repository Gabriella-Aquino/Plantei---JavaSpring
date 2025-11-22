package br.com.gabriella.plantei.dtos.PlantUser;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantUserReadDTO {
  private Long id;
  private String nickname;
  private Long plantId;
  private Long userId;
  private Long gardenId;
  private LocalDate acquisitionDate;
}
