package br.com.techpib.ap.ms_anotacoes_ebd.adapter.inbound.controller;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.configuration.security.TokenService;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.dto.TokenDto;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.form.LoginForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@RestController
@RequestMapping("/ap-anotacoes-ebd/auth")
public class AutenticacaoController {

    private static final Logger log = LogManager.getLogger(AutenticacaoController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
        log.info("[POST] - autenticar, LoginForm: {}, data: {}", form, new Date());
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            var tokenDto = new TokenDto(token, "Bearer");
            log.info("[POST] - autenticar, Token gerado com sucesso! LoginForm: {}, token: {}, data: {}", form, tokenDto, new Date());
            return ResponseEntity.ok(tokenDto);
        } catch (AuthenticationException e){
            log.error("[POST] - autenticar, Erro na geracao do Token! LoginForm: {}, data: {}", form, new Date());
            return ResponseEntity.badRequest().build();
        }
    }

}