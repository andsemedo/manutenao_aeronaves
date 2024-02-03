package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.RegistoTarefaEntity;
import cv.unipiaget.manutencao_aeronave.Repository.RegistoTarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Anderson Semedo
 * @Date 18/01/2024
 */
@Service
public class RegistoTarefaService {

    private final RegistoTarefaRepository registoTarefaRepository;

    public RegistoTarefaService(RegistoTarefaRepository registoTarefaRepository) {
        this.registoTarefaRepository = registoTarefaRepository;
    }

    public List<RegistoTarefaEntity> getAllRegistoTarefa() {
        return registoTarefaRepository.findAll();
    }

    public RegistoTarefaEntity getRegistoTarefaById(Long id) {
        Optional<RegistoTarefaEntity> registoTarefa = registoTarefaRepository.findById(id);
        if (registoTarefa.isEmpty()) {
            return null;
        }
        return registoTarefa.get();
    }

    public List<RegistoTarefaEntity> getAllRegistoTarefaByManutencaoId(Long id) {
        return registoTarefaRepository.findByAtividadeManutencao(id);
    }

    public List<RegistoTarefaEntity> getAllRegistoTarefaByEquipaId(Long id) {
        return registoTarefaRepository.findByIdequipa(id);
    }

    public RegistoTarefaEntity addNewRegistoTarefa(RegistoTarefaEntity tarefa) {
        return registoTarefaRepository.save(tarefa);
    }

    public RegistoTarefaEntity updateRegistoTarefa(RegistoTarefaEntity tarefa) {
        return registoTarefaRepository.save(tarefa);
    }

    public void deleteRegistoTarefa(RegistoTarefaEntity tarefa) {
        registoTarefaRepository.delete(tarefa);
    }

}
