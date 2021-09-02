package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "status")
@Data
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatus;

    @OneToMany(
            mappedBy = "status",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Anotacao> anotacao;

    @NotNull
    @NotBlank
    @Size(max = 45)
    @Column(columnDefinition = "VARCHAR(45)", name = "statusAnotacao")
    private String statusAnotacao;

    @NotNull
    @NotBlank
    @Column(name = "dataHoraUltimaAtualizacao")
    private Date dataHoraUltimaAtualizacao;

    @Override
    public String toString() {
        return "Status{" +
                "idStatus=" + idStatus +
                ", anotacao=" + anotacao.toString() +
                ", statusAnotacao='" + statusAnotacao + '\'' +
                ", dataHoraUltimaAtualizacao=" + dataHoraUltimaAtualizacao +
                '}';
    }
}
