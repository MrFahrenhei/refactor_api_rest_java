package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.RegisterTutorDto;
import br.com.alura.adopet.api.dto.UpdateTutorDto;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService service;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid RegisterTutorDto dto) {
        try{
            service.register(dto);
            return ResponseEntity.ok().build();
        }catch (ValidationException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid UpdateTutorDto dto) {
        try{
            service.update(dto);
            return ResponseEntity.ok().build();
        }catch ( ValidationException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
