package com.sistemadenunciaamb.sda.models.dtos;

public class AndamentoDTO {
    private Integer id;

    private String cpfAnalista;
    private String dataCadastroAndamento;
    private String status;
    private String descricao;
    private String parecerTecnico;
    private Integer idDenuncia;

    public AndamentoDTO() {
        super();
    }

    public AndamentoDTO(Integer id, String cpfAnalista, String dataCadastroAndamento, String status, String descricao,
            String parecerTecnico, Integer idDenuncia) {
        this.id = id;
        this.cpfAnalista = cpfAnalista;
        this.dataCadastroAndamento = dataCadastroAndamento;
        this.status = status;
        this.descricao = descricao;
        this.parecerTecnico = parecerTecnico;
        this.idDenuncia = idDenuncia;
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

    public Integer getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(Integer idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    
}
