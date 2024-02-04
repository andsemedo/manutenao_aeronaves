package cv.unipiaget.manutencao_aeronave.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "atividade_equipa")
public class AtividadeEquipaEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEquipa;
    private String nomeEquipa;

    @OneToMany(mappedBy = "equipa", fetch = FetchType.EAGER)
    private List<RegistoTarefaEntity> registoTarefaEntities;

    @OneToMany(mappedBy = "equipa", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("equipa")
    private List<AtividadeTecnicoEntities> tecnicoEntities = new ArrayList<AtividadeTecnicoEntities>();

    public AtividadeEquipaEntities() {

    }

    public AtividadeEquipaEntities(String nomeEquipa) {
        this.nomeEquipa = nomeEquipa;
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

    public List<AtividadeTecnicoEntities> getTecnicoEntities() {
        return tecnicoEntities;
    }

    public void setTecnicoEntities(List<AtividadeTecnicoEntities> tecnicoEntities) {
        this.tecnicoEntities = tecnicoEntities;
    }
}
