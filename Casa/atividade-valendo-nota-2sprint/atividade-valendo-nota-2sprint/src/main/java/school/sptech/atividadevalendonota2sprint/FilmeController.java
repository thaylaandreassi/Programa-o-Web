package school.sptech.atividadevalendonota2sprint;

import org.apache.tomcat.util.scan.JarFileUrlNestedJar;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    List<Filme> filmes;

    public FilmeController() {
        filmes = new ArrayList<>();
    }

    public List<OscarRto> lista() {

        List<OscarRto> filmeRto = filmes.stream().map(f -> new OscarRto(f)).collect(Collectors.toList());
        return  filmeRto;

    }

    @GetMapping()
    public ResponseEntity<List<Filme>> getFilme(){
        if(filmes.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> getId(@PathVariable int id){
        if(id < filmes.size() && id >= 0){
            return ResponseEntity.status(200).body(filmes.get(id));
        }
        return ResponseEntity.status(404).build();
    }


    @PostMapping()
    public ResponseEntity<Filme> getNome(@RequestBody Filme filme){
        if(filme.getNome().length() >= 2 && filme.getAnoLancamento() > 1895){
            filmes.add(filme);
            return ResponseEntity.status(200).body(filme);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> setId(@PathVariable int id, @RequestBody Filme filme){
        if(id >= 0 && id < filmes.size()){
                filmes.add(id, filme);
                return ResponseEntity.status(200).body(filme);
        }
        return ResponseEntity.status(404).build();
    }


    @PatchMapping("/{id}/{oscar}")
    public ResponseEntity<OscarRto> atualizarOscar(@PathVariable int id, @PathVariable int oscar){
        if(id >= 0 && id < filmes.size()){
            Filme filme = filmes.get(id);
            filme.setQtdOscar(oscar);
            return ResponseEntity.status(200).body(lista().get(id));
        }
        return ResponseEntity.status(404).build();
    }
}
