package cv.unipiaget.manutencao_aeronave.Repository;

import com.sun.jdi.connect.Connector;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeTecnicoEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface AtividadeTecnicoRepository extends JpaRepository<AtividadeTecnicoEntities, Long>{
}
