package school.sptech.atividadecarrosprint2.dominio;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 12)
    private String nome;

    @NotBlank
    @Size(min = 2, max = 10)
    private String fabricante;

    @PastOrPresent
    private LocalDate dataFabricacao;

    @NotNull
    @Digits(integer = 4, fraction = 0)
    @Range(min = 1950, max = 2022)
    private Integer modelo;

    @NotNull
    @DecimalMin("0.2")
    @DecimalMax("7.0")
    private Double potenciaMotor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public Double getPotenciaMotor() {
        return potenciaMotor;
    }

    public void setPotenciaMotor(Double potenciaMotor) {
        this.potenciaMotor = potenciaMotor;
    }
}
