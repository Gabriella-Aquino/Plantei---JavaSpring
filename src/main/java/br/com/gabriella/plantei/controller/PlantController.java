package br.com.gabriella.plantei.controller;

import br.com.gabriella.plantei.dtos.Plant.PlantCreateDTO;
import br.com.gabriella.plantei.dtos.Plant.PlantReadDTO;
import br.com.gabriella.plantei.dtos.Plant.PlantUpdateDTO;
import br.com.gabriella.plantei.service.PlantService;
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

    @GetMapping
    public ResponseEntity<List<PlantReadDTO>> getAllPlants(){
        return ResponseEntity.ok(plantService.getAllPlants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantReadDTO> getById(@PathVariable long id){
        return ResponseEntity.ok(plantService.getPlantById(id));
    }

    @PostMapping
    public ResponseEntity<PlantReadDTO> createPlant(@Valid @RequestBody PlantCreateDTO data){
        PlantReadDTO created = plantService.createPlant(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<PlantReadDTO> updatePlant(@PathVariable long id, @Valid @RequestBody PlantUpdateDTO data){
        return  ResponseEntity.ok(plantService.updatePlant(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable long id){
        plantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
