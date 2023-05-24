package school.sptech.cafeteria.repository;

import ch.qos.logback.core.net.server.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cafeteria.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
