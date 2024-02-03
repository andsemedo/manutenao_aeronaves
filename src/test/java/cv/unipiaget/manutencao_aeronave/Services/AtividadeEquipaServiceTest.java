package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeEquipaRepository;
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
import java.util.Optional;

/**
 * @author Anderson Semedo
 * @Date 02/02/2024
 */
@ExtendWith(MockitoExtension.class)
public class AtividadeEquipaServiceTest {

    @InjectMocks
    AtividadeEquipaServices service;

    @Mock
    AtividadeEquipaRepository repository;

    AtividadeEquipaEntities equipa, equipa1;

    @BeforeEach
    public void setUp() {
        equipa = new AtividadeEquipaEntities(1L, "Supa");
        equipa1 = new AtividadeEquipaEntities(2L, "Supa");
    }

    @Test
    void deveObterTodasAsEquipas() {
        when(repository.findAll()).thenReturn(Arrays.asList(equipa,equipa1));
        List<AtividadeEquipaEntities> equipas = service.getAllEquipa();
        assertEquals(Arrays.asList(equipa,equipa1), equipas);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveCriarNovaEquipa() {
        when(repository.save(equipa)).thenReturn(equipa);
        AtividadeEquipaEntities equipaEntity = service.createNewEquipa(equipa);
        assertEquals(equipa, equipaEntity);
        verify(repository).save(equipa);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveObterUmaEquipaPorId() {
        when(repository.findById(equipa.getIdEquipa())).thenReturn(Optional.ofNullable(equipa));
        Optional<AtividadeEquipaEntities> equipaEntity = service.getEquipaById(equipa.getIdEquipa());
        assertEquals(Optional.ofNullable(equipa), equipaEntity);
        verify(repository).findById(equipa.getIdEquipa());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveAtualizarUmaEquipa() {
        when(repository.save(equipa)).thenReturn(equipa);
        AtividadeEquipaEntities equipaEntity = service.updateEquipa(equipa);
        assertEquals(equipa, equipaEntity);
        verify(repository).save(equipa);
        verifyNoMoreInteractions(repository);
    }


}
