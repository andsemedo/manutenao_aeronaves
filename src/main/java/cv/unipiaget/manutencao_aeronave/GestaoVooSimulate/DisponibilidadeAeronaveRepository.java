package cv.unipiaget.manutencao_aeronave.GestaoVooSimulate;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */

public interface DisponibilidadeAeronaveRepository extends JpaRepository<DisponibilidadeAeronaveEntity, Long> {
    DisponibilidadeAeronaveEntity findByMatricula(String matricula);
}
