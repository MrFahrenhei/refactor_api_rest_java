package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Abrigo;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
    boolean existsByNomeOrTelefoneOrEmail(@NotNull String nome, @NotNull String telefone, @NotNull String email);

    Optional<Abrigo> findByNome(String idOuName);
}
