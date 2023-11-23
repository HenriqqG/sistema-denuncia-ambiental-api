package com.sistemadenunciaamb.sda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemadenunciaamb.sda.models.Denuncia;
import com.sistemadenunciaamb.sda.services.DenunciaService;

@RestController
@RequestMapping("/denuncia")
@CrossOrigin(origins = "*")
public class DenunciaController {

    @Autowired
    private DenunciaService denuncianteService;

    @GetMapping("/listar")
    public List<Denuncia> listarDenuncias(){
        return denuncianteService.listarDenuncias();
    }

    @GetMapping("/listar-por-cpf/{cpf}")
    public List<Denuncia>listarDenunciasPorCPF(@PathVariable String cpf){
        return denuncianteService.listarDenunciasPorCPF(cpf);
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarDenuncia(@RequestBody Denuncia denuncia){
        denuncianteService.cadastrarDenuncia(denuncia);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarDenuncia(@PathVariable Long id, @RequestBody Denuncia denuncia){
        denuncianteService.editarDenuncia(id, denuncia);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarDenuncia(@PathVariable Long id){
        denuncianteService.deletarDenuncia(id);
        return ResponseEntity.ok().build();
    }
}
