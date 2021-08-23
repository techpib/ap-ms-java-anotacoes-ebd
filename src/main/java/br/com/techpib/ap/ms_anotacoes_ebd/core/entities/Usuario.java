package br.com.techpib.ap.ms_anotacoes_ebd.core.entities;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "usuario")
@Data
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(name = "idUsuario")
    private UUID idUsuario;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(columnDefinition = "VARCHAR(100)", name = "nome")
    private String nome;

    @NotNull
    @NotBlank
    @Size(max = 256)
    @Column(columnDefinition = "VARCHAR(256)", name = "email")
    private String email;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "CHAR(32)", name = "senha")
    private String senha;

    @NotNull
    @NotBlank
    @Column(name = "dataHoraUltimaAtualizacao")
    private Date dataHoraUltimaAtualizacao;

    @OneToMany(
            mappedBy = "usuario",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Anotacao> anotacoes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
