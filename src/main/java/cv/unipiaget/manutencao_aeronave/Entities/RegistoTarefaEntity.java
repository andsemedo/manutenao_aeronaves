package cv.unipiaget.manutencao_aeronave.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Anderson Semedo
 * @Date 17/01/2024
 */
@Entity
@Table(name = "registo_tarefa")
public class RegistoTarefaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registotarefaid;

    @Column(name = "data_exucucao")
    private LocalDate data_execucao;
    private String comentario;

    @ManyToOne()
    @JoinColumn(name = "manutencaoid", insertable = false, updatable = false)
    private AtividadeManutencaoEntity manutencao;
    private Long atividadeManutencao;

    @ManyToOne()
    @JoinColumn(name = "id_equipa", insertable = false, updatable = false)
    private AtividadeEquipaEntities equipa;
    private Long idequipa;

    @OneToMany(mappedBy = "registoTarefa")
    private List<UsoPecaEntity> usoPeca;

    public RegistoTarefaEntity() {
    }

    public RegistoTarefaEntity(LocalDate data_execucao, String comentario, AtividadeManutencaoEntity manutencao, Long atividadeManutencao, AtividadeEquipaEntities equipa, Long idequipa) {
        this.data_execucao = data_execucao;
        this.comentario = comentario;
        this.manutencao = manutencao;
        this.atividadeManutencao = atividadeManutencao;
        this.equipa = equipa;
        this.idequipa = idequipa;
    }

    public RegistoTarefaEntity(Long registotarefaid, LocalDate data_execucao, String comentario, AtividadeManutencaoEntity manutencao, Long atividadeManutencao, AtividadeEquipaEntities equipa, Long idequipa) {
        this.registotarefaid = registotarefaid;
        this.data_execucao = data_execucao;
        this.comentario = comentario;
        this.manutencao = manutencao;
        this.atividadeManutencao = atividadeManutencao;
        this.equipa = equipa;
        this.idequipa = idequipa;
    }

    public RegistoTarefaEntity(long registotarefaid, String s, String reparação_no_motor_esquerdo, AtividadeManutencaoEntity manutencao, Long manutencaoid, AtividadeEquipaEntities equipa, long idEquipa) {
    }

    public Long getRegistotarefaid() {
        return registotarefaid;
    }

    public void setRegistotarefaid(Long registotarefaid) {
        this.registotarefaid = registotarefaid;
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

    public Long getIdequipa() {
        return idequipa;
    }

    public void setIdequipa(Long idequipa) {
        this.idequipa = idequipa;
    }

    public AtividadeManutencaoEntity getManutencao() {
        return manutencao;
    }

    public void setManutencao(AtividadeManutencaoEntity manutencao) {
        this.manutencao = manutencao;
    }

    public AtividadeEquipaEntities getEquipa() {
        return equipa;
    }

    public void setEquipa(AtividadeEquipaEntities equipa) {
        this.equipa = equipa;
    }
}
