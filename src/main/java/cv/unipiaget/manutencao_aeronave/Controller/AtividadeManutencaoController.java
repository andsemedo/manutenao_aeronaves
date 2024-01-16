package cv.unipiaget.manutencao_aeronave.Controller;

import cv.unipiaget.manutencao_aeronave.Entities.AtividadeManutencaoEntity;
import cv.unipiaget.manutencao_aeronave.Services.AtividadeManutencaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Anderson Semedo
 * @Date 16/01/2024
 */
@RestController
@RequestMapping(path = "/api/manutencao")
public class AtividadeManutencaoController {

    public final AtividadeManutencaoService atividadeManutencaoService;

    public AtividadeManutencaoController(AtividadeManutencaoService atividadeManutencaoService) {
        this.atividadeManutencaoService = atividadeManutencaoService;
    }

    @GetMapping
    public List<AtividadeManutencaoEntity> obterTodasManutencao() {
        return atividadeManutencaoService.obterTodasManutencao();
    }

}
