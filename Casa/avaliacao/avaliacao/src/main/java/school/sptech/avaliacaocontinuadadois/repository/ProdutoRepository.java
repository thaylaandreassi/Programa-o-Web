package school.sptech.avaliacaocontinuadadois.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ProblemDetail;
import school.sptech.avaliacaocontinuadadois.domain.Produto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    Long countByQuantidade();
    Long countByCategoriaIgnoreCase();
    List<Produto> findByCategoriaContainsIgnoreCase(String nome);
    Produto findByCategoriaNomeOrderByPrecoDescContainsIgnoreCase(String nome);
    Produto findByNomeOrderByPrecoAscContainsIgnoreCase(String nome);
    Long countByCategoriaNomeIgnoreCaseContainsPromocaoIsTrue(String nome);

    List<Produto> findByDataCadastroBetween(LocalDate inicio, LocalDate fim);
}
