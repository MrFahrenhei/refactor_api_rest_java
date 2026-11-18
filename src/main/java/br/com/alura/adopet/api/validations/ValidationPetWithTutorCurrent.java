package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.RequestAdocaoDto;
import br.com.alura.adopet.api.exception.ValidatingException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationPetWithTutorCurrent implements Validations {

    @Autowired
    private AdocaoRepository adocaoRepository;

    public void validate(RequestAdocaoDto dto) {
        boolean tutorPossuiAdocao = adocaoRepository.existsByPetIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO);
        if (tutorPossuiAdocao) {
            throw new ValidatingException("Tutor já possui outra adoção aguardando avaliação!");
        }
    }

}
