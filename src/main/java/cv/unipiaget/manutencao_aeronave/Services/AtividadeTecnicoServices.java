package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeTecnicoEntities;
import cv.unipiaget.manutencao_aeronave.Enums.AtividadeTecnicoEnum;
import cv.unipiaget.manutencao_aeronave.GestaoVooSimulate.GestaoVooMockService;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeTecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
         return atividadeTecnicoRepository.findBy();
    }

//-------------------------------------------------
// Delete Tecnico
//-------------------------------------------------
    public void deleteTecnico(long idTecnico){
        if(!atividadeTecnicoRepository.existsById(idTecnico)){
            throw new IllegalStateException("Esse tecnico não existe. Tente novamente");
        }
        atividadeTecnicoRepository.deleteById(idTecnico);
    }

//-------------------------------------------------
//Create Tecnico
//-------------------------------------------------
    public AtividadeTecnicoEntities addnewTecnico(AtividadeTecnicoEntities tecnico){
        return atividadeTecnicoRepository.save(tecnico);
    }

    public AtividadeTecnicoEntities getTecnicoById(Long id) {
        Optional<AtividadeTecnicoEntities> tecnico = atividadeTecnicoRepository.findById(id);
        if (tecnico.isEmpty()) {
            return null;
        }
        return tecnico.get();
    }


//-------------------------------------------------
//Update Tecnico
//-------------------------------------------------
    public void updateTecnico(long idTecnico, AtividadeTecnicoEnum cargo) {
        AtividadeEquipaEntities tecnicoEntities = atividadeTecnicoRepository.findById(idTecnico)
                .orElseThrow(() -> new IllegalStateException("Tecnico não existe!"));

        if (cargo != null) {
            tecnicoEntities.setCargo(cargo);
        }
        atividadeTecnicoRepository.save(tecnicoEntities);
    }


    //----------------------------------------------

}
