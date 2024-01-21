package cv.unipiaget.manutencao_aeronave.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "atividade_equipa")
public class AtividadeEquipaEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idEquipa;
    private String nomeAautor;

public AtividadeEquipaEntities() {

}

    public AtividadeEquipaEntities(long idEquipa, String nomeAautor) {
        this.idEquipa = idEquipa;
        this.nomeAautor = nomeAautor;
    }

    public long getIdEquipa() {
        return idEquipa;
    }

    public void setIdEquipa(long idEquipa) {
        this.idEquipa = idEquipa;
    }

    public String getNomeAautor() {
        return nomeAautor;
    }

    public void setNomeAautor(String nomeAautor) {
        this.nomeAautor = nomeAautor;
    }
}
