package school.sptech.exercicioprodutoentrega;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping()
    public ResponseEntity<Produto> criar(@RequestBody Produto novoProduto){
        if(novoProduto.getNome().length() >= 2 && novoProduto.getPrecoUnitario() >= 0.01 && novoProduto.getQuantidadeEstoque() >= 1) {
            novoProduto.setValorEmEstoque(novoProduto.getQuantidadeEstoque() * novoProduto.getPrecoUnitario());
            Produto produtoN = produtoRepository.save(novoProduto);
            return ResponseEntity.status(201).body(produtoN);
        }
        return ResponseEntity.status(400).build();
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
    public ResponseEntity<Produto> retornaPorIndice(@PathVariable Long id){
        Optional<Produto> produtos = produtoRepository.findById(id);
        if(produtos.isPresent()){
            return ResponseEntity.status(200).body(produtos.get());
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorIndice(@PathVariable Long id){
        Optional<Produto> produtos = produtoRepository.findById(id);
        if(produtos.isPresent()){
            produtoRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
}
