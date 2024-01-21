package cv.unipiaget.manutencao_aeronave.Entities;

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
    private long idEequipa;

    public AtividadeTecnicoEntities(){
    }

    public AtividadeTecnicoEntities(long idTecnico, String nomeTecnico, AtividadeTecnicoEnum cargo, long idEequipa) {
        this.idTecnico = idTecnico;
        this.nomeTecnico = nomeTecnico;
        this.cargo = cargo;
        this.idEequipa = idEequipa;
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

    public long getIdEequipa() {
        return idEequipa;
    }

    public void setIdEequipa(long idEequipa) {
        this.idEequipa = idEequipa;
    }
}
