package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import cv.unipiaget.manutencao_aeronave.Entities.RegistoTarefaEntity;
import cv.unipiaget.manutencao_aeronave.Entities.UsoPecaEntity;
import cv.unipiaget.manutencao_aeronave.Services.PecaService;
import cv.unipiaget.manutencao_aeronave.Services.RegistoTarefaService;
import cv.unipiaget.manutencao_aeronave.Services.UsoPecaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Anderson Semedo
 * @Date 25/01/2024
 */
@RestController
@RequestMapping("/api/manutencao/usopeca")
public class UsoPecaController {
    private final UsoPecaService usoPecaService;
    private final RegistoTarefaService tarefaService;
    private final PecaService pecaService;

    public UsoPecaController(UsoPecaService usoPecaService, RegistoTarefaService tarefaService, PecaService pecaService) {
        this.usoPecaService = usoPecaService;
        this.tarefaService = tarefaService;
        this.pecaService = pecaService;
    }

    @Operation(description = "Endpoint que retorna todas os uso peças")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna uma lista de uso peças"),
            @ApiResponse(responseCode = "404", description = "Nenhum uso peça encontrada")
    })
    @GetMapping
    public ResponseEntity<Object> getAllUsoPeca() {
        List<UsoPecaEntity> usoPecaList = usoPecaService.getAllUsoPeca();
        if(usoPecaList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum uso peça encontrado");
        return ResponseEntity.status(HttpStatus.OK).body(usoPecaList);
    }

    @Operation(description = "Endpoint que retorna um uso peça por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna o uso de peça procurado"),
            @ApiResponse(responseCode = "404", description = "Uso peca não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsoPecaById(@PathVariable("id") Long id) {
        UsoPecaEntity usoPeca = usoPecaService.getUsoPecaById(id);
        if(usoPeca == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Uso peca não encontrado");

        return ResponseEntity.status(HttpStatus.OK).body(usoPeca);
    }

    @Operation(description = "Endpoint que adiciona um uso peça")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "uso de peça adicionado, retorna o uso de peça"),
            @ApiResponse(responseCode = "404", description = "Registo tarefa não encontrado"),
            @ApiResponse(responseCode = "404", description = "Peca não encontrado"),
            @ApiResponse(responseCode = "500", description = "Estoque de peça não disponivel")
    })
    @PostMapping
    public ResponseEntity<Object> addNewUsoPeca(@RequestBody UsoPecaEntity usoPeca) {
        //procurar se o registo tarefa existe
        RegistoTarefaEntity tarefa = tarefaService.getRegistoTarefaById(usoPeca.getTarefaId());
        if (tarefa == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registo tarefa não encontrado");
        //procurar se peca existe
        Optional<PecaEntity> pecaOptional = pecaService.obterPecaPorId(usoPeca.getPecaId());
        if (pecaOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Peca não encontrado");
        PecaEntity peca = pecaOptional.get();
        //verificar se a peca está disponivel em estoque
        int quantidadePecaDispo = peca.getQuantidade();
        if (usoPeca.getQuantidade() > quantidadePecaDispo) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Estoque de peça não disponivel");
        }
        //atualizar estoque
        int novoEstoque = quantidadePecaDispo - usoPeca.getQuantidade();
        peca.setQuantidade(novoEstoque);
        pecaService.updateEstoque(peca);

        usoPeca.setRegistoTarefa(tarefa);
        usoPeca.setPeca(peca);

        return ResponseEntity.status(HttpStatus.CREATED).body(usoPecaService.addNewUsoPeca(usoPeca));
    }

    @Operation(description = "Endpoint que atualiza a quantidade de peça no uso peça")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "uso de peça atualizado, retorna o uso de peça"),
            @ApiResponse(responseCode = "404", description = "Uso Peça não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsoPeca(@PathVariable("id") Long id, @RequestBody UsoPecaEntity usoPeca) {
        UsoPecaEntity usoPecaEntity = usoPecaService.getUsoPecaById(id);
        if (usoPecaEntity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Uso Peca não encontrado");

        //procurar se peca existe
        Optional<PecaEntity> pecaOptional = pecaService.obterPecaPorId(usoPecaEntity.getPecaId());
        if (pecaOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Peca não encontrado");
        PecaEntity peca = pecaOptional.get();
        //verificar se a peca está disponivel em estoque
        int quantidadePecaDispo = peca.getQuantidade();
        peca.setQuantidade(quantidadePecaDispo + usoPecaEntity.getQuantidade());
        if (usoPeca.getQuantidade() > quantidadePecaDispo) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Estoque de peça não disponivel");
        }
        //atualizar estoque
        int novoEstoque = peca.getQuantidade() - usoPeca.getQuantidade();
        peca.setQuantidade(novoEstoque);
        pecaService.updateEstoque(peca);

        usoPecaEntity.setQuantidade(usoPeca.getQuantidade());

        return ResponseEntity.status(HttpStatus.OK).body(usoPecaService.updateUsoPeca(usoPecaEntity));
    }

    @Operation(description = "Endpoint que atualiza a quantidade de peça no uso peça")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "uso de peça atualizado, retorna o uso de peça"),
            @ApiResponse(responseCode = "404", description = "Uso Peça não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsoPeca(@PathVariable("id") Long id) {
        UsoPecaEntity usoPeca = usoPecaService.getUsoPecaById(id);
        if (usoPeca == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Uso Peca não encontrado");

        usoPecaService.deleteUsoPeca(id);
        return ResponseEntity.status(HttpStatus.OK).body("Peca deletado com sucesso");

    }

}
