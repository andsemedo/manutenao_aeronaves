/**
 * @author Silvino Gomes
 * @Date 22/01/2024
 */

package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.EncomendaPecaEntity;
import cv.unipiaget.manutencao_aeronave.Services.EncomendaPecaService;
import cv.unipiaget.manutencao_aeronave.recursos.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/encomendas-pecas")
public class EncomendaPecaController {

    private final EncomendaPecaService encomendaPecaService;

    @Autowired
    public EncomendaPecaController(EncomendaPecaService encomendaPecaService) {
        this.encomendaPecaService = encomendaPecaService;
    }

    @GetMapping
    public ResponseEntity<List<EncomendaPecaEntity>> listarEncomendas() {
        List<EncomendaPecaEntity> encomendas = encomendaPecaService.listarTodas();
        return new ResponseEntity<>(encomendas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterEncomendaPorId(@PathVariable int id) {

        Optional<EncomendaPecaEntity> encomendaExistente = encomendaPecaService.obterPorId(id);

        if (encomendaExistente.isPresent()) {
            return encomendaExistente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            String mensagem = "Encomenda com ID " + id + " não encontrada.";
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, mensagem);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<EncomendaPecaEntity> adicionarEncomenda(@RequestBody EncomendaPecaEntity encomenda) {
        EncomendaPecaEntity novaEncomenda = encomendaPecaService.adicionarEncomenda(encomenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEncomenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEncomenda(@PathVariable int id, @RequestBody EncomendaPecaEntity encomenda) {

        Optional<EncomendaPecaEntity> encomendaExistente = encomendaPecaService.obterPorId(id);

        if (encomendaExistente.isPresent()) {
            encomenda.getIdEncomendaPeca(id);
            EncomendaPecaEntity encomendaAtualizada = encomendaPecaService.atualizarEncomenda(id, encomenda);
            String mensagem = "Encomenda com ID " + id + " atualizada com sucesso.";
            return ResponseEntity.ok().body(mensagem);
        } else {
            String mensagem = "Encomenda com ID " + id + " não encontrada. A atualização não pode ser realizada.";
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, mensagem);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirEncomenda(@PathVariable int id) {

        Optional<EncomendaPecaEntity> encomendaExistente = encomendaPecaService.obterPorId(id);

        if (encomendaExistente.isPresent()) {
            encomendaPecaService.excluirEncomenda(id);
            String mensagem = "Encomenda com ID " + id + " excluída com sucesso.";
            return ResponseEntity.ok().body(mensagem);
        } else {
            String mensagem = "Encomenda com ID " + id + " não encontrada.";
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, mensagem);
            return ResponseEntity.ok().body(mensagem);
        }


    }

}