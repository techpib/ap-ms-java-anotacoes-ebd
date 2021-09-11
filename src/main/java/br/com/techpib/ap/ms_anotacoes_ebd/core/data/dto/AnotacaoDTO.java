package br.com.techpib.ap.ms_anotacoes_ebd.core.data.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class AnotacaoDTO {

    private UUID idUsuario;
    private Integer sequencialAnotacao;
    private String titulo;
    private String texto;
    private Date dataHoraUltimaAtualizacao;

}
