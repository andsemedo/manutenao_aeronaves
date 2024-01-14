package cv.unipiaget.manutencao_aeronave.classes;


import jakarta.persistence.Entity;

@Entity
public class UsoPeca {
    private int id_peca;
    private int id_registro;
    private int qtd_utilizado;


    public UsoPeca(int id_peca, int id_registro, int qtd_utilizado) {
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
