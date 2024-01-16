package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.Enums.StatusManutencaoEnum;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeManutencaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */
@Service
public class AtividadeManutencaoService {

    public final AtividadeManutencaoRepository atividadeManutencaoRepository;
    public final GestaoVooMockService gestaoVooMockService;

    public AtividadeManutencaoService(AtividadeManutencaoRepository atividadeManutencaoRepository, GestaoVooMockService gestaoVooMockService) {
        this.atividadeManutencaoRepository = atividadeManutencaoRepository;
        this.gestaoVooMockService = gestaoVooMockService;
    }

    public List<AtividadeManutencaoEntity> obterTodasManutencao() {
        return atividadeManutencaoRepository.findAll();
    }

    public ResponseEntity<Object> adicionarNovaManutencao(AtividadeManutencaoEntity manutencaoEntity) {
        //verificar disponibilidade de aeronave
        String disponibilidade = gestaoVooMockService.verificarDisponibilidadeAeronave(manutencaoEntity.getIdAeronave());
        if(disponibilidade.equals("não")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aeronave não disponivel");
        } else if (disponibilidade.equals("sim")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(atividadeManutencaoRepository.save(manutencaoEntity));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aeronave não encontrado");
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
