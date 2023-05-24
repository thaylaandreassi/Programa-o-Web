package school.sptech.cafeteria.dto;

public class PedidoResumoDto {

    private  String nomePedido;
    private String nomeCliente;
    private Double custo;

    public PedidoResumoDto(String nomePedido, String nomeCliente, Double custo) {
        this.nomePedido = nomePedido;
        this.nomeCliente = nomeCliente;
        this.custo = custo;
    }

    public String getNomePedido() {
        return nomePedido;
    }

    public void setNomePedido(String nomePedido) {
        this.nomePedido = nomePedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }
}
