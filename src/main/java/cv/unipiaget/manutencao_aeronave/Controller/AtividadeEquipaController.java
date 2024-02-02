package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeEquipaServices;
import cv.unipiaget.manutencao_aeronave.responses.AtividadeEquipaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/manutencao/equipa/")
public class AtividadeEquipaController {
    private final AtividadeEquipaServices atividadeEquipaServices;

 public AtividadeEquipaController(AtividadeEquipaServices atividadeEquipaServices){
        this.atividadeEquipaServices=atividadeEquipaServices;
}

 @GetMapping
public List<AtividadeEquipaResponse> getAllEquipa() {
     List<AtividadeEquipaEntities> equipaEntities = atividadeEquipaServices.getAllEquipa();
     List<AtividadeEquipaResponse> equipaResponsesList = equipaEntities.stream()
             .map(eq -> {
                 AtividadeEquipaResponse equipaResponse = new AtividadeEquipaResponse();
                 equipaResponse.setId(eq.getIdEquipa());
                 equipaResponse.setNomeEquipa(eq.getNomeEquipa());
                 return equipaResponse;
             })
             .collect(Collectors.toList());

     return equipaResponsesList;
}

//--------------------------------------------------------
@PostMapping
public ResponseEntity<Object> createNewEquipa(@RequestBody AtividadeEquipaEntities equipa) {
    AtividadeEquipaEntities equipa_verify = atividadeEquipaServices.getEquipaByNome(equipa.getNomeEquipa());

    if (equipa_verify != null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe uma equipa com este nome");
    }



    return ResponseEntity.status(HttpStatus.CREATED).body(atividadeEquipaServices.createNewEquipa(equipa));
}


//--------------------------------------------------------------
@GetMapping("/{idEquipa}")
public ResponseEntity<Object> getEquipaById(@PathVariable(name = "idEquipa") Long idEquipa) {
    Optional<AtividadeEquipaEntities> equipa = atividadeEquipaServices.getEquipaById(idEquipa);
    if (equipa.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipa não encontrada");
    }

    List<AtividadeEquipaResponse> equipaResponsesList = equipa.stream()
            .map(eq -> {
                AtividadeEquipaResponse equipaResponse = new AtividadeEquipaResponse();
                equipaResponse.setId(eq.getIdEquipa());
                equipaResponse.setNomeEquipa(eq.getNomeEquipa());
                equipaResponse.setListaTecnicos(eq.getTecnicoEntities());
                return equipaResponse;
            })
            .collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(equipaResponsesList);
}

//---------------------------------------------------------------
@PutMapping("/{idEquipa}")
public ResponseEntity<Object> updateEquipa(@PathVariable(name = "idEquipa") Long idEquipa, @RequestBody AtividadeEquipaEntities equipa) {
    if (getEquipaById(idEquipa).getStatusCode() == HttpStatus.NOT_FOUND) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipa com o id "+idEquipa+" não existe");
    }
    //verificar se ja existe uma equipa com o nome passado
    if (atividadeEquipaServices.getEquipaByNome(equipa.getNomeEquipa()) != null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe uma equipa com o nome passado.");
    }

    AtividadeEquipaEntities atividadeEquipaEntities = (AtividadeEquipaEntities) getEquipaById(idEquipa).getBody();
    atividadeEquipaEntities.setNomeEquipa(equipa.getNomeEquipa());
    return ResponseEntity.status(HttpStatus.OK).body(atividadeEquipaServices.updateEquipa(atividadeEquipaEntities));
}


//-----------------------------------------------------------------------
@DeleteMapping("/{idEquipa}")
public ResponseEntity<String> deleteEquipa(@PathVariable(name = "idEquipa") Long idEquipa) {

    if (getEquipaById(idEquipa).getStatusCode() == HttpStatus.NOT_FOUND) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipa com o id "+idEquipa+" não existe");
    }

    atividadeEquipaServices.deleteEquipa(idEquipa);
    return ResponseEntity.status(HttpStatus.OK).body("Equipa deletado com sucesso");
}








}