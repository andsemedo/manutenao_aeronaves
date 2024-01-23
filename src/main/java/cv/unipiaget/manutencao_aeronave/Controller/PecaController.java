package cv.unipiaget.manutencao_aeronave.Controller;


/**
 * @author Silvino Gomes
 * @Date 17/01/2024
 */

import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import cv.unipiaget.manutencao_aeronave.Services.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pecas")
public class PecaController {

    private final PecaService pecaService;

    @Autowired
    public PecaController(PecaService pecaService) {
        this.pecaService = pecaService;
    }

    @GetMapping
    public ResponseEntity<List<PecaEntity>> listarTodasPecas() {
        List<PecaEntity> pecas = pecaService.listarTodasPecas();
        return ResponseEntity.ok(pecas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PecaEntity> obterPeca(@PathVariable int id) {
        Optional<PecaEntity> peca = pecaService.obterPecaPorId(id);
        return peca.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PecaEntity> adicionarPeca(@RequestBody PecaEntity peca) {
        try {
            // guardar na base de dados
            PecaEntity pecaSalva = pecaService.salvarPeca(peca);

            return ResponseEntity.status(HttpStatus.CREATED).body(pecaSalva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<PecaEntity> atualizarPeca(@PathVariable int id, @RequestBody PecaEntity peca) {
        Optional<PecaEntity> pecaExistente = pecaService.obterPecaPorId(id);
        if (pecaExistente.isPresent()) {
            peca.setId(id);
            PecaEntity pecaAtualizada = pecaService.salvarPeca(peca);
            return ResponseEntity.ok(pecaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPeca(@PathVariable int id) {
        pecaService.excluirPeca(id);
        return ResponseEntity.noContent().build();
    }

}
