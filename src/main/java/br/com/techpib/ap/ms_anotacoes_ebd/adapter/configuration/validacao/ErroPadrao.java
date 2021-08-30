package br.com.techpib.ap.ms_anotacoes_ebd.adapter.configuration.validacao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroPadrao {

    private String titulo;
    private String descricao;

}
