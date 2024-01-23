package cv.unipiaget.manutencao_aeronave.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "atividade_equipa")
public class AtividadeEquipaEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idEquipa;
    private String nomeEquipa;

public AtividadeEquipaEntities() {

}

    public AtividadeEquipaEntities(long idEquipa, String nomeEquipa) {
        this.idEquipa = idEquipa;
        this.nomeEquipa = nomeEquipa;
    }

    public long getIdEquipa() {
        return idEquipa;
    }

    public void setIdEquipa(long idEquipa) {
        this.idEquipa = idEquipa;
    }

    public String getNomeEquipa() {
        return nomeEquipa;
    }

    public void setNomeEquipa(String nomeAautor) {
        this.nomeEquipa = nomeAautor;
    }
}
