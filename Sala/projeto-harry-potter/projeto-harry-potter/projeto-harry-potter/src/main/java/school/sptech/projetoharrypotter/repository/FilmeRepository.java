package school.sptech.projetoharrypotter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ProblemDetail;
import school.sptech.projetoharrypotter.domain.Filme;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

    Optional<Filme> findByNome(String nome);

    List<Filme> findByIndicacaoOscarTrue();

    int countByIndicacaoOscarFalse();

    List<Filme> findByDiretorIgnoreCase(String nome);

    List<Filme> findByDataLancamentoLessThanEqual(LocalDate data);

    List<Filme> findByCustoProducaoGreaterThanEqual(double custo);
}
