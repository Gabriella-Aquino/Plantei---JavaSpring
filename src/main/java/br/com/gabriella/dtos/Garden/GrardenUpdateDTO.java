package br.com.gabriella.dtos.Garden;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrardenUpdateDTO {
  private Long adminId;
  private String name;
}
