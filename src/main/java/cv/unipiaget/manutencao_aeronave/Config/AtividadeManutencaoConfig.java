package cv.unipiaget.manutencao_aeronave.Config;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.Enums.StatusManutencaoEnum;
import cv.unipiaget.manutencao_aeronave.Enums.TipoManutencaoEnum;
import cv.unipiaget.manutencao_aeronave.Repository.AtividadeManutencaoRepository;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeManutencaoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
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
                TipoManutencaoEnum.preventiva,"Teste de manutencao", StatusManutencaoEnum.pendente,1,new Date(2023,12,25));

            AtividadeManutencaoEntity manutencao2 = new AtividadeManutencaoEntity(
                    TipoManutencaoEnum.corretiva,"Teste de manutencao 2", StatusManutencaoEnum.emProgresso,1,new Date(2023,12,25));

            AtividadeManutencaoEntity manutencao3 = new AtividadeManutencaoEntity(
                    TipoManutencaoEnum.corretiva,"Teste de manutencao 3", StatusManutencaoEnum.emProgresso,1,new Date(2023,12,25));

            manutencaoRepository.saveAll(
                    List.of(manutencao1,manutencao2,manutencao3)
            );
        };
    }
}
