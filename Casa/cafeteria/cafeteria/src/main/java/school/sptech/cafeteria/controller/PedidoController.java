package school.sptech.cafeteria.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cafeteria.domain.Pedido;
import school.sptech.cafeteria.dto.PedidoResumoDto;
import school.sptech.cafeteria.repository.PedidoRepository;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public ResponseEntity<Pedido> cadastrar(@RequestBody Pedido pedido){
        Pedido pedidoRegistrado = this.pedidoRepository.save(pedido);
        return ResponseEntity.status(201).body(pedidoRegistrado);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedido(){
        List<Pedido> pedidos = this.pedidoRepository.findAll();
        if(pedidos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(this.pedidoRepository.findById(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<PedidoResumoDto>> retornoDto() {
        List<PedidoResumoDto> pedidos = this.pedidoRepository.listagemPedido();
        if(pedidos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(pedidos);
    }

    @GetMapping("/custo/{custo}")
    public ResponseEntity<List<Pedido>> buscarPorValor(@PathVariable double custo){
        List<Pedido> pedidoFiltrado = this.pedidoRepository.findByCustoGreaterThan(custo);

        if(pedidoFiltrado.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(pedidoFiltrado);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        if(this.pedidoRepository.existsById(id)){
            this.pedidoRepository.deletaPorId(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizaNovoNome(@PathVariable Long id, @RequestParam String nome){
        if(this.pedidoRepository.existsById(id)){
            this.pedidoRepository.atualizaNomePorId(id, nome);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestParam String nome){
        if(this.pedidoRepository.existsById(id)){
            this.pedidoRepository.atualizaNomePorId(id, nome);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

}
