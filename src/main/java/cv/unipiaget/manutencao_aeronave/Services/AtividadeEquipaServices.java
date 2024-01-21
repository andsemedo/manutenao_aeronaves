package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeEquipaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AtividadeEquipaServices {
    private final AtividadeEquipaRepository atividadeEquipaRepository;

    public AtividadeEquipaServices(AtividadeEquipaRepository atividadeEquipaRepository) {
        this.atividadeEquipaRepository = atividadeEquipaRepository;
    }

 public List<AtividadeEquipaEntities> getAllEquipa(){

        return atividadeEquipaRepository.findAll();
}

public AtividadeEquipaEntities createNewEquipa(AtividadeEquipaEntities equipa){
        return atividadeEquipaRepository.save(equipa);
}

public Optional<AtividadeEquipaEntities> getEquipaById(Long idEquipa){
        return AtividadeEquipaRepository.findById(idEquipa);
}


public AtividadeEquipaEntities getEquipaByNome(String nomeEquipa){
        return atividadeEquipaRepository.findByNome(nomeEquipa);
}

public AtividadeEquipaEntities updateEquipa(AtividadeEquipaEntities equipa){
        return atividadeEquipaRepository.save(equipa);
    }

public Boolean deleteEquipa(long idEquipa){
        atividadeEquipaRepository.deleteById(idEquipa);
        return true;
}


}
