package school.sptech.bibliotecaspring2.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.bibliotecaspring2.domain.Livro;
import school.sptech.bibliotecaspring2.repository.LivroRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro){
        Livro livroRegistrado = this.livroRepository.save(livro);
        return ResponseEntity.status(201).body(livroRegistrado);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listar(){
        List<Livro> livros = this.livroRepository.findAll();

        if(livros.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(livros);
    }

    @GetMapping("/escritor")
    public ResponseEntity<List<Livro>> buscarPorEscritor(@RequestParam String nome){
        List<Livro> livrosRegistrados = this.livroRepository.findEscritorIgnoreCase(nome);

        if(livrosRegistrados.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(livrosRegistrados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscaPorId(@PathVariable Long id){
        return ResponseEntity.of(this.livroRepository.findById(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<Livro> buscarPorNome(@RequestParam String nome){
        return ResponseEntity.of(this.livroRepository.findByNome(nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(livroRepository.existsById(id)){
            livroRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return  ResponseEntity.status(404).build();
    }


    @GetMapping("/escritor/{escritores}")
    public List<Livro> buscarLivrosPorAutor(@PathVariable String escritor) {
        return livroRepository.findByEscritor(escritor);
    }

    @GetMapping("/ano/{anoLancamento}")
    public List<Livro> buscarLivrosPorAnoLancamento(@PathVariable Integer anoLancamento) {
        return livroRepository.findByAnoLancamento(anoLancamento);
    }

    @GetMapping("/editora/{editora}")
    public List<Livro> buscarLivrosPorEditora(@PathVariable String editora) {
        return livroRepository.findByEditora(editora);
    }

}
