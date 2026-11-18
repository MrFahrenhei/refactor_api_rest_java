package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.RequestAdocaoDto;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ValidationPetWithTutorCurrentTest {
    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private RequestAdocaoDto dto;



    @Test
    void checkIfTutorWaitingThrows(){

    }
    @Test
    void checkIfTutorWaitingNotThrows(){ }
}