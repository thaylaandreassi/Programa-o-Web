package school.sptech.atividadevalendonota2sprint;

public class OscarRto {
    private int quantidade;


    public OscarRto(Filme filme) {

        this.quantidade = filme.getQtdOscar();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
