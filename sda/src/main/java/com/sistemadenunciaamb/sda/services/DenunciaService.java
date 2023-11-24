package com.sistemadenunciaamb.sda.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.sistemadenunciaamb.sda.enums.StatusDenunciaEnum;
import com.sistemadenunciaamb.sda.models.Andamento;
import com.sistemadenunciaamb.sda.models.Denuncia;
import com.sistemadenunciaamb.sda.models.dtos.AndamentoDTO;
import com.sistemadenunciaamb.sda.models.dtos.DenunciaDTO;
import com.sistemadenunciaamb.sda.repository.AndamentoRepository;
import com.sistemadenunciaamb.sda.repository.DenunciaRepository;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;
    @Autowired
    private AndamentoRepository andamentoRepository;

    private final SimpleDateFormat SDF_PT_BR = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat SDF_EN_US = new SimpleDateFormat("yyyy-MM-dd");
    
    public List<DenunciaDTO> listarDenuncias(){
        List<Denuncia> denuncias = denunciaRepository.findAll();
        List<DenunciaDTO> retornoDenunciaDTOs = new ArrayList<>();

        for(Denuncia denuncia : denuncias){
            DenunciaDTO denunciaDTO = getDenunciaDTO(denuncia);
            retornoDenunciaDTOs.add(denunciaDTO);
        }

        return retornoDenunciaDTOs;
    }

    public List<DenunciaDTO> listarDenunciasPorCPF(String cpf){
        List<Denuncia> denuncias = denunciaRepository.findByCpfDenunciante(cpf);
        List<DenunciaDTO> retornoDenunciaDTOs = new ArrayList<>();

        for(Denuncia denuncia : denuncias){
            DenunciaDTO denunciaDTO = getDenunciaDTO(denuncia);
            retornoDenunciaDTOs.add(denunciaDTO);
        }

        return retornoDenunciaDTOs;
    }

    public DenunciaDTO buscarDenunciaPorNumProtocolo(String numrProtocolo) throws ParseException {
        Denuncia denuncia = denunciaRepository.findByNumrProtocolo(numrProtocolo);

        Date dataIncidenteAux = SDF_PT_BR.parse(denuncia.getDataIncidente());
        Date dataCadastroAux = SDF_PT_BR.parse(denuncia.getDataIncidente());

        DenunciaDTO denunciaDTO = getDenunciaDTO(denuncia);

        denunciaDTO.setDataCadastro(SDF_EN_US.format(dataCadastroAux));
        denunciaDTO.setDataIncidente(SDF_EN_US.format(dataIncidenteAux));

        return denunciaDTO;
    }

    public List<DenunciaDTO> filtrarDenuncias(DenunciaDTO denuncia) {
        String numrProtocolo = ObjectUtils.isEmpty(denuncia.getNumrProtocolo()) ? null : denuncia.getNumrProtocolo();
        String municipio = ObjectUtils.isEmpty(denuncia.getMunicipio()) ? null : denuncia.getMunicipio();
        String status = ObjectUtils.isEmpty(denuncia.getStatus()) ? null : denuncia.getStatus();
        String dataIncidente = ObjectUtils.isEmpty(denuncia.getDataIncidente()) ? null : denuncia.getDataIncidente();
        String dataCadastro = ObjectUtils.isEmpty(denuncia.getDataCadastro()) ? null : denuncia.getDataCadastro();
        String categoria = ObjectUtils.isEmpty(denuncia.getCategoria()) ? null : denuncia.getCategoria();

        if (numrProtocolo == null && municipio == null && status == null && dataIncidente == null && dataCadastro == null && categoria == null) {
            return listarDenuncias();
        } else {
            List<Denuncia> denuncias = denunciaRepository.findAllByQuery(numrProtocolo, municipio, status, dataIncidente, dataCadastro, categoria);
            List<DenunciaDTO> retornoDenunciaDTOs = new ArrayList<>();

            for (Denuncia denunciaAux : denuncias) {
                DenunciaDTO denunciaDTO = getDenunciaDTO(denunciaAux);
                retornoDenunciaDTOs.add(denunciaDTO);
            }

            return retornoDenunciaDTOs;
        }
    }
    
    @Transactional
    public void cadastrarDenuncia(DenunciaDTO denuncia) throws ParseException {
        validateDenuncia(denuncia);

        Denuncia denunciaToSave = new Denuncia();

        denunciaToSave.setId(denuncia.getId());
        denunciaToSave.setCep(denuncia.getCep());
        denunciaToSave.setBairro(denuncia.getBairro());
        denunciaToSave.setMunicipio(denuncia.getMunicipio());
        denunciaToSave.setReferencia(denuncia.getReferencia());
        denunciaToSave.setLatitude(denuncia.getLatitude());
        denunciaToSave.setLongitude(denuncia.getLongitude());
        denunciaToSave.setRua(denuncia.getRua());

        denunciaToSave.setDenunciante(denuncia.getDenunciante());
        denunciaToSave.setCategoria(denuncia.getCategoria());
        denunciaToSave.setTexto(denuncia.getTexto());
        denunciaToSave.setProvavelAutor(denuncia.getProvavelAutor());

        denunciaToSave.setStatus(StatusDenunciaEnum.CRIADA.getDescricao());
        denunciaToSave.setCpfDenunciante(denuncia.getCpfDenunciante());
        denunciaToSave.setNumrProtocolo(denuncia.getId().toString()+"2023");

        Date dataAux = SDF_EN_US.parse(denuncia.getDataIncidente());
        denunciaToSave.setDataIncidente(SDF_PT_BR.format(dataAux));
        denunciaToSave.setDataCadastro(SDF_PT_BR.format(new Date()));
        
        denunciaRepository.save(denunciaToSave);
    }

    @Transactional
    public void deletarDenuncia(Long id) {
        denunciaRepository.deleteById(Integer.parseInt(id.toString()));
    }

    @Transactional
    public void editarDenuncia(Long id, DenunciaDTO denuncia) throws ParseException {
        validateDenuncia(denuncia);

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

        denunciaRepository.save(denunciaToUpdate);
    }

    @Transactional
    public void cadastrarAndamento(AndamentoDTO andamento) {
        Denuncia denuncia = denunciaRepository.findById(Integer.parseInt(andamento.getIdDenuncia().toString())).get();
        
        List<Andamento> andamentoList = new ArrayList<>();
        Andamento andamentoJPA = new Andamento();

        andamentoJPA.setDataCadastroAndamento(SDF_PT_BR.format(new Date()));
        andamentoJPA.setDescricao(andamento.getDescricao());
        andamentoJPA.setParecerTecnico(andamento.getParecerTecnico());
        andamentoJPA.setCpfAnalista(andamento.getCpfAnalista());
        andamentoJPA.setDescricao(andamento.getDescricao());
        andamentoJPA.setDenuncia(denuncia);

        andamentoRepository.save(andamentoJPA);

        andamentoList.add(andamentoJPA);
        denuncia.setAndamentos(andamentoList);

        denuncia.setStatus(andamento.getStatus());
        denunciaRepository.save(denuncia);
    }

    private void validateDenuncia(DenunciaDTO denuncia) throws ParseException {
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

        Date dataAux = SDF_EN_US.parse(denuncia.getDataIncidente());
        if(dataAux.after(new Date())){
            throw new RuntimeException("Data do incidente não pode ser maior que a data atual");
        }
    }

    private DenunciaDTO getDenunciaDTO(Denuncia denuncia) {
        DenunciaDTO denunciaDTO = new DenunciaDTO();

        denunciaDTO.setId(denuncia.getId());
        denunciaDTO.setBairro(denuncia.getBairro());
        denunciaDTO.setCategoria(denuncia.getCategoria());
        denunciaDTO.setCep(denuncia.getCep());
        denunciaDTO.setCpfDenunciante(denuncia.getCpfDenunciante());
        denunciaDTO.setDataCadastro(denuncia.getDataCadastro());
        denunciaDTO.setDataIncidente(denuncia.getDataIncidente());
        denunciaDTO.setDenunciante(denuncia.getDenunciante());
        denunciaDTO.setLatitude(denuncia.getLatitude());
        denunciaDTO.setLongitude(denuncia.getLongitude());
        denunciaDTO.setMunicipio(denuncia.getMunicipio());
        denunciaDTO.setNumrProtocolo(denuncia.getNumrProtocolo());
        denunciaDTO.setProvavelAutor(denuncia.getProvavelAutor());
        denunciaDTO.setReferencia(denuncia.getReferencia());
        denunciaDTO.setRua(denuncia.getRua());
        denunciaDTO.setStatus(denuncia.getStatus());
        denunciaDTO.setTexto(denuncia.getTexto());
        return denunciaDTO;
    }

}
