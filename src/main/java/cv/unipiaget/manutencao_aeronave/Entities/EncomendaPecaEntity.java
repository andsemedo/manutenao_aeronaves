package cv.unipiaget.manutencao_aeronave.Entities;

import cv.unipiaget.manutencao_aeronave.Enums.EncomendaStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * @Silvino Gomes
 * @Date 17/01/2024
 */

@Entity
@Table(name = "tbl_encomenda_pecas")
public class EncomendaPecaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_encomendaPeca;
    @NotNull(message = "Campo quantidade é obrigatorio")
    private int quantidade;
    @NotNull(message = "Campo data_encomenda é obrigatorio")
    private LocalDate dataEncomenda;
    @NotNull(message = "Campo status é obrigatorio")
    private EncomendaStatus status;

    @ManyToOne()
    @JoinColumn(name = "encomendaPecaId")
    private PecaEntity peca;
    @NotNull(message = "Id de peca é obrigatorio")
    private int idPeca;

    public EncomendaPecaEntity() {
    }

    public EncomendaPecaEntity(int quantidade) {
        this.quantidade = quantidade;
    }

    public EncomendaPecaEntity(int quantidade, LocalDate dataEncomenda, EncomendaStatus status, PecaEntity peca) {
        this.quantidade = quantidade;
        this.dataEncomenda = dataEncomenda;
        this.status = status;
        this.peca = peca;
    }

    public EncomendaPecaEntity(int id_encomendaPeca, int quantidade, LocalDate dataEncomenda, EncomendaStatus status, int idPeca) {
        this.id_encomendaPeca = id_encomendaPeca;
        this.quantidade = quantidade;
        this.dataEncomenda = dataEncomenda;
        this.status = status;
        this.idPeca = idPeca;
    }



    public int getIdEncomendaPeca() {
        return id_encomendaPeca;
    }

    public void setIdEncomendaPeca(int id_encomendaPeca) {
        this.id_encomendaPeca = id_encomendaPeca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataEncomenda() {
        return dataEncomenda;
    }

    public void setDataEncomenda(LocalDate data_encomenda) {
        this.dataEncomenda = data_encomenda;
    }

    public EncomendaStatus getStatus() {
        return status;
    }

    public void setStatus(EncomendaStatus status) {
        this.status = status;
    }

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int id_peca) {
        this.idPeca = id_peca;
    }
}
