package br.com.gabriella.plantei.controller;

import br.com.gabriella.plantei.dtos.Plant.PlantCreateDTO;
import br.com.gabriella.plantei.dtos.Plant.PlantReadDTO;
import br.com.gabriella.plantei.dtos.Plant.PlantUpdateDTO;
import br.com.gabriella.plantei.service.PlantService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/plant")
@Tag(name = "Plant", description = "Endpoints de gerenciamento de plantas")
public class PlantController {
    @Autowired
    private PlantService plantService;

    @Operation(summary = "Listar todas as plantas categorizadas",
            description = "Retorna uma lista de plantas cadastradas como base (categoria).")
    @GetMapping
    public ResponseEntity<List<PlantReadDTO>> getAllPlants(){
        return ResponseEntity.ok(plantService.getAllPlants());
    }

    @Operation(summary = "Buscar planta por ID",
            description = "Retorna uma planta categorizada com base no seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<PlantReadDTO> getById(@PathVariable long id){
        return ResponseEntity.ok(plantService.getPlantById(id));
    }

    @Operation(summary = "Cadastrar nova planta base",
            description = "Cria uma nova planta categorizada para ser associada aos usuários.")
    @PostMapping
    public ResponseEntity<PlantReadDTO> createPlant(@Valid @RequestBody PlantCreateDTO data){
        PlantReadDTO created = plantService.createPlant(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Atualizar planta base",
            description = "Atualiza os dados de uma planta categorizada existente.")
    @PutMapping("/{id}")
    public  ResponseEntity<PlantReadDTO> updatePlant(@PathVariable long id, @Valid @RequestBody PlantUpdateDTO data){
        return  ResponseEntity.ok(plantService.updatePlant(id, data));
    }

    @Operation(summary = "Excluir planta base",
            description = "Remove uma planta categorizada. Não afeta PlantUsers já existentes.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable long id){
        plantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
