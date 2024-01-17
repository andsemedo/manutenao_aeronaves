package cv.unipiaget.manutencao_aeronave.GestaoVooSimulate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */
@RestController
@RequestMapping(path = "/api/voos/aeronave")
public class AeronaveController {

    private final AeronaveService aeronaveService;

    public AeronaveController(AeronaveService aeronaveService) {
        this.aeronaveService = aeronaveService;
    }

    @GetMapping
    public List<AeronaveEntity> getAllAeronave() {
        return aeronaveService.getAllAeronaves();
    }

    @GetMapping("/disponibilidade/{id}")
    public ResponseEntity<Object> verificarDisponibilidade(@PathVariable(value = "id") Long idAeronave) {
        return aeronaveService.verificarDisponibilidade(idAeronave);
    }
}
