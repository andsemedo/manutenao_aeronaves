package cv.unipiaget.manutencao_aeronave.Entities;

import cv.unipiaget.manutencao_aeronave.Enums.StatusManutencaoEnum;
import cv.unipiaget.manutencao_aeronave.Enums.TipoManutencaoEnum;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Anderson Semedo
 * @Date 15/01/2024
 */
@Entity
@Table(name = "atividade_manutencao")
public class AtividadeManutencaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manutencaoid;
    private TipoManutencaoEnum tipoManutencao;
    private String descricao;
    private StatusManutencaoEnum statusManutencao;
    //private String matriculaAeronave;
    private LocalDate data;

    @OneToMany(mappedBy = "manutencao", fetch = FetchType.EAGER)
    private List<RegistoTarefaEntity> tarefa;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aeronaveid", insertable = false, updatable = false)
    private AeronaveEntity aeronave;

    private Long aeronaveid;

    public AtividadeManutencaoEntity() {
    }

    public AtividadeManutencaoEntity(TipoManutencaoEnum tipoManutencao, String descricao, StatusManutencaoEnum statusManutencao, LocalDate data, AeronaveEntity aeronave) {
        this.tipoManutencao = tipoManutencao;
        this.descricao = descricao;
        this.statusManutencao = statusManutencao;
        this.data = data;
        this.aeronave = aeronave;
    }

    public AtividadeManutencaoEntity(Long manutencaoid, TipoManutencaoEnum tipoManutencao, String descricao, StatusManutencaoEnum statusManutencao, LocalDate data, AeronaveEntity aeronave) {
        this.manutencaoid = manutencaoid;
        this.tipoManutencao = tipoManutencao;
        this.descricao = descricao;
        this.statusManutencao = statusManutencao;
        this.data = data;
        this.aeronave = aeronave;
    }

    public Long getManutencaoid() {
        return manutencaoid;
    }

    public void setManutencaoid(Long manutencaoid) {
        this.manutencaoid = manutencaoid;
    }

    public TipoManutencaoEnum getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(TipoManutencaoEnum tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusManutencaoEnum getStatusManutencao() {
        return statusManutencao;
    }

    public void setStatusManutencao(StatusManutencaoEnum statusManutencao) {
        this.statusManutencao = statusManutencao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public AeronaveEntity getAeronave() {
        return aeronave;
    }

    public void setAeronave(AeronaveEntity aeronave) {
        this.aeronave = aeronave;
    }

    public Long getAeronaveid() {
        return aeronaveid;
    }

    public void setAeronaveid(Long aeronaveid) {
        this.aeronaveid = aeronaveid;
    }
}
