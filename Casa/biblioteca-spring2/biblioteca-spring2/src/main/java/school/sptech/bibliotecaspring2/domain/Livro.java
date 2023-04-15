package school.sptech.bibliotecaspring2.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max = 15)
    @NotBlank
    @Schema(name = "nome", description = "Título de Livro", example = "Um dia")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    private Escritor escritores;

    @NotNull
    private Integer anoLancamento;

    private String editora;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Escritor getEscritores() {
        return escritores;
    }

    public void setEscritores(Escritor escritores) {
        this.escritores = escritores;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
}
