/**
 * @author Silvino Gomes
 * @Date 17/01/2024
 */

package cv.unipiaget.manutencao_aeronave.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_uso_pecas")
public class UsoPecaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_peca_uso;

    @ManyToOne
    @JoinColumn(name = "id_peca", referencedColumnName = "id_peca")
    private PecaEntity peca;

    @ManyToOne
    @JoinColumn(name = "atividade_manutencao_id", referencedColumnName = "id")
    private AtividadeManutencaoEntity atividadeManutencao;

    private int qtd_utilizado;

    public UsoPecaEntity() {
    }

    public UsoPecaEntity(PecaEntity peca, AtividadeManutencaoEntity atividadeManutencao, int qtd_utilizado) {
        this.peca = peca;
        this.atividadeManutencao = atividadeManutencao;
        this.qtd_utilizado = qtd_utilizado;
    }

    public Long getIdPecaUso() {
        return id_peca_uso;
    }

    public void setIdPecaUso(Long id_peca_uso) {
        this.id_peca_uso = id_peca_uso;
    }

    public PecaEntity getPeca() {
        return peca;
    }

    public void setPeca(PecaEntity peca) {
        this.peca = peca;
    }

    public AtividadeManutencaoEntity getAtividadeManutencao() {
        return atividadeManutencao;
    }

    public void setAtividadeManutencao(AtividadeManutencaoEntity atividadeManutencao) {
        this.atividadeManutencao = atividadeManutencao;
    }

    public int getQtdUtilizado() {
        return qtd_utilizado;
    }

    public void setQtdUtilizado(int qtd_utilizado) {
        this.qtd_utilizado = qtd_utilizado;
    }
}
