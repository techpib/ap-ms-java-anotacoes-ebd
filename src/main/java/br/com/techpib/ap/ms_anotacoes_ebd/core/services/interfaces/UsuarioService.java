package br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {

    Usuario save(Usuario usuario);

    Usuario update(Usuario usuario);

    Optional<Usuario> findUsuarioByEmail(String email);

    Optional<Usuario> findUsuarioByIdUsuario(UUID idUsuario);

}
