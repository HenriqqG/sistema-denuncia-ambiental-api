package com.sistemadenunciaamb.sda.models.auth.dtos;

public class RegistrationDTO {
    
    private String cpf;
    private String password;
    private String role;

    
    public RegistrationDTO() {
        super();
    }

    public RegistrationDTO(String cpf, String password, String role) {
        super();
        this.cpf = cpf;
        this.password = password;
        this.role = role;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
