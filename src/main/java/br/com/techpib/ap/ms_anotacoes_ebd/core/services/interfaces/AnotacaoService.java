package br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Anotacao;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.AnotacaoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface AnotacaoService {

    Anotacao save(Anotacao anotacao);

    Anotacao update(Anotacao anotacao);

    Optional<Anotacao> findAnotacaoByAnotacaoId(AnotacaoId anotacaoId);

    Page<Anotacao> findAnotacaoByIdUsuario(Pageable paginacao, UUID idUsuario);

}
