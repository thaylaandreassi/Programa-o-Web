package school.sptech.bibliotecaspring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.bibliotecaspring2.domain.Escritor;

public interface EscritorRepository extends JpaRepository<Escritor, Long> {
}
