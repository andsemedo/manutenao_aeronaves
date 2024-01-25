package cv.unipiaget.manutencao_aeronave.GestaoVooSimulate;

import jakarta.persistence.*;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */
@Entity
@Table(name = "dispobibilidade_aeronave")
public class DisponibilidadeAeronaveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    @Column(unique = true)
    private String matricula;
    private int disponibilidade;

    public DisponibilidadeAeronaveEntity() {
    }

    public DisponibilidadeAeronaveEntity(String modelo, String matricula, int disponibilidade) {
        this.modelo = modelo;
        this.matricula = matricula;
        this.disponibilidade = disponibilidade;
    }

    public DisponibilidadeAeronaveEntity(Long id, String modelo, String matricula, int disponibilidade) {
        this.id = id;
        this.modelo = modelo;
        this.matricula = matricula;
        this.disponibilidade = disponibilidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(int disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
