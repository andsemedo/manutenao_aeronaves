package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeTecnicoEntities;
import cv.unipiaget.manutencao_aeronave.Enums.AtividadeTecnicoEnum;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeTecnicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

/**
 * @author Anderson Semedo
 * @Date 02/02/2024
 */
@ExtendWith(MockitoExtension.class)
public class AtividadeTecnicoServiceTest {

    @InjectMocks
    AtividadeTecnicoServices service;

    @Mock
    AtividadeTecnicoRepository repository;

    AtividadeTecnicoEntities tecnico, tecnico1;

    @BeforeEach
    public void setUp() {
        tecnico = new AtividadeTecnicoEntities();
        tecnico.setIdTecnico(1L);
        tecnico.setNomeTecnico("Anderson Semedo");
        tecnico.setCargo(AtividadeTecnicoEnum.tecnico_normal);
        tecnico.setIdequipa(1L);

        tecnico1 = new AtividadeTecnicoEntities();
        tecnico1.setIdTecnico(1L);
        tecnico1.setNomeTecnico("Anderson Semedo");
        tecnico1.setCargo(AtividadeTecnicoEnum.tecnico_normal);
        tecnico1.setIdequipa(1L);
    }

    @Test
    void deveObterTodosOsTecmicos() {
        when(repository.findAll()).thenReturn(Arrays.asList(tecnico,tecnico1));
        List<AtividadeTecnicoEntities> tecnicos = service.getAllTecnico();
        assertEquals(Arrays.asList(tecnico,tecnico1), tecnicos);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }
}
