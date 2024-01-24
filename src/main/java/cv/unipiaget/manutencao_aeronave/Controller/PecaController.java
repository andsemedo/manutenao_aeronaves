package cv.unipiaget.manutencao_aeronave.Controller;


/**
 * @author Silvino Gomes
 * @Date 17/01/2024
 */

import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import cv.unipiaget.manutencao_aeronave.Services.PecaService;
import cv.unipiaget.manutencao_aeronave.recursos.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
    public HttpEntity<? extends Object> obterPeca(@PathVariable int id) {

        Optional<PecaEntity> pecaExistente = pecaService.obterPecaPorId(id);

        if (pecaExistente.isPresent()) {
            return pecaExistente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            String mensagem = "Peca com ID " + id + " não encontrada.";
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, mensagem);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<PecaEntity> adicionarPeca(@RequestBody PecaEntity peca) {
        try {
            PecaEntity pecaSalva = pecaService.salvarPeca(peca);

            return ResponseEntity.status(HttpStatus.CREATED).body(pecaSalva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPeca(@PathVariable int id, @RequestBody PecaEntity peca) {
        Optional<PecaEntity> pecaExistente = pecaService.obterPecaPorId(id);

        if (pecaExistente.isPresent()) {
            peca.setId(id);
            PecaEntity pecaAtualizada = pecaService.salvarPeca(peca);
            String mensagem = "Peca com ID " + id + " atualizada com sucesso.";
            return ResponseEntity.ok().body(mensagem);
        } else {
            String mensagem = "Peca com ID " + id + " não encontrada. A atualização não pode ser realizada.";
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, mensagem);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPeca(@PathVariable int id) {
        Optional<PecaEntity> pecaExistente = pecaService.obterPecaPorId(id);

        if (pecaExistente.isPresent()) {
            pecaService.excluirPeca(id);
            String mensagem = "Peca com ID " + id + " excluída com sucesso.";
            return ResponseEntity.ok().body(mensagem);
        } else {
            String mensagem = "Peca com ID " + id + " não encontrada.";
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, mensagem);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }



}
