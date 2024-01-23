package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.GestaoVooSimulate.GestaoVooMockService;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeManutencaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */
@RestController
@RequestMapping(path = "/api/manutencao/atividade/")
public class AtividadeManutencaoController {

    public final AtividadeManutencaoService atividadeManutencaoService;
    private final GestaoVooMockService gestaoVooMockService;

    public AtividadeManutencaoController(AtividadeManutencaoService atividadeManutencaoService, GestaoVooMockService gestaoVooMockService) {
        this.atividadeManutencaoService = atividadeManutencaoService;
        this.gestaoVooMockService = gestaoVooMockService;
    }

    @GetMapping
    public List<AtividadeManutencaoEntity> getAllManutencao() {
        return atividadeManutencaoService.getAllManutencao();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getManutencaoById(@PathVariable("id") Long id) {
        AtividadeManutencaoEntity manutencao = atividadeManutencaoService.getManutencaoById(id);
        if (manutencao != null) {
            return ResponseEntity.ok(manutencao);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manutenção com o id " + id + " não existe");
    }

    @PostMapping
    public ResponseEntity<Object> addNewManutencao(@RequestBody AtividadeManutencaoEntity manutencaoEntity) {
        //verificar disponibilidade de aeronave
        Boolean disponibilidade = gestaoVooMockService.verificarDisponibilidade(manutencaoEntity.getIdAeronave());
        if(!disponibilidade) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aeronave não disponivel");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeManutencaoService.addNewManutencao(manutencaoEntity));
    }

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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteManutencao(@PathVariable("id") Long idManutencao) {
        ResponseEntity<Object> manutencao = this.getManutencaoById(idManutencao);
        if (manutencao.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manutenção com o id " + idManutencao + " não existe");
        }
        atividadeManutencaoService.deleteManutencao(idManutencao);
        return ResponseEntity.status(HttpStatus.OK).body("Manutenção deletada com sucesso");
    }


}
