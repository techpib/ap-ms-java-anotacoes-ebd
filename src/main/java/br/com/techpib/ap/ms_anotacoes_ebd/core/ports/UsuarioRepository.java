package br.com.techpib.ap.ms_anotacoes_ebd.core.ports;

import br.com.techpib.ap.ms_anotacoes_ebd.core.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {

    Usuario save(Usuario usuario);
    Page<Usuario> findAll(Pageable pageable);
    Optional<Usuario> findById(UUID idUsuario);
    Optional<Usuario> findByEmail(String email);

}
