package br.com.gabriella.plantei.controller;

import br.com.gabriella.plantei.dtos.User.UserCreateDTO;
import br.com.gabriella.plantei.dtos.User.UserReadDTO;
import br.com.gabriella.plantei.dtos.User.UserUpdateDTO;
import br.com.gabriella.plantei.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "Endpoints de gerenciamento de usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserReadDTO>> getAllUsers(){
        return  ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReadDTO> getUserById(@PathVariable long id){
        return ResponseEntity.ok((userService.getUserById((id))));
    }

    @PostMapping
    public ResponseEntity<UserReadDTO> createUser(@Valid @RequestBody UserCreateDTO data){
        UserReadDTO created = userService.creatUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserReadDTO> updateUser(@PathVariable long id, @Valid @RequestBody UserUpdateDTO data){
        return ResponseEntity.ok(userService.updateUser(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
