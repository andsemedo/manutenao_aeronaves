package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.AeronaveEntity;
import cv.unipiaget.manutencao_aeronave.Repository.AeronaveRepository;
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
 * @Date 04/02/2024
 */
@ExtendWith(MockitoExtension.class)
public class AeronaveServiceTest {

    @InjectMocks
    AeronaveService service;

    @Mock
    AeronaveRepository repository;

    AeronaveEntity aeronave, aeronave1;

    @BeforeEach
    public void setUp() {
        aeronave = new AeronaveEntity(1L,"N12345", "Boeing 777", 2015, 120);
        aeronave1 = new AeronaveEntity(2L,"N12345", "Boeing 777", 2015, 120);
    }

    @Test
    void deveObterTodasAsAeronaves() {
        when(repository.findAll()).thenReturn(Arrays.asList(aeronave, aeronave1));
        List<AeronaveEntity> aeronaveList = service.getAllAeronave();
        assertEquals(Arrays.asList(aeronave, aeronave1),aeronaveList);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveAdicionarUmaNovaAeronave() {
        when(repository.save(aeronave)).thenReturn(aeronave);
        AeronaveEntity aeronaveEntity = service.addNewAeronave(aeronave);
        assertEquals(aeronave, aeronaveEntity);
        verify(repository).save(aeronave);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveObterAeronavePorId() {
        when(repository.findById(aeronave.getAeronaveid())).thenReturn(Optional.ofNullable(aeronave));
        AeronaveEntity aeronaveEntity = service.getAeronaveById(aeronave.getAeronaveid());
        assertEquals(Optional.ofNullable(aeronave).get(),aeronaveEntity);
        verify(repository).findById(aeronave.getAeronaveid());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveObterAeronavePorMatricula() {
        when(repository.findByMatricula(aeronave.getMatricula())).thenReturn(aeronave);
        AeronaveEntity aeronaveEntity = service.getAeronaveByMatricula(aeronave.getMatricula());
        assertEquals(Optional.ofNullable(aeronave).get(),aeronaveEntity);
        verify(repository).findByMatricula(aeronave.getMatricula());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveAtualizarUmaAeronave() {
        when(repository.save(aeronave)).thenReturn(aeronave);
        AeronaveEntity aeronaveEntity = service.updateAeronave(aeronave);
        assertEquals(aeronave, aeronaveEntity);
        verify(repository).save(aeronave);
        verifyNoMoreInteractions(repository);
    }

}
