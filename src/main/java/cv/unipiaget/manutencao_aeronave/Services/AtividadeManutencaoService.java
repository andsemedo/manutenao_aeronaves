package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.Enums.StatusManutencaoEnum;
import cv.unipiaget.manutencao_aeronave.GestaoVooSimulate.GestaoVooMockService;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeManutencaoRepository;
import jakarta.transaction.Transactional;
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
public class AtividadeManutencaoService {

    public final AtividadeManutencaoRepository atividadeManutencaoRepository;

    public AtividadeManutencaoService(AtividadeManutencaoRepository atividadeManutencaoRepository) {
        this.atividadeManutencaoRepository = atividadeManutencaoRepository;
    }

    public List<AtividadeManutencaoEntity> getAllManutencao() {
        return atividadeManutencaoRepository.findAll();
    }

    public AtividadeManutencaoEntity addNewManutencao(AtividadeManutencaoEntity manutencaoEntity) {

        return atividadeManutencaoRepository.save(manutencaoEntity);

    }

    public AtividadeManutencaoEntity getManutencaoById(Long idManutencao) {
        Optional<AtividadeManutencaoEntity> manutencao = atividadeManutencaoRepository.findById(idManutencao);
        if(manutencao.isEmpty()) {
            return null;
        }
        return manutencao.get();
    }

    public List<AtividadeManutencaoEntity> getHistoricoByAeronave(Long id) {
        return atividadeManutencaoRepository.findByAeronaveid(id);
    }

    public void deleteManutencao(Long idManutencao) {

        atividadeManutencaoRepository.deleteById(idManutencao);
    }

    @Transactional
    public AtividadeManutencaoEntity updateManutencao(AtividadeManutencaoEntity manutencaoEntity) {
        /*AtividadeManutencaoEntity manutencaoEntity = atividadeManutencaoRepository.findById(idManutencao)
                .orElseThrow( () -> new IllegalStateException(
                        "Manutenção com o id " + idManutencao + " não existe"
                ));

        if(status != null) {
            manutencaoEntity.setStatusManutencao(status);
        }
        if(descricao != null) {
            manutencaoEntity.setDescricao(descricao);
        }*/

        return atividadeManutencaoRepository.save(manutencaoEntity);

    }



}
