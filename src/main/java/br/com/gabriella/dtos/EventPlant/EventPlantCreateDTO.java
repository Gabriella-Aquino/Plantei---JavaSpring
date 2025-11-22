package br.com.gabriella.dtos.EventPlant;

import java.time.LocalDateTime;

import br.com.gabriella.plantei.model.enums.EventType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventPlantCreateDTO {
  @NotBlank private Long plantUserId;
  @NotBlank private Long userId;
  @NotBlank private EventType type;
  private String description;
  @NotBlank private LocalDateTime eventDate;
}
