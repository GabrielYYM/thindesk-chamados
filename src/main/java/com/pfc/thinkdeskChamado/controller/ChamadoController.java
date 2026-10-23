package com.pfc.thinkdeskChamado.controller;

import com.pfc.thinkdeskChamado.DTO.ChamadoDTO;
import com.pfc.thinkdeskChamado.entity.Chamado;
import com.pfc.thinkdeskChamado.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @PostMapping
    public ResponseEntity<ChamadoDTO.OpenResponse> criarChamado(
            @Valid @RequestBody ChamadoDTO.OpenRequest request,
            Principal principal){
        if (principal == null) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado");
    }
    ChamadoDTO.OpenResponse response = chamadoService.criarChamado(request, principal.getName());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<ChamadoDTO.ListResponse>> listarPorUsuario(@PathVariable String id) {
        List<ChamadoDTO.ListResponse> chamados = chamadoService.listarPorUsuario(id);
        return ResponseEntity.ok(chamados);
    }
}    