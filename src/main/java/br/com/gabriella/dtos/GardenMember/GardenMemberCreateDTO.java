package br.com.gabriella.dtos.GardenMember;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GardenMemberCreateDTO {
  private Long gardenId;
  private Long userId;
  private LocalDate entryDate;
}
