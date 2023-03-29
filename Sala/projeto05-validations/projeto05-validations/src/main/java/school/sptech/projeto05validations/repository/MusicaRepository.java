package school.sptech.projeto05validations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projeto05validations.dominio.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Integer> {
}
