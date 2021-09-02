package br.com.techpib.ap.ms_anotacoes_ebd.core.services.impl;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Usuario;
import br.com.techpib.ap.ms_anotacoes_ebd.core.ports.UsuarioRepository;
import br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Optional<Usuario> findUsuarioByIdUsuario(UUID idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    @Override
    public Optional<Usuario> findUsuarioByEmailAndSenha(String email, String senha) {
        return usuarioRepository.findUsuarioByEmailAndSenha(email, senha);
    }

}
