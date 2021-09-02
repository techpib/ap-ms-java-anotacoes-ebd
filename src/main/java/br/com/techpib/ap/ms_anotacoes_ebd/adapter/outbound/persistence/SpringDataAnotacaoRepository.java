package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Anotacao;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.AnotacaoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataAnotacaoRepository extends JpaRepository<Anotacao, AnotacaoId> {

    Optional<Anotacao> findAnotacaoByAnotacaoId(AnotacaoId anotacaoId);

    Page<Anotacao> findAnotacaoByAnotacaoId_IdUsuario(Pageable paginacao, UUID idUsuario);

}
