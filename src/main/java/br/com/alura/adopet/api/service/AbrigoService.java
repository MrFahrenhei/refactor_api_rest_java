package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.dto.RegisterShelterDto;
import br.com.alura.adopet.api.exception.ValidatingException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {
    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private PetRepository petRepository;

    public List<AbrigoDto> list(){
        return repository.findAll().stream().map(AbrigoDto::new).toList();
    }

    public void registerAbrigo(RegisterShelterDto dto){
        boolean isRegistered = repository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email());

        if(isRegistered){
            throw new ValidatingException("Dados já cadastrados para outro abrigo");
        }

        repository.save(new Abrigo(dto));
    }

    public List<PetDto> listPetsFromShelter(String idOuName){
        Abrigo abrigo = loadShelter(idOuName);
        return petRepository.findByAbrigo(abrigo).stream().map(PetDto::new).toList();
    }

    public Abrigo loadShelter(String idOuName){
        Optional<Abrigo> optional;

        try{
            Long id = Long.parseLong(idOuName);
            optional = repository.findById(id);
        }catch(NumberFormatException exception){
            optional = repository.findByNome(idOuName);
        }

        return optional.orElseThrow(
                () -> new ValidatingException("Abrigo não econtrado")
        );
    }

}
