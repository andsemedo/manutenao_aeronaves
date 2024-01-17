package cv.unipiaget.manutencao_aeronave.Repository;


/**
 * @author Silvino Gomes
 * @Date 17/01/2024
 */


import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PecaRepository extends JpaRepository<PecaEntity, Integer> {

}