package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import cv.unipiaget.manutencao_aeronave.Entities.RegistoTarefaEntity;
import cv.unipiaget.manutencao_aeronave.Entities.UsoPecaEntity;
import cv.unipiaget.manutencao_aeronave.Services.PecaService;
import cv.unipiaget.manutencao_aeronave.Services.RegistoTarefaService;
import cv.unipiaget.manutencao_aeronave.Services.UsoPecaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Anderson Semedo
 * @Date 25/01/2024
 */
@RestController
@RequestMapping("/api/manutencao/usopeca")
public class UsoPecaController {
    private final UsoPecaService usoPecaService;
    private final RegistoTarefaService tarefaService;
    private final PecaService pecaService;

    public UsoPecaController(UsoPecaService usoPecaService, RegistoTarefaService tarefaService, PecaService pecaService) {
        this.usoPecaService = usoPecaService;
        this.tarefaService = tarefaService;
        this.pecaService = pecaService;
    }

    @GetMapping
    public List<UsoPecaEntity> getAllUsoPeca() {return usoPecaService.getAllUsoPeca();}

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsoPecaById(@PathVariable("id") Long id) {
        UsoPecaEntity usoPeca = usoPecaService.getUsoPecaById(id);
        if(usoPeca == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Uso peca não encontrado");

        return ResponseEntity.status(HttpStatus.OK).body(usoPeca);
    }

    @PostMapping
    public ResponseEntity<Object> addNewUsoPeca(@RequestBody UsoPecaEntity usoPeca) {
        //procurar se o registo tarefa existe
        RegistoTarefaEntity tarefa = tarefaService.getRegistoTarefaById(usoPeca.getTarefaId());
        if (tarefa == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registo tarefa não encontrado");
        //procurar se peca existe
        Optional<PecaEntity> pecaOptional = pecaService.obterPecaPorId(usoPeca.getPecaId());
        if (pecaOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Peca não encontrado");
        PecaEntity peca = pecaOptional.get();
        //verificar se a peca está disponivel em estoque
        int quantidadePecaDispo = peca.getQuantidade();
        if (usoPeca.getQuantidade() > quantidadePecaDispo) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estoque de peça não disponivel");
        }
        //atualizar estoque
        int novoEstoque = quantidadePecaDispo - usoPeca.getQuantidade();
        peca.setQuantidade(novoEstoque);
        pecaService.updateEstoque(peca);

        usoPeca.setRegistoTarefa(tarefa);
        usoPeca.setPeca(peca);

        return ResponseEntity.status(HttpStatus.CREATED).body(usoPecaService.addNewUsoPeca(usoPeca));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsoPeca(@PathVariable("id") Long id, @RequestBody UsoPecaEntity usoPeca) {
        UsoPecaEntity usoPecaEntity = usoPecaService.getUsoPecaById(id);
        if (usoPecaEntity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Uso Peca não encontrado");

        usoPecaEntity.setQuantidade(usoPeca.getQuantidade());

        return ResponseEntity.status(HttpStatus.OK).body(usoPecaService.updateUsoPeca(usoPecaEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsoPeca(@PathVariable("id") Long id) {
        UsoPecaEntity usoPeca = usoPecaService.getUsoPecaById(id);
        if (usoPeca == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Uso Peca não encontrado");

        usoPecaService.deleteUsoPeca(id);
        return ResponseEntity.status(HttpStatus.OK).body("Peca deletado com sucesso");

    }

}
