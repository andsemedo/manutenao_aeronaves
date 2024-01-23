package cv.unipiaget.manutencao_aeronave.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @Silvino Gomes
 * @Date 17/01/2024
 */

@Entity
@Table(name = "tbl_uso_pecas")
public class UsoPecaEntity {

    @Id
    private int id_peca;
    private int id_registro;
    private int qtd_utilizado;


    public UsoPecaEntity(int id_peca, int id_registro, int qtd_utilizado) {
        this.id_peca = id_peca;
        this.id_registro = id_registro;
        this.qtd_utilizado = qtd_utilizado;
    }


    public int getIdPeca() {
        return id_peca;
    }

    public void setIdPeca(int id_peca) {
        this.id_peca = id_peca;
    }

    public int getIdRegistro() {
        return id_registro;
    }

    public void setIdRegistro(int idRegistro) {
        this.id_registro = idRegistro;
    }

    public int getQtdUtilizado() {
        return qtd_utilizado;
    }

    public void setQtdUtilizado(int qtd_utilizado) {
        this.qtd_utilizado = qtd_utilizado;
    }
}
