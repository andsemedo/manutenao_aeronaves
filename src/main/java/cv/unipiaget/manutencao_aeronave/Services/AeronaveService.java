package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.AeronaveEntity;
import cv.unipiaget.manutencao_aeronave.Repository.AeronaveRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * @author Anderson Semedo
 * @Date 23/01/2024
 */
@Service
public class AeronaveService {
    private final AeronaveRepository aeronaveRepository;

    public AeronaveService(AeronaveRepository aeronaveRepository) {
        this.aeronaveRepository = aeronaveRepository;
    }

    public List<AeronaveEntity> getAllAeronave() {return aeronaveRepository.findAll();}

    public AeronaveEntity addNewAeronave(AeronaveEntity aeronave) {return aeronaveRepository.save(aeronave);}

    public AeronaveEntity getAeronaveById(Long id) {
        Optional<AeronaveEntity> aeronave = aeronaveRepository.findById(id);
        if (aeronave.isEmpty()) {
            return null;
        }
        return aeronave.get();
    }
    public AeronaveEntity getAeronaveByMatricula(String matricula) {
        return aeronaveRepository.findByMatricula(matricula);
    }

    public AeronaveEntity updateAeronave(AeronaveEntity aeronave) {return aeronaveRepository.save(aeronave);}

    public Boolean deleteAeronave(Long id) {
        aeronaveRepository.deleteById(id);
        return true;
    }
}
