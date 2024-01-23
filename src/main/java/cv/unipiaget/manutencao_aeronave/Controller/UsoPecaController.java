/**
 * @author Silvino Gomes
 * @Date 22/01/2024
 */


package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.UsoPecaEntity;
import cv.unipiaget.manutencao_aeronave.Services.UsoPecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usopecas")
public class UsoPecaController {

    private final UsoPecaService usoPecaService;

    @Autowired
    public UsoPecaController(UsoPecaService usoPecaService) {
        this.usoPecaService = usoPecaService;
    }

    @GetMapping
    public ResponseEntity<List<UsoPecaEntity>> listarTodos() {
        List<UsoPecaEntity> usoPecas = usoPecaService.listarTodos();
        return new ResponseEntity<>(usoPecas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsoPecaEntity> obterPorId(@PathVariable Long id) {
        UsoPecaEntity usoPeca = usoPecaService.obterPorId(id);
        return new ResponseEntity<>(usoPeca, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsoPecaEntity> adicionarUsoPeca(@RequestBody UsoPecaEntity usoPeca) {
        UsoPecaEntity novoUsoPeca = usoPecaService.adicionarUsoPeca(usoPeca);
        return new ResponseEntity<>(novoUsoPeca, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsoPecaEntity> atualizarUsoPeca(@PathVariable Long id, @RequestBody UsoPecaEntity usoPeca) {
        UsoPecaEntity usoPecaAtualizado = usoPecaService.atualizarUsoPeca(id, usoPeca);
        return new ResponseEntity<>(usoPecaAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsoPeca(@PathVariable Long id) {
        usoPecaService.excluirUsoPeca(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}