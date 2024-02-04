package cv.unipiaget.manutencao_aeronave.Config;

import cv.unipiaget.manutencao_aeronave.Entities.AeronaveEntity;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeEquipaEntities;
import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.Entities.RegistoTarefaEntity;
import cv.unipiaget.manutencao_aeronave.Enums.StatusManutencaoEnum;
import cv.unipiaget.manutencao_aeronave.Enums.TipoManutencaoEnum;
import cv.unipiaget.manutencao_aeronave.Repository.AeronaveRepository;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeEquipaRepository;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeManutencaoRepository;
import cv.unipiaget.manutencao_aeronave.Repository.RegistoTarefaRepository;
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
/*
    @Bean
    CommandLineRunner commandLineRunnerAddAeronave(AeronaveRepository aeronaveRepository) {

        return args -> {
            AeronaveEntity aeronave1 = new AeronaveEntity("N12341", "Boeing 777",2010,120);
            AeronaveEntity aeronave2 = new AeronaveEntity("N12342", "Boeing 747",2010,120);
            AeronaveEntity aeronave3 = new AeronaveEntity("N12343", "Boeing 767",2010,120);
            AeronaveEntity aeronave4 = new AeronaveEntity("N12344", "Boeing 757",2010,120);
            AeronaveEntity aeronave5 = new AeronaveEntity("N12345", "Boeing 777",2010,120);

            aeronaveRepository.saveAll(List.of(aeronave1,aeronave2,aeronave3,aeronave4,aeronave5));
        };

    }

    @Bean
    CommandLineRunner commandLineRunner(AtividadeManutencaoRepository manutencaoRepository) {
        return args -> {
            AtividadeManutencaoEntity manutencao1 = new AtividadeManutencaoEntity(
                TipoManutencaoEnum.preventiva,"Teste de manutencao", StatusManutencaoEnum.pendente,LocalDate.of(2024,1,22), 1L);

            AtividadeManutencaoEntity manutencao2 = new AtividadeManutencaoEntity(
                    TipoManutencaoEnum.corretiva,"Teste de manutencao 2", StatusManutencaoEnum.emProgresso,LocalDate.of(2024,1,22), 1L);

            AtividadeManutencaoEntity manutencao3 = new AtividadeManutencaoEntity(
                    TipoManutencaoEnum.corretiva,"Teste de manutencao 3", StatusManutencaoEnum.emProgresso,LocalDate.of(2024,1,22), 1L);

            manutencaoRepository.saveAll(
                    List.of(manutencao1,manutencao2,manutencao3)
            );
        };
    }

    @Bean
    CommandLineRunner commandLineRunnerEquipa(AtividadeEquipaRepository equipaRepository) {
        return args -> {
            AtividadeEquipaEntities equipa = new AtividadeEquipaEntities("supa");
            AtividadeEquipaEntities equipa1 = new AtividadeEquipaEntities("strikas");
            AtividadeEquipaEntities equipa2 = new AtividadeEquipaEntities("powa");

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
    }*/
}
