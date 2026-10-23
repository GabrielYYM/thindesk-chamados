package com.pfc.thinkdeskChamado.DTO;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.pfc.thinkdeskChamado.entity.Chamado;
import com.pfc.thinkdeskChamado.entity.enums.StatusChamado;

public class ChamadoDTO {
    public record OpenRequest(
            @NotBlank(message = "Preencha o campo titulo")
            @Size (min = 2, max = 100, message = "A menssagem deve ter entre 2 a 100 caracteres" ) 
            String titulo,

            @NotBlank(message = "Preencha o campo descrição")
            @Size (min = 10, max = 400, message = "A descrição deve ter entre 10 a 400 caracteres" )
            String descricao
    ) {
    }

    public record OpenResponse(
            String id,
            String titulo,
            String descricao,
            StatusChamado status,
            String usuario,
            LocalDateTime dataAbertura) {

        public static OpenResponse fromEntity(Chamado chamado) {
            return new OpenResponse(
                    chamado.getId(),
                    chamado.getTitulo(),
                    chamado.getDescricao(),
                    chamado.getStatus(),
                    chamado.getUsuario(),
                    chamado.getCriadoHa()
            );
        }
    }
}    