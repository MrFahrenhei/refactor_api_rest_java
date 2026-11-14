package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotNull;

public record RequestAbrigoDTO(
        @NotNull
        String nome,
        @NotNull
        String telefone,
        @NotNull
        String email
) {
}
