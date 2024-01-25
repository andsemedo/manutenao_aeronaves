package cv.unipiaget.manutencao_aeronave.GestaoVooSimulate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */
@Configuration
public class AeronaveConfig {

    @Bean
    CommandLineRunner commandLineRunnerAeronave(AeronaveRepository aeronaveRepository) {
        return args -> {
            AeronaveEntity aeronave_1 = new AeronaveEntity("Boeing 777",1);
            AeronaveEntity aeronave_2 = new AeronaveEntity("Boeing 747",1);
            AeronaveEntity aeronave_3 = new AeronaveEntity("Boeing 777-303",0);
            AeronaveEntity aeronave_4 = new AeronaveEntity("Emirates Fly",0);
            AeronaveEntity aeronave_5 = new AeronaveEntity("ATR 554",1);

            aeronaveRepository.saveAll(
                    List.of(aeronave_1,aeronave_2,aeronave_3,aeronave_4,aeronave_5)
            );
        };
    }

}
