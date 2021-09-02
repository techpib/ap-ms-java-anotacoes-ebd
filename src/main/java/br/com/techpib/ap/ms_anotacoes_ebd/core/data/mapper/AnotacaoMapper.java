package br.com.techpib.ap.ms_anotacoes_ebd.core.data.mapper;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Anotacao;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Status;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Usuario;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.dto.AnotacaoDTO;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.form.AnotacaoForm;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.mapper.interfaces.MapperAnotacaoForm;
import br.com.techpib.ap.ms_anotacoes_ebd.core.data.mapper.interfaces.MapperDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Component
public class AnotacaoMapper implements MapperDTO<Anotacao, AnotacaoDTO>, MapperAnotacaoForm<Anotacao, AnotacaoForm> {

    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public Anotacao converteParaEntity(AnotacaoForm anotacaoForm, Status status, Usuario usuario) {
        return mapper.typeMap(AnotacaoForm.class, Anotacao.class).addMappings(mapper -> {
            mapper.map(src -> new Date(), Anotacao::setDataHoraUltimaAtualizacao);
            mapper.map(src -> usuario, Anotacao::setUsuario);
            mapper.map(src -> status, Anotacao::setStatus);
            mapper.<UUID>map(src -> anotacaoForm.getIdUsuario(), (anotacao, idUsuario) -> anotacao.getAnotacaoId().setIdUsuario(idUsuario));
            mapper.<Long>map(src -> anotacaoForm.getSequencialAnotacao(), (anotacao, sequencialAnotacao) -> anotacao.getAnotacaoId().setSequencialAnotacao(sequencialAnotacao));
        }).map(anotacaoForm);
    }

    @Override
    public AnotacaoDTO converteParaDTO(Anotacao anotacao) {
        return mapper.typeMap(Anotacao.class, AnotacaoDTO.class).map(anotacao);
    }

    @Override
    public Page<AnotacaoDTO> converteParaDTO(Page<Anotacao> anotacoes) {
        return anotacoes.map(new Function<Anotacao, AnotacaoDTO>(){
            @Override
            public AnotacaoDTO apply(Anotacao anotacao) {
                return mapper.typeMap(Anotacao.class, AnotacaoDTO.class).map(anotacao);
            }
        });
    }
}

