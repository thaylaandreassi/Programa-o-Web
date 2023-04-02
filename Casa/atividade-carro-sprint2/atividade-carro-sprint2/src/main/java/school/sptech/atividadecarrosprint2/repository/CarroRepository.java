package school.sptech.atividadecarrosprint2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.atividadecarrosprint2.dominio.Carro;

public interface CarroRepository extends JpaRepository<Carro, Integer> {
}
