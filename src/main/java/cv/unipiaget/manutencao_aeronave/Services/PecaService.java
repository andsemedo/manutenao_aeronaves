package cv.unipiaget.manutencao_aeronave.Services;


/**
 * @author Silvino Gomes
 * @Date 17/01/2024
 */

import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import cv.unipiaget.manutencao_aeronave.Repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PecaService {

    private final PecaRepository pecaRepository;

    @Autowired
    public PecaService(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
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

    public PecaEntity updateEstoque(PecaEntity peca) {return pecaRepository.save(peca);}

    public void excluirPeca(int id) {
        pecaRepository.deleteById(id);
    }

    // OBS: Falta a de adcionar


}
