package cv.unipiaget.manutencao_aeronave.Controller;


/**
 * @author Silvino Gomes
 * @Date 17/01/2024
 */

import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import cv.unipiaget.manutencao_aeronave.Entities.UsoPecaEntity;
import cv.unipiaget.manutencao_aeronave.Services.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/obter/{id}")
    public ResponseEntity<PecaEntity> obterPecaPorId(@PathVariable int id) {
        Optional<PecaEntity> peca = pecaService.obterPecaPorId(id);
        return peca.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PecaEntity> salvarPeca(@RequestBody PecaEntity peca) {
        PecaEntity pecaSalva = pecaService.salvarPeca(peca);
        return ResponseEntity.ok(pecaSalva);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirPeca(@PathVariable int id) {
        pecaService.excluirPeca(id);
        return ResponseEntity.noContent().build();
    }



    // Pecas em uso
    // - /api/pecas/pecaUso

    @GetMapping("/pecaUso")
    public List<UsoPecaEntity> listarPecasEmUso() {
        return pecaService.listarPecasEmUso();
    }
}
