package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities;

import br.com.techpib.ap.ms_anotacoes_ebd.core.utils.EncoderMD5;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Entity
@Table(name = "usuario")
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
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
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

    public void setAnotacoes(List<Anotacao> anotacoes) {
        this.anotacoes = anotacoes.stream().sorted(Comparator.comparing(anotacao -> anotacao.getAnotacaoId().getSequencialAnotacao())).collect(Collectors.toList());
    }

    public void setSenha(String senha) throws NoSuchAlgorithmException {
        this.senha = EncoderMD5.encodeToMD5(senha);
    }

    private Long getUltimoSequencialAnotacao(){
        return anotacoes.isEmpty() ? 0 : anotacoes.get(anotacoes.size()-1).getAnotacaoId().getSequencialAnotacao();
    }

    public Long getProximoSequencialAnotacao(){
        return getUltimoSequencialAnotacao() +1;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataHoraUltimaAtualizacao=" + dataHoraUltimaAtualizacao +
                ", anotacoes=" + anotacoes.toString() +
                '}';
    }
}
