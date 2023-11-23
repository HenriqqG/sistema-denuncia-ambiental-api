package com.sistemadenunciaamb.sda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemadenunciaamb.sda.models.Denuncia;
import com.sistemadenunciaamb.sda.repository.DenunciaRepository;

@Service
@Transactional
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;
    
    public List<Denuncia> listarDenuncias(){
        return denunciaRepository.findAll();
    }

    public List<Denuncia> listarDenunciasPorCPF(String cpf){
        return denunciaRepository.findByCpfDenunciante(cpf);
    }

    public Denuncia cadastrarDenuncia(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    public void deletarDenuncia(Long id) {
        denunciaRepository.deleteById(Integer.parseInt(id.toString()));
    }

    public void editarDenuncia(Long id, Denuncia denuncia) {
        // Denuncia denunciaFromDB = denunciaRepository.findById(Integer.parseInt(id.toString())).get();
    }
}
