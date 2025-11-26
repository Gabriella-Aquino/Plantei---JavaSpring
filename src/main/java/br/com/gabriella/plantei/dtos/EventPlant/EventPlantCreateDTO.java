package br.com.gabriella.plantei.dtos.EventPlant;

import java.time.LocalDateTime;

import br.com.gabriella.plantei.model.enums.EventType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventPlantCreateDTO {
  @NotNull
  private Long plantUserId;
  @NotNull
  private Long userId;
  @NotNull
  private EventType type;
  private String description;
  @NotNull
  private LocalDateTime eventDate;
}
