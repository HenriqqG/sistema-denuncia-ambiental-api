package com.sistemadenunciaamb.sda.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemadenunciaamb.sda.models.auth.ApplicationUser;
import com.sistemadenunciaamb.sda.models.auth.dtos.LoginResponseDTO;
import com.sistemadenunciaamb.sda.models.auth.dtos.RegistrationDTO;
import com.sistemadenunciaamb.sda.services.auth.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO registerDTO){
        return authenticationService.registerUser(registerDTO.getCpf(), registerDTO.getPassword(), registerDTO.getRole());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getCpf(), body.getPassword());
    }
}
