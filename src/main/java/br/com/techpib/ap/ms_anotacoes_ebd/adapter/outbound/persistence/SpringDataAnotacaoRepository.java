package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence;

import br.com.techpib.ap.ms_anotacoes_ebd.core.entities.Anotacao;
import br.com.techpib.ap.ms_anotacoes_ebd.core.entities.AnotacaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataAnotacaoRepository extends JpaRepository<Anotacao, AnotacaoId> {
}
