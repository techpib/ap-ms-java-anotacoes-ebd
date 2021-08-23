package br.com.techpib.ap.ms_anotacoes_ebd.core.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto {

    private String token;
    private String tipo;

}
