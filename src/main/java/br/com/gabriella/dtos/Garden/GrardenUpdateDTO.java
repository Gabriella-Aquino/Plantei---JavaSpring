package br.com.gabriella.dtos.Garden;

import br.com.gabriella.validation.OptionalNotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrardenUpdateDTO {
    @OptionalNotBlank private String name;
}
