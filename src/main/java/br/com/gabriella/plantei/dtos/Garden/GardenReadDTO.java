package br.com.gabriella.plantei.dtos.Garden;

import java.time.LocalDateTime;
import java.util.List;

import br.com.gabriella.plantei.dtos.GardenMember.GardenMemberReadDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GardenReadDTO {
  private Long id;
  private String name;
  private Long adminId;
  private LocalDateTime createdAt;
  private List<GardenMemberReadDTO> members;
}
