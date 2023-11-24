package com.sistemadenunciaamb.sda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistemadenunciaamb.sda.models.Denuncia;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Integer>{
    public List<Denuncia> findByCpfDenunciante(String cpf);
    public Denuncia findByNumrProtocolo(String numrProtocolo);

    @Query("SELECT d FROM Denuncia d WHERE d.numrProtocolo = ?1 OR d.municipio = ?2 OR d.status = ?3 OR d.dataIncidente = ?4 OR d.dataCadastro = ?5 OR d.categoria = ?6")
    public List<Denuncia> findAllByQuery(String numrProtocolo, String municipio, String status, String dataIncidente, String dataCadastro, String categoria);
    
}
