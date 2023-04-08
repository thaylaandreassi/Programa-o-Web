package school.sptech.projetoharrypotter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoharrypotter.domain.Filme;
import school.sptech.projetoharrypotter.repository.FilmeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    @PostMapping
    public ResponseEntity<Filme>criar(@RequestBody Filme novoFilme){
        Filme newMovie = filmeRepository.save(novoFilme);
        if(newMovie != null){
            return ResponseEntity.status(201).body(newMovie);
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listar(){
        List<Filme> filmes = filmeRepository.findAll();
        if(filmes.isEmpty()){
         return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> listarPorId(@PathVariable Long id){
        Optional<Filme> filmes = filmeRepository.findById(id);
        if(filmes.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(filmes.get());
    }

    @GetMapping("/titulo")
    public ResponseEntity<Filme> buscarPeloNome(@RequestParam String nome){

        return ResponseEntity.of(this.filmeRepository.findByNome(nome));
    }
//        List<Filme> filmes = this.filmeRepository.findAll();
//        Optional<Filme> filmeProcurado = filmes.stream().filter(filme -> filme.getNome().equals(nome)).findAny();
//
//        if(filmeProcurado.isPresent()){
//            return ResponseEntity.status(200).body(filmeProcurado.get());
//        }
//        return ResponseEntity.status(404).build();
//    }

    @GetMapping("/diretor")
    public ResponseEntity<List<Filme>> buscarPorDiretor(@RequestParam String nome){

        List<Filme> filmesFiltrados = this.filmeRepository.findByDiretorIgnoreCase(nome);

//        List<Filme> filmes = this.filmeRepository.findAll();
//       List<Filme> filmesFiltrados = filmes.stream().filter(filme -> filme.getDiretor().equalsIgnoreCase(nome)).collect(Collectors.toList());

        if(filmesFiltrados.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(filmesFiltrados);

    }

    @GetMapping("/periodo/{data}")
    public ResponseEntity<List<Filme>> buscaPorData(@PathVariable LocalDate data){
        List<Filme> filmesFiltrados = this.filmeRepository.findByDataLancamentoLessThanEqual(data);

        if(filmesFiltrados.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(filmesFiltrados);
    }

    @GetMapping("/indicadores")
    public ResponseEntity<List<Filme>> buscarSomenteIndicados(){
        List<Filme> filmesFiltrados = this.filmeRepository.findByIndicacaoOscarTrue();

        if(filmesFiltrados.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(filmesFiltrados);
    }

    @GetMapping("/nao-indicados/quantidade")
    public ResponseEntity<Integer> contarSomenteNaoIndicados(){
        int contador = this.filmeRepository.countByIndicacaoOscarFalse();
        return ResponseEntity.status(200).body(contador);
    }

    @GetMapping("/custo-producao/{custo}")
    public ResponseEntity<List<Filme>> buscarFilmesComCustoAlto(@PathVariable double custo){
        List<Filme> filmesFiltrados = this.filmeRepository.findByCustoProducaoGreaterThanEqual(custo);

        if(filmesFiltrados.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(filmesFiltrados);
    }

}
