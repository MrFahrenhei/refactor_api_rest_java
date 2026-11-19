package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.RequestAdocaoDto;
import br.com.alura.adopet.api.exception.ValidatingException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import jakarta.xml.bind.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidationPetWithTutorCurrentTest {
    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private RequestAdocaoDto dto;

    @InjectMocks
    private ValidationPetWithTutorCurrent validator;

    @Test
    void checkIfTutorWaitingThrows(){
        // Arrange
        given(adocaoRepository.existsByPetIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(true);
        // act and assert
        assertThrows(ValidatingException.class, ()->validator.validate(dto));
    }
    @Test
    void checkIfTutorWaitingNotThrows(){
        given(adocaoRepository.existsByPetIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(false);
        assertDoesNotThrow(()->validator.validate(dto));

    }
}