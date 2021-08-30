package br.com.techpib.ap.ms_anotacoes_ebd.adapter.inbound.controller.utils;

import br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces.UsuarioService;

public class UsuarioUtils {

    public static boolean usuarioExiste(UsuarioService usuarioService, String email){
        return usuarioService.findUsuarioByEmail(email).isPresent();
    }

    public static boolean usuarioESenhaValido(UsuarioService usuarioService, String email, String senha){
        return usuarioService.findUsuarioByEmailAndSenha(email, senha).isPresent();
    }

}
