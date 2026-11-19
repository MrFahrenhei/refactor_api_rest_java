package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.RequestAdocaoDto;
import br.com.alura.adopet.api.exception.ValidatingException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidationPetCurrentTest {

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private RequestAdocaoDto dto;

    @InjectMocks
    private ValidationPetCurrent validation;

    @Test
    void checkIfPetWaitingWillThrown(){
       //Arrange
        BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(true);
       //Act
       //Assert
        Assertions.assertThrows(ValidatingException.class, ()->validation.validate(dto));
    }
    @Test
    void checkIfPetWaitingWillNotThrown(){
        //Arrange
        BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(false);
        //Act
        //Assert
        Assertions.assertDoesNotThrow(()->validation.validate(dto));
    }
}