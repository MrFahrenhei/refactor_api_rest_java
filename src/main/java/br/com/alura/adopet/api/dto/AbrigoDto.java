package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;

public record AbrigoDto(
        String nome,
        String telefone,
        String email
) {

    public AbrigoDto(Abrigo abrigo){
        this(abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail());
    }
}
