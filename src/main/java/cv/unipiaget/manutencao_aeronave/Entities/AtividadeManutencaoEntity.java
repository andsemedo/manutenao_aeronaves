package cv.unipiaget.manutencao_aeronave.Entities;

import cv.unipiaget.manutencao_aeronave.Enums.StatusManutencaoEnum;
import cv.unipiaget.manutencao_aeronave.Enums.TipoManutencaoEnum;
import jakarta.persistence.*;

import java.util.Date;

/**
 * @author Anderson Semedo
 * @Date 15/01/2024
 */
@Entity
@Table(name = "atividade_manutencao")
public class AtividadeManutencaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TipoManutencaoEnum tipoManutencao;
    private String descricao;
    private StatusManutencaoEnum statusManutencao;
    private int idAeronave;
    private Date data;

    public AtividadeManutencaoEntity() {
    }

    public AtividadeManutencaoEntity(TipoManutencaoEnum tipoManutencao, String descricao, StatusManutencaoEnum statusManutencao, int idAeronav, Date data) {
        this.tipoManutencao = tipoManutencao;
        this.descricao = descricao;
        this.statusManutencao = statusManutencao;
        this.idAeronave = idAeronave;
        this.data = data;
    }

    public AtividadeManutencaoEntity(Long id, TipoManutencaoEnum tipoManutencao, String descricao, StatusManutencaoEnum statusManutencao, int idAeronave, Date data) {
        this.id = id;
        this.tipoManutencao = tipoManutencao;
        this.descricao = descricao;
        this.statusManutencao = statusManutencao;
        this.idAeronave = idAeronave;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getIdAeronave() {
        return idAeronave;
    }

    public void setIdAeronave(int idAeronave) {
        this.idAeronave = idAeronave;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
