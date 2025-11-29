package br.com.gabriella.plantei.dtos.Garden;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GardenCreateDTO {
  @NotNull
  private Long adminId;
  @NotBlank private String name;
}
