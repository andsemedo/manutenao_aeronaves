/**
 * @author Silvino Gomes
 * @Date 22/01/2024
 */

package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.UsoPecaEntity;
import cv.unipiaget.manutencao_aeronave.Repository.UsoPecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsoPecaService {

    private final UsoPecaRepository usoPecaRepository;

    @Autowired
    public UsoPecaService(UsoPecaRepository usoPecaRepository) {
        this.usoPecaRepository = usoPecaRepository;
    }

    public List<UsoPecaEntity> listarTodos() {
        return usoPecaRepository.findAll();
    }

    public UsoPecaEntity obterPorId(Long id) {
        Optional<UsoPecaEntity> usoPecaOptional = usoPecaRepository.findById(id);
        return usoPecaOptional.orElse(null);
    }

    public UsoPecaEntity adicionarUsoPeca(UsoPecaEntity usoPeca) {
        return usoPecaRepository.save(usoPeca);
    }

    public UsoPecaEntity atualizarUsoPeca(Long id, UsoPecaEntity usoPeca) {
        usoPeca.setIdPecaUso(id);
        return usoPecaRepository.save(usoPeca);
    }

    public void excluirUsoPeca(Long id) {
        usoPecaRepository.deleteById(id);
    }
}