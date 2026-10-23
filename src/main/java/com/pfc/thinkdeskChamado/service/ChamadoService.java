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

    public List<ChamadoDTO.ListResponse> listarPorUsuario(String usuario) {
        return chamadoRepository.findByUsuario(usuario)
            .stream()
            .map(ChamadoDTO.ListResponse::fromEntity)
            .toList();
    }
    //Não utilizado
    public List<Chamado> listarChamados() {
        return chamadoRepository.findAll();
    }

    public ChamadoDTO.DetailResponse buscarPorId(String id) {
        return chamadoRepository.findById(id)
            .map(ChamadoDTO.DetailResponse::fromEntity)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado não encontrado com id: " + id));
    }
    //Não utilizado
    public Chamado atualizarChamado(String id, Chamado chamadoAtualizado) {
        return chamadoRepository.findById(id)
                .map(chamado -> {
                    chamado.setDescricao(chamadoAtualizado.getDescricao());
                    chamado.setStatus(chamadoAtualizado.getStatus());
                    chamado.setTipo(chamadoAtualizado.getTipo());
                    chamado.setTecnico(chamadoAtualizado.getTecnico());
                    chamado.setUsuario(chamadoAtualizado.getUsuario());
                    return chamadoRepository.save(chamado);
                })
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado com id: " + id));
    }
}    