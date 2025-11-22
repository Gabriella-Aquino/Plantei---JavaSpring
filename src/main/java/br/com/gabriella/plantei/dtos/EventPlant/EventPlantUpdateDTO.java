package br.com.gabriella.plantei.dtos.EventPlant;

import java.time.LocalDateTime;

import br.com.gabriella.plantei.model.enums.EventType;
import br.com.gabriella.validation.OptionalNotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventPlantUpdateDTO {
  @OptionalNotBlank private Long plantUserId;
  @OptionalNotBlank private EventType type;
  private String description;
  @OptionalNotBlank private LocalDateTime eventDate;
}
