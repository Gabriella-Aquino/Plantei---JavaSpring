package br.com.gabriella.plantei.dtos.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
  private String username;
  @Email
  private String email;
  @Pattern(regexp = "^$|^(?!\\s+$).+", message = "Se a senha for informada, não pode ser vazia.")
  private String password;
}
