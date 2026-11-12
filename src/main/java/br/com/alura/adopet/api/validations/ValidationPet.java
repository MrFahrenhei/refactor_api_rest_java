package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.RequestAdocaoDto;
import br.com.alura.adopet.api.exception.ValidatingException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationPet implements Validations {

    @Autowired
    private PetRepository repository;

    public void validate(RequestAdocaoDto dto){
        Pet pet = repository.getReferenceById(dto.idPet());
        if (pet.getAdotado()) {
            throw new ValidatingException("Pet já foi adotado");
        }
    }
}
