package br.com.gabriella.dtos.PlantUser;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantUserUpdateDTO {
  private String nickname;
    private Long plantId;
    private Long userId;
    private Long gardenId;
    private LocalDate acquisitionDate;
}
