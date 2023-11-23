package com.sistemadenunciaamb.sda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "denuncias")
public class Denuncia{

    @Id
    @Column(name = "id_denuncia")
    private Integer id;
    private String rua;
    private String denunciante;
    private String bairro;
    private String municipio;
    private String cep;
    private String referencia;
    private String latitude;
    private String longitude;
    private String texto;
    private String status;
    private String parecertecnico;
    private String dataIncidente;
    private String dataCadastro;
    private String provavelAutor;
    private String categoria;
    private String cpfDenunciante;

    public Denuncia() {
        super();
    }

    public Denuncia(Integer id, String rua, String denunciante, String bairro, String municipio, String cep,
            String referencia, String latitude, String longitude, String texto, String status, String parecertecnico,
            String dataIncidente, String dataCadastro, String provavelAutor, String categoria, String cpfDenunciante) {
        super();
        this.id = id;
        this.rua = rua;
        this.denunciante = denunciante;
        this.bairro = bairro;
        this.municipio = municipio;
        this.cep = cep;
        this.referencia = referencia;
        this.latitude = latitude;
        this.longitude = longitude;
        this.texto = texto;
        this.status = status;
        this.parecertecnico = parecertecnico;
        this.dataIncidente = dataIncidente;
        this.dataCadastro = dataCadastro;
        this.provavelAutor = provavelAutor;
        this.categoria = categoria;
        this.cpfDenunciante = cpfDenunciante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getDenunciante() {
        return denunciante;
    }

    public void setDenunciante(String denunciante) {
        this.denunciante = denunciante;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParecertecnico() {
        return parecertecnico;
    }

    public void setParecertecnico(String parecertecnico) {
        this.parecertecnico = parecertecnico;
    }

    public String getDataIncidente() {
        return dataIncidente;
    }

    public void setDataIncidente(String dataIncidente) {
        this.dataIncidente = dataIncidente;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getProvavelAutor() {
        return provavelAutor;
    }

    public void setProvavelAutor(String provavelAutor) {
        this.provavelAutor = provavelAutor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCpfDenunciante() {
        return cpfDenunciante;
    }

    public void setCpfDenunciante(String cpfDenunciante) {
        this.cpfDenunciante = cpfDenunciante;
    }

    

    
    
}
