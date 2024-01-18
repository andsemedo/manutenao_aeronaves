package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
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
@RequestMapping(path = "/api/manutencao")
public class AtividadeManutencaoController {

    public final AtividadeManutencaoService atividadeManutencaoService;

    public AtividadeManutencaoController(AtividadeManutencaoService atividadeManutencaoService) {
        this.atividadeManutencaoService = atividadeManutencaoService;
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
        return atividadeManutencaoService.addNewManutencao(manutencaoEntity);
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
