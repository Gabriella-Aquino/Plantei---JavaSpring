package br.com.gabriella.dtos.Garden;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GardenCreateDTO {
  @NotBlank private Long adminId;
  @NotBlank private String name;
}
