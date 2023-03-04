package school.sptech.login01221137thayla;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private List<Usuario> user;

    public UsuarioController() {
        this.user = new ArrayList<>();
    }

    @PostMapping
    public Usuario criar(@RequestBody Usuario novoUsuario){
        user.add(novoUsuario);
        return novoUsuario;
    }

    @PostMapping("/autenticacao/{usuario}/{senha}")
    public Usuario autenticar(@PathVariable String usuario, @PathVariable String senha){
        for(int i = 0; i < user.size(); i++){
            if(user.get(i).getUsuario().equals(usuario) &&
            user.get(i).getSenha().equals(senha)){
                user.get(i).setAutenticacao(true);
                return user.get(i);
            }
        }
        return null;
    }

    @GetMapping
    private List<Usuario>lista(){
        return user;
    }

    @DeleteMapping("/autenticacao/{usuario}")
    public String remover(@PathVariable String usuario){
        for(int i = 0; i < user.size(); i++){
            if(user.get(i).getUsuario().equals(usuario) &&
                    user.get(i).isAutenticacao() == true){
                user.get(i).setAutenticacao(false);
                return "Logoff do usuário " + user.get(i).getNome() + " concluído";
            } else if(user.get(i).getUsuario().equals(usuario) &&
                    user.get(i).isAutenticacao() == false){
                return "Usuário " + user.get(i).getNome() + " NÃO está autenticado";
            }
        }
        return "Usuário " + usuario + " não encontrado";
    }

    // Buscar o usuário apenas pelo nome no parâmetro

    @GetMapping("/{nome}")
    public String buscarNome(@PathVariable String nome){
        for(int i = 0; i < user.size(); i++){
            if(user.get(i).getNome().equals(nome)){
                user.get(i);
            }
        }
        return "Não encontrado";
    }



}
