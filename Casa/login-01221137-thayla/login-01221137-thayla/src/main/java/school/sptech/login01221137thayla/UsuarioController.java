package school.sptech.login01221137thayla;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private List<Usuario> user;

    public UsuarioController() {
        this.user = new ArrayList<>();
    }

    @GetMapping
    public List<UsuarioTransfer>list(){
        List<UsuarioTransfer> listaUser = user.stream().map(u -> new UsuarioTransfer(u)).collect(Collectors.toList());
        return listaUser;
    }
    @PostMapping
    public Usuario criar(@RequestBody Usuario novoUsuario){
        user.add(novoUsuario);
        return novoUsuario;
    }

    @PostMapping("/autenticacao/{usuario}/{senha}")
    public UsuarioTransfer autenticar(@PathVariable String usuario, @PathVariable String senha){
        for (Usuario us : user){
            if(us.getUsuario().equals(usuario) && us.getSenha().equals(senha)){
                us.setAutenticacao(true);
                var usuarioT = new UsuarioTransfer(us);
                return usuarioT;
            }
        }
        return null;
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
