package br.com.gabriella.plantei.controller;


import br.com.gabriella.plantei.dtos.PlantUser.PlantUserCreateDTO;
import br.com.gabriella.plantei.dtos.PlantUser.PlantUserReadDTO;
import br.com.gabriella.plantei.dtos.PlantUser.PlantUserUpdateDTO;
import br.com.gabriella.plantei.model.PlantUser;
import br.com.gabriella.plantei.service.PlantUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/plantuser")
@Tag(name = "PlantUser", description = "Endpoints de gerenciamento de plantas do usuarios")
public class PlantUserController {
    @Autowired
    private PlantUserService plantUserService;

    @Operation(summary = "Listar todas plantas do usuário por ID do usuário")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlantUserReadDTO>> getAllPlantUserByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(plantUserService.getAllPlantUserByUserId(userId));
    }

    @Operation(summary = "Buscar planta do usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PlantUserReadDTO> getPlantUserById(@PathVariable long id){
        return ResponseEntity.ok(plantUserService.getPlantUserById(id));
    }

    @Operation(summary = "Cadastrar uma nova planta para o usuário")
    @PostMapping()
    public ResponseEntity<PlantUserReadDTO> createPlantUser(@Valid @RequestBody PlantUserCreateDTO data){
        PlantUserReadDTO created = plantUserService.createPlantUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Atualizar planta do usuário")
    @PutMapping("/{id}")
    public ResponseEntity<PlantUserReadDTO> updatePlantUser(@PathVariable long id, @RequestBody PlantUserUpdateDTO data){
        return ResponseEntity.ok(plantUserService.updatePlantUser(id, data));
    }

    @Operation(summary = "Excluir planta do usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlantUser(@PathVariable long id){
        plantUserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
