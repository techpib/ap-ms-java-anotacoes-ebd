package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "anotacao")
@Data
public class Anotacao {

    @EmbeddedId
    private AnotacaoId anotacaoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    @MapsId("idUsuario")
    private Usuario usuario;

    @Size(max = 100)
    @Column(columnDefinition = "VARCHAR(100)", name = "titulo")
    private String titulo;

    @Size(max = 5000)
    @Column(columnDefinition = "VARCHAR(5000)", name = "texto")
    private String texto;


    @ManyToOne
    @JoinColumn(name = "idStatus")
    private Status status;

    @NotNull
    @Column(name = "dataHoraUltimaAtualizacao")
    private Date dataHoraUltimaAtualizacao;

    @Override
    public String toString() {
        return "Anotacao{" +
                "anotacaoId=" + anotacaoId +
                ", usuario=" + usuario.toString() +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", status=" + status.toString() +
                ", dataHoraUltimaAtualizacao=" + dataHoraUltimaAtualizacao +
                '}';
    }
}
