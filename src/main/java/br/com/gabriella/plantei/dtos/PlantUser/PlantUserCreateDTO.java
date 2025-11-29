package br.com.gabriella.plantei.dtos.PlantUser;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantUserCreateDTO {
  @NotNull private Long plantId;
  private String nickname;
  @NotNull private Long userId;
  @NotNull private Long gardenId;
  @NotNull private LocalDate acquisitionDate;
}
