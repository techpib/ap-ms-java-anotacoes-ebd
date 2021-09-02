package br.com.techpib.ap.ms_anotacoes_ebd.core.data.mapper.interfaces;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Status;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Usuario;

public interface MapperAnotacaoForm <Entity, Form> {

    Entity converteParaEntity(Form form, Status status, Usuario usuario);

}