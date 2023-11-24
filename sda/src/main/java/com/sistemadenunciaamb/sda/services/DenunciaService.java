package com.sistemadenunciaamb.sda.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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
        denuncia.setDataCadastro(SDF_PT_BR.format(new Date()));

        validateDenuncia(denuncia);
        return denunciaRepository.save(denuncia);
    }

    public void deletarDenuncia(Long id) {
        denunciaRepository.deleteById(Integer.parseInt(id.toString()));
    }

    public void editarDenuncia(Long id, Denuncia denuncia) throws ParseException {
        Denuncia denunciaToUpdate = denunciaRepository.findById(Integer.parseInt(id.toString())).get();

        denunciaToUpdate.setBairro(denuncia.getBairro());
        denunciaToUpdate.setCategoria(denuncia.getCategoria());
        denunciaToUpdate.setCep(denuncia.getCep());
        denunciaToUpdate.setCpfDenunciante(denuncia.getCpfDenunciante());

        Date dataCadastroAux = SDF_EN_US.parse(denuncia.getDataCadastro());
        denunciaToUpdate.setDataCadastro(SDF_PT_BR.format(dataCadastroAux));

         Date dataIncidenteAux = SDF_EN_US.parse(denuncia.getDataIncidente());
        denunciaToUpdate.setDataIncidente(SDF_PT_BR.format(dataIncidenteAux));

        denunciaToUpdate.setLatitude(denuncia.getLatitude());
        denunciaToUpdate.setLongitude(denuncia.getLongitude());
        denunciaToUpdate.setNumrProtocolo(denuncia.getNumrProtocolo());
        denunciaToUpdate.setProvavelAutor(denuncia.getProvavelAutor());
        denunciaToUpdate.setStatus(denuncia.getStatus());
        denunciaToUpdate.setRua(denuncia.getRua());
        denunciaToUpdate.setTexto(denuncia.getTexto());
        denunciaToUpdate.setMunicipio(denuncia.getMunicipio());
        denunciaToUpdate.setReferencia(denuncia.getReferencia());

        validateDenuncia(denunciaToUpdate);
        denunciaRepository.save(denunciaToUpdate);
    }

    private void validateDenuncia(Denuncia denuncia) throws ParseException {
        String msg = "Todos os campos obrigatorios devem ser preenchidos";

        if(denuncia.getBairro() == null || denuncia.getBairro().isEmpty()){
            throw new RuntimeException(msg);
        }
        if(denuncia.getCep() == null || denuncia.getCep().isEmpty()){
            throw new RuntimeException(msg);
        }
        if(denuncia.getDataIncidente() == null || denuncia.getDataIncidente().isEmpty()){
            throw new RuntimeException(msg);
        }
        if(denuncia.getMunicipio() == null || denuncia.getMunicipio().isEmpty()){
            throw new RuntimeException(msg);
        }
        if(denuncia.getRua() == null || denuncia.getRua().isEmpty()){
            throw new RuntimeException(msg);
        }
        if(denuncia.getTexto() == null || denuncia.getTexto().isEmpty()){
            throw new RuntimeException(msg);
        }
        if(denuncia.getDenunciante() == null || denuncia.getDenunciante().isEmpty()){
            throw new RuntimeException(msg);
        }

        Date dataAux = SDF_PT_BR.parse(denuncia.getDataIncidente());
        if(dataAux.after(new Date())){
            throw new RuntimeException("Data do incidente não pode ser maior que a data atual");
        }
    }

    public List<Denuncia> filtrarDenuncias(Denuncia denuncia) {
        String numrProtocolo = ObjectUtils.isEmpty(denuncia.getNumrProtocolo()) ? null : denuncia.getNumrProtocolo();
        String municipio = ObjectUtils.isEmpty(denuncia.getMunicipio()) ? null : denuncia.getMunicipio();
        String status = ObjectUtils.isEmpty(denuncia.getStatus()) ? null : denuncia.getStatus();
        String dataIncidente = ObjectUtils.isEmpty(denuncia.getDataIncidente()) ? null : denuncia.getDataIncidente();
        String dataCadastro = ObjectUtils.isEmpty(denuncia.getDataCadastro()) ? null : denuncia.getDataCadastro();
        String categoria = ObjectUtils.isEmpty(denuncia.getCategoria()) ? null : denuncia.getCategoria();

        if (numrProtocolo == null && municipio == null && status == null && dataIncidente == null && dataCadastro == null && categoria == null) {
            return denunciaRepository.findAll();
        } else {
            return denunciaRepository.findAllByQuery(numrProtocolo, municipio, status, dataIncidente, dataCadastro, categoria);
        }
    }

}
