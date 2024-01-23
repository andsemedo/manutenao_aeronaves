/**
 * @author Silvino Gomes
 * @Date 22/01/2024
 */

package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.EncomendaPecaEntity;
import cv.unipiaget.manutencao_aeronave.Services.EncomendaPecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<EncomendaPecaEntity> obterEncomendaPorId(@PathVariable int id) {
        EncomendaPecaEntity encomenda = encomendaPecaService.obterPorId(id);
        if (encomenda != null) {
            return new ResponseEntity<>(encomenda, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<EncomendaPecaEntity> adicionarEncomenda(@RequestBody EncomendaPecaEntity encomenda) {
        EncomendaPecaEntity novaEncomenda = encomendaPecaService.adicionarEncomenda(encomenda);
        return new ResponseEntity<>(novaEncomenda, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EncomendaPecaEntity> atualizarEncomenda(@PathVariable int id, @RequestBody EncomendaPecaEntity encomenda) {
        EncomendaPecaEntity encomendaAtualizada = encomendaPecaService.atualizarEncomenda(id, encomenda);
        if (encomendaAtualizada != null) {
            return new ResponseEntity<>(encomendaAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEncomenda(@PathVariable int id) {
        encomendaPecaService.excluirEncomenda(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}