package com.sistemadenunciaamb.sda.controllers;

import java.text.ParseException;
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

import com.sistemadenunciaamb.sda.models.dtos.DenunciaDTO;
import com.sistemadenunciaamb.sda.services.DenunciaService;

@RestController
@RequestMapping("/denuncia")
@CrossOrigin(origins = "*")
public class DenunciaController {

    @Autowired
    private DenunciaService denuncianteService;

    @GetMapping("/listar")
    public List<DenunciaDTO> listarDenuncias(){
        return denuncianteService.listarDenuncias();
    }

    @GetMapping("/listar-por-cpf/{cpf}")
    public List<DenunciaDTO>listarDenunciasPorCPF(@PathVariable String cpf){
        return denuncianteService.listarDenunciasPorCPF(cpf);
    }

    @GetMapping("/buscar-por-numrprotocolo/{numrProtocolo}")
    public DenunciaDTO buscarDenunciaPorId(@PathVariable String numrProtocolo) throws ParseException{
        return denuncianteService.buscarDenunciaPorNumProtocolo(numrProtocolo);
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarDenuncia(@RequestBody DenunciaDTO denuncia) throws ParseException{
        denuncianteService.cadastrarDenuncia(denuncia);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarDenuncia(@PathVariable Long id, @RequestBody DenunciaDTO denuncia) throws ParseException{
        denuncianteService.editarDenuncia(id, denuncia);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarDenuncia(@PathVariable Long id){
        denuncianteService.deletarDenuncia(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/filtrar")
    public List<DenunciaDTO> filtrarDenuncias(@RequestBody DenunciaDTO denuncia){
        return denuncianteService.filtrarDenuncias(denuncia);
    }
}
