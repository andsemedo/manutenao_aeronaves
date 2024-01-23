/**
 * @author Silvino Gomes
 * @Date 17/01/2024
 */

package cv.unipiaget.manutencao_aeronave.Repository;

import cv.unipiaget.manutencao_aeronave.Entities.EncomendaPecaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncomendaPecaRepository extends JpaRepository<EncomendaPecaEntity, Integer> {

}
