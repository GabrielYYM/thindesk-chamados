package com.pfc.thinkdeskChamado.repository;

import com.pfc.thinkdeskChamado.entity.Chamado;
import com.pfc.thinkdeskChamado.entity.enums.StatusChamado;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ChamadoRepository extends MongoRepository<Chamado, String> {
    List<Chamado> findByStatus(StatusChamado status);
    List<Chamado> findByUsuario(String usuario);
}
