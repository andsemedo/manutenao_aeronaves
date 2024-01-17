package cv.unipiaget.manutencao_aeronave.Services;


/**
 * @author Silvino Gomes
 * @Date 17/01/2024
 */

import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import cv.unipiaget.manutencao_aeronave.Entities.UsoPecaEntity;
import cv.unipiaget.manutencao_aeronave.Repository.PecaRepository;
import cv.unipiaget.manutencao_aeronave.Repository.UsoPecaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PecaService {

    private final PecaRepository pecaRepository;
    private final UsoPecaEntityRepository UsoPecaEntityRepository;

    @Autowired
    public PecaService(PecaRepository pecaRepository, cv.unipiaget.manutencao_aeronave.Repository.UsoPecaEntityRepository usoPecaEntityRepository) {
        this.pecaRepository = pecaRepository;
        UsoPecaEntityRepository = usoPecaEntityRepository;
    }

    public List<PecaEntity> listarTodasPecas() {
        return pecaRepository.findAll();
    }

    public Optional<PecaEntity> obterPecaPorId(int id) {
        return pecaRepository.findById(id);
    }

    public PecaEntity salvarPeca(PecaEntity peca) {
        return pecaRepository.save(peca);
    }

    public void excluirPeca(int id) {
        pecaRepository.deleteById(id);
    }

    // OBS: Falta a de adcionar



    // Uso de Pecas
    public List<UsoPecaEntity> listarPecasEmUso() {
        return UsoPecaEntityRepository.findAll();
    }

}
