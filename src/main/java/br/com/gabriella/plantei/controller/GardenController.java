package br.com.gabriella.plantei.controller;

import br.com.gabriella.plantei.dtos.Garden.GardenCreateDTO;
import br.com.gabriella.plantei.dtos.Garden.GardenReadDTO;
import br.com.gabriella.plantei.dtos.Garden.GardenUpdateDTO;
import br.com.gabriella.plantei.dtos.GardenMember.GardenMemberCreateDTO;
import br.com.gabriella.plantei.dtos.GardenMember.GardenMemberReadDTO;
import br.com.gabriella.plantei.dtos.PlantUser.PlantUserReadDTO;
import br.com.gabriella.plantei.service.GardenMemberService;
import br.com.gabriella.plantei.service.GardenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garden")
@Tag(name = "Garden", description = "Endpoints de gerenciamento de Jardins")
public class GardenController {
    @Autowired
    private GardenService gardenService;

    @Autowired
    private GardenMemberService gardenMemberService;

    @Operation(summary = "Listar todos os jardins")
    @GetMapping
    public ResponseEntity<List<GardenReadDTO>> getAllGardens() {
        return ResponseEntity.ok(gardenService.getAllGardens());
    }

    @Operation(summary = "Buscar jardim por ID")
    @GetMapping("/{id}")
    public ResponseEntity<GardenReadDTO> getGardenById(@PathVariable Long id) {
        return ResponseEntity.ok(gardenService.getGardenById(id));
    }

    @Operation(summary = "Listar todas as plantas presentes em um jardim")
    @GetMapping("/{gardenId}/plant-users")
    public ResponseEntity<List<PlantUserReadDTO>> getPlantUsers(
            @PathVariable Long gardenId
    ) {
        return ResponseEntity.ok(gardenService.getPlantUsersByGarden(gardenId));
    }

    @Operation(summary = "Cadastrar novo jardim")
    @PostMapping
    public ResponseEntity<GardenReadDTO> createGarden(@Valid @RequestBody GardenCreateDTO data) {
        GardenReadDTO created = gardenService.createGarden(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Atualizar jardim")
    @PutMapping("/{id}")
    public ResponseEntity<GardenReadDTO> updateGarden(@PathVariable Long id, @Valid @RequestBody GardenUpdateDTO data) {
        return ResponseEntity.ok(gardenService.updateGarden(id, data));
    }

    @Operation(summary = "Excluir jardim")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGarden(@PathVariable Long id) {
        gardenService.deleteGarden(id);
        return ResponseEntity.noContent().build();
    }

    //GardenMember

    @PostMapping("/{id}/members")
    public ResponseEntity<GardenMemberReadDTO> addMember(
            @PathVariable Long id,
            @RequestBody GardenMemberCreateDTO dto
    ) {
        GardenMemberReadDTO created =
                gardenMemberService.createGardenMember(id, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}/members/{userId}")
    public ResponseEntity<Void> removeMember(
            @PathVariable Long id,
            @PathVariable Long userId
    ) {
        gardenMemberService.removeMember(id, userId);
        return ResponseEntity.noContent().build();
    }

}
