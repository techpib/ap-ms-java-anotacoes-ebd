package br.com.techpib.ap.ms_anotacoes_ebd.core.ports;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Anotacao;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.AnotacaoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface AnotacaoRepository {

    Anotacao save(Anotacao anotacao);
    void delete(Anotacao anotacao);
    Optional<Anotacao> findAnotacaoByAnotacaoId(AnotacaoId anotacaoId);
    Page<Anotacao> findAnotacaoByIdUsuario(Pageable paginacao, UUID idUsuario);
    void updateAnotacaParaAExpurgar(Date dataBase);
    Integer qtdAnotacoesAtualizadas(Date dataBase, Integer codigoStatus);
    Integer qtdAnotacoesParaExcluir(Date dataBase, Integer codigoStatus);
    void deleteAnotacaAExcluir(Date dataBase);

}
