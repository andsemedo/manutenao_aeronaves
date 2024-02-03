package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.*;
import cv.unipiaget.manutencao_aeronave.Repository.UsoPecaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Anderson Semedo
 * @Date 03/02/2024
 */
@ExtendWith(MockitoExtension.class)
public class UsoPecaServiceTest {
    @InjectMocks
    UsoPecaService service;

    @Mock
    UsoPecaRepository repository;

    UsoPecaEntity usoPeca, usoPeca1;
    AtividadeManutencaoEntity manutencao, manutencao1;
    AeronaveEntity aeronave;
    AtividadeEquipaEntities equipa;
    RegistoTarefaEntity tarefa, tarefa1;
    PecaEntity peca;

    @BeforeEach
    public void setUp() {
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
        equipa = new AtividadeEquipaEntities(1L, "Supa Strikas");
        tarefa = new RegistoTarefaEntity(1L, "2024-02-05","Reparação no motor esquerdo", manutencao,manutencao.getManutencaoid(),equipa,equipa.getIdEquipa());
        peca = new PecaEntity(1, "Peca Teste", 20);
        usoPeca = new UsoPecaEntity(1L, tarefa, 1L, peca, 1, 5);
        usoPeca1 = new UsoPecaEntity(1L, tarefa, 1L, peca, 1, 5);
    }

    @Test
    void deveObterTodosOsUsoPecas() {
        //setup
        when(repository.findAll()).thenReturn(Arrays.asList(usoPeca, usoPeca1));
        //execucao
        List<UsoPecaEntity> usoPecaList = service.getAllUsoPeca();
        //verificao
        //verificar se o que foi retornado é igual ao retornado no service
        assertEquals(2, usoPecaList.size());
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveObterUmUsoPecaPorId() {
        when(repository.findById(usoPeca.getId())).thenReturn(Optional.ofNullable(usoPeca));
        UsoPecaEntity usoPecaEntity = service.getUsoPecaById(usoPeca.getId());
        assertEquals(Optional.ofNullable(usoPeca).get(), usoPecaEntity);
        verify(repository).findById(usoPeca.getId());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveAdicionarNovoUsoPeca() {
        when(repository.save(usoPeca)).thenReturn(usoPeca);
        UsoPecaEntity usoPecaEntity = service.addNewUsoPeca(usoPeca);
        assertEquals(usoPeca, usoPecaEntity);
        verify(repository).save(usoPeca);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveAtualizarUsoPeca() {
        when(repository.save(usoPeca)).thenReturn(usoPeca);
        UsoPecaEntity usoPecaEntity = service.updateUsoPeca(usoPeca);
        assertEquals(usoPeca, usoPecaEntity);
        verify(repository).save(usoPeca);
        verifyNoMoreInteractions(repository);
    }



}
