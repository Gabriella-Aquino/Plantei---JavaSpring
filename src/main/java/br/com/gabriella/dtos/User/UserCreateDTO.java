package br.com.gabriella.dtos.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {
  @NotBlank(message = "username é obrigatório.")
  private String username;
  @Email
  private String email;
  @NotBlank(message = "senha é obrigatório.")
  private String password;
}
