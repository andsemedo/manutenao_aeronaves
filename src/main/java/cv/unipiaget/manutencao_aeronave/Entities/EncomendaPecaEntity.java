package cv.unipiaget.manutencao_aeronave.Entities;

import jakarta.persistence.*;

import java.util.Date;

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
    private int quantidade;
    private Date data_encomenda;
    private String status;
    private int id_peca;


    public EncomendaPecaEntity(int id_encomendaPeca, int quantidade, Date data_encomenda, String status, int id_peca) {
        this.id_encomendaPeca = id_encomendaPeca;
        this.quantidade = quantidade;
        this.data_encomenda = data_encomenda;
        this.status = status;
        this.id_peca = id_peca;
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

    public Date getDataEncomenda() {
        return data_encomenda;
    }

    public void setDataEncomenda(Date data_encomenda) {
        this.data_encomenda = data_encomenda;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdPeca() {
        return id_peca;
    }

    public void setIdPeca(int id_peca) {
        this.id_peca = id_peca;
    }
}
