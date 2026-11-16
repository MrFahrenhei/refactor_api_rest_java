package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.RequestAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validations.Validations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {

    @InjectMocks
    private AdocaoService service;

    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private EmailService emailService;
    @Mock
    private Pet pet;
    @Mock
    private Tutor tutor;
    @Spy
    private List<Validations> validations = new ArrayList<>();
    @Mock
    private Validations validator1;
    @Mock
    private Validations validator2;
    @Mock
    private Abrigo abrigo;
    private RequestAdocaoDto dto;
    @Captor
    private ArgumentCaptor<Adocao> adocaoCapter;

    @Test
    void shouldSaveAdoptionOnRequest(){
        //Arrange
        this.dto = new RequestAdocaoDto(10L, 20L, "Motivo qualquer");
        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);

        //Act
        service.request(dto);

        //Assert
        then(adocaoRepository).should().save(adocaoCapter.capture());
        Adocao adocaoSalva = adocaoCapter.getValue();
        Assertions.assertEquals(pet, adocaoSalva.getPet());
        Assertions.assertEquals(tutor, adocaoSalva.getTutor());
        Assertions.assertEquals(dto.motivo(), adocaoSalva.getMotivo());
    }
    @Test
    void shouldCallValidatorsAdoptionsRequest(){
        //Arrange
        this.dto = new RequestAdocaoDto(10L, 20L, "Motivo qualquer");
        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);

        validations.add(validator1);
        validations.add(validator2);

        //Act
        service.request(dto);

        //Assert
        BDDMockito.then(validator1).should().validate(dto);
        BDDMockito.then(validator2).should().validate(dto);
    }

}