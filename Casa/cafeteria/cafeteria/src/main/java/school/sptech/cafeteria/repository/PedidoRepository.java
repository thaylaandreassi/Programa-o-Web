package school.sptech.cafeteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import school.sptech.cafeteria.domain.Pedido;
import school.sptech.cafeteria.dto.PedidoResumoDto;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCustoGreaterThan(double custo);

    @Modifying
    @Query("DELETE FROM Pedido p WHERE p.id = :id")
    void deletaPorId(Long id);

    @Modifying
    @Query("UPDATE Pedido p SET p.nome = :nome WHERE p.id = :id")
    void atualizaNomePorId(Long id, String nome);
    @Query("SELECT new school.sptech.cafeteria.dto.PedidoResumoDto(p.id, p.nome) FROM Pedido p")
    List<PedidoResumoDto> listagemPedido();

}
