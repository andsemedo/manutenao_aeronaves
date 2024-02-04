package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.Entities.RegistoTarefaEntity;
import cv.unipiaget.manutencao_aeronave.Entities.UsoPecaEntity;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeEquipaServices;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeManutencaoService;
import cv.unipiaget.manutencao_aeronave.Services.RegistoTarefaService;
import cv.unipiaget.manutencao_aeronave.Services.UsoPecaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Anderson Semedo
 * @Date 18/01/2024
 */
@RestController
@RequestMapping(path = "/api/manutencao/tarefa/")
public class RegistoTarefaController {

    private final RegistoTarefaService registoTarefaService;
    private final AtividadeManutencaoService manutencaoService;
    private final AtividadeEquipaServices equipaService;
    private final UsoPecaService usoPecaService;

    public RegistoTarefaController(RegistoTarefaService registoTarefaService, AtividadeManutencaoService manutencaoService, AtividadeEquipaServices equipaService, UsoPecaService usoPecaService) {
        this.registoTarefaService = registoTarefaService;
        this.manutencaoService = manutencaoService;
        this.equipaService = equipaService;
        this.usoPecaService = usoPecaService;
    }

    @Operation(description = "Endpoint para listar todas as tarefas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna uma lista de tarefas"),
            @ApiResponse(responseCode = "404", description = "Nenhuma tarefa encontrada")
    })
    @GetMapping
    public ResponseEntity<Object> getAllRegistoTarefa() {
        List<RegistoTarefaEntity> tarefaList = registoTarefaService.getAllRegistoTarefa().stream()
                .map(tar -> {
                    RegistoTarefaEntity tarefa = new RegistoTarefaEntity();
                    tarefa.setRegistotarefaid(tar.getRegistotarefaid());
                    tarefa.setData_execucao(tar.getData_execucao());
                    tarefa.setComentario(tar.getComentario());
                    tarefa.setAtividadeManutencao(tar.getAtividadeManutencao());
                    tarefa.setIdequipa(tar.getIdequipa());
                    return tarefa;
                })
                .collect(Collectors.toList());

        if (tarefaList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma tarefa encontrada");

        return ResponseEntity.status(HttpStatus.OK).body(tarefaList);
    }

    @Operation(description = "Endpoint para retornar tarefa por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna a tarefa procurado"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getRegistoTarefaById(@PathVariable("id") Long id) {
        RegistoTarefaEntity tarefa = registoTarefaService.getRegistoTarefaById(id);

        if(tarefa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @Operation(description = "Endpoint para adicionar uma nova tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "tarefa adicionado e, retorna a tarefa"),
            @ApiResponse(responseCode = "404", description = "Atividade manutenção não encontrada"),
            @ApiResponse(responseCode = "404", description = "Equipa não encontrada")
    })
    @PostMapping
    public ResponseEntity<Object> addNewRegistoTarefa(@RequestBody RegistoTarefaEntity tarefa) {
        //verificar se a atividade manutencao existe
        AtividadeManutencaoEntity manutencao = manutencaoService.getManutencaoById(tarefa.getAtividadeManutencao());
        if (manutencao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atividade manutenção não encontrada");
        }
        //verificar se a equipa existe
        Optional<AtividadeEquipaEntities> equipa = equipaService.getEquipaById(tarefa.getIdequipa());
        if (equipa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipa não encontrada");
        }

        tarefa.setManutencao(manutencao);
        tarefa.setEquipa(equipa.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(registoTarefaService.addNewRegistoTarefa(tarefa));

    }

    @Operation(description = "Endpoint para atualizar apenas a data execução, comentario e id_equipa de uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "tarefa atualizado e, retorna a tarefa"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Equipa não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRegistoTarefa(@PathVariable(name = "id") Long id, @RequestBody RegistoTarefaEntity tarefaEntity) {
        RegistoTarefaEntity tarefa = registoTarefaService.getRegistoTarefaById(id);
        if (tarefa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registo tarefa com id "+id+" não encontrado");
        }
        tarefa.setData_execucao(tarefaEntity.getData_execucao());
        tarefa.setComentario(tarefaEntity.getComentario());
        //ver se a equipa existe
        Optional<AtividadeEquipaEntities> equipa = equipaService.getEquipaById(tarefa.getIdequipa());
        if (equipa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipa não encontrada");
        }
        tarefa.setIdequipa(tarefaEntity.getIdequipa());

        return ResponseEntity.status(HttpStatus.OK).body(registoTarefaService.updateRegistoTarefa(tarefa));
    }

    @Operation(description = "Endpoint para deletar uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Manutenção deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Manutenção não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar. A manutenção está referenciada a uma tarefa")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegistoTarefa(@PathVariable(name = "id") Long id) {
        RegistoTarefaEntity tarefa = registoTarefaService.getRegistoTarefaById(id);
        if(tarefa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }

        //verificar se a tarefa esta referenciada em algum uso peça
        List<UsoPecaEntity> usoPecaList = usoPecaService.getAllUsoPecaByTarefa(id);
        if(!usoPecaList.isEmpty()) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao deletar. A tarefa está referenciada a um uso peca");

        registoTarefaService.deleteRegistoTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada com sucesso");

    }

}
