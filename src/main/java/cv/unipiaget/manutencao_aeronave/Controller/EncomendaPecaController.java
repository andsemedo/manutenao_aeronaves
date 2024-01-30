package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.EncomendaPecaEntity;
import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import cv.unipiaget.manutencao_aeronave.Enums.EncomendaStatus;
import cv.unipiaget.manutencao_aeronave.Services.EncomendaPecaService;
import cv.unipiaget.manutencao_aeronave.Services.PecaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manutencao/encomendapecas")
public class EncomendaPecaController {

    private final EncomendaPecaService encomendaPecaService;
    private final PecaService pecaService;


    public EncomendaPecaController(EncomendaPecaService encomendaPecaService, PecaService pecaService) {
        this.encomendaPecaService = encomendaPecaService;
        this.pecaService = pecaService;
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
    public ResponseEntity<Object> salvarEncomendaPeca(@RequestBody EncomendaPecaEntity encomendaPeca) {
        Optional<PecaEntity> pecaOptional = pecaService.obterPecaPorId(encomendaPeca.getIdPeca());
        if (pecaOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Peca não encontrado");
        if (encomendaPeca.getStatus() == EncomendaStatus.ENTREGUE) {
            //atualizar quantidade de peca
            PecaEntity peca = pecaOptional.get();
            int stock = peca.getQuantidade();
            int new_stock = encomendaPeca.getQuantidade() + stock;
            peca.setQuantidade(new_stock);
            pecaService.updateEstoque(peca);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(encomendaPecaService.salvarEncomendaPeca(encomendaPeca));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Object> atualizarStatusEncomendaPeca(@PathVariable("id") int id,@RequestBody EncomendaPecaEntity encomendaPeca) {
        Optional<EncomendaPecaEntity> encomendaOptional = encomendaPecaService.obterEncomendaPecaPorId(id);
        if (encomendaOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Encomenda não encontrada");
        EncomendaPecaEntity encomenda = encomendaOptional.get();
        encomenda.setStatus(encomendaPeca.getStatus());
        return ResponseEntity.status(HttpStatus.OK).body(encomendaPecaService.atualizarStatus(encomenda));
    }


    @DeleteMapping("/excluir/{id}")
    public void excluirEncomendaPeca(@PathVariable int id) {
        encomendaPecaService.excluirEncomendaPeca(id);
    }


}