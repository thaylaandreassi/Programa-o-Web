package school.sptech.bibliotecaspring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ProblemDetail;
import school.sptech.bibliotecaspring2.domain.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findEscritorIgnoreCase(String nome);
    Optional<Livro> findByNome(String nome);
    List<Livro> findByEscritor(String nomeEscritor);
    List<Livro> findByAnoLancamento(Integer anoLancamento);
    List<Livro> findByEditora(String editora);
}
