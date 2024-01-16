package cv.unipiaget.manutencao_aeronave.Services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Anderson Semedo
 * @Date 14/01/2024
 */

@Service
public class GestaoVooMockService {

    public String verificarDisponibilidadeAeronave(int idAeronave) {
        if(idAeronave == 1 || idAeronave == 4 || idAeronave == 5) {
            return "sim";
        }
        return "não";
    }

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
