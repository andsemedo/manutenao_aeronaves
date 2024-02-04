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
public class DisponibilidadeAeronaveController {

    private final DisponibilidadeAeronaveService aeronaveService;

    public DisponibilidadeAeronaveController(DisponibilidadeAeronaveService aeronaveService) {
        this.aeronaveService = aeronaveService;
    }

    @GetMapping
    public List<DisponibilidadeAeronaveEntity> getAllAeronave() {
        return aeronaveService.getAllAeronaves();
    }

    @GetMapping("/disponibilidade/{matricula}")
    public ResponseEntity<Object> verificarDisponibilidade(@PathVariable(value = "matricula") String matricula) {
        return aeronaveService.verificarDisponibilidade(matricula);
    }

}
