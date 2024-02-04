package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AeronaveEntity;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.Entities.RegistoTarefaEntity;
import cv.unipiaget.manutencao_aeronave.GestaoVooSimulate.GestaoVooMockService;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeManutencaoRepository;
import cv.unipiaget.manutencao_aeronave.Services.AeronaveService;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeManutencaoService;
import cv.unipiaget.manutencao_aeronave.Services.RegistoTarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */
@RestController
@RequestMapping(path = "/api/manutencao/atividade/")
public class AtividadeManutencaoController {

    public final AtividadeManutencaoService atividadeManutencaoService;
    public final AtividadeManutencaoRepository atividadeManutencaoRepository;
    private final GestaoVooMockService gestaoVooMockService;
    private final AeronaveService aeronaveService;
    private final RegistoTarefaService registoTarefaService;

    public AtividadeManutencaoController(AtividadeManutencaoService atividadeManutencaoService, AtividadeManutencaoRepository atividadeManutencaoRepository, GestaoVooMockService gestaoVooMockService, AeronaveService aeronaveService, RegistoTarefaService registoTarefaService) {
        this.atividadeManutencaoService = atividadeManutencaoService;
        this.atividadeManutencaoRepository = atividadeManutencaoRepository;
        this.gestaoVooMockService = gestaoVooMockService;
        this.aeronaveService = aeronaveService;
        this.registoTarefaService = registoTarefaService;
    }

    @Operation(description = "Endpoint para listar todas as manutenções")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "aeronave deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aeronave não encontrada")
    })
    @GetMapping
    public List<AtividadeManutencaoEntity> getAllManutencao() {
        return atividadeManutencaoService.getAllManutencao();
    }

    @Operation(description = "Endpoint para retornar manutenção por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna a manutenção procurado"),
            @ApiResponse(responseCode = "404", description = "Manutenção não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getManutencaoById(@PathVariable("id") Long id) {
        AtividadeManutencaoEntity manutencao = atividadeManutencaoService.getManutencaoById(id);
        if (manutencao != null) {
            return ResponseEntity.ok(manutencao);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manutenção com o id " + id + " não existe");
    }

    @Operation(description = "Endpoint para listar todas as manutenções de uma aeronave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna a lista com as manutenções"),
            @ApiResponse(responseCode = "404", description = "Aeronave não encontrada")
    })
    @GetMapping("aeronave/{id}/historico")
    public ResponseEntity<Object> getHistoricoManutencaoByAeronave(@PathVariable("id") Long id) {
        AeronaveEntity aeronave = aeronaveService.getAeronaveById(id);
        if (aeronave == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aeronave não encontrado");
        }

        List<AtividadeManutencaoEntity> manutencaoList = atividadeManutencaoService.getHistoricoByAeronave(id).stream()
                .map(man -> {
                    AtividadeManutencaoEntity manutencao = new AtividadeManutencaoEntity(
                        man.getManutencaoid(),
                        man.getTipoManutencao(),
                        man.getDescricao(),
                        man.getStatusManutencao(),
                        man.getData()
                    );
                    return manutencao;
                })
                .collect(Collectors.toList());

        //if (manutencaoList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma manuteção nesta aeronave para mostrar");

        return ResponseEntity.status(HttpStatus.OK).body(manutencaoList);
    }

    @Operation(description = "Endpoint para adicionar uma nova manutenção")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "manutenção adicionado e, retorna a manutenção"),
            @ApiResponse(responseCode = "404", description = "Aeronave não encontrada"),
            @ApiResponse(responseCode = "500", description = "Aeronave não disponivel")
    })
    @PostMapping
    public ResponseEntity<Object> addNewManutencao(@RequestBody AtividadeManutencaoEntity manutencaoEntity) {
        AeronaveEntity aeronave = aeronaveService.getAeronaveById(manutencaoEntity.getAeronaveid());
        if (aeronave == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aeronave não encontrado");
        //verificar disponibilidade de aeronave
        Boolean disponibilidade = gestaoVooMockService.verificarDisponibilidade(aeronave.getMatricula());
        if(!disponibilidade) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Aeronave não disponivel");
        }
        AtividadeManutencaoEntity manutencao = new AtividadeManutencaoEntity(
                manutencaoEntity.getTipoManutencao(),
                manutencaoEntity.getDescricao(),
                manutencaoEntity.getStatusManutencao(),
                manutencaoEntity.getData(),
                aeronave,
                manutencaoEntity.getAeronaveid()
                );
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeManutencaoService.addNewManutencao(manutencao));
    }

    @Operation(description = "Endpoint para atualizar apenas o status e a descrição de uma manutenção")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "manutenção atualizado e, retorna a manutenção"),
            @ApiResponse(responseCode = "404", description = "Manutenção não encontrada"),
            @ApiResponse(responseCode = "500", description = "Aeronave não disponivel")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateManutencao(@PathVariable("id") Long id, @RequestBody AtividadeManutencaoEntity manutencaoEntity) {
        AtividadeManutencaoEntity atividadeManutencao = atividadeManutencaoService.getManutencaoById(id);
        if(atividadeManutencao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atividade manutenção com o id "+id+" não encontrada");
        }

        if (manutencaoEntity.getStatusManutencao() != null) {
            atividadeManutencao.setStatusManutencao(manutencaoEntity.getStatusManutencao());
        }
        if (manutencaoEntity.getDescricao() != null) {
            atividadeManutencao.setDescricao(manutencaoEntity.getDescricao());
        }

        return ResponseEntity.status(HttpStatus.OK).body(atividadeManutencaoService.updateManutencao(atividadeManutencao));
    }

    @Operation(description = "Endpoint para deletar uma manutenção")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Manutenção deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Manutenção não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar. A manutenção está referenciada a uma tarefa")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteManutencao(@PathVariable("id") Long idManutencao) {
        ResponseEntity<Object> manutencao = this.getManutencaoById(idManutencao);
        if (manutencao.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manutenção com o id " + idManutencao + " não existe");
        }

        //verificar se a atividade manutencao esta referenciada em alguma tarefa
        List<RegistoTarefaEntity> tarefaList = registoTarefaService.getAllRegistoTarefaByManutencaoId(idManutencao);
        if(!tarefaList.isEmpty()) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao deletar. A manutenção está referenciada a uma tarefa");

        atividadeManutencaoService.deleteManutencao(idManutencao);
        return ResponseEntity.status(HttpStatus.OK).body("Manutenção deletada com sucesso");
    }


}
