package br.com.techpib.ap.ms_anotacoes_ebd.core.data.mapper.interfaces;

import org.springframework.data.domain.Page;

public interface MapperDTO<Entity, DTO> {

        DTO converteParaDTO(Entity e);

        Page<DTO> converteParaDTO(Page<Entity> e);

}
