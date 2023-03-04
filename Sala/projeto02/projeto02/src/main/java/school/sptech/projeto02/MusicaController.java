package school.sptech.projeto02;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private List<Musica> playlist;

    public MusicaController() {
        this.playlist = new ArrayList<>();
        playlist.add(new Musica("Carolina", "Luiz Gonzaga", 2000));
        playlist.add(new Musica("Dias de luta, Dias de luta", "Skate", 2014));
        playlist.add(new Musica("Boate Azul", "Cachorrão do Brega", 2018));
    }

    @GetMapping
    private List<Musica> lista(){
        return playlist;
    }


    @GetMapping("/{indice}")
    public Musica recuperar(@PathVariable int indice){
        if(indice >= 0 && indice < playlist.size()){
            return playlist.get(indice);
        }
        return null;
    }

    // para remover
    @DeleteMapping("/{indice}")
    public String remover(@PathVariable int indice){
        if(indice >= 0 && indice < playlist.size()){
            playlist.remove(indice);
            return "Música removida com sucesso!";
        }
        return "Música não encontrada!";
    }

    // para criar uma nova requisição
    @PostMapping
    public Musica criar(@RequestBody Musica novaMusica){
        playlist.add(novaMusica);
        return novaMusica;
    }

    // fazendo alteração
    @PutMapping("/{indice}")
    public String alterar(@PathVariable int indice, @RequestBody Musica musicaAlterada){
        if(indice >= 0 && indice < playlist.size()){
            playlist.set(indice, musicaAlterada);
            return "Alterado com sucesso";
        }
        return "Música não encontrada";
    }


}
