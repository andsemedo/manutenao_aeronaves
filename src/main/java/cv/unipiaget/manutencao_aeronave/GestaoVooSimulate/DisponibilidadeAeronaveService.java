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
public class DisponibilidadeAeronaveService {

    private final DisponibilidadeAeronaveRepository aeronaveRepository;

    public DisponibilidadeAeronaveService(DisponibilidadeAeronaveRepository aeronaveRepository) {
        this.aeronaveRepository = aeronaveRepository;
    }

    public List<DisponibilidadeAeronaveEntity> getAllAeronaves() {
        return aeronaveRepository.findAll();
    }
    public ResponseEntity<Object> verificarDisponibilidade(String matricula) {
        DisponibilidadeAeronaveEntity aeronaveEntity = aeronaveRepository.findByMatricula(matricula);
        if(aeronaveEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aeronave não encontrado");
        }


        if(aeronaveEntity.getDisponibilidade() == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }

        return ResponseEntity.status(HttpStatus.OK).body(false);

    }

}
