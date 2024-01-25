package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AeronaveEntity;
import cv.unipiaget.manutencao_aeronave.Repository.AeronaveRepository;
import cv.unipiaget.manutencao_aeronave.Services.AeronaveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Anderson Semedo
 * @Date 23/01/2024
 */
@RestController
@RequestMapping("/api/manutencao/aeronave")
public class AeronaveController {
    private final AeronaveService aeronaveService;
    private final AeronaveRepository aeronaveRepository;

    public AeronaveController(AeronaveService aeronaveService, AeronaveRepository aeronaveRepository) {
        this.aeronaveService = aeronaveService;
        this.aeronaveRepository = aeronaveRepository;
    }

    @GetMapping
    public List<AeronaveEntity> getAllAeronave() {
        return aeronaveService.getAllAeronave();
    }

    @PostMapping
    public ResponseEntity<Object> addNewAeronave(@RequestBody @Valid AeronaveEntity aeronave) {
        AeronaveEntity aeronaveMatricula = aeronaveRepository.findByMatricula(aeronave.getMatricula());
        if (aeronaveMatricula != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Já existe uma aeronave registada com esta matricula");

        return ResponseEntity.status(HttpStatus.CREATED).body(aeronaveService.addNewAeronave(aeronave));
    }

    public ResponseEntity<String> deleteAeronave(Long id) {
        Optional<AeronaveEntity> aeronave = aeronaveRepository.findById(id);
        if(aeronave.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aeronave nao encontrada");

        aeronaveService.deleteAeronave(id);
        return ResponseEntity.status(HttpStatus.OK).body("Aeronave deletada com sucesso");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String erroMessage = error.getDefaultMessage();
            errors.put(fieldName, erroMessage);
        });

        return errors;
    }


}
