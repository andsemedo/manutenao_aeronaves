package cv.unipiaget.manutencao_aeronave.Repository;

import cv.unipiaget.manutencao_aeronave.Entities.RegistoTarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anderson Semedo
 * @Date 18/01/2024
 */
public interface RegistoTarefaRepository extends JpaRepository<RegistoTarefaEntity, Long> {
}
