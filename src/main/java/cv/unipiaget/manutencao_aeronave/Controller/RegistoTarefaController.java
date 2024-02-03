package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.Entities.RegistoTarefaEntity;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeEquipaServices;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeManutencaoService;
import cv.unipiaget.manutencao_aeronave.Services.RegistoTarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    public RegistoTarefaController(RegistoTarefaService registoTarefaService, AtividadeManutencaoService manutencaoService, AtividadeEquipaServices equipaService) {
        this.registoTarefaService = registoTarefaService;
        this.manutencaoService = manutencaoService;
        this.equipaService = equipaService;
    }

    @GetMapping
    public List<RegistoTarefaEntity> getAllRegistoTarefa() {
        return registoTarefaService.getAllRegistoTarefa();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRegistoTarefaById(@PathVariable("id") Long id) {
        RegistoTarefaEntity tarefa = registoTarefaService.getRegistoTarefaById(id);

        if(tarefa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegistoTarefa(@PathVariable(name = "id") Long id) {
        RegistoTarefaEntity tarefa = registoTarefaService.getRegistoTarefaById(id);
        if(tarefa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }

        registoTarefaService.deleteRegistoTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada com sucesso");

    }

}
