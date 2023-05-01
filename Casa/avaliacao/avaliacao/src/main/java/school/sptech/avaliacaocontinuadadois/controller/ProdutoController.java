package school.sptech.avaliacaocontinuadadois.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.avaliacaocontinuadadois.domain.Produto;
import school.sptech.avaliacaocontinuadadois.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/*
    TODO:
     - A equipe relatou ter dificuldades em utilizar ORM e Dynamic finders, Você poderia ajudá-los?
*/

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Deve ser resolvido apenas com dynamic finder
    @GetMapping("/contagem")
    public ResponseEntity<Long> contagemGeral() {
        Long contador = this.produtoRepository.countByQuantidade();
        return ResponseEntity.status(200).body(contador);
    }

    // Deve ser resolvido apenas com dynamic finder
    @GetMapping("/categorias/contagem")
    public ResponseEntity<Long> contagemPorCategoria(@RequestParam String nome) {
        Long categoria = this.produtoRepository.countByCategoriaIgnoreCase();
        return ResponseEntity.status(200).body(categoria);

    }

    // Deve ser resolvido apenas com dynamic finder e checagem de lista vazia
    @GetMapping("/categorias")
    public ResponseEntity<List<Produto>> buscarPorCategoria(@RequestParam String nome) {
        List<Produto> categoria = this.produtoRepository.findByCategoriaContainsIgnoreCase(nome);
        if(categoria.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(categoria);

    }

    // Deve ser resolvido apenas com dynamic finder
    @GetMapping("/categorias/maior-preco")
    public ResponseEntity<Produto> buscarPorCategoriaMaiorPreco(@RequestParam String nome) {
        Produto produto = produtoRepository.findByCategoriaNomeOrderByPrecoDescContainsIgnoreCase(nome);
        return ResponseEntity.status(200).body(produto);
    }

    // Deve ser resolvido apenas com dynamic finder
    @GetMapping("/categorias/menor-preco")
    public ResponseEntity<Produto> buscarPorCategoriaMenorPreco(@RequestParam String nome) {
        Produto produto = produtoRepository.findByNomeOrderByPrecoAscContainsIgnoreCase(nome);
        return ResponseEntity.status(200).body(produto);
    }


    // Deve ser resolvido apenas com dynamic finder
    @GetMapping("/categorias/contagem/promocoes")
    public ResponseEntity<Long> contarPorCategoriaPromocao(@RequestParam String nome) {
        Long quantidadeProdutosEmPromocao = produtoRepository.countByCategoriaNomeIgnoreCaseContainsPromocaoIsTrue(nome);
        return ResponseEntity.status(200).body(quantidadeProdutosEmPromocao);

    }

    // Esse endpoint exige dynamic finder + uso de laco de repeticao ou uso de stream para cálculo
    @GetMapping("/total-estoque/data")
    public ResponseEntity<Double> buscarTotalEstoquePorPeriodo(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim
    ) {
        List<Produto> produtos = produtoRepository.findByDataCadastroBetween(inicio, fim);
        Double valorTotal = 0.0;
        for (Produto produto : produtos) {
            valorTotal += produto.getPreco() * produto.getQuantidade();
        }
        return ResponseEntity.status(200).body(valorTotal);
    }
}
