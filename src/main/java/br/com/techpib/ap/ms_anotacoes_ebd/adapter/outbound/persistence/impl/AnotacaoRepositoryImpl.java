package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.impl;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.SpringDataAnotacaoRepository;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Anotacao;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.AnotacaoId;
import br.com.techpib.ap.ms_anotacoes_ebd.core.ports.AnotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Primary
public class AnotacaoRepositoryImpl implements AnotacaoRepository {

    @Autowired
    private SpringDataAnotacaoRepository anotacaoRepository;

    @Override
    public Anotacao save(Anotacao anotacao) {
        return anotacaoRepository.save(anotacao);
    }

    @Override
    public void delete(Anotacao anotacao) {
        anotacaoRepository.save(anotacao);
    }

    @Override
    public Optional<Anotacao> findAnotacaoByAnotacaoId(AnotacaoId anotacaoId) {
        return anotacaoRepository.findAnotacaoByAnotacaoId(anotacaoId.getIdUsuario().toString(), anotacaoId.getSequencialAnotacao());
    }

    @Override
    public Page<Anotacao> findAnotacaoByIdUsuario(Pageable paginacao, UUID idUsuario) {
        return anotacaoRepository.findAnotacaoByAnotacaoId_IdUsuario(paginacao, idUsuario.toString());
    }

}
