package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.RequestAdocaoDto;
import br.com.alura.adopet.api.exception.ValidatingException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationPetCurrent implements Validations {

    @Autowired
    private AdocaoRepository adocaoRepository;

//    @Autowired
//    private PetRepository petRepository;


    public void validate(RequestAdocaoDto dto) {
//        List<Adocao> adocoes = adocaoRepository.findAll();
//        Pet pet = petRepository.getReferenceById(dto.idPet());
//        for (Adocao a : adocoes) {
        boolean petTemAdocaoEmAndamento = adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO);
            if (petTemAdocaoEmAndamento) {
                throw new ValidatingException("Pet já está aguardando avaliação para ser adotado!");
            }
//        }
    }

}
