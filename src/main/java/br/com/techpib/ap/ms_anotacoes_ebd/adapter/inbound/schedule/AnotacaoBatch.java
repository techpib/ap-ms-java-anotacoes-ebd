package br.com.techpib.ap.ms_anotacoes_ebd.adapter.inbound.schedule;

import br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.enums.StatusEnum;
import br.com.techpib.ap.ms_anotacoes_ebd.core.services.interfaces.AnotacaoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

import java.util.Calendar;
import java.util.Date;

@Component
@EnableScheduling
public class AnotacaoBatch {

    private static final Logger log = LoggerFactory.getLogger(AnotacaoBatch.class);

    private final String TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    private AnotacaoService anotacaoService;

    @Transactional
    @Scheduled(cron = "${anotacao.batch.schedule.expurgo}", zone = TIME_ZONE)
    public void criarAnotacaoAExpurgar() {
        Date dataBase = new Date();

        log.info("[BATCH] - criarAnotacaoAExpurgar, iniciando processo de expurgo de anotacoes do dia, data: {}", dataBase);
        anotacaoService.updateAnotacaParaAExpurgar(dataBase);
        Integer qtdAnotacoesAtualizadas = anotacaoService.qtdAnotacoesAtualizadas(dataBase, StatusEnum.A_EXPURGAR.idStatus);

        log.info("[BATCH] - criarAnotacaoAExpurgar, {} anotacoes alteradas para A_EXPURGAR, data: {}", qtdAnotacoesAtualizadas, dataBase);
    }

    @Transactional
    @Scheduled(cron = "${anotacao.batch.schedule.exclusao}", zone = TIME_ZONE)
    public void excluirAnotacoes() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -7);
        Date dataBase = c.getTime();

        Integer qtdAnotacoesParaExcluir = anotacaoService.qtdAnotacoesParaExcluir(dataBase, StatusEnum.A_EXPURGAR.idStatus);

        log.info("[BATCH] - excluirAnotacoes, iniciando processo de exclusao de anotacoes do dia, {} anotacoes para excluir, data menor ou igual a: {}, data: {}", qtdAnotacoesParaExcluir, dataBase, new Date());
        anotacaoService.deleteAnotacaAExcluir(dataBase);
        Integer qtdAnotacoesExcluidas = (anotacaoService.qtdAnotacoesParaExcluir(dataBase, StatusEnum.A_EXPURGAR.idStatus) - qtdAnotacoesParaExcluir) * -1;

        log.info("[BATCH] - excluirAnotacoes, {} anotacoes excluidas, data menor ou igual a: {}, data: {}", qtdAnotacoesExcluidas, dataBase, new Date());
    }

}
