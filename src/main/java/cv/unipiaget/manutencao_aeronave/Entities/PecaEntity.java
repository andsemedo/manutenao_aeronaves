package cv.unipiaget.manutencao_aeronave.Entities;


import jakarta.persistence.*;


/**
 * @Silvino Gomes
 * @Date 17/01/2024
 */

@Entity
@Table(name = "tbl_pecas")
public class PecaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_peca;
    private String nome;
    private int quantidade;



    public PecaEntity() {

    }

/*
    public PecaEntity(long id_peca, String nome, int quantidade, String status) {
        this.id_peca = id_peca;
        this.nome = nome;
        this.quantidade = quantidade;
    }

 */

    // Getters e Setters
    public long getId() {
        return id_peca;
    }

    public void setId(long id_peca) {
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
