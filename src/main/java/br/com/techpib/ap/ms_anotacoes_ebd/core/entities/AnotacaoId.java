package br.com.techpib.ap.ms_anotacoes_ebd.core.entities;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
public class AnotacaoId implements Serializable {

    @Type(type="uuid-char")
    @Column(name = "idUsuario")
    private UUID idUsuario;

    @NotNull
    @Column(name = "sequencialAnotacao")
    private Integer sequencialAnotacao;

}
