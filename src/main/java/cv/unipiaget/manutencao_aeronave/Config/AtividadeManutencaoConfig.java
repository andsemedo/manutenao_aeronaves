package cv.unipiaget.manutencao_aeronave.Config;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.Entities.RegistoTarefaEntity;
import cv.unipiaget.manutencao_aeronave.Enums.StatusManutencaoEnum;
import cv.unipiaget.manutencao_aeronave.Enums.TipoManutencaoEnum;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeManutencaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */
@Configuration
public class AtividadeManutencaoConfig {

    @Bean
    CommandLineRunner commandLineRunner(AtividadeManutencaoRepository manutencaoRepository) {
        return args -> {
            AtividadeManutencaoEntity manutencao1 = new AtividadeManutencaoEntity(
                TipoManutencaoEnum.preventiva,"Teste de manutencao", StatusManutencaoEnum.pendente,1,LocalDate.of(2024,1,22));

            AtividadeManutencaoEntity manutencao2 = new AtividadeManutencaoEntity(
                    TipoManutencaoEnum.corretiva,"Teste de manutencao 2", StatusManutencaoEnum.emProgresso,1,LocalDate.of(2024,1,22));

            AtividadeManutencaoEntity manutencao3 = new AtividadeManutencaoEntity(
                    TipoManutencaoEnum.corretiva,"Teste de manutencao 3", StatusManutencaoEnum.emProgresso,1,LocalDate.of(2024,1,22));

            manutencaoRepository.saveAll(
                    List.of(manutencao1,manutencao2,manutencao3)
            );
        };
    }

    @Bean
    CommandLineRunner commandLineRunnerEquipa(EquipaRepository equipaRepository) {
        return args -> {
            EquipaEntity equipa = new EquipaEntity("supa");
            EquipaEntity equipa1 = new EquipaEntity("strikas");
            EquipaEntity equipa2 = new EquipaEntity("powa");

            equipaRepository.saveAll(
                    List.of(equipa, equipa1, equipa2)
            );
        };
    }
    @Bean
    CommandLineRunner commandLineRunnerRegisto(RegistoTarefaRepository tarefaRepository) {
        return args -> {
            RegistoTarefaEntity registoTarefa = new RegistoTarefaEntity(LocalDate.now(),"coments", 1L,1L);
            RegistoTarefaEntity registoTarefa1 = new RegistoTarefaEntity(LocalDate.now(),"coments example", 2L,2L);

            tarefaRepository.saveAll(
                    List.of(registoTarefa, registoTarefa1)
            );
        };
    }
}
