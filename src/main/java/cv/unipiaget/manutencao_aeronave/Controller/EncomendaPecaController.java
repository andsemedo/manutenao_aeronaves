package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.EncomendaPecaEntity;
import cv.unipiaget.manutencao_aeronave.Services.EncomendaPecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/encomendaspecas")
public class EncomendaPecaController {

    private final EncomendaPecaService encomendaPecaService;

    @Autowired
    public EncomendaPecaController(EncomendaPecaService encomendaPecaService) {
        this.encomendaPecaService = encomendaPecaService;
    }

    @GetMapping
    public List<EncomendaPecaEntity> listarTodasEncomendasPecas() {
        return encomendaPecaService.listarTodasEncomendasPecas();
    }

    /*
    @GetMapping("/{id}")
    public EncomendaPecaEntity obterEncomendaPecaPorId(@PathVariable int id) {
        return encomendaPecaService.obterEncomendaPecaPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("EncomendaPecaEntity", "id", id));
    }
     */

    @PostMapping
    public EncomendaPecaEntity salvarEncomendaPeca(@RequestBody EncomendaPecaEntity encomendaPeca) {
        return encomendaPecaService.salvarEncomendaPeca(encomendaPeca);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirEncomendaPeca(@PathVariable int id) {
        encomendaPecaService.excluirEncomendaPeca(id);
    }


}