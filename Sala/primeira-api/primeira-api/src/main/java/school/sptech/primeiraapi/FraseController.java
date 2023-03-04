package school.sptech.primeiraapi;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frases")
public class FraseController {

    @GetMapping
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/bom-dia")
    public String bomDia(){
        return "Bom dia!";
    }

    // PathVariable
    @GetMapping("/bom-dia/{nome}/{sobrenome}")
    public String personalizada(@PathVariable String nome, @PathVariable String sobrenome){
        return "Bom dia! " + nome + " " + sobrenome + " , você está lindo(a) como sempre.";
    }


}
