package com.sistemadenunciaamb.sda.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemadenunciaamb.sda.enums.StatusDenunciaEnum;
import com.sistemadenunciaamb.sda.models.Denuncia;
import com.sistemadenunciaamb.sda.repository.DenunciaRepository;

@Service
@Transactional
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    private final SimpleDateFormat SDF_PT_BR = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat SDF_EN_US = new SimpleDateFormat("yyyy-MM-dd");
    
    public List<Denuncia> listarDenuncias(){
        return denunciaRepository.findAll();
    }

    public List<Denuncia> listarDenunciasPorCPF(String cpf){
        return denunciaRepository.findByCpfDenunciante(cpf);
    }

    public Denuncia buscarDenunciaPorNumProtocolo(String numrProtocolo) throws ParseException {
        Denuncia denuncia = denunciaRepository.findByNumrProtocolo(numrProtocolo);

        Date dataIncidenteAux = SDF_PT_BR.parse(denuncia.getDataIncidente());
        Date dataCadastroAux = SDF_PT_BR.parse(denuncia.getDataIncidente());

        denuncia.setDataCadastro(SDF_EN_US.format(dataCadastroAux));
        denuncia.setDataIncidente(SDF_EN_US.format(dataIncidenteAux));
        return denuncia;
    }

    public Denuncia cadastrarDenuncia(Denuncia denuncia) throws ParseException {
        denuncia.setStatus(StatusDenunciaEnum.CRIADA.getDescricao());
        denuncia.setCpfDenunciante("05497491103");
        denuncia.setNumrProtocolo(denuncia.getId().toString()+"2023");

        Date dataAux = SDF_EN_US.parse(denuncia.getDataIncidente());
        denuncia.setDataIncidente(SDF_PT_BR.format(dataAux));
        
        return denunciaRepository.save(denuncia);
    }

    public void deletarDenuncia(Long id) {
        denunciaRepository.deleteById(Integer.parseInt(id.toString()));
    }

    public void editarDenuncia(Long id, Denuncia denuncia) {
        Denuncia denunciaToUpdate = denunciaRepository.findById(Integer.parseInt(id.toString())).get();

        denunciaToUpdate.setBairro(denuncia.getBairro());
        denunciaToUpdate.setCategoria(denuncia.getCategoria());
        denunciaToUpdate.setCep(denuncia.getCep());
        denunciaToUpdate.setCpfDenunciante(denuncia.getCpfDenunciante());
        denunciaToUpdate.setDataCadastro(denuncia.getDataCadastro());
        denunciaToUpdate.setDataIncidente(denuncia.getDataIncidente());
        denunciaToUpdate.setLatitude(denuncia.getLatitude());
        denunciaToUpdate.setLongitude(denuncia.getLongitude());
        denunciaToUpdate.setNumrProtocolo(denuncia.getNumrProtocolo());
        denunciaToUpdate.setProvavelAutor(denuncia.getProvavelAutor());
        denunciaToUpdate.setStatus(denuncia.getStatus());
        denunciaToUpdate.setRua(denuncia.getRua());
        denunciaToUpdate.setTexto(denuncia.getTexto());
        denunciaToUpdate.setMunicipio(denuncia.getMunicipio());
        denunciaToUpdate.setReferencia(denuncia.getReferencia());

        denunciaRepository.save(denunciaToUpdate);
    }
}