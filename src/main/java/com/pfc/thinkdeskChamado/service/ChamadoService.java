package com.pfc.thinkdeskChamado.service;

import com.pfc.thinkdeskChamado.DTO.ChamadoDTO;
import com.pfc.thinkdeskChamado.entity.Chamado;
import com.pfc.thinkdeskChamado.entity.enums.StatusChamado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pfc.thinkdeskChamado.repository.ChamadoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public ChamadoDTO.OpenResponse criarChamado(ChamadoDTO.OpenRequest request, String usuarioSolicitante) {
        Chamado chamado = new Chamado();

        chamado.setTitulo(request.titulo());
        chamado.setDescricao(request.descricao());
        chamado.setUsuario(usuarioSolicitante);
        chamado.setStatus(StatusChamado.ABERTO);
        chamado.setCriadoHa(LocalDateTime.now());

        Chamado salvo = chamadoRepository.save(chamado);
        return ChamadoDTO.OpenResponse.fromEntity(salvo);
    }
}    