package com.sistemadenunciaamb.sda.services.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemadenunciaamb.sda.models.auth.ApplicationUser;
import com.sistemadenunciaamb.sda.models.auth.Role;
import com.sistemadenunciaamb.sda.models.auth.dtos.LoginResponseDTO;
import com.sistemadenunciaamb.sda.repository.auth.RoleRepository;
import com.sistemadenunciaamb.sda.repository.auth.UserRepository;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser(String username, String password, String role) throws Exception{
        if(!userRepository.findByUsername(username).isEmpty()){
            throw new Exception("Usuário já existe");
        }

        String encodedPass = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority(role).get();

        Set<Role> authority = new HashSet<>();
        authority.add(userRole);

        return userRepository.save(new ApplicationUser(username, encodedPass, authority));
    }

    public LoginResponseDTO loginUser(String username, String password){
        try{
            Authentication auth= authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(username, password));

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
        }catch (AuthenticationException e){
            e.printStackTrace();
            return new LoginResponseDTO(null, "");
        }
    }
    
}
