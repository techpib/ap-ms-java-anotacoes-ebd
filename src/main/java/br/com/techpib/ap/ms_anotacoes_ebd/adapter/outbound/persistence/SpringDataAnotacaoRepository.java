package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Anotacao;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.AnotacaoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataAnotacaoRepository extends JpaRepository<Anotacao, AnotacaoId> {

    @Query(value = "SELECT * FROM ANOTACAO " +
            "WHERE ID_USUARIO = :idUsuario " +
            "  AND SEQUENCIAL_ANOTACAO = :sequencialAnotacao " +
            "  AND (ID_STATUS  = 1 " +
            "  OR   ID_STATUS  = 2)", nativeQuery = true)
    Optional<Anotacao> findAnotacaoByAnotacaoId(@Param("idUsuario") String idUsuario, @Param("sequencialAnotacao") Integer sequencialAnotacao);

    @Query(value = "SELECT * FROM ANOTACAO " +
            "WHERE ID_USUARIO =:idUsuario " +
            "  AND (ID_STATUS  = 1 " +
            "  OR   ID_STATUS  = 2)", nativeQuery = true)
    Page<Anotacao> findAnotacaoByAnotacaoId_IdUsuario(Pageable paginacao, @Param("idUsuario") String idUsuario);

}
