package br.com.techpib.ap.ms_anotacoes_ebd.adapter.inbound.controller.utils;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.AnotacaoId;
import br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces.AnotacaoService;

import java.util.UUID;

public class AnotacaoUtils {

    public static boolean anotacaoExiste(AnotacaoService anotacaoService, UUID idUsuario, Integer sequencialAnotacao){
        return anotacaoService.findAnotacaoByAnotacaoId(new AnotacaoId(idUsuario, sequencialAnotacao)).isPresent();
    }

}
