package cv.unipiaget.manutencao_aeronave.responses;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeTecnicoEntities;

import java.util.List;

/**
 * @author Anderson Semedo
 * @Date 31/01/2024
 */
public class AtividadeEquipaResponse {
    private Long id;
    private String nomeEquipa;
    private List<AtividadeTecnicoEntities> listaTecnicos;

    public AtividadeEquipaResponse() {
    }

    public AtividadeEquipaResponse(String nomeEquipa, List<AtividadeTecnicoEntities> listaTecnicos) {
        this.nomeEquipa = nomeEquipa;
        this.listaTecnicos = listaTecnicos;
    }

    public AtividadeEquipaResponse(Long id, String nomeEquipa, List<AtividadeTecnicoEntities> listaTecnicos) {
        this.id = id;
        this.nomeEquipa = nomeEquipa;
        this.listaTecnicos = listaTecnicos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEquipa() {
        return nomeEquipa;
    }

    public void setNomeEquipa(String nomeEquipa) {
        this.nomeEquipa = nomeEquipa;
    }

    public List<AtividadeTecnicoEntities> getListaTecnicos() {
        return listaTecnicos;
    }

    public void setListaTecnicos(List<AtividadeTecnicoEntities> listaTecnicos) {
        this.listaTecnicos = listaTecnicos;
    }
}
