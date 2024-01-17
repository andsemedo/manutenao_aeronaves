package cv.unipiaget.manutencao_aeronave.GestaoVooSimulate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */

public interface AeronaveRepository extends JpaRepository<AeronaveEntity, Long> {
}
