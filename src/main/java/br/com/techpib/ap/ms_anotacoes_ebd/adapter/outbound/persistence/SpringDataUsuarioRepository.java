package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataUsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findUsuarioByEmailAndSenha(String email, String senha);

}
