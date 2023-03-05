package school.sptech.login01221137thayla;

public class UsuarioTransfer {
    private String usuario;
    private String nome;
    private boolean autenticacao;

    public UsuarioTransfer(Usuario usuario) {
      this.nome = usuario.getNome();
      this.usuario = usuario.getUsuario();
      this.autenticacao = usuario.isAutenticacao();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(boolean autenticacao) {
        this.autenticacao = autenticacao;
    }
}
