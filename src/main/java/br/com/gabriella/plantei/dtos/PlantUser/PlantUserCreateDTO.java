package br.com.gabriella.plantei.dtos.PlantUser;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantUserCreateDTO {
  @NotBlank private Long plantId;
  private String nickname;
  @NotBlank private Long userId;
  @NotBlank private Long gardenId;
  @NotBlank private LocalDate acquisitionDate;
}
