package br.com.techpib.ap.ms_anotacoes_ebd.core.services.impl;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Anotacao;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.AnotacaoId;
import br.com.techpib.ap.ms_anotacoes_ebd.core.ports.AnotacaoRepository;
import br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces.AnotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnotacaoServiceImpl implements AnotacaoService {

    @Autowired
    private AnotacaoRepository anotacaoRepository;

    @Override
    public Anotacao save(Anotacao anotacao) {
        return anotacaoRepository.save(anotacao);
    }

    @Override
    public Anotacao update(Anotacao anotacao) {
        return anotacaoRepository.save(anotacao);
    }

    @Override
    public void delete(Anotacao anotacao) {
        anotacaoRepository.delete(anotacao);
    }

    @Override
    public Optional<Anotacao> findAnotacaoByAnotacaoId(AnotacaoId anotacaoId) {
        return anotacaoRepository.findAnotacaoByAnotacaoId(anotacaoId);
    }

    @Override
    public Page<Anotacao> findAnotacaoByIdUsuario(Pageable paginacao, UUID idUsuario) {
        return anotacaoRepository.findAnotacaoByIdUsuario(paginacao, idUsuario);
    }

    @Override
    public void updateAnotacaParaAExpurgar(Date dataBase) {
        anotacaoRepository.updateAnotacaParaAExpurgar(dataBase);
    }

    @Override
    public Integer qtdAnotacoesAtualizadas(Date dataBase, Integer codigoStatus) {
        return anotacaoRepository.qtdAnotacoesAtualizadas(dataBase, codigoStatus);
    }

    @Override
    public Integer qtdAnotacoesParaExcluir(Date dataBase, Integer codigoStatus) {
        return anotacaoRepository.qtdAnotacoesParaExcluir(dataBase, codigoStatus);
    }

    @Override
    public void deleteAnotacaAExcluir(Date dataBase) {
        anotacaoRepository.deleteAnotacaAExcluir(dataBase);
    }
}
