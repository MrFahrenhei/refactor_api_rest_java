package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.dto.RegisterPetDto;
import br.com.alura.adopet.api.dto.RequestAbrigoDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<AbrigoDto>> listar() {
        List<AbrigoDto> abrigos = abrigoService.list();
        return ResponseEntity.ok(abrigos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid RequestAbrigoDTO dto) {
        try{
            abrigoService.registerAbrigo(dto);
            return ResponseEntity.ok().build();
        }catch(ValidationException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<PetDto>> listarPets(@PathVariable String idOuNome) {
        try{
            List<PetDto> petsDoAbrigo = abrigoService.listPetsFromShelter(idOuNome);
            return ResponseEntity.ok(petsDoAbrigo);
        }catch (ValidationException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid RegisterPetDto pet) {
        try{
            Abrigo abrigo = abrigoService.loadShelter(idOuNome);
            petService.registerPet(abrigo, dto);
            return ResponseEntity.ok().build();
        }catch(ValidationException exception){
            return ResponseEntity.notFound().build();
        }
    }

}
