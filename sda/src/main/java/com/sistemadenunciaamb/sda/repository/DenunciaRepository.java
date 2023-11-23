package com.sistemadenunciaamb.sda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemadenunciaamb.sda.models.Denuncia;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Integer>{
    public List<Denuncia> findByCpfDenunciante(String cpf);
    public Denuncia findByNumrProtocolo(String numrProtocolo);
    
}
