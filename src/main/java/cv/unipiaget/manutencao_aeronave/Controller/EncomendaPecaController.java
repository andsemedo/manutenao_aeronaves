package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.EncomendaPecaEntity;
import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import cv.unipiaget.manutencao_aeronave.Enums.EncomendaStatus;
import cv.unipiaget.manutencao_aeronave.Services.EncomendaPecaService;
import cv.unipiaget.manutencao_aeronave.Services.PecaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Endpoint que retorna uma lista de encomenda peças")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna uma lista de encomenda peças"),
            @ApiResponse(responseCode = "404", description = "Nenhuma encomenda peças encontrada")
    })
    @GetMapping
    public ResponseEntity<Object> listarTodasEncomendasPecas() {
        List<EncomendaPecaEntity> encomendaPecaList = encomendaPecaService.listarTodasEncomendasPecas();
        if (encomendaPecaList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Encomenda Peça não encontrado");

        return ResponseEntity.status(HttpStatus.OK).body(encomendaPecaList);
    }

    @Operation(description = "Endpoint que retorna uma encomenda peça por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna a encomenda procurada"),
            @ApiResponse(responseCode = "404", description = "Encomenda peça não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> obterEncomendaPecaPorId(@PathVariable("id") int id) {
        EncomendaPecaEntity encomendaPeca = encomendaPecaService.obterEncomendaPecaPorId(id);
        if (encomendaPeca == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Encomenda Peca não encontrada");
        return ResponseEntity.status(HttpStatus.OK).body(encomendaPeca);
    }


    @Operation(description = "Endpoint que cria uma encomenda peça")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "encomenda peças adicionado, retorna a encomenda"),
            @ApiResponse(responseCode = "404", description = "Peça não encontrada")
    })
    @PostMapping
    public ResponseEntity<Object> salvarEncomendaPeca(@RequestBody EncomendaPecaEntity encomendaPeca) {
        Optional<PecaEntity> pecaOptional = pecaService.obterPecaPorId(encomendaPeca.getIdPeca());
        if (pecaOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Peça não encontrado");
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

    @Operation(description = "Endpoint que atualiza o status de uma encomenda peça")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status atualizado, retorna a encomenda"),
            @ApiResponse(responseCode = "404", description = "Encomenda não encontrada"),
    })
    @PutMapping("/status/{id}")
    public ResponseEntity<Object> atualizarStatusEncomendaPeca(@PathVariable("id") int id,@RequestBody EncomendaPecaEntity encomendaPeca) {
        EncomendaPecaEntity encomenda = encomendaPecaService.obterEncomendaPecaPorId(id);
        if (encomenda == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Encomenda não encontrada");

        encomenda.setStatus(encomendaPeca.getStatus());

        if (encomendaPeca.getStatus() == EncomendaStatus.ENTREGUE) {
            //atualizar quantidade de peca
            PecaEntity peca = pecaService.obterPecaPorId(encomendaPeca.getIdPeca()).get();;
            int stock = peca.getQuantidade();
            int new_stock = encomendaPeca.getQuantidade() + stock;
            peca.setQuantidade(new_stock);
            pecaService.updateEstoque(peca);
        }

        return ResponseEntity.status(HttpStatus.OK).body(encomendaPecaService.atualizarStatus(encomenda));
    }

    @Operation(description = "Endpoint que deleta uma encomenda peça")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encomenda deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Encomenda não encontrada"),
    })
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirEncomendaPeca(@PathVariable int id) {
        EncomendaPecaEntity encomenda = encomendaPecaService.obterEncomendaPecaPorId(id);
        if (encomenda == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Encomenda não encontrada");
        encomendaPecaService.excluirEncomendaPeca(id);
        return ResponseEntity.status(HttpStatus.OK).body("Encomenda deletado com sucesso");
    }


}