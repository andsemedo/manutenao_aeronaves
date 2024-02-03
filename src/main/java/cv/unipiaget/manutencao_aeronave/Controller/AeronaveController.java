package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AeronaveEntity;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.Repository.AeronaveRepository;
import cv.unipiaget.manutencao_aeronave.Services.AeronaveService;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeManutencaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Anderson Semedo
 * @Date 23/01/2024
 */
@RestController
@RequestMapping("/api/manutencao/aeronave")
public class AeronaveController {
    private final AeronaveService aeronaveService;
    private final AeronaveRepository aeronaveRepository;
    private final AtividadeManutencaoService manutencaoService;

    public AeronaveController(AeronaveService aeronaveService, AeronaveRepository aeronaveRepository, AtividadeManutencaoService manutencaoService) {
        this.aeronaveService = aeronaveService;
        this.aeronaveRepository = aeronaveRepository;
        this.manutencaoService = manutencaoService;
    }

    @Operation(description = "Endpoint que retorna todas as aeronaves")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "retorna uma lista de aeronaves"),
        @ApiResponse(responseCode = "404", description = "Nenhuma aeronave encontrada")
    })
    @GetMapping
    public List<AeronaveEntity> getAllAeronave() {
        List<AeronaveEntity> aeronaveList = aeronaveService.getAllAeronave().stream()
                .map(aer -> {
                    AeronaveEntity aeronave = new AeronaveEntity();
                    aeronave.setAeronaveid(aer.getAeronaveid());
                    aeronave.setMatricula(aer.getMatricula());
                    aeronave.setModelo(aer.getModelo());
                    aeronave.setAno_fabrico(aer.getAno_fabrico());
                    aeronave.setCapacidade(aer.getCapacidade());
                    return aeronave;
                }).toList();
        return aeronaveList;
    }

    @Operation(description = "Endpoint que retorna uma aeronave por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna a aeronave encontrada"),
            @ApiResponse(responseCode = "404", description = "Aeronave não encontrado")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getAeronaveById(@PathVariable("id") Long id) {
        AeronaveEntity aeronave = aeronaveService.getAeronaveById(id);
        if (aeronave == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aeronave não encontrado");

        AeronaveEntity aeronaveResponse = new AeronaveEntity(
                aeronave.getAeronaveid(),
                aeronave.getMatricula(),
                aeronave.getModelo(),
                aeronave.getAno_fabrico(),
                aeronave.getCapacidade()
        );

        return ResponseEntity.status(HttpStatus.OK).body(aeronaveResponse);
    }

    @Operation(description = "Endpoint que retorna uma aeronave pela matricula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna a aeronave encontrada"),
            @ApiResponse(responseCode = "404", description = "Aeronave não encontrado")
    })
    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Object> getAeronaveByMatricula(@PathVariable("matricula") String matricula) {
        AeronaveEntity aeronave = aeronaveService.getAeronaveByMatricula(matricula);
        if (aeronave == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aeronave não encontrado");

        AeronaveEntity aeronaveResponse = new AeronaveEntity(
                aeronave.getAeronaveid(),
                aeronave.getMatricula(),
                aeronave.getModelo(),
                aeronave.getAno_fabrico(),
                aeronave.getCapacidade()
        );

        return ResponseEntity.status(HttpStatus.OK).body(aeronaveResponse);
    }

    @Operation(description = "Endpoint para registar uma nova aeronaves")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "aeronave registado e, retorna a aeronave"),
            @ApiResponse(responseCode = "500", description = "Já existe uma aeronave registada com esta matricula")
    })
    @PostMapping
    public ResponseEntity<Object> addNewAeronave(@RequestBody @Valid AeronaveEntity aeronave) {
        AeronaveEntity aeronaveMatricula = aeronaveRepository.findByMatricula(aeronave.getMatricula());
        if (aeronaveMatricula != null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Já existe uma aeronave registada com esta matricula");

        return ResponseEntity.status(HttpStatus.CREATED).body(aeronaveService.addNewAeronave(aeronave));
    }

    @Operation(description = "Endpoint para deletar uma aeronaves")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "aeronave deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aeronave não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar. A aeronave está associado a uma atividade manutenção")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAeronave(@PathVariable("id") Long id) {
        Optional<AeronaveEntity> aeronave = aeronaveRepository.findById(id);
        if(aeronave.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aeronave nao encontrada");

        //verificao se a aeronave esta associado a alguma manutencao
        List<AtividadeManutencaoEntity> manutencaoList = manutencaoService.getHistoricoByAeronave(id);
        if(!manutencaoList.isEmpty()) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao deletar. A aeronave está associado a uma atividade manutenção");

        aeronaveService.deleteAeronave(id);
        return ResponseEntity.status(HttpStatus.OK).body("Aeronave deletada com sucesso");
    }




}
