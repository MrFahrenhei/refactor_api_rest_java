package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.RequestAdocaoDto;
import br.com.alura.adopet.api.exception.ValidatingException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationTutorLimit implements Validations {

    @Autowired
    private AdocaoRepository adocaoRepository;


    public void validate(RequestAdocaoDto dto) {
        int contador = adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO);
        if (contador == 5) {
            throw new ValidatingException("Tutor chegou ao limite máximo de 5 adoções!");
        }
    }

}
