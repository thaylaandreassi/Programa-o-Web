package school.sptech.projeto04jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    // private List<Produto> produtos = new ArrayList<>();
    @Autowired
    private ProdutoRepository produtoRepository;


    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto novoProduto){
        // produtos.add(novoProduto);
        Produto produtoBanco = produtoRepository.save(novoProduto);
        return ResponseEntity.status(201).body(produtoBanco);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar(){

        List<Produto> produtos = produtoRepository.findAll();
        if(produtos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(produtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Produto>retornaPorIndice(@PathVariable Long id){
            Optional<Produto> produtoOpt = produtoRepository.findById(id);
            if(produtoOpt.isPresent()){
                return ResponseEntity.status(200).body(produtoOpt.get());
            }
            // Se eu der um return ResponseEntity.of(produtoOpt), se der certo ele retorna um 200 e se não 404
        return ResponseEntity.status(404).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Produto> alterar(@PathVariable Long id, @RequestBody Produto produtoAtualizado){
        if(produtoRepository.existsById(id)){
            produtoAtualizado.setId(id);
            produtoRepository.save(produtoAtualizado);
           // produtos.set(indice, produtoAtualizado);
            return ResponseEntity.status(200).body(produtoAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorIndice(@PathVariable Long id){
        if(produtoRepository.existsById(id)){
            // produtos.remove(indice);
            produtoRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();

    }
}
