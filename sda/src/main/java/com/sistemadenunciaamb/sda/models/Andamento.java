package com.sistemadenunciaamb.sda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "andamentos")
public class Andamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_andamento")
    private Integer id;

    private String cpfAnalista;
    private String dataCadastroAndamento;
    private String status;
    private String descricao;
    private String parecerTecnico;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "denuncias")
    private Denuncia denuncia;

    public Andamento() {
        super();
    }

    public Andamento(Integer id, String cpfAnalista, String dataCadastroAndamento, String status, String descricao,
            String parecerTecnico, Denuncia denuncia) {
        this.id = id;
        this.cpfAnalista = cpfAnalista;
        this.dataCadastroAndamento = dataCadastroAndamento;
        this.status = status;
        this.descricao = descricao;
        this.parecerTecnico = parecerTecnico;
        this.denuncia = denuncia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpfAnalista() {
        return cpfAnalista;
    }

    public void setCpfAnalista(String cpfAnalista) {
        this.cpfAnalista = cpfAnalista;
    }

    public String getDataCadastroAndamento() {
        return dataCadastroAndamento;
    }

    public void setDataCadastroAndamento(String dataCadastroAndamento) {
        this.dataCadastroAndamento = dataCadastroAndamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getParecerTecnico() {
        return parecerTecnico;
    }

    public void setParecerTecnico(String parecerTecnico) {
        this.parecerTecnico = parecerTecnico;
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }

}
