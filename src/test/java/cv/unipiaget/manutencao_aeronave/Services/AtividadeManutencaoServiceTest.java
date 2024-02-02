package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.AeronaveEntity;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeManutencaoRepository;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

/**
 * @author Anderson Semedo
 * @Date 02/02/2024
 */
@ExtendWith(MockitoExtension.class)
public class AtividadeManutencaoServiceTest {

    @InjectMocks
    AtividadeManutencaoService service;

    @Mock
    AtividadeManutencaoRepository repository;

    AtividadeManutencaoEntity manutencao, manutencao1;
    AeronaveEntity aeronave;

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
        manutencao1 = new AtividadeManutencaoEntity(
                2L,
                "preventiva",
                "Verificação dos motores",
                "pendente",
                "2024-01-26",
                aeronave,
                aeronave.getAeronaveid()
        );
    }

    @Test
    void deveRetornarTodasAsManutencoes() {
        //setup
        when(repository.findAll()).thenReturn(Arrays.asList(manutencao,manutencao1));
        //execução
        List<AtividadeManutencaoEntity> manutencaoList = service.getAllManutencao();
        //verificação
        //verificar se o que foi retornado é igual ao retornado no service
        assertEquals(Arrays.asList(manutencao,manutencao1), manutencaoList);
        //verificar se o repository é chamado apenas uma vez
        verify(repository).findAll();
        //verificar se o repository não é chamado mais de que uma vez
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveAdicionarNovaManutencao() {
        //setup
        when(repository.save(manutencao)).thenReturn(manutencao);
        //execucao
        AtividadeManutencaoEntity atividade = service.addNewManutencao(manutencao);
        //verificação
        assertEquals(manutencao, atividade);
        verify(repository).save(manutencao);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveBuscarAtividadeManutencaoPorId() {
        //setup
        when(repository.findById(manutencao.getManutencaoid())).thenReturn(Optional.ofNullable(manutencao));
        //execução
        AtividadeManutencaoEntity atividade = service.getManutencaoById(manutencao.getManutencaoid());
        //verificação
        //verificar se o que foi retornado é igual ao retornado no service
        assertEquals(Optional.ofNullable(manutencao).get(), atividade);
        //verificar se o repository é chamado apenas uma vez
        verify(repository).findById(manutencao.getManutencaoid());
        //verificar se o repository não é chamado mais de que uma vez
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveRetornarNullCasoManutencaoIdNaoForEncontrada() {
        when(repository.findById(3L)).thenReturn(Optional.empty());

        AtividadeManutencaoEntity atividade = service.getManutencaoById(3L);
        assertNull(atividade);
        verify(repository).findById(3L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveRetornarHistoricoDeManutencaoPorAeronave() {
        //setup
        when(repository.findByAeronaveid(1L)).thenReturn(Arrays.asList(manutencao,manutencao1));
        //execução
        List<AtividadeManutencaoEntity> manutencaoList = service.getHistoricoByAeronave(1L);
        //verificação
        assertEquals(Arrays.asList(manutencao,manutencao1), manutencaoList);
        verify(repository).findByAeronaveid(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveAtualizarUmaManutencao() {
        when(repository.save(manutencao)).thenReturn(manutencao);

        AtividadeManutencaoEntity atividade = service.updateManutencao(manutencao);

        assertEquals(manutencao, atividade);
        verify(repository).save(manutencao);
        verifyNoMoreInteractions(repository);
    }

}
