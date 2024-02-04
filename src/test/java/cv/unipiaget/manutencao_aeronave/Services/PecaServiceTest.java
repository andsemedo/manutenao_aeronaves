package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import cv.unipiaget.manutencao_aeronave.Repository.PecaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Anderson Semedo
 * @Date 03/02/2024
 */
@ExtendWith(MockitoExtension.class)
public class PecaServiceTest {

    @InjectMocks
    PecaService service;

    @Mock
    PecaRepository repository;

    PecaEntity peca, peca1;

    @BeforeEach
    public void setUp() {
        peca = new PecaEntity(1, "Peca Teste", 20);
        peca1 = new PecaEntity(2, "Peca Teste", 20);
    }

    @Test
    void deveObterTodasAsPecas() {
        when(repository.findAll()).thenReturn(Arrays.asList(peca, peca1));
        List<PecaEntity> pecaList = service.listarTodasPecas();
        assertEquals(2, pecaList.size());
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }
}
