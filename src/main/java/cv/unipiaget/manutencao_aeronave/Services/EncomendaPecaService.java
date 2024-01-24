/**
 * @author Silvino Gomes
 * @Date 22/01/2024
 */

package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.EncomendaPecaEntity;
import cv.unipiaget.manutencao_aeronave.Entities.PecaEntity;
import cv.unipiaget.manutencao_aeronave.Repository.EncomendaPecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncomendaPecaService {
    private final EncomendaPecaRepository encomendaPecaRepository;

    @Autowired
    public EncomendaPecaService(EncomendaPecaRepository encomendaPecaRepository) {
        this.encomendaPecaRepository = encomendaPecaRepository;
    }

    public List<EncomendaPecaEntity> listarTodas() {
        return encomendaPecaRepository.findAll();
    }

    public Optional<EncomendaPecaEntity> obterPorId(int id) {
        return encomendaPecaRepository.findById(id);
    }

    public EncomendaPecaEntity adicionarEncomenda(EncomendaPecaEntity encomenda) {
        return encomendaPecaRepository.save(encomenda);
    }

    public EncomendaPecaEntity atualizarEncomenda(long id, EncomendaPecaEntity encomenda) {
        encomenda.setIdEncomendaPeca(id);
        return encomendaPecaRepository.save(encomenda);
    }

    public void excluirEncomenda(int id) {
        encomendaPecaRepository.deleteById(id);
    }


}