package cv.unipiaget.manutencao_aeronave.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cv.unipiaget.manutencao_aeronave.Enums.AtividadeTecnicoEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "atividade_tecnico")
public class AtividadeTecnicoEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTecnico;
    private String nomeTecnico;
    private AtividadeTecnicoEnum cargo;

    @ManyToOne()
    @JoinColumn(name = "id_equipa", insertable = false, updatable = false)
    @JsonIgnoreProperties("tecnicoEntities")
    private AtividadeEquipaEntities equipa;
    private long id_Equipa;

    public AtividadeTecnicoEntities(){
    }

    public AtividadeTecnicoEntities(long idTecnico, String nomeTecnico, AtividadeTecnicoEnum cargo, AtividadeEquipaEntities equipa, long id_Equipa) {
        this.idTecnico = idTecnico;
        this.nomeTecnico = nomeTecnico;
        this.cargo = cargo;
        this.equipa = equipa;
        this.id_Equipa = id_Equipa;
    }

    public AtividadeTecnicoEntities(String nomeTecnico, AtividadeTecnicoEnum cargo, AtividadeEquipaEntities equipa) {
        this.nomeTecnico = nomeTecnico;
        this.cargo = cargo;
        this.equipa = equipa;
    }

    public AtividadeTecnicoEntities(String nomeTecnico, AtividadeTecnicoEnum cargo, AtividadeEquipaEntities equipa, long id_Equipa) {
        this.nomeTecnico = nomeTecnico;
        this.cargo = cargo;
        this.equipa = equipa;
        this.id_Equipa = id_Equipa;
    }

    public long getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(long idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public AtividadeTecnicoEnum getCargo() {
        return cargo;
    }

    public void setCargo(AtividadeTecnicoEnum cargo) {
        this.cargo = cargo;
    }

    public AtividadeEquipaEntities getEquipa() {
        return equipa;
    }

    public void setEquipa(AtividadeEquipaEntities equipa) {
        this.equipa = equipa;
    }

    public long getId_Equipa() {
        return id_Equipa;
    }

    public void setId_Equipa(long id_Equipa) {
        this.id_Equipa = id_Equipa;
    }
}
