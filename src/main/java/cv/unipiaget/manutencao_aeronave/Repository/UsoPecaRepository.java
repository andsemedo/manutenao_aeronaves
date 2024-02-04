package cv.unipiaget.manutencao_aeronave.Repository;

import cv.unipiaget.manutencao_aeronave.Entities.UsoPecaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Anderson Semedo
 * @Date 25/01/2024
 */
public interface UsoPecaRepository extends JpaRepository<UsoPecaEntity, Long> {
    List<UsoPecaEntity> findByTarefaId(Long id);
}
