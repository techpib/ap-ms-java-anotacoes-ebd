package br.com.techpib.ap.ms_anotacoes_ebd.core.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class TokenDto {

    private UUID idUsuario;
    private String token;
    private String tipo;

}
