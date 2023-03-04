package school.sptech.primeiraapi;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private List<String> pokemon = new ArrayList<>();


    @GetMapping
    public String pokemon(){

        return "Total de pokemons cadastrados: " + pokemon.size();
    }

    @GetMapping("/cadastrar/{nome}")
    public String cadastrado(@PathVariable String nome){
        pokemon.add(nome);
        return "Pokemon " + nome + " cadastrado";
    }

    @GetMapping ("/recuperar/{indice}")
    public String recuperar(@PathVariable Integer indice){
        if(indice >= 0 && indice < pokemon.size()){
                return pokemon.get(indice);
        }
        return "Pokemon não encontrado";
    }

    @GetMapping ("/excluir/{indice}")
    public String deletar(@PathVariable Integer indice){
        if(pokemon.size() >= indice){
            pokemon.remove(indice);
            return "Deletado com sucesso!";
        }
        return "Pokemon não encontrado.";
    }

    @GetMapping("/atualizar/{indice}/{novoNome}")
    public String atualizar(@PathVariable Integer indice, @PathVariable String novoNome){
        if(pokemon.size() >= indice){
            pokemon.set(indice, novoNome);
            return "Alterado com sucesso!";
        }
        return "Pokemon não encontrado";
    }
}




