package br.com.techpib.ap.ms_anotacoes_ebd.core.ports;

import br.com.techpib.ap.ms_anotacoes_ebd.core.entities.Anotacao;
import br.com.techpib.ap.ms_anotacoes_ebd.core.entities.AnotacaoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AnotacaoRepository {

    Anotacao save(Anotacao anotacao);
    Page<Anotacao> findAll(Pageable pageable);
    Optional<Anotacao> findById(AnotacaoId anotacaoId);

}
