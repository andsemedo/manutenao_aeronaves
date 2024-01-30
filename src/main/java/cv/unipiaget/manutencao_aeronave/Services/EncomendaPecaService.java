package cv.unipiaget.manutencao_aeronave.Services;

import cv.unipiaget.manutencao_aeronave.Entities.EncomendaPecaEntity;
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

    public List<EncomendaPecaEntity> listarTodasEncomendasPecas() {
        return encomendaPecaRepository.findAll();
    }

    public Optional<EncomendaPecaEntity> obterEncomendaPecaPorId(int id) {
        return encomendaPecaRepository.findById(id);
    }

    public EncomendaPecaEntity salvarEncomendaPeca(EncomendaPecaEntity encomendaPeca) {
        return encomendaPecaRepository.save(encomendaPeca);
    }

    public void excluirEncomendaPeca(int id) {
        encomendaPecaRepository.deleteById(id);
    }


    public EncomendaPecaEntity atualizarStatus(EncomendaPecaEntity encomenda) {
        return encomendaPecaRepository.save(encomenda);
    }
}