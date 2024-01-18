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
    private int atividadeManutencao;
    private int id_uso_peca;

    public RegistoTarefaEntity() {
    }

    public RegistoTarefaEntity(LocalDate data_execucao, String comentario, int atividadeManutencao, int id_uso_peca) {
        this.data_execucao = data_execucao;
        this.comentario = comentario;
        this.atividadeManutencao = atividadeManutencao;
        this.id_uso_peca = id_uso_peca;
    }

    public RegistoTarefaEntity(Long id, LocalDate data_execucao, String comentario, int atividadeManutencao, int id_uso_peca) {
        this.id = id;
        this.data_execucao = data_execucao;
        this.comentario = comentario;
        this.atividadeManutencao = atividadeManutencao;
        this.id_uso_peca = id_uso_peca;
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

    public int getAtividadeManutencao() {
        return atividadeManutencao;
    }

    public void setAtividadeManutencao(int atividadeManutencao) {
        this.atividadeManutencao = atividadeManutencao;
    }

    public int getId_uso_peca() {
        return id_uso_peca;
    }

    public void setId_uso_peca(int id_uso_peca) {
        this.id_uso_peca = id_uso_peca;
    }
}
