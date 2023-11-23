package com.sistemadenunciaamb.sda.enums;

public enum StatusDenunciaEnum {
    CRIADA("1", "Criada"),
    EM_ANDAMENTO("2", "Em andamento"),
    CONCLUIDA("3", "Concluída");

    private String codigo;
    private String descricao;

    StatusDenunciaEnum(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static StatusDenunciaEnum getEnum(String codigo) {
        for (StatusDenunciaEnum status : StatusDenunciaEnum.values()) {
            if (status.getCodigo().equals(codigo)) {
                return status;
            }
        }
        return null;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
