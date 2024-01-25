package cv.unipiaget.manutencao_aeronave.Repository;

import cv.unipiaget.manutencao_aeronave.Entities.AeronaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anderson Semedo
 * @Date 23/01/2024
 */
public interface AeronaveRepository extends JpaRepository<AeronaveEntity, Long> {
    AeronaveEntity findByMatricula(String matricula);
}
