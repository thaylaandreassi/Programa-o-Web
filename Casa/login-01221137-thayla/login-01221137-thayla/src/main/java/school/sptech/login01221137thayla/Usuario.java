package school.sptech.login01221137thayla;

public class Usuario {
    private String usuario;
    private String senha;
    private String nome;
    private boolean autenticacao;

    public Usuario(String usuario, String senha, String nome, Boolean autenticacao) {
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.autenticacao = autenticacao;
    }
    public Usuario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
