package school.sptech.primeiraapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/herois")
public class HeroiController {

    private List<Heroi> herois = new ArrayList<>();

   @GetMapping("/favorito")
    public Heroi favorito(){
       Heroi heroi = new Heroi("Batman", 17, "Rico", 8000.1, true);

       return heroi;
   }

   @GetMapping("/")
    public List<Heroi> listar(){
       return herois;
   }

   @GetMapping("/{indice}")
    public Heroi recuperar(@PathVariable Integer indice){
       if(indice >= 0 && indice < herois.size()){
           return herois.get(indice);
       }
       return null;
   }

   @GetMapping("/cadastrar/{nome}/{habilidade}/{idade}/{forca}/{vivo}")
    public List<Heroi> cadastrar(@PathVariable String nome, @PathVariable String habilidade, @PathVariable Integer idade, @PathVariable Double forca, @PathVariable Boolean vivo){
        herois.add(new Heroi(nome, idade, habilidade, forca, vivo));
        return herois;
   }

   @GetMapping("/atualizar/{indice}/{nome}/{habilidade}/{idade}/{forca}/{vivo}")
   public String atualizar(@PathVariable Integer indice, @PathVariable String nome, @PathVariable String habilidade,
                           @PathVariable Integer idade, @PathVariable Double forca, @PathVariable Boolean vivo){
       if (indice < herois.size() && indice >= 0 ){

           for (int i = 0; i < herois.size(); i++) {
                   Heroi heroi = new Heroi(nome, idade, habilidade, forca, vivo);
                   herois.set(indice, heroi);
                   return "Atualizado com sucesso!";
           }
       }
       return null;
   }

    @GetMapping("/remover/{indice}")
    public String remover(@PathVariable Integer indice){
        if (indice < herois.size() && indice >= 0 ){

            for (int i = 0; i < herois.size(); i++){
                if (i == indice){
                    herois.remove(i);
                }
            }

            return "Herói removido.";
        }
        return "Herói não encontrado";
    }


}
