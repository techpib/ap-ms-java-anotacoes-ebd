package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.impl;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.SpringDataAnotacaoRepository;
import br.com.techpib.ap.ms_anotacoes_ebd.core.entities.Anotacao;
import br.com.techpib.ap.ms_anotacoes_ebd.core.entities.AnotacaoId;
import br.com.techpib.ap.ms_anotacoes_ebd.core.ports.AnotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public Page<Anotacao> findAll(Pageable pageable) {
        return anotacaoRepository.findAll(pageable);
    }

    @Override
    public Optional<Anotacao> findById(AnotacaoId anotacaoId) {
        return anotacaoRepository.findById(anotacaoId);
    }
}
