package cv.unipiaget.manutencao_aeronave.Controller;


/**
 * @author Silvino Gomes
 * @Date 17/01/2024
 */

import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import cv.unipiaget.manutencao_aeronave.Services.PecaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manutencao/pecas")
public class PecaController {

    private final PecaService pecaService;

    @Autowired
    public PecaController(PecaService pecaService) {
        this.pecaService = pecaService;
    }

    @Operation(description = "Endpoint que retorna todas as peças")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna uma lista de peças"),
            @ApiResponse(responseCode = "404", description = "Nenhuma peça encontrada")
    })
    @GetMapping
    public ResponseEntity<Object> listarTodasPecas() {
        List<PecaEntity> pecaList = pecaService.listarTodasPecas();
        if (pecaList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma peça encontrada");
        return ResponseEntity.status(HttpStatus.OK).body(pecaList);
    }

    @Operation(description = "Endpoint que retorna uma peça por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna a peça procurada"),
            @ApiResponse(responseCode = "404", description = "Nenhuma peça encontrada")
    })
    @GetMapping("/obter/{id}")
    public ResponseEntity<PecaEntity> obterPecaPorId(@PathVariable int id) {
        Optional<PecaEntity> peca = pecaService.obterPecaPorId(id);
        return peca.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(description = "Endpoint que regista uma peça")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna registado, retorna a peça")
    })
    @PostMapping
    public ResponseEntity<PecaEntity> salvarPeca(@RequestBody PecaEntity peca) {
        PecaEntity pecaSalva = pecaService.salvarPeca(peca);
        return ResponseEntity.ok(pecaSalva);
    }

    @Operation(description = "Endpoint que deleta uma peça")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "peça deletado")
    })
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirPeca(@PathVariable int id) {
        pecaService.excluirPeca(id);
        return ResponseEntity.noContent().build();
    }




}
