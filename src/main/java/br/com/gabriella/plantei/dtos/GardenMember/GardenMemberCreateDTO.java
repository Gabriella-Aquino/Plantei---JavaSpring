package br.com.gabriella.plantei.dtos.GardenMember;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GardenMemberCreateDTO {
  @NotBlank
  private Long userId;
  private LocalDate entryDate;
}
