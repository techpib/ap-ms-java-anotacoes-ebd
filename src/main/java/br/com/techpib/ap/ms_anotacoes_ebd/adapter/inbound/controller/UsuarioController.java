package br.com.techpib.ap.ms_anotacoes_ebd.adapter.inbound.controller;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.inbound.controller.utils.UsuarioUtils;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.dto.UsuarioDTO;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.form.UsuarioForm;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.form.UsuarioSenhaForm;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.mapper.UsuarioMapper;
import br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces.UsuarioService;
import br.com.techpib.ap.ms_anotacoes_ebd.core.utils.EncoderMD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Slf4j
@RestController
@RequestMapping(value = "/ap-anotacoes-ebd/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaUsuarios", allEntries = true)
    public ResponseEntity<UsuarioDTO> save(@RequestBody @Valid UsuarioForm usuarioForm) throws NoSuchAlgorithmException {
        if(UsuarioUtils.usuarioExiste(usuarioService, usuarioForm.getEmail()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return new ResponseEntity<>(usuarioMapper.converteParaDTO(usuarioService.save(usuarioMapper.converteParaEntity(usuarioForm))), HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = "listaUsuarios", allEntries = true)
    public ResponseEntity<UsuarioDTO> updateSenha(@RequestBody @Valid UsuarioSenhaForm usuarioSenhaForm) throws NoSuchAlgorithmException {
        if(!UsuarioUtils.usuarioESenhaValido(usuarioService, usuarioSenhaForm.getEmail(), EncoderMD5.encodeToMD5(usuarioSenhaForm.getSenhaAtual())) || !novaSenhaValida(usuarioSenhaForm.getSenhaAtual(), usuarioSenhaForm.getSenhaNova()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        var usuario = usuarioService.findUsuarioByEmail(usuarioSenhaForm.getEmail()).get();
        usuario.setSenha(usuarioSenhaForm.getSenhaNova());

        return new ResponseEntity<>(usuarioMapper.converteParaDTO(usuarioService.save(usuario)), HttpStatus.OK);
    }

    private boolean novaSenhaValida(String senhaAtual, String senhaNova){
        return senhaAtual.compareTo(senhaNova) == 0 ? false : true;
    }

}
