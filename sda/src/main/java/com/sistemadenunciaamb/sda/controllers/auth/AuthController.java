package com.sistemadenunciaamb.sda.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemadenunciaamb.sda.models.auth.dtos.LoginResponseDTO;
import com.sistemadenunciaamb.sda.models.auth.dtos.RegistrationDTO;
import com.sistemadenunciaamb.sda.services.auth.AuthenticationService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registerDTO) throws Exception{
        authenticationService.registerUser(registerDTO.getCpf(), registerDTO.getPassword(), registerDTO.getRole());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getCpf(), body.getPassword());
    }
}
