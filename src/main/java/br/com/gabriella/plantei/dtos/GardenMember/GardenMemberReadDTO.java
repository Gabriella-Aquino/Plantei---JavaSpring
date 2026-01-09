package br.com.gabriella.plantei.dtos.GardenMember;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GardenMemberReadDTO {
  private Long id;
  private Long gardenId;
  private Long userId;
  private LocalDate entryDate;
}
