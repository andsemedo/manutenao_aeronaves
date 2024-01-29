package cv.unipiaget.manutencao_aeronave.Entities;


import jakarta.persistence.*;

import java.util.List;


/**
 * @Silvino Gomes
 * @Date 17/01/2024
 */

@Entity
@Table(name = "tbl_pecas")
public class PecaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_peca;
    private String nome;
    private int quantidade;

    @OneToMany(mappedBy = "peca")
    private List<UsoPecaEntity> usoPeca;

    public PecaEntity() {
    }

    public PecaEntity(int id_peca, String nome, int quantidade, String status) {
        this.id_peca = id_peca;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getId() {
        return id_peca;
    }

    public void setId(int id_peca) {
        this.id_peca = id_peca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
