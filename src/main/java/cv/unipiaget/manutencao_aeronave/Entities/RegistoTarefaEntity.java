package cv.unipiaget.manutencao_aeronave.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Anderson Semedo
 * @Date 17/01/2024
 */
@Entity
@Table(name = "registo_tarefa")
public class RegistoTarefaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_exucucao")
    private LocalDate data_execucao;
    private String comentario;
    private Long atividadeManutencao;
    private Long id_equipa;

    public RegistoTarefaEntity() {
    }

    public RegistoTarefaEntity(LocalDate data_execucao, String comentario, Long atividadeManutencao, Long id_equipa) {
        this.data_execucao = data_execucao;
        this.comentario = comentario;
        this.atividadeManutencao = atividadeManutencao;
        this.id_equipa = id_equipa;
    }

    public RegistoTarefaEntity(Long id, LocalDate data_execucao, String comentario, Long atividadeManutencao, Long id_equipa) {
        this.id = id;
        this.data_execucao = data_execucao;
        this.comentario = comentario;
        this.atividadeManutencao = atividadeManutencao;
        this.id_equipa = id_equipa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData_execucao() {
        return data_execucao;
    }

    public void setData_execucao(LocalDate data_execucao) {
        this.data_execucao = data_execucao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getAtividadeManutencao() {
        return atividadeManutencao;
    }

    public void setAtividadeManutencao(Long atividadeManutencao) {
        this.atividadeManutencao = atividadeManutencao;
    }

    public Long getId_equipa() {
        return id_equipa;
    }

    public void setId_equipa(Long id_equipa) {
        this.id_equipa = id_equipa;
    }
}