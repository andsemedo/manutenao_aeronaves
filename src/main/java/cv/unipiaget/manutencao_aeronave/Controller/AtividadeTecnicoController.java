package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeTecnicoEntities;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeEquipaServices;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeTecnicoServices;
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
@RequestMapping(path = "api/manutencao/tecnico/")
public class AtividadeTecnicoController {
    private final AtividadeTecnicoServices atividadeTecnicoServices;
    private final AtividadeEquipaServices atividadeEquipaServices;


    public AtividadeTecnicoController(AtividadeTecnicoServices atividadeTecnicoServices, AtividadeEquipaServices atividadeEquipaServices) {
        this.atividadeTecnicoServices = atividadeTecnicoServices;
        this.atividadeEquipaServices = atividadeEquipaServices;
    }

    @Operation(description = "Endpoint que retorna todos os tecnicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna uma lista de tecnicos"),
            @ApiResponse(responseCode = "404", description = "Nenhum tecnico encontrado")
    })
    @GetMapping
    public ResponseEntity<Object> getAllTecnico(){

        List<AtividadeTecnicoEntities> tecnicoResponseList = atividadeTecnicoServices.getAllTecnico().stream()
                .map(tec -> {
                    AtividadeTecnicoEntities tecnico = new AtividadeTecnicoEntities();
                    tecnico.setIdTecnico(tec.getIdTecnico());
                    tecnico.setNomeTecnico(tec.getNomeTecnico());
                    tecnico.setCargo(tec.getCargo());
                    tecnico.setIdequipa(tec.getIdequipa());
                    return tecnico;
                })
                .collect(Collectors.toList());

        if (tecnicoResponseList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum tecnico encontrado");

        return ResponseEntity.status(HttpStatus.OK).body(tecnicoResponseList);
    }
//-----------------------------------------------------------------------
    @Operation(description = "Endpoint que adicionar um novo tecnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "retorna adicionado, retorna o tecnico"),
            @ApiResponse(responseCode = "404", description = "Equipa não encontrada")
    })
    @PostMapping
    public ResponseEntity<Object> addNewTecnico(@RequestBody AtividadeTecnicoEntities tecnico){
        //verificar se a equipa existe
        Optional<AtividadeEquipaEntities> equipa = atividadeEquipaServices.getEquipaById(tecnico.getIdequipa());
        if (equipa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipa não encontrada");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeTecnicoServices.addNewTecnico(tecnico));
    }

    @Operation(description = "Endpoint que procura um tecnico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna o tecnico procurado"),
            @ApiResponse(responseCode = "404", description = "Tecnico não encontrado")
    })
    @GetMapping("/{idTecnico}")
    public ResponseEntity<Object> getTecnicoById(@PathVariable ("idTecnico") long idTecnico){
      AtividadeTecnicoEntities tecnico = atividadeTecnicoServices.getTecnicoById(idTecnico);
      if (tecnico == null){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Tencico não encontrado!");
      }
      return ResponseEntity.status(HttpStatus.OK).body(tecnico);
    }
//-------------------------------------------------------------------------------------
    @Operation(description = "Endpoint que atualiza um tecnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna atualizado, retorna o tecnico"),
            @ApiResponse(responseCode = "404", description = "Tecnico não encontrada"),
            @ApiResponse(responseCode = "404", description = "Equipa não encontrada"),
    })
    @PutMapping("/{idTecnico}")
    public ResponseEntity<Object> updateTecnico(@PathVariable("idTecnico") Long idTecnico, @RequestBody AtividadeTecnicoEntities tecnico) {
        //verificar se o tecnico existe
        AtividadeTecnicoEntities atividadeTecnicoEntities = atividadeTecnicoServices.getTecnicoById(idTecnico);
        if (atividadeTecnicoEntities == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Técnico não encontrada!");
        }
        //verificar se a equipa existe
        Optional<AtividadeEquipaEntities> equipa = atividadeEquipaServices.getEquipaById(tecnico.getIdequipa());
        if (equipa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipa não encontrada");
        }

        atividadeTecnicoEntities.setNomeTecnico(tecnico.getNomeTecnico());
        atividadeTecnicoEntities.setCargo(tecnico.getCargo());
        atividadeTecnicoEntities.setIdequipa(tecnico.getIdequipa());

        return ResponseEntity.status(HttpStatus.OK).body(atividadeTecnicoServices.updateTecnico(atividadeTecnicoEntities));
    }

//-----------------------------------------------------------------
    @Operation(description = "Endpoint que deleta um tecnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "tecnico deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tecnico não encontrado")
    })
    @DeleteMapping("/{idTecnico}")
    public ResponseEntity<Object> deleteTecnico(@PathVariable("idTecnico") Long idTecnico) {
        //verificar se o tecnico existe
        AtividadeTecnicoEntities atividadeTecnicoEntities = atividadeTecnicoServices.getTecnicoById(idTecnico);
        if (atividadeTecnicoEntities == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Técnico não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(atividadeTecnicoServices.deleteTecnico(idTecnico));
    }

}
