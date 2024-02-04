package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.UsoPecaEntity;
import cv.unipiaget.manutencao_aeronave.Repository.UsoPecaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Anderson Semedo
 * @Date 25/01/2024
 */
@Service
public class UsoPecaService {
    private final UsoPecaRepository usoPecaRepository;

    public UsoPecaService(UsoPecaRepository usoPecaRepository) {
        this.usoPecaRepository = usoPecaRepository;
    }

    public List<UsoPecaEntity> getAllUsoPeca() {return usoPecaRepository.findAll();}

    public UsoPecaEntity getUsoPecaById(Long id) {return usoPecaRepository.findById(id).get();}

    public UsoPecaEntity addNewUsoPeca(UsoPecaEntity usoPecaEntity) {return usoPecaRepository.save(usoPecaEntity);}

    public UsoPecaEntity updateUsoPeca(UsoPecaEntity usoPecaEntity) {return usoPecaRepository.save(usoPecaEntity);}

    public Boolean deleteUsoPeca(Long id) {usoPecaRepository.deleteById(id); return true;}
}
