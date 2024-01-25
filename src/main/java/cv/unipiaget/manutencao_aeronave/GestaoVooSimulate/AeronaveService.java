package cv.unipiaget.manutencao_aeronave.GestaoVooSimulate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */
@Service
public class AeronaveService {

    private final AeronaveRepository aeronaveRepository;

    public AeronaveService(AeronaveRepository aeronaveRepository) {
        this.aeronaveRepository = aeronaveRepository;
    }

    public List<AeronaveEntity> getAllAeronaves() {
        return aeronaveRepository.findAll();
    }
    public ResponseEntity<Object> verificarDisponibilidade(Long idAeronave) {
        Optional<AeronaveEntity> aeronaveEntity = aeronaveRepository.findById(idAeronave);
        if(aeronaveEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aeronave não encontrado");
        }

        AeronaveEntity aeronave = aeronaveEntity.get();

        if(aeronave.getDisponibilidade() == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }

        return ResponseEntity.status(HttpStatus.OK).body(false);

    }

}
