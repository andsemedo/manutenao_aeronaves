package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AeronaveEntity;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.GestaoVooSimulate.GestaoVooMockService;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeManutencaoRepository;
import cv.unipiaget.manutencao_aeronave.Services.AeronaveService;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeManutencaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */
@RestController
@RequestMapping(path = "/api/manutencao/atividade/")
public class AtividadeManutencaoController {

    public final AtividadeManutencaoService atividadeManutencaoService;
    public final AtividadeManutencaoRepository atividadeManutencaoRepository;
    private final GestaoVooMockService gestaoVooMockService;
    private final AeronaveService aeronaveService;

    public AtividadeManutencaoController(AtividadeManutencaoService atividadeManutencaoService, AtividadeManutencaoRepository atividadeManutencaoRepository, GestaoVooMockService gestaoVooMockService, AeronaveService aeronaveService) {
        this.atividadeManutencaoService = atividadeManutencaoService;
        this.atividadeManutencaoRepository = atividadeManutencaoRepository;
        this.gestaoVooMockService = gestaoVooMockService;
        this.aeronaveService = aeronaveService;
    }

    @GetMapping
    public List<AtividadeManutencaoEntity> getAllManutencao() {
        return atividadeManutencaoService.getAllManutencao();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getManutencaoById(@PathVariable("id") Long id) {
        AtividadeManutencaoEntity manutencao = atividadeManutencaoService.getManutencaoById(id);
        if (manutencao != null) {
            return ResponseEntity.ok(manutencao);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manutenção com o id " + id + " não existe");
    }

    @GetMapping("aeronave/{id}/historico")
    public ResponseEntity<Object> getHistoricoManutencaoByAeronave(@PathVariable("id") Long id) {
        AeronaveEntity aeronave = aeronaveService.getAeronaveById(id);
        if (aeronave == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aeronave não encontrado");
        }

        List<AtividadeManutencaoEntity> manutencaoList = atividadeManutencaoService.getHistoricoByAeronave(id).stream()
                .map(man -> {
                    AtividadeManutencaoEntity manutencao = new AtividadeManutencaoEntity(
                        man.getManutencaoid(),
                        man.getTipoManutencao(),
                        man.getDescricao(),
                        man.getStatusManutencao(),
                        man.getData()
                    );
                    return manutencao;
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(manutencaoList);
    }

    @PostMapping
    public ResponseEntity<Object> addNewManutencao(@RequestBody AtividadeManutencaoEntity manutencaoEntity) {
        AeronaveEntity aeronave = aeronaveService.getAeronaveById(manutencaoEntity.getAeronaveid());
        if (aeronave == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aeronave não encontrado");
        //verificar disponibilidade de aeronave
        Boolean disponibilidade = gestaoVooMockService.verificarDisponibilidade(aeronave.getMatricula());
        if(!disponibilidade) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aeronave não disponivel");
        }
        AtividadeManutencaoEntity manutencao = new AtividadeManutencaoEntity(
                manutencaoEntity.getTipoManutencao(),
                manutencaoEntity.getDescricao(),
                manutencaoEntity.getStatusManutencao(),
                manutencaoEntity.getData(),
                aeronave,
                manutencaoEntity.getAeronaveid()
                );
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeManutencaoService.addNewManutencao(manutencao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateManutencao(@PathVariable("id") Long id, @RequestBody AtividadeManutencaoEntity manutencaoEntity) {
        AtividadeManutencaoEntity atividadeManutencao = atividadeManutencaoService.getManutencaoById(id);
        if(atividadeManutencao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atividade manutenção com o id "+id+" não encontrada");
        }

        if (manutencaoEntity.getStatusManutencao() != null) {
            atividadeManutencao.setStatusManutencao(manutencaoEntity.getStatusManutencao());
        }
        if (manutencaoEntity.getDescricao() != null) {
            atividadeManutencao.setDescricao(manutencaoEntity.getDescricao());
        }

        return ResponseEntity.status(HttpStatus.OK).body(atividadeManutencaoService.updateManutencao(atividadeManutencao));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteManutencao(@PathVariable("id") Long idManutencao) {
        ResponseEntity<Object> manutencao = this.getManutencaoById(idManutencao);
        if (manutencao.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manutenção com o id " + idManutencao + " não existe");
        }
        atividadeManutencaoService.deleteManutencao(idManutencao);
        return ResponseEntity.status(HttpStatus.OK).body("Manutenção deletada com sucesso");
    }


}
