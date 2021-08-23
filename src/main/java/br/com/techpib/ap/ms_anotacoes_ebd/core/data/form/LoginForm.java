package br.com.techpib.ap.ms_anotacoes_ebd.core.data.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    private String email;
    private String senha;

    public UsernamePasswordAuthenticationToken converter(){
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }

}
