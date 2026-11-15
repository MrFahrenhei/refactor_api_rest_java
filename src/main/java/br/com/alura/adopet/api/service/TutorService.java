package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.RegisterTutorDto;
import br.com.alura.adopet.api.dto.UpdateTutorDto;
import br.com.alura.adopet.api.exception.ValidatingException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {
    @Autowired
    private TutorRepository repository;

    public void register(RegisterTutorDto dto){
        boolean isRegister = repository.existsByTelefoneOrEmail(dto.telefone(), dto.email());
        if(isRegister){
            throw new ValidatingException("Dados já cadastrados");
        }
        repository.save(new Tutor(dto));
    }

    public void update(UpdateTutorDto dto){
        Tutor tutor = repository.getReferenceById(dto.id());
        tutor.updateInformations(dto);
    }
}
