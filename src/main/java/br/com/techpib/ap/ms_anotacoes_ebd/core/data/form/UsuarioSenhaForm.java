package br.com.techpib.ap.ms_anotacoes_ebd.core.data.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioSenhaForm {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 256)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 5)
    private String senhaAtual;

    @NotNull
    @NotBlank
    @Size(min = 5)
    private String senhaNova;

}
