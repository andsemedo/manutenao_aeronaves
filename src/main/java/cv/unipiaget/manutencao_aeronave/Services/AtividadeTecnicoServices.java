package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeTecnicoEntities;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeTecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeTecnicoServices {
     public final AtividadeTecnicoRepository atividadeTecnicoRepository;

    public AtividadeTecnicoServices(AtividadeTecnicoRepository atividadeTecnicoRepository) {
        this.atividadeTecnicoRepository = atividadeTecnicoRepository;
    }
//-------------------------------------------------
//    Listar tecnico
//-------------------------------------------------
     public List<AtividadeTecnicoEntities> getAllTecnico() {
         return atividadeTecnicoRepository.findAll();
    }

//-------------------------------------------------
// Delete Tecnico
//-------------------------------------------------
    public Boolean deleteTecnico(long idTecnico){
        atividadeTecnicoRepository.deleteById(idTecnico);
        return true;
    }

//-------------------------------------------------
//Create Tecnico
//-------------------------------------------------
    public AtividadeTecnicoEntities addNewTecnico(AtividadeTecnicoEntities tecnico){
        return atividadeTecnicoRepository.save(tecnico);
    }

    public AtividadeTecnicoEntities getTecnicoById(Long id) {
        Optional<AtividadeTecnicoEntities> tecnico = atividadeTecnicoRepository.findById(id);
        if (tecnico.isEmpty()) {
            return null;
        }
        return tecnico.get();
    }

    public List<AtividadeTecnicoEntities> getAllTecnicoByEquipa(Long id) {
        return atividadeTecnicoRepository.findByIdequipa(id);
    }

//-------------------------------------------------
//Update Tecnico
//-------------------------------------------------
    public AtividadeTecnicoEntities updateTecnico(AtividadeTecnicoEntities tecnico) {
        return atividadeTecnicoRepository.save(tecnico);
    }

//----------------------------------------------

}
