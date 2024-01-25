package cv.unipiaget.manutencao_aeronave.Repository;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeEquipaRepository extends JpaRepository<AtividadeEquipaEntities, Long> {
    AtividadeEquipaEntities findByNomeEquipa(String nomeEquipa);
}
