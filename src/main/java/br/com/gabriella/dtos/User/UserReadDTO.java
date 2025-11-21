package br.com.gabriella.dtos.User;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReadDTO {
  private Long id;
  private String username;
  private String email;
  private LocalDateTime createdAt;
}
