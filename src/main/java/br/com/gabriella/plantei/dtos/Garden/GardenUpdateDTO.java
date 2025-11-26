package br.com.gabriella.plantei.dtos.Garden;

import br.com.gabriella.validation.OptionalNotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GardenUpdateDTO {
    @OptionalNotBlank private String name;
}
