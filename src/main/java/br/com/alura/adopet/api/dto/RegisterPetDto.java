package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterPetDto(
        TipoPet tipo,
        String nome,
        String raca,
        Integer idade,
        String cor,
        Float peso
) {
}
