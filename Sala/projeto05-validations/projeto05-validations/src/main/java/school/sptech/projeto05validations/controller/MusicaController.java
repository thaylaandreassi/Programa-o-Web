package school.sptech.projeto05validations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projeto05validations.repository.MusicaRepository;
import school.sptech.projeto05validations.dominio.Musica;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaRepository musicaRepository;

    @GetMapping
    public ResponseEntity<List<Musica>> listar(){
        List<Musica> musicas = musicaRepository.findAll();
        if(musicas.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(musicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> listarPorId(@PathVariable Integer id){
        Optional<Musica> musicas = musicaRepository.findById(id);
        if(musicas.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(musicas.get());
    }

    @PostMapping
    public ResponseEntity<Musica> criar(@RequestBody Musica novaMusica){
     Musica newMusica = musicaRepository.save(novaMusica);
     return ResponseEntity.status(201).body(newMusica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musica> setId(@PathVariable @Valid Integer id, @RequestBody Musica musicaAlterada){
        if(musicaRepository.existsById(id)){
            musicaAlterada.setId(id);
            Musica musicaNova = musicaRepository.save(musicaAlterada);
            return ResponseEntity.status(200).body(musicaNova);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        if(musicaRepository.existsById(id)){
            musicaRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
}
