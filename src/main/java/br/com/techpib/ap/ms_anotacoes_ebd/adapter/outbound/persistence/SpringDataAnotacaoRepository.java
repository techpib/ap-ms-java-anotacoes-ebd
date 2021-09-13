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

    @Query(value = "SELECT * FROM anotacao " +
            "WHERE id_usuario = :idUsuario " +
            "  AND sequencial_anotacao = :sequencialAnotacao " +
            "  AND (id_status  = 1 " +
            "  OR   id_status  = 2)", nativeQuery = true)
    Optional<Anotacao> findAnotacaoByAnotacaoId(@Param("idUsuario") String idUsuario, @Param("sequencialAnotacao") Integer sequencialAnotacao);

    @Query(value = "SELECT * FROM anotacao " +
            "WHERE id_usuario =:idUsuario " +
            "  AND (id_status  = 1 " +
            "  OR   id_status  = 2)", nativeQuery = true)
    Page<Anotacao> findAnotacaoByAnotacaoId_IdUsuario(Pageable paginacao, @Param("idUsuario") String idUsuario);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE anotacao " +
            "SET id_status = 4, " +
            "    data_hora_ultima_atualizacao =:dataBase " +
            "WHERE id_status = 3", nativeQuery = true)
    void updateAnotacaParaAExpurgar(@Param("dataBase") Date dataBase);

    @Query(value = "SELECT COUNT(*) FROM anotacao " +
            "WHERE id_status =:codigoStatus " +
            "  AND data_hora_ultima_atualizacao =:dataBase", nativeQuery = true)
    Integer qtdAnotacoesAtualizadas(@Param("dataBase") Date dataBase, @Param("codigoStatus") Integer codigoStatus);

    @Query(value = "SELECT COUNT(*) FROM anotacao " +
            "WHERE id_status =:codigoStatus " +
            "  AND data_hora_ultima_atualizacao <=:dataBase", nativeQuery = true)
    Integer qtdAnotacoesParaExcluir(@Param("dataBase") Date dataBase, @Param("codigoStatus") Integer codigoStatus);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM anotacao " +
            "WHERE id_status = 4 " +
            "  AND data_hora_ultima_atualizacao <=:dataBase", nativeQuery = true)
    void deleteAnotacaAExcluir(@Param("dataBase") Date dataBase);

}
