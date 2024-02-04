package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.EncomendaPecaEntity;
import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import cv.unipiaget.manutencao_aeronave.Enums.EncomendaStatus;
import cv.unipiaget.manutencao_aeronave.Repository.EncomendaPecaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Anderson Semedo
 * @Date 04/02/2024
 */
@ExtendWith(MockitoExtension.class)
public class EncomendaPecaServiceTest {

    @InjectMocks
    EncomendaPecaService service;

    @Mock
    EncomendaPecaRepository repository;

    EncomendaPecaEntity encomendaPeca, encomendaPeca1;
    PecaEntity peca;

    @BeforeEach
    public void setUp() {
        peca = new PecaEntity(1, "Peca Teste", 20);
        encomendaPeca = new EncomendaPecaEntity(1, 5, LocalDate.parse("2024-02-24"), EncomendaStatus.PROCESSANDO,1);
        encomendaPeca1 = new EncomendaPecaEntity(2, 5, LocalDate.parse("2024-02-24"), EncomendaStatus.PROCESSANDO,1);
    }

    @Test
    void deveListarTodasAsEncomendasPecas() {
        when(repository.findAll()).thenReturn(Arrays.asList(encomendaPeca, encomendaPeca1));
        List<EncomendaPecaEntity> encomendaPecaEntityList = service.listarTodasEncomendasPecas();
        assertEquals(2,encomendaPecaEntityList.size());
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveObterEncomendaPecaPorId() {
        when(repository.findById(encomendaPeca.getIdEncomendaPeca())).thenReturn(Optional.ofNullable(encomendaPeca));
        EncomendaPecaEntity encomendaPecaEntity = service.obterEncomendaPecaPorId(encomendaPeca.getIdEncomendaPeca());
        assertEquals(Optional.ofNullable(encomendaPeca).get(),encomendaPecaEntity);
        verify(repository).findById(encomendaPeca.getIdEncomendaPeca());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveAdicionarUmaNovaEncomendaPeca() {
        when(repository.save(encomendaPeca)).thenReturn(encomendaPeca);
        EncomendaPecaEntity encomendaPecaEntity = service.salvarEncomendaPeca(encomendaPeca);
        assertEquals(encomendaPeca, encomendaPecaEntity);
        verify(repository).save(encomendaPeca);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveAtualizarEncomendaPeca() {
        when(repository.save(encomendaPeca)).thenReturn(encomendaPeca);
        EncomendaPecaEntity encomendaPecaEntity = service.atualizarStatus(encomendaPeca);
        assertEquals(encomendaPeca, encomendaPecaEntity);
        verify(repository).save(encomendaPeca);
        verifyNoMoreInteractions(repository);
    }
}
