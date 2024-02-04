package cv.unipiaget.manutencao_aeronave.GestaoVooSimulate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * @author Anderson Semedo
 * @Date 14/01/2024
 */

@Service
public class GestaoVooMockService {

    private final WebClient webClient;

    public GestaoVooMockService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Boolean verificarDisponibilidade(String matricula) {
        // Simulando a chamada à API para verificar a disponibilidade

        try {
            ResponseEntity<Boolean> response = webClient.get()
                    .uri("/api/voos/aeronave/disponibilidade/{matricula}", matricula)
                    .retrieve()
                    .toEntity(Boolean.class)
                    .block();

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody() != null && response.getBody();
            } else {
                // Tratar outros códigos de status conforme necessário
                return false;
            }
        } catch (WebClientResponseException.NotFound ex) {
            // Tratar recurso não encontrado (404) conforme necessário
            System.out.println("Aeronave não encontrada");
            return false;
        }
    }

    /*
    public String verificarDisponibilidadeAeronave(int idAeronave) {
        if(idAeronave == 1 || idAeronave == 4 || idAeronave == 5) {
            return "sim";
        } else if (idAeronave == 2 || idAeronave == 3 || idAeronave == 6) {
            return "não";
        }
        return "aeronave não encontrado";
    }
    */

    public String obterRequisitosManutencaoAeronave(int idAeronave) {
        if (idAeronave == 1) {
            return "Check engine, Troca de óleo";
        } else if (idAeronave == 2) {
            return "Substituição de filtros, Inspeção estrutural";
        } else if (idAeronave == 5) {
            return "Verificação de sistemas, Limpeza do motor";
        } else {
            return null;
        }
    }



}
