package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.AeronaveEntity;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.Entities.RegistoTarefaEntity;
import cv.unipiaget.manutencao_aeronave.Repository.RegistoTarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Anderson Semedo
 * @Date 02/02/2024
 */
@ExtendWith(MockitoExtension.class)
public class RegistoTarefaServiceTest {
    @InjectMocks
    RegistoTarefaService service;

    @Mock
    RegistoTarefaRepository repository;

    RegistoTarefaEntity tarefa, tarefa1;
    AtividadeManutencaoEntity manutencao;
    AtividadeEquipaEntities equipa;
    AeronaveEntity aeronave;

    @BeforeEach
    public void setUp() {
        equipa = new AtividadeEquipaEntities(1L, "Supa Strikas");
        aeronave = new AeronaveEntity(1L,"N12345", "Boeing 777", 2015, 120);
        manutencao = new AtividadeManutencaoEntity(
                1L,
                "preventiva",
                "Verificação dos motores",
                "pendente",
                "2024-01-26",
                aeronave,
                aeronave.getAeronaveid()
        );
        tarefa = new RegistoTarefaEntity(1L, "2024-02-05","Reparação no motor esquerdo", manutencao,manutencao.getManutencaoid(),equipa,equipa.getIdEquipa());
        tarefa1 = new RegistoTarefaEntity(2L, "2024-02-05","Reparação no motor esquerdo", manutencao,manutencao.getManutencaoid(),equipa,equipa.getIdEquipa());
    }

    @Test
    void deveObterTodasAsTarefasManutencao() {
        //setup
        when(repository.findAll()).thenReturn(Arrays.asList(tarefa, tarefa1));
        //execucao
        List<RegistoTarefaEntity> tarefas = service.getAllRegistoTarefa();
        //verificação
        //verificar se o que foi retornado é igual ao retornado no service
        assertEquals(Arrays.asList(tarefa, tarefa1), tarefas);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveObterRegistoTarefaPorId() {
        when(repository.findById(tarefa.getRegistotarefaid())).thenReturn(Optional.ofNullable(tarefa));
        RegistoTarefaEntity tarefaEntity = service.getRegistoTarefaById(tarefa.getRegistotarefaid());
        assertEquals(Optional.ofNullable(tarefa).get(), tarefaEntity);
        verify(repository).findById(tarefa.getRegistotarefaid());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveObterRegistoTarefaPorManutencaoId() {
        when(repository.findByAtividadeManutencao(manutencao.getManutencaoid())).thenReturn(Arrays.asList(tarefa, tarefa1));
        List<RegistoTarefaEntity> tarefaEntity = service.getAllRegistoTarefaByManutencaoId(manutencao.getManutencaoid());
        assertEquals(2, tarefaEntity.size());
        verify(repository).findByAtividadeManutencao(manutencao.getManutencaoid());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveObterRegistoTarefaPorIdEquipa() {
        when(repository.findByIdequipa(equipa.getIdEquipa())).thenReturn(Arrays.asList(tarefa, tarefa1));
        List<RegistoTarefaEntity> tarefaEntity = service.getAllRegistoTarefaByEquipaId(equipa.getIdEquipa());
        assertEquals(2, tarefaEntity.size());
        verify(repository).findByIdequipa(equipa.getIdEquipa());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveAdicionarNovoRegistoTarefa() {
        when(repository.save(tarefa)).thenReturn(tarefa);
        RegistoTarefaEntity tarefaEntity = service.addNewRegistoTarefa(tarefa);
        assertEquals(tarefa, tarefaEntity);
        verify(repository).save(tarefa);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveAtualizarRegistoTarefa() {
        when(repository.save(tarefa)).thenReturn(tarefa);
        RegistoTarefaEntity tarefaEntity = service.updateRegistoTarefa(tarefa);
        assertEquals(tarefa, tarefaEntity);
        verify(repository).save(tarefa);
        verifyNoMoreInteractions(repository);
    }

}
