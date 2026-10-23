package com.pfc.thinkdeskChamado.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pfc.thinkdeskChamado.entity.enums.StatusChamado;
import com.pfc.thinkdeskChamado.entity.enums.TipoChamado;

@Document(collection = "chamados")
public class Chamado {

    @Id
    private String id;
    private String descricao;
    private StatusChamado status;
    private TipoChamado tipo;
    private String tecnico;
    private String usuario;
    private String titulo;
    private String solucao;
    private LocalDateTime CriadoHa;
    private LocalDateTime FechadoHa;

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public LocalDateTime getFechadoHa() {
        return FechadoHa;
    }

    public void setFechadoHa(LocalDateTime fechadoHa) {
        FechadoHa = fechadoHa;
    }

    public LocalDateTime getCriadoHa() {
        return CriadoHa;
    }

    public void setCriadoHa(LocalDateTime criadoA) {
        CriadoHa = criadoA;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public TipoChamado getTipo() {
        return tipo;
    }

    public void setTipo(TipoChamado tipo) {
        this.tipo = tipo;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
