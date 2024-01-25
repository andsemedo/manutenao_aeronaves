package cv.unipiaget.manutencao_aeronave.GestaoVooSimulate;

import jakarta.persistence.*;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */
@Entity
@Table(name = "aeronave")
public class AeronaveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private int disponibilidade;

    public AeronaveEntity() {
    }

    public AeronaveEntity(String modelo, int disponibilidade) {
        this.modelo = modelo;
        this.disponibilidade = disponibilidade;
    }

    public AeronaveEntity(Long id, String modelo, int disponibilidade) {
        this.id = id;
        this.modelo = modelo;
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
}
