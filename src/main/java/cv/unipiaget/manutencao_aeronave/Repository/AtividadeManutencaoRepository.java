package cv.unipiaget.manutencao_aeronave.Repository;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */
public interface AtividadeManutencaoRepository extends JpaRepository<AtividadeManutencaoEntity, Long> {
    List<AtividadeManutencaoEntity> findByAeronaveid(Long id);
}
