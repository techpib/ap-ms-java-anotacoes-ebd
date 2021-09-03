package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.Anotacao;
import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.AnotacaoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE ANOTACAO " +
            "SET ID_STATUS = 4, " +
            "    DATA_HORA_ULTIMA_ATUALIZACAO =:dataBase " +
            "WHERE ID_STATUS = 3", nativeQuery = true)
    void updateAnotacaParaAExpurgar(@Param("dataBase") Date dataBase);

    @Query(value = "SELECT COUNT(*) FROM ANOTACAO " +
            "WHERE ID_STATUS =:codigoStatus " +
            "  AND DATA_HORA_ULTIMA_ATUALIZACAO =:dataBase", nativeQuery = true)
    Integer qtdAnotacoesAtualizadas(@Param("dataBase") Date dataBase, @Param("codigoStatus") Integer codigoStatus);

    @Query(value = "SELECT COUNT(*) FROM ANOTACAO " +
            "WHERE ID_STATUS =:codigoStatus " +
            "  AND DATA_HORA_ULTIMA_ATUALIZACAO <=:dataBase", nativeQuery = true)
    Integer qtdAnotacoesParaExcluir(@Param("dataBase") Date dataBase, @Param("codigoStatus") Integer codigoStatus);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM ANOTACAO " +
            "WHERE ID_STATUS = 4 " +
            "  AND DATA_HORA_ULTIMA_ATUALIZACAO <=:dataBase", nativeQuery = true)
    void deleteAnotacaAExcluir(@Param("dataBase") Date dataBase);

}
