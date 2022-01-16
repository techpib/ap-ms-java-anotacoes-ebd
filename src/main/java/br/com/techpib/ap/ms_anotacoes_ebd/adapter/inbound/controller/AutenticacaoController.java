package br.com.techpib.ap.ms_anotacoes_ebd.adapter.inbound.controller;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.configuration.security.TokenService;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.dto.TokenDto;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.form.LoginForm;
import br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces.UsuarioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/ap-anotacoes-ebd/auth")
public class AutenticacaoController {

    private static final Logger log = LoggerFactory.getLogger(AutenticacaoController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
        log.info("[POST] - autenticar, LoginForm: {}, data: {}", form, new Date());
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            String token = obterToken(authenticationManager.authenticate(dadosLogin));
            UUID idUsuario = obterIdUsuario(form.getEmail());
            var tokenDto = new TokenDto(idUsuario, token, "Bearer");
            log.info("[POST] - autenticar, Token gerado com sucesso! LoginForm: {}, tokenDTO: {}, data: {}", form, tokenDto, new Date());
            return ResponseEntity.ok(tokenDto);
        } catch (AuthenticationException e){
            log.error("[POST] - autenticar, Erro na geracao do Token! LoginForm: {}, data: {}", form, new Date());
            return ResponseEntity.badRequest().build();
        }
    }

    private String obterToken(Authentication authentication){
        return tokenService.gerarToken(authentication);
    }

    private UUID obterIdUsuario(String email){
        return usuarioService.findUsuarioByEmail(email).get().getIdUsuario();
    }

}