package br.com.techpib.ap.ms_anotacoes_ebd.core.data.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class AnotacaoForm {

    @NotNull
    private UUID idUsuario;

    private Integer sequencialAnotacao;

    @Size(max = 100)
    private String titulo;

    @Size(max = 5000)
    private String texto;

}
