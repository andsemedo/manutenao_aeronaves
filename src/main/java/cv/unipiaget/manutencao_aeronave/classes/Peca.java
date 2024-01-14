package cv.unipiaget.manutencao_aeronave.classes;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Peca {

    @Id
    private int id_peca;
    private String nome;
    private int quantidade;


    public Peca(int id_peca, String nome, int quantidade, String status) {
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
