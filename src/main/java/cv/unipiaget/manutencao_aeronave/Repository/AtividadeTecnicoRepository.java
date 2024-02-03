package cv.unipiaget.manutencao_aeronave.Repository;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeTecnicoEntities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeTecnicoRepository extends JpaRepository<AtividadeTecnicoEntities, Long>{
    List<AtividadeTecnicoEntities> findByIdequipa(Long id);
}
