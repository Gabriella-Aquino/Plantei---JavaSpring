package br.com.gabriella.plantei.dtos.EventPlant;

import java.time.LocalDateTime;
import br.com.gabriella.plantei.model.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventPlantReadDTO {
  private Long id;
  private Long plantUserId;
  private Long userId;
  private EventType type;
  private String description;
  private LocalDateTime eventDate;
}
