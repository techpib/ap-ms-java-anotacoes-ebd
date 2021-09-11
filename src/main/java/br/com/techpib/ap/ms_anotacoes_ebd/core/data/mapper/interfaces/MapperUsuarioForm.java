package br.com.techpib.ap.ms_anotacoes_ebd.core.data.mapper.interfaces;

public interface MapperUsuarioForm <Entity, Form> {

    Entity converteParaEntity(Form form);

}