package cv.unipiaget.manutencao_aeronave.Entities;

import jakarta.persistence.*;

/**
 * @author Anderson Semedo
 * @Date 25/01/2024
 */
@Entity
@Table(name = "uso_peca")
public class UsoPecaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "registotarefaid")
    private RegistoTarefaEntity registoTarefa;
    private Long tarefaId;

    @ManyToOne()
    @JoinColumn(name = "id_peca")
    private PecaEntity peca;
    private int pecaId;

    private int quantidade;

    public UsoPecaEntity() {
    }

    public UsoPecaEntity(RegistoTarefaEntity registoTarefa, PecaEntity peca, int quantidade) {
        this.registoTarefa = registoTarefa;
        this.peca = peca;
        this.quantidade = quantidade;
    }

    public UsoPecaEntity(Long id, RegistoTarefaEntity registoTarefa, PecaEntity peca, int quantidade) {
        this.id = id;
        this.registoTarefa = registoTarefa;
        this.peca = peca;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegistoTarefaEntity getRegistoTarefa() {
        return registoTarefa;
    }

    public void setRegistoTarefa(RegistoTarefaEntity registoTarefa) {
        this.registoTarefa = registoTarefa;
    }

    public PecaEntity getPeca() {
        return peca;
    }

    public void setPeca(PecaEntity peca) {
        this.peca = peca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Long getTarefaId() {
        return tarefaId;
    }

    public void setTarefaId(Long tarefaId) {
        this.tarefaId = tarefaId;
    }

    public int getPecaId() {
        return pecaId;
    }

    public void setPecaId(int pecaId) {
        this.pecaId = pecaId;
    }
}
