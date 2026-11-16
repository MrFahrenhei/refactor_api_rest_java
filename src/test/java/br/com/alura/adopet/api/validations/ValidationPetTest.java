package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.RequestAdocaoDto;
import br.com.alura.adopet.api.exception.ValidatingException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidationPetTest {

    @Mock
    private PetRepository petRepository;
    @InjectMocks
    private ValidationPet validation;
    @Mock
    private Pet pet;
    @Mock
    private RequestAdocaoDto dto;

    @Test
    void shouldAllowAdoptionPet(){
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(false);
        Assertions.assertDoesNotThrow(()->validation.validate(dto));
    }

    @Test
    void shouldNotAllowAdoptionPet(){
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);
        Assertions.assertThrows(ValidatingException.class,()->validation.validate(dto));
    }

}