package br.com.techpib.ap.ms_anotacoes_ebd.core.data.mapper;

import br.com.techpib.ap.ms_anotacoes_ebd.core.data.dto.UsuarioDTO;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.form.UsuarioForm;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.mapper.interfaces.MapperDTO;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.mapper.interfaces.MapperUsuarioForm;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class UsuarioMapper implements MapperDTO<Usuario, UsuarioDTO>, MapperUsuarioForm<Usuario, UsuarioForm> {

    private static final ModelMapper mapper = new ModelMapper();

    @Override
        public UsuarioDTO converteParaDTO(Usuario usuario) {
        return mapper.typeMap(Usuario.class, UsuarioDTO.class).map(usuario);
    }

    @Override
    public Page<UsuarioDTO> converteParaDTO(Page<Usuario> usuarios) {
        return usuarios.map(new Function<Usuario, UsuarioDTO>(){
            @Override
            public UsuarioDTO apply(Usuario usuario) {
                return mapper.typeMap(Usuario.class, UsuarioDTO.class).map(usuario);
            }
        });
    }

    @Override
    public Usuario converteParaEntity(UsuarioForm usuarioForm) {
        return mapper.typeMap(UsuarioForm.class, Usuario.class).addMappings(mapper -> {
            mapper.map(src -> new Date(), Usuario::setDataHoraUltimaAtualizacao);
            mapper.map(src -> usuarioForm.getEmail().toLowerCase(), Usuario::setEmail);
        }).map(usuarioForm);
    }
}
