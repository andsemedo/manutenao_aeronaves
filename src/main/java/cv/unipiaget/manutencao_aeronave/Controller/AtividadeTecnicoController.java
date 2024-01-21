package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeTecnicoEntities;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeTecnicoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/manutencao/tecnico/")
public class AtividadeTecnicoController {
    private final AtividadeTecnicoServices atividadeTecnicoServices;
    private final AtividadeEquipaEntities atividadeEquipaServices;


    public AtividadeTecnicoController(AtividadeTecnicoServices atividadeTecnicoServices, AtividadeEquipaEntities atividadeEquipaServices) {
        this.atividadeTecnicoServices = atividadeTecnicoServices;
        this.atividadeEquipaServices = atividadeEquipaServices;
    }

    @GetMapping
    public List<AtividadeTecnicoEntities> getAllTecnico(){
        return atividadeTecnicoServices.getAllTecnico();
    }
//-----------------------------------------------------------------------
    @PostMapping
    public ResponseEntity<Object> addNewTecnico(@RequestBody AtividadeTecnicoEntities tecnico){
        //verificar se a equipa existe
        Optional<AtividadeEquipaEntities> equipa = atividadeEquipaServices.getIdEquipa(tecnico.getId_Equipa());
        if (equipa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipa não encontrada");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeTecnicoServices.addNewTecnico(tecnico));
    }


      @GetMapping("/{idTecnico}")
      public ResponseEntity<Object> getTecnicoById(@PathVariable ("idTecnico") long idTecnico){
          AtividadeTecnicoEntities tecnico = atividadeTecnicoServices.getTecnicoById(idTecnico);
          if (tecnico == null){
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Tencico não encontrada!");
          }
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tecnico);
        }
//-------------------------------------------------------------------------------------
    @PutMapping("/{idTecnico}")
    public ResponseEntity<Object> updateTecnico(@PathVariable("idTecnico") Long idTecnico, @RequestBody AtividadeTecnicoEntities tecnico) {
        //verificar se o tecnico existe
        AtividadeTecnicoEntities atividadeTecnicoEntities = atividadeTecnicoServices.getTecnicoById(idTecnico);
        if (atividadeTecnicoEntities == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Técnico não encontrada!");
        }
        //verificar se a equipa existe
        Optional<AtividadeEquipaEntities> equipa = atividadeTecnicoServices.getEquipaById(tecnico.getId_Equipa());
        if (equipa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipa não encontrada");
        }

        atividadeTecnicoEntities.setNomeTecnico(tecnico.getNomeTecnico());
        atividadeTecnicoEntities.setCargo(tecnico.getCargo());
        atividadeTecnicoEntities.setId_Equipa(tecnico.getId_Equipa());

        return ResponseEntity.status(HttpStatus.OK).body(atividadeTecnicoServices.updateTecnico(atividadeTecnicoEntities));
    }

//-----------------------------------------------------------------
    @DeleteMapping("/{idTecnico}")
    public ResponseEntity<Object> deleteTecnico(@PathVariable("idTecnico") Long idTecnico) {
        //verificar se o tecnico existe
        AtividadeTecnicoEntities atividadeTecnicoEntities = atividadeTecnicoServices.getTecnicoById(idTecnico);
        if (atividadeTecnicoEntities == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Técnico não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(atividadeTecnicoServices.deleteTecnico(idTecnico));
    }

}
