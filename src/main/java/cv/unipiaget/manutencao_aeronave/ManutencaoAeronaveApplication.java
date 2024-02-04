package cv.unipiaget.manutencao_aeronave;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Subsistema Manutenção Aeronave API", version = "2.0", description = "Subsistema – Manutenção de aeronaves – Equipa Seul"))
public class ManutencaoAeronaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManutencaoAeronaveApplication.class, args);

		System.out.println("Compilado com sucesso");
	}

}
