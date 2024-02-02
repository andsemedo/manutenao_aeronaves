package cv.unipiaget.manutencao_aeronave.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anderson Semedo
 * @Date 23/01/2024
 */
@Entity
@Table(name = "aeronave")
public class AeronaveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aeronaveid;
    @Column(unique = true)
    @NotNull(message = "Campo matricula é obrigatorio")
    private String matricula;
    @NotNull(message = "Campo modelo é obrigatorio")
    private String modelo;
    @NotNull(message = "Campo ano fabrico é obrigatorio")
    @Min(message = "Ano minimo aceitavel é 1960", value = 1960)
    @Max(message = "Ano maximo aceitavel é 2024", value = 2024)
    private int ano_fabrico;
    private int capacidade;

    @OneToMany(mappedBy = "aeronave", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("aeronave")
    private List<AtividadeManutencaoEntity> manutencao;

    public AeronaveEntity() {
    }

    public AeronaveEntity(String matricula, String modelo, int ano_fabrico, int capacidade) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.ano_fabrico = ano_fabrico;
        this.capacidade = capacidade;
    }

    public AeronaveEntity(Long aeronaveid, String matricula, String modelo, int ano_fabrico, int capacidade) {
        this.aeronaveid = aeronaveid;
        this.matricula = matricula;
        this.modelo = modelo;
        this.ano_fabrico = ano_fabrico;
        this.capacidade = capacidade;
    }

    public AeronaveEntity(Long aeronaveid, String matricula, String modelo, int ano_fabrico, int capacidade, List<AtividadeManutencaoEntity> manutencao) {
        this.aeronaveid = aeronaveid;
        this.matricula = matricula;
        this.modelo = modelo;
        this.ano_fabrico = ano_fabrico;
        this.capacidade = capacidade;
        this.manutencao = manutencao;
    }

    public Long getAeronaveid() {
        return aeronaveid;
    }

    public void setAeronaveid(Long aeronaveid) {
        this.aeronaveid = aeronaveid;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno_fabrico() {
        return ano_fabrico;
    }

    public void setAno_fabrico(int ano_fabrico) {
        this.ano_fabrico = ano_fabrico;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public List<AtividadeManutencaoEntity> getManutencao() {
        return manutencao;
    }

    public void setManutencao(List<AtividadeManutencaoEntity> manutencao) {
        this.manutencao = manutencao;
    }
}
