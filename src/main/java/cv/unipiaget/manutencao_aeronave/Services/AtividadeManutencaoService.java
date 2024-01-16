package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.Enums.StatusManutencaoEnum;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeManutencaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AtividadeManutencaoEntity> obterTodasManutencao() {
        return atividadeManutencaoRepository.findAll();
    }

    public void adicionarNovaManutencao(AtividadeManutencaoEntity manutencaoEntity) {
        atividadeManutencaoRepository.save(manutencaoEntity);
    }

    public void deletarManutencao(Long idManutencao) {
        if(!atividadeManutencaoRepository.existsById(idManutencao)) {
            throw new IllegalStateException("Manutenção com o " + idManutencao + " não existe");
        }
    }

    @Transactional
    public void atualizarManutencao(Long idManutencao, StatusManutencaoEnum status, String descricao) {
        AtividadeManutencaoEntity manutencaoEntity = atividadeManutencaoRepository.findById(idManutencao)
                .orElseThrow( () -> new IllegalStateException(
                        "Manutenção com o " + idManutencao + " não existe"
                ));

        if(status != null) {
            manutencaoEntity.setStatusManutencao(status);
        }
        if(descricao != null) {
            manutencaoEntity.setDescricao(descricao);
        }

        atividadeManutencaoRepository.save(manutencaoEntity);

    }



}
