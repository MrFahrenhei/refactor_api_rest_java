package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.RequestAdocaoDto;
import br.com.alura.adopet.api.exception.ValidatingException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidationTutorLimitTest {
    @InjectMocks
    private ValidationTutorLimit validation;
    @Mock
    private AdocaoRepository repository;
    @Mock
    private RequestAdocaoDto dto;

    @Test
    void tutorCanNotRequestAdoption(){
        given(repository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO)).willReturn(5);
        assertThrows(ValidatingException.class, ()->validation.validate(dto));
    }

    @Test
    void tutorCanRequestAdoption(){
        given(repository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO)).willReturn(4);
        assertDoesNotThrow(()->validation.validate(dto));

    }


}