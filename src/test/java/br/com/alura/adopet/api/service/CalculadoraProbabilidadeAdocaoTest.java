package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.RegisterPetDto;
import br.com.alura.adopet.api.dto.RegisterShelterDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilityAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraProbabilidadeAdocaoTest {
    @Test
    @DisplayName("Probabilidade alta de adotar gato leve jovem")
    void testProbabilityForHightAdoption() {
        Abrigo abrigo = new Abrigo( new RegisterShelterDto("Happier", "45999999999", "happyshelter@email.com.br") );
        Pet pet = new Pet( new RegisterPetDto( TipoPet.GATO, "Café", "Siames", 4, "Grey", 4.0f ), abrigo);

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilityAdocao probability = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilityAdocao.ALTA, probability);
    }

    @Test
    @DisplayName("Probabilidade média de adotar gato leve idoso")
    void testProbabilityForMediumAdoption(){
        Abrigo abrigo = new Abrigo( new RegisterShelterDto("Happier", "45999999999", "happyshelter@email.com.br") );
        Pet pet = new Pet( new RegisterPetDto( TipoPet.GATO, "Café", "Siames", 15, "Grey", 4.0f ), abrigo);

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilityAdocao probability = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilityAdocao.MEDIA, probability);
    }

}