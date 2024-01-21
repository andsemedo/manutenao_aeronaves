package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeEquipaServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/manutencao/equipa/")
public class AtividadeEquipaController {
    private final AtividadeEquipaServices atividadeEquipaServices;

    public AtividadeEquipaController(AtividadeEquipaServices atividadeEquipaServices){
        this.atividadeEquipaServices=atividadeEquipaServices;
    }

 @GetMapping
public List<AtividadeEquipaEntities> getAllEquipa() {
    return AtividadeEquipaServices.getAllEquipa();
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
    return ResponseEntity.status(HttpStatus.OK).body(equipa.get());
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