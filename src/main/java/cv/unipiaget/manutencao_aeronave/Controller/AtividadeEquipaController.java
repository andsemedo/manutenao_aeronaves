package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeTecnicoEntities;
import cv.unipiaget.manutencao_aeronave.Entities.RegistoTarefaEntity;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeEquipaServices;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeTecnicoServices;
import cv.unipiaget.manutencao_aeronave.Services.RegistoTarefaService;
import cv.unipiaget.manutencao_aeronave.responses.AtividadeEquipaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final RegistoTarefaService tarefaService;
    private final AtividadeTecnicoServices tecnicoService;

 public AtividadeEquipaController(AtividadeEquipaServices atividadeEquipaServices, RegistoTarefaService tarefaService, AtividadeTecnicoServices tecnicoService){
        this.atividadeEquipaServices=atividadeEquipaServices;
     this.tarefaService = tarefaService;
     this.tecnicoService = tecnicoService;
 }

@Operation(description = "Endpoint que retorna todas as equipas")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "retorna uma lista de equipas"),
        @ApiResponse(responseCode = "404", description = "Nenhuma equipa encontrada")
})
@GetMapping
public ResponseEntity<Object> getAllEquipa() {
     List<AtividadeEquipaEntities> equipaEntities = atividadeEquipaServices.getAllEquipa();
     List<AtividadeEquipaResponse> equipaResponsesList = equipaEntities.stream()
             .map(eq -> {
                 AtividadeEquipaResponse equipaResponse = new AtividadeEquipaResponse();
                 equipaResponse.setId(eq.getIdEquipa());
                 equipaResponse.setNomeEquipa(eq.getNomeEquipa());
                 return equipaResponse;
             })
             .collect(Collectors.toList());

     if (equipaResponsesList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
             .body("Nenhuma equipa encontrado");

     return ResponseEntity.status(HttpStatus.OK).body(equipaResponsesList);
}

//--------------------------------------------------------
@Operation(description = "Endpoint criar uma nova equipa")
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "equipa criado e, retorna a equipa"),
        @ApiResponse(responseCode = "500", description = "Já existe uma equipa com este nome")
})
@PostMapping
public ResponseEntity<Object> createNewEquipa(@RequestBody AtividadeEquipaEntities equipa) {
    AtividadeEquipaEntities equipa_verify = atividadeEquipaServices.getEquipaByNome(equipa.getNomeEquipa());

    if (equipa_verify != null) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Já existe uma equipa com este nome");
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(atividadeEquipaServices.createNewEquipa(equipa));
}


//--------------------------------------------------------------
@Operation(description = "Endpoint para retornar uma equipa por id e seus tecnicos")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "retorna a equipa pesquisado"),
        @ApiResponse(responseCode = "404", description = "Equipa não encontrada")
})
@GetMapping("/{idEquipa}")
public ResponseEntity<Object> getEquipaById(@PathVariable(name = "idEquipa") Long idEquipa) {
    Optional<AtividadeEquipaEntities> equipa = atividadeEquipaServices.getEquipaById(idEquipa);
    if (equipa.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipa não encontrada");
    }
    /*
    AtividadeEquipaResponse equipaResponse = new AtividadeEquipaResponse();
    equipaResponse.setId(equipa.get().getIdEquipa());
    equipaResponse.setNomeEquipa(equipa.get().getNomeEquipa());
    equipaResponse.setListaTecnicos(equipa.get().getTecnicoEntities());
       */

    return ResponseEntity.status(HttpStatus.OK).body(equipa.get());
}

//---------------------------------------------------------------
@Operation(description = "Endpoint para atualizar o nome de uma equipa")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "retorna a equipa atualizado"),
        @ApiResponse(responseCode = "404", description = "Equipa não encontrada"),
        @ApiResponse(responseCode = "500", description = "Já existe uma equipa com o nome passado.")
})
@PutMapping("/{idEquipa}")
public ResponseEntity<Object> updateEquipa(@PathVariable(name = "idEquipa") Long idEquipa, @RequestBody AtividadeEquipaEntities equipa) {
    if (getEquipaById(idEquipa).getStatusCode() == HttpStatus.NOT_FOUND) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipa com o id "+idEquipa+" não existe");
    }
    //verificar se ja existe uma equipa com o nome passado
    if (atividadeEquipaServices.getEquipaByNome(equipa.getNomeEquipa()) != null) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Já existe uma equipa com o nome passado.");
    }

    AtividadeEquipaEntities atividadeEquipaEntities = (AtividadeEquipaEntities) getEquipaById(idEquipa).getBody();
    atividadeEquipaEntities.setNomeEquipa(equipa.getNomeEquipa());
    return ResponseEntity.status(HttpStatus.OK).body(atividadeEquipaServices.updateEquipa(atividadeEquipaEntities));
}


//-----------------------------------------------------------------------
@Operation(description = "Endpoint para deletar uma equipa pelo id")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "retorna a equipa atualizado"),
        @ApiResponse(responseCode = "404", description = "Equipa não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro ao deletar. A equipa está referenciada a uma tarefa"),
        @ApiResponse(responseCode = "500", description = "Erro ao deletar. A equipa está referenciada a um tecnico")
})
@DeleteMapping("/{idEquipa}")
public ResponseEntity<String> deleteEquipa(@PathVariable(name = "idEquipa") Long idEquipa) {

    if (getEquipaById(idEquipa).getStatusCode() == HttpStatus.NOT_FOUND) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipa com o id "+idEquipa+" não existe");
    }

    //verificar se a equipa está referencia a alguma tarefa
    List<RegistoTarefaEntity> tarefaList = tarefaService.getAllRegistoTarefaByEquipaId(idEquipa);
    if(!tarefaList.isEmpty()) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro ao deletar. A equipa está referenciada a uma tarefa");

    //verificar se a equipa está relacionada a um tecnico
    List<AtividadeTecnicoEntities> tecnicoList = tecnicoService.getAllTecnicoByEquipa(idEquipa);
    if (!tecnicoList.isEmpty()) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro ao deletar. A equipa está referenciada a um tecnico");

    atividadeEquipaServices.deleteEquipa(idEquipa);
    return ResponseEntity.status(HttpStatus.OK).body("Equipa deletado com sucesso");
}








}